package de.bot.utils;

public class AutoVIPSettings {

    private int months;
    private int streams;

    public AutoVIPSettings(int months, int messages) {
        this.months = months;
        this.streams = messages;
    }

    public void updateAutoVIPSettings(int months, int streams) {
        this.months = months;
        this.streams = streams;
    }

    public int getStreams() {
        return streams;
    }

    public int getMonths() {
        return months;
    }

}
