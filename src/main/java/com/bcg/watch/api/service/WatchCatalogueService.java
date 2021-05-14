package com.bcg.watch.api.service;

import com.bcg.watch.api.exception.WatchIdAlreadyExistsException;
import com.bcg.watch.api.model.Watch;
import com.bcg.watch.api.model.WatchCatalogue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
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
            return Response.status(400).build();
        }
        return Response.status(200).entity(watches).build();
    }

}
