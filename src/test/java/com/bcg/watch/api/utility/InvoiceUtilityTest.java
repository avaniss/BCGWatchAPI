package com.bcg.watch.api.utility;

import com.bcg.watch.api.exception.WatchCatalogueException;
import com.bcg.watch.api.exception.WatchNotFoundException;
import com.bcg.watch.api.exception.WatchOrderRequestParserException;
import com.bcg.watch.api.model.Invoice;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InvoiceUtilityTest {


    private static final String correctOrderRequest = "[\"001\", \"002\", \"003\", \"001\", \"002\", \"002\", \"001\", \"004\"]";
    private static final String incorrectOrderRequest = "[\"005\", \"006\", \"007\"]";
    private static final String malformedOrderRequest = "malformedOrderRequest";

    @Test
    public void testGenerateInvoiceSuccess() {
        InputStream inputStream = new ByteArrayInputStream(correctOrderRequest.getBytes());
        Invoice expectedInvoice = new Invoice(480);
        Invoice actualInvoice = null;
        try {
            actualInvoice = InvoiceUtility.generateInvoice(inputStream);
        } catch (WatchCatalogueException e) {
            e.printStackTrace();
        }
        assertNotNull(actualInvoice);
        assertEquals(actualInvoice.getPrice(), expectedInvoice.getPrice());
    }

    @Test(expected = WatchNotFoundException.class)
    public void testGenerateInvoiceFailNotFound() throws WatchCatalogueException {
        InputStream inputStream = new ByteArrayInputStream(incorrectOrderRequest.getBytes());
        InvoiceUtility.generateInvoice(inputStream);
    }

    @Test(expected = WatchOrderRequestParserException.class)
    public void testGenerateInvoiceFailJsonParser() throws WatchCatalogueException {
        InputStream inputStream = new ByteArrayInputStream(malformedOrderRequest.getBytes());
        InvoiceUtility.generateInvoice(inputStream);
    }

}