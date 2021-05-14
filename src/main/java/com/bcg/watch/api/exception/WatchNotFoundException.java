package com.bcg.watch.api.exception;

public class WatchNotFoundException extends WatchCatalogueException {

    public WatchNotFoundException(String watchId) {
        super("Watch with ID: " + watchId + " not found");
    }
}
