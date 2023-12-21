package de.bot.utils;

import java.awt.*;

public class AccountRank {

    private String title;
    private Color rgbColor;
    private String htmlColor;
    private boolean team;
    private boolean head_team;
    private boolean admin_team;
    private boolean vip;
    private boolean defaultRank;

    public AccountRank(String title, Color rgbColor, String htmlColor, boolean team, boolean head_team, boolean admin_team, boolean vip) {
        this.title = title;
        this.rgbColor = rgbColor;
        this.htmlColor = htmlColor;
        this.team = team;
        this.head_team = head_team;
        this.admin_team = admin_team;
        this.vip = vip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDefaultRank() {
        return defaultRank;
    }


    public Color getRgbColor() {
        return rgbColor;
    }

    public String getHtmlColor() {
        return htmlColor;
    }

    public boolean isAdmin_team() {
        return admin_team;
    }

    public boolean isHead_team() {
        return head_team;
    }

    public boolean isTeam() {
        return team;
    }

    public boolean isVip() {
        return vip;
    }

    public void setAdmin_team(boolean admin_team) {
        this.admin_team = admin_team;
    }

    public void setHead_team(boolean head_team) {
        this.head_team = head_team;
    }

    public void setHtmlColor(String htmlColor) {
        this.htmlColor = htmlColor;
    }

    public void setRgbColor(Color rgbColor) {
        this.rgbColor = rgbColor;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
