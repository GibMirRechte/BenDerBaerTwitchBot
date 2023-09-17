package de.bot.utils;

public class AutoVIPSettings {

    private int months;
    private int streams;
    private String whitelist;
    private String blacklist;

    public AutoVIPSettings(int months, int messages, String whitelist, String blacklist) {
        this.months = months;
        this.streams = messages;
        this.whitelist = whitelist;
        this.blacklist = blacklist;
    }

    public void updateAutoVIPSettings(int months, int streams, String whitelist, String blacklist) {
        this.months = months;
        this.streams = streams;
        this.whitelist = whitelist.replace(" ", "");
        this.blacklist = blacklist.replace(" ", "");
    }

    public int getStreams() {
        return streams;
    }

    public int getMonths() {
        return months;
    }

    public String getBlacklist() {
        return blacklist;
    }

    public String getWhitelist() {
        return whitelist;
    }

    public boolean containsWhitelist(String username) {
        String[] split = this.whitelist.split(",");
        for (String user : split) {
            if (user.equalsIgnoreCase(username))
                return true;
        }

        return false;
    }

    public boolean containsBlacklist(String username) {
        String[] split = this.blacklist.split(",");
        for (String user : split) {
            if (user.equalsIgnoreCase(username))
                return true;
        }

        return false;
    }
}
