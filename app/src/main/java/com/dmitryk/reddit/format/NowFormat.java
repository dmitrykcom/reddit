package com.dmitryk.reddit.format;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;

public class NowFormat implements TimeFormat {
    @Override
    public String format(Duration duration) {
        return "now";
    }

    @Override
    public String formatUnrounded(Duration duration) {
        return null;
    }

    @Override
    public String decorate(Duration duration, String time) {
        return time;
    }

    @Override
    public String decorateUnrounded(Duration duration, String time) {
        return null;
    }
}
