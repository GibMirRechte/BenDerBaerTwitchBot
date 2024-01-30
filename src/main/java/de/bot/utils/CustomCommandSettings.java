package de.bot.utils;

import java.util.HashMap;

public class CustomCommandSettings {

    private String commandString;

    public CustomCommandSettings(String commandString) {
        this.commandString = commandString;
    }

    public HashMap<Integer, CustomCommand> getCommands() {
        HashMap<Integer, CustomCommand> map = new HashMap<>();
        String[] splittedString = this.commandString.split("-&&&-");

        for (int i = 0; i < splittedString.length; i += 2) {
            if (i + 1 < splittedString.length) {
                String suffix = splittedString[i];
                String message = splittedString[i + 1];
                CustomCommand customCommand = new CustomCommand(suffix, message);
                map.put(i / 2, customCommand);
            }
        }

        return map;
    }

    public void updateCommandString(String commandString) {
        this.commandString = commandString;
    }

}
