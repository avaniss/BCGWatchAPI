package com.bcg.watch.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

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

}
