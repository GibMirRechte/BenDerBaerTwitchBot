package de.bot.utils;

public class Announcement {

    public boolean isActive;
    public String text;
    public int typeID;
    public String color;
    public boolean canUseTool;

    public Announcement(String text, int typeID, String color, boolean canUseTool, boolean isActive) {
        this.text = text;
        this.typeID = typeID;
        this.color = color;
        this.canUseTool = canUseTool;
        this.isActive = isActive;
    }

}
