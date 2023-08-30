package de.bot.utils;

import com.github.twitch4j.TwitchClient;
import de.bot.handler.AccountHandler;

public class Account {

    private final String name;
    private final String password;
    private final String channelID;
    private final String accessToken;
    private final String refreshToken;
    private final AccountHandler.AccountType accountType;
    private int autoVIP_monthsIntervall;
    private int autoVIP_streams;
    private final BanData banData;
    private TwitchClient twitchClient;


    //TwitchClient benderbaer = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", accessToken)).withEnableChat(Boolean.TRUE).build();


    public Account(String name, String password, String channelID, String accessToken, String refreshToken, AccountHandler.AccountType accountType, int autoVIP_monthsIntervall, int autoVIP_streams, BanData banData) {
        this.name = name;
        this.password = password;
        this.channelID = channelID;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accountType = accountType;
        this.autoVIP_monthsIntervall = autoVIP_monthsIntervall;
        this.autoVIP_streams = autoVIP_streams;
        this.banData = banData;

        //if(!banData.isActiveBan())
        //this.twitchClient = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", this.accessToken)).withEnableChat(Boolean.TRUE).build();
    }

    public String getChannelID() {
        return channelID;
    }

    public TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public int getAutoVIP_months() {
        return this.autoVIP_monthsIntervall;
    }

    public int getAutoVIP_streams() {
        return this.autoVIP_streams;
    }

    public void updateAutoVIPSettings(int months, int streams) {
        this.autoVIP_monthsIntervall = months;
        this.autoVIP_streams = streams;
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
