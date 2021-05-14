package com.bcg.watch.api.service;

import com.bcg.watch.api.exception.WatchCatalogueException;
import com.bcg.watch.api.exception.WatchIdAlreadyExistsException;
import com.bcg.watch.api.model.Invoice;
import com.bcg.watch.api.model.Watch;
import com.bcg.watch.api.model.WatchCatalogue;
import com.bcg.watch.api.utility.InvoiceUtility;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.InputStream;
import java.util.List;

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
            invoice = InvoiceUtility.generateInvoice(inputStream);
        } catch (WatchCatalogueException ex) {
            return Response.status(404).entity(ex).build();
        }
        return Response.status(200).entity(invoice).build();
    }
}
