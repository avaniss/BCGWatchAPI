package com.bcg.watch.api.exception;

public class WatchOrderRequestParserException extends WatchCatalogueException {

    public WatchOrderRequestParserException() {
        super("Failed to parse order request");
    }
}
