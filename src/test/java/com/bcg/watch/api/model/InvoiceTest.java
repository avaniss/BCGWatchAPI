package com.bcg.watch.api.model;

import junit.framework.TestCase;
import org.junit.Test;

public class InvoiceTest {

    @Test
    public void testInvoiceTestObjectCreation() {
        int expectedPrice = 100;
        Invoice invoice = new Invoice(expectedPrice);
        TestCase.assertEquals(invoice.getPrice(), expectedPrice);
    }
}