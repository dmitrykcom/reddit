package com.dmitryk.reddit.format;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.TimeFormat;

public class DayFormat implements TimeFormat {
    @Override
    public String format(Duration duration) {
        return Math.abs(duration.getQuantity()) + "d";
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
