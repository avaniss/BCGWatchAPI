package com.bcg.watch.api.model;

import junit.framework.TestCase;
import org.junit.Test;

public class MichaelKorsTest {

    @Test
    public void testMichaelKorsObjectCreation() {
        MichaelKors michaelKors = new MichaelKors();
        TestCase.assertEquals(michaelKors.id, MichaelKors.defaultID);
        TestCase.assertEquals(michaelKors.name, MichaelKors.class.getSimpleName());
        TestCase.assertEquals(michaelKors.price, MichaelKors.defaultPrice);
    }
}