package com.solution.grapeApp.enums;

import java.time.DayOfWeek;

public enum DaysOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static DaysOfWeek fromDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return MONDAY;
            case TUESDAY:
                return TUESDAY;
            case WEDNESDAY:
                return WEDNESDAY;
            case THURSDAY:
                return THURSDAY;
            case FRIDAY:
                return FRIDAY;
            case SATURDAY:
                return SATURDAY;
            case SUNDAY:
                return SUNDAY;
            default:
                throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        }
    }
}
