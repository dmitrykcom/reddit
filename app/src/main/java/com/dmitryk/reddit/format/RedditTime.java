package com.dmitryk.reddit.format;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Second;

import java.util.Date;

public class RedditTime extends PrettyTime {

    public RedditTime(Date reference) {
        super(reference);

        clearUnits();
        registerUnit(new Second(), new SecondFormat());
        registerUnit(new Minute(), new MinuteFormat());
        registerUnit(new Hour(), new HourFormat());
        registerUnit(new Day(), new DayFormat());
        registerUnit(new Millisecond(), new NowFormat());
        registerUnit(new JustNow(), new NowFormat());

    }
}
