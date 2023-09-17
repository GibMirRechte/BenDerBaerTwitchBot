package de.bot.utils;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.bot.events.OnChannelMessageEvent;
import de.bot.handler.AccountHandler;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String name;
    private final String password;
    private final String channelID;
    private final String accessToken;
    private final String refreshToken;
    private final AccountHandler.AccountType accountType;
    private final BanData banData;
    private TwitchClient twitchClient;
    private final AutoVIPSettings autoVIPSettings;
    private final AutoShoutSettings autoShoutSettings;
    private final List<String> autoshout_Cache = new ArrayList<>();


    //TwitchClient benderbaer = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", accessToken)).withEnableChat(Boolean.TRUE).build();


    public Account(String name, String password, String channelID, String accessToken, String refreshToken, AccountHandler.AccountType accountType, AutoVIPSettings autoVIPSettings, AutoShoutSettings autoShoutSettings, BanData banData) {
        this.name = name;
        this.password = password;
        this.channelID = channelID;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accountType = accountType;
        this.banData = banData;
        this.autoVIPSettings = autoVIPSettings;
        this.autoShoutSettings = autoShoutSettings;

        if (!banData.isActiveBan()) {
            this.twitchClient = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", this.accessToken)).withEnableChat(Boolean.TRUE).build();
            this.twitchClient.getChat().joinChannel(this.name);
            this.twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> new OnChannelMessageEvent().printChannelMessage(event));
        }
    }

    public List<String> getAutoshout_Cache() {
        return autoshout_Cache;
    }

    public AutoShoutSettings getAutoShoutSettings() {
        return autoShoutSettings;
    }

    public String getChannelID() {
        return channelID;
    }

    public TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public AutoVIPSettings getAutoVIPSettings() {
        return autoVIPSettings;
    }

    public BanData getBanData() {
        return banData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public AccountHandler.AccountType getAccountType() {
        return this.accountType;
    }

}
