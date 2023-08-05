package de.bot.events;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;

public class ChannelGoLiveEvent {

    @EventSubscriber
    public void onChannelGoLiveEvent(com.github.twitch4j.events.ChannelGoLiveEvent channelGoLiveEvent) {
        System.out.println(channelGoLiveEvent.getChannel().getName());
    }

}
