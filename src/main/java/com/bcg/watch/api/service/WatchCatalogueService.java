package com.bcg.watch.api.service;

import com.bcg.watch.api.exception.WatchCatalogueException;
import com.bcg.watch.api.exception.WatchIdAlreadyExistsException;
import com.bcg.watch.api.model.Invoice;
import com.bcg.watch.api.model.Watch;
import com.bcg.watch.api.model.WatchCatalogue;
import com.bcg.watch.api.utility.InvoiceUtility;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "watch")
@Path("/")
public class WatchCatalogueService {

    @GET
    @Path("/health")
    @Produces("text/PLAIN")
    public String getHealth() {
        return "Status: OK";
    }

    @GET
    @Path("/watches")
    @Produces("application/json")
    public Response getWatch() {
        List<Watch> watches = null;
        try {
            watches = WatchCatalogue.getInstance().getAllWatches();
        } catch (WatchIdAlreadyExistsException e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
        return Response.status(200).entity(watches).build();
    }

    @POST
    @Path("/checkout")
    @Produces("application/json")
    @Consumes("application/json")
    public Response checkout(InputStream inputStream) {
        Invoice invoice = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> orders = new ObjectMapper().readValue(inputStream, collectionType);
            Map<String, Integer> orderedWatches = new HashMap<>();
            for (String watchId : orders) {
                orderedWatches.put(watchId, orderedWatches.getOrDefault(watchId, 0) + 1);
            }
            int totalPrice = 0;
            WatchCatalogue watchCatalogue = WatchCatalogue.getInstance();
            for (Map.Entry<String, Integer> orderedWatch : orderedWatches.entrySet()) {
                String watchId = orderedWatch.getKey();
                int quantity = orderedWatch.getValue();
                Watch watch = watchCatalogue.getWatchById(watchId);
                totalPrice += (watch.getPrice()) * quantity;
            }
            invoice = new Invoice(totalPrice);
        } catch (WatchCatalogueException ex) {
            return Response.status(404).entity(ex).build();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(invoice).build();
    }
}
