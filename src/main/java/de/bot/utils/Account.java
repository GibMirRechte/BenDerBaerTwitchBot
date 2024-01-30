package de.bot.utils;

import com.github.twitch4j.TwitchClient;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String name;
    private final String password;
    private final String channelID;
    private final String accessToken;
    private final String refreshToken;
    private final AccountRank accountRank;
    private final BanData banData;
    private TwitchClient twitchClient;
    private final AutoVIPSettings autoVIPSettings;
    private final AutoShoutSettings autoShoutSettings;
    private final List<String> autoshout_Cache = new ArrayList<>();
    private final AutoMessagesSettings autoMessagesSettings;
    private long lastFeedback = 0;
    private CustomCommandSettings customCommandSettings;


    //TwitchClient benderbaer = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", accessToken)).withEnableChat(Boolean.TRUE).build();


    public Account(String name, String password, String channelID, String accessToken, String refreshToken, AccountRank accountRank, AutoVIPSettings autoVIPSettings, AutoShoutSettings autoShoutSettings, AutoMessagesSettings autoMessagesSettings, CustomCommandSettings customCommandSettings, BanData banData) {
        this.name = name;
        this.password = password;
        this.channelID = channelID;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accountRank = accountRank;
        this.banData = banData;
        this.autoVIPSettings = autoVIPSettings;
        this.autoMessagesSettings = autoMessagesSettings;
        this.autoShoutSettings = autoShoutSettings;
        this.customCommandSettings = customCommandSettings;

        if (!banData.isActive()) {
            //this.twitchClient = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", this.accessToken)).withEnableChat(Boolean.TRUE).build();
            //this.twitchClient.getChat().joinChannel(this.name);
            //this.twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> new OnChannelMessageEvent().printChannelMessage(event));
        }
    }

    public long getLastFeedback() {
        return lastFeedback;
    }

    public CustomCommandSettings getCustomCommandSettings() {
        return customCommandSettings;
    }

    public AutoMessagesSettings getAutoMessagesSettings() {
        return autoMessagesSettings;
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

    public AccountRank getAccountRank() {
        return this.accountRank;
    }

    public boolean canSendNewFeedback() {
        return this.lastFeedback < (System.currentTimeMillis() - 30000);
    }

    public void updateLastFeedback() {
        this.lastFeedback = System.currentTimeMillis();
    }

}
