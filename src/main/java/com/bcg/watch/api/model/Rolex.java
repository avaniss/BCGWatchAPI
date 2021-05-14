package com.bcg.watch.api.model;

public class Rolex extends Watch {

    public static final String defaultID = "001";
    public static final int defaultPrice = 100;

    public Rolex() {
        this.name = "Rolex";
        this.price = defaultPrice;
        this.id = defaultID;
    }

    @Override
    public int getDiscountedPrice(int quantity) {
        // 3 for 200, every 3rd is free
        int nonFreeWatches = (int) Math.ceil(quantity * 2d / 3d);
        return nonFreeWatches * price;
    }
}
