package de.bot.utils;

public class CustomCommand {

    private String suffix;
    private String message;

    public CustomCommand(String suffix, String message) {
        this.suffix = suffix;
        this.message = message;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getMessage() {
        return message;
    }
}
