package com.bcg.watch.api.model;

public class MichaelKors extends Watch {

    public static final String defaultID = "002";
    public static final int defaultPrice = 80;

    public MichaelKors() {
        this.name = "MichaelKors";
        this.price = defaultPrice;
        this.id = defaultID;
    }

    @Override
    public int getDiscountedPrice(int quantity) {
        // 2 for 120
        if (quantity <= 0) {
            return 0;
        }
        if (quantity % 2 == 0) {
            return 120 * quantity / 2;
        } else {
            return (120 * (quantity - 1) / 2) + price;
        }
    }
}
