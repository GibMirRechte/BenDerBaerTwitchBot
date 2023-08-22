package de.bot.utils;

import de.bot.handler.AccountHandler;

public class Account {

    private final String name;
    private final String password;
    private final AccountHandler.AccountType accountType;
    public BanData banData;

    public Account(String name, String password, AccountHandler.AccountType accountType, BanData banData) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.banData = banData;
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
