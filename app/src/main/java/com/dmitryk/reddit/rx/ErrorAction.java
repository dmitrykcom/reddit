package com.dmitryk.reddit.rx;

import com.dmitryk.reddit.bus.Bus;
import com.dmitryk.reddit.bus.event.OnError;

import rx.functions.Action1;

public class ErrorAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();
        Bus.getDefault().post(new OnError(throwable));
    }
}
