package com.vt.fish.utility;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilityTest {

    @Test
    public void dateUtility_shouldAdd1Hour() {
        Date date = new Date();
        int before = Integer.parseInt(new SimpleDateFormat("HH").format(date));
        int after = Integer.parseInt(new SimpleDateFormat("HH").format(DateUtility.addHoursToJavaUtilDate(date, 1)));
        assertEquals((before + 1) % 24, after % 24);
    }

    @Test
    public void dateUtility_shouldAdd1Minute() {
        Date date = new Date();
        int before = Integer.parseInt(new SimpleDateFormat("mm").format(date));
        int after = Integer.parseInt(new SimpleDateFormat("mm").format(DateUtility.addMinutesToJavaUtilDate(date, 1)));
        assertEquals((before + 1) % 60, after % 60);

    }
}
