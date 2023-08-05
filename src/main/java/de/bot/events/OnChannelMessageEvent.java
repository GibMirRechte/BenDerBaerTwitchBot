package de.bot.events;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class OnChannelMessageEvent {

    @EventSubscriber
    public void printChannelMessage(ChannelMessageEvent event) {
        System.out.println(event.getMessage() + " :)");
    }
}
