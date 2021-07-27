package com.hiperium.java.cert.prep.test.unit.four;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;

public class FlowControlTest {

    /**
     * Given the following method, how many lines contain compilation errors?
     */
    @Test
    public void question7() {
        getWeekDay(1, 3);
        Assert.assertTrue(true);
    }

    private DayOfWeek getWeekDay(int day, final int thursday) {
        int otherDay = day;
        int Sunday = 0;
        switch (otherDay) {
            default:
            case 1: // continue;                               // 1ST ERROR -> Continue outside of loop
            // case thursday: return DayOfWeek.THURSDAY;       // 2ND ERROR -> Constant expression required
            case 2: break;
            // case Sunday: return DayOfWeek.SUNDAY;           // 3RD ERROR -> Constant expression required
            // case DayOfWeek.MONDAY: return DayOfWeek.MONDAY; // 4TH ERROR -> Required: int - Provided: enum constant
        }
        return DayOfWeek.FRIDAY;
    }

    /**
     * What is the output of the following code snippet?
     * R: 5 2 1
     */
    @Test
    public void question16() {
        var tailFeathers = 3;
        final var one = 1;
        switch (tailFeathers) {
            case one: System.out.print(3 + " ");
            default: case 3: System.out.print(5 + " ");
        }
        while (tailFeathers > 1)
            System.out.print(--tailFeathers + " ");
        Assert.assertTrue(true);
    }
}
