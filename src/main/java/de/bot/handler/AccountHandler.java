package de.bot.handler;

import de.bot.utils.Account;

public class AccountHandler {

    private Account account;
    static AccountHandler instance;

    public enum AccountType {
        ADMIN, STAFF, PREMIUM, NORMAL;
    }

    public Account getAccount() {
        return account;
    }

    public void logout() {
        account = null;
    }

    public static AccountHandler getInstance() {
        if (instance == null)
            instance = new AccountHandler();
        return instance;
    }

    public void setAccount(Account account) {
        account = account;
    }

}
