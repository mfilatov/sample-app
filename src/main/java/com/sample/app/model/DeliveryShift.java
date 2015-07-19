package com.sample.app.model;

public enum DeliveryShift {
    MORNING("M"),
    NOON("N"),
    EVENING("E");

    private final String name;

    DeliveryShift(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DeliveryShift parseByName(String name) {
        for (DeliveryShift deliveryShift : values()) {
            if (name.equals(deliveryShift.name)) {
                return deliveryShift;
            }
        }

        return null;
    }
}
