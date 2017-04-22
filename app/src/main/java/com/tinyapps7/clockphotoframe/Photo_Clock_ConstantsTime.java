package com.tinyapps7.clockphotoframe;

public class Photo_Clock_ConstantsTime {
    public static Photo_Clock_ConstantsTime INSTANCE;
    private int endDay;
    private int endHours;
    private int endMinutes;
    private int endMonth;
    private int endSeconds;
    private int endYear;

    static {
        INSTANCE = new Photo_Clock_ConstantsTime();
    }

    private Photo_Clock_ConstantsTime() {
    }

    public int getEndDay() {
        return this.endDay;
    }

    public int getEndHours() {
        return this.endHours;
    }

    public int getEndMinutes() {
        return this.endMinutes;
    }

    public int getEndMonth() {
        return this.endMonth;
    }

    public int getEndSeconds() {
        return this.endSeconds;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public void setEndDay(int i) {
        this.endDay = i;
    }

    public void setEndHours(int i) {
        this.endHours = i;
    }

    public void setEndMinutes(int i) {
        this.endMinutes = i;
    }

    public void setEndMonth(int i) {
        this.endMonth = i;
    }

    public void setEndSeconds(int i) {
        this.endSeconds = i;
    }

    public void setEndYear(int i) {
        this.endYear = i;
    }
}
