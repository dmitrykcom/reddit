package com.dmitryk.reddit.bus;

public class Bus {

    private static com.squareup.otto.Bus ottoBus;

    public static com.squareup.otto.Bus getDefault() {
        if (ottoBus == null) {
            ottoBus = new com.squareup.otto.Bus();
        }
        return ottoBus;
    }
}
