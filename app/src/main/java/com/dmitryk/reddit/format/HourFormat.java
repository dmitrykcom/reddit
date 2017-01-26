package com.dmitryk.reddit.format;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;

public class HourFormat implements TimeFormat {
    @Override
    public String format(Duration duration) {
        return Math.abs(duration.getQuantity()) + "h";
    }

    @Override
    public String formatUnrounded(Duration duration) {
        return "";
    }

    @Override
    public String decorate(Duration duration, String time) {
        return time;
    }

    @Override
    public String decorateUnrounded(Duration duration, String time) {
        return time;
    }
}
