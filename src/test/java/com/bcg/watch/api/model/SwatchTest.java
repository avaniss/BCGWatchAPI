package com.bcg.watch.api.model;

import junit.framework.TestCase;
import org.junit.Test;

public class SwatchTest {

    @Test
    public void testSwatchTestObjectCreation() {
        Swatch swatch = new Swatch();
        TestCase.assertEquals(swatch.id, Swatch.defaultID);
        TestCase.assertEquals(swatch.name, Swatch.class.getSimpleName());
        TestCase.assertEquals(swatch.price, Swatch.defaultPrice);
    }
}