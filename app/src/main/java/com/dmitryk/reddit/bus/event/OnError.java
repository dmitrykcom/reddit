package com.dmitryk.reddit.bus.event;

public class OnError {

    private Throwable t;

    public OnError(Throwable t) {
        this.t = t;
    }

    public Throwable getT() {
        return t;
    }
}
