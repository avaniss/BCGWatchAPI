package com.bcg.watch.api.model;

public class Casio extends Watch {

    public static final String defaultID = "004";
    public static final int defaultPrice = 30;

    public Casio() {
        this.name = "Casio";
        this.price = defaultPrice;
        this.id = defaultID;
    }

}
