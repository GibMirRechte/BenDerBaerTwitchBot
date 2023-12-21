package de.bot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoMessagesSettings {

    private final List<String> messages = new ArrayList<>();
    private int delay;

    public AutoMessagesSettings(String messagesString, int delay) {
        String[] splittedMessages = messagesString.split("&&&-");
        Collections.addAll(this.messages, splittedMessages);

        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public List<String> getMessages() {
        return messages;
    }
}
