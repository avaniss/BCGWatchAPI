package com.bcg.watch.api.model;

public class Swatch extends Watch {

    public static final String defaultID = "003";
    public static final int defaultPrice = 50;

    public Swatch() {
        this.name = "Swatch";
        this.price = defaultPrice;
        this.id = defaultID;
    }

}
