package com.bcg.watch.api.model;

import junit.framework.TestCase;
import org.junit.Test;

public class CasioTest {

    @Test
    public void testCasioObjectCreation() {
        Casio casio = new Casio();
        TestCase.assertEquals(casio.id, Casio.defaultID);
        TestCase.assertEquals(casio.name, Casio.class.getSimpleName());
        TestCase.assertEquals(casio.price, Casio.defaultPrice);
    }

}
