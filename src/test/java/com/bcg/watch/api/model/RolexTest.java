package com.bcg.watch.api.model;

import junit.framework.TestCase;
import org.junit.Test;

public class RolexTest {

    @Test
    public void testRolexTestObjectCreation() {
        Rolex rolex = new Rolex();
        TestCase.assertEquals(rolex.id, Rolex.defaultID);
        TestCase.assertEquals(rolex.name, Rolex.class.getSimpleName());
        TestCase.assertEquals(rolex.price, Rolex.defaultPrice);
    }
}