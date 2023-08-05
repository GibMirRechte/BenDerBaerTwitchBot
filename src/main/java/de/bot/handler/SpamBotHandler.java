package de.bot.handler;

import com.github.twitch4j.TwitchClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SpamBotHandler {

    List<String> messages = new ArrayList<>();
    HashMap<TwitchClient, String> lastMessages = new HashMap<>();

    public void followChannel(String channelID, String botChannelID, String authToken, TwitchClient twitchClient) {
        twitchClient.getHelix().createFollow(authToken, botChannelID, channelID, true);
    }

    public void sendMessage(String chat, TwitchClient twitchClient) {

        setList();
        if (messages.size() == 1) {
            twitchClient.getChat().sendMessage(chat, messages.get(0));

        } else {
            if (messages.size() == 0) return;
            String msg = messages.get(new Random().nextInt(messages.size()));
            messages.remove(msg);

            lastMessages.put(twitchClient, msg);

            try {
                twitchClient.getChat().sendMessage(chat, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setList() {
        if (!messages.isEmpty()) return;
        messages.add("LUL");
        messages.add("Hey was macht ihr?");
        messages.add("Was sucht ihr?");
        messages.add("Hey :)");
    }

}
