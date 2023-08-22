package de.bot.utils;

public class BanData {

    public boolean activeBan;
    public long bannedUntil;
    public String bannedReason;

    public BanData(boolean activeBan, long bannedUntil, String bannedReason) {
        this.activeBan = activeBan;
        this.bannedUntil = bannedUntil;
        this.bannedReason = bannedReason;
    }

}
