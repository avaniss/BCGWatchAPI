package com.bcg.watch.api.exception;

public class WatchIdAlreadyExistsException extends WatchCatalogueException {
    public WatchIdAlreadyExistsException(String watchId) {
        super("Watch with ID: " + watchId + " already exists");
    }
}
