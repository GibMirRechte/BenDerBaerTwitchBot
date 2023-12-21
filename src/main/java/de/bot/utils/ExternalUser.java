package de.bot.utils;

import java.util.List;

public class ExternalUser {

    private final String username;
    private AccountRank accountRank;
    private String password;
    private String channelID;
    private String accessToken;
    private String refreshToken;
    private String modComment;
    private String modCommentCreator;
    private AccountRank modCommentCreatorRank;
    private List<BanData> bans;
    private List<Warn> warns;

    public ExternalUser(String username, String password, String accessToken, String refreshToken, String channelID, AccountRank accountRank, String modCommentCreator, AccountRank modCommentCreatorRank, String modComment, List<BanData> bans, List<Warn> warns) {
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.channelID = channelID;
        this.accountRank = accountRank;
        this.modCommentCreator = modCommentCreator;
        this.modCommentCreatorRank = modCommentCreatorRank;
        this.modComment = modComment;
        this.bans = bans;
        this.warns = warns;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getPassword() {
        return password;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAccountRank(AccountRank accountRank) {
        this.accountRank = accountRank;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getValidBanSize() {
        int i = 0;
        for (BanData banData : this.bans) {
            if (!banData.isCancelled()) i++;
        }
        return i;
    }

    public int getInvalidBanSize() {
        int i = 0;
        for (BanData banData : this.bans) {
            if (banData.isCancelled()) i++;
        }
        return i;
    }

    public int getValidWarnSize() {
        int i = 0;
        for (Warn warn : this.warns) {
            if (!warn.isCancelled()) i++;
        }
        return i;
    }

    public int getInvalidWarnSize() {
        int i = 0;
        for (Warn warn : this.warns) {
            if (warn.isCancelled()) i++;
        }
        return i;
    }

    public AccountRank getModCommentCreatorRank() {
        return modCommentCreatorRank;
    }

    public void updateModComment(String comment, String creator, AccountRank modCommentCreatorRank) {
        this.modComment = comment;
        this.modCommentCreator = creator;
        this.modCommentCreatorRank = modCommentCreatorRank;
    }

    public boolean isBanned() {
        for (BanData banData : this.bans) {
            if (banData.isActive()) return true;
        }
        return false;
    }

    public String getModComment() {
        return modComment;
    }

    public String getModCommentCreator() {
        return modCommentCreator;
    }

    public List<Warn> getWarns() {
        return warns;
    }

    public List<BanData> getBans() {
        return bans;
    }

    public String getUsername() {
        return username;
    }

    public AccountRank getAccountRank() {
        return accountRank;
    }

}
