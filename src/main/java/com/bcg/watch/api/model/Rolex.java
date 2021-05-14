package com.bcg.watch.api.model;

public class Rolex extends Watch {

    public static final String defaultID = "001";
    public static final int defaultPrice = 100;

    public Rolex() {
        this.name = "Rolex";
        this.price = defaultPrice;
        this.id = defaultID;
    }

}
