package de.bot.handler;

import de.bot.utils.Account;
import de.bot.utils.BanData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerCommunication {

    private final String serverIp = "";
    private final int port = 3459;
    private Account account;

    public Account login(String username, String password) {
        try {
            Socket socket = new Socket(serverIp, port);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("LOGIN");
            printStream.println(username);
            printStream.println(password);

            if (Boolean.parseBoolean(bufferedReader.readLine())) {
                AccountHandler.AccountType accountType;
                String accountTypeString = bufferedReader.readLine();
                boolean isBanned = Boolean.parseBoolean(bufferedReader.readLine());
                long bannedUntil = 0;
                String bannedReason = "";

                if (isBanned) {
                    bannedUntil = Long.parseLong(bufferedReader.readLine());
                    bannedReason = bufferedReader.readLine();
                }

                try {
                    accountType = AccountHandler.AccountType.valueOf(accountTypeString);
                } catch (IllegalArgumentException e) {
                    accountType = AccountHandler.AccountType.NORMAL;
                }

                Account acc = new Account(username, password, accountType, new BanData(isBanned, bannedUntil, bannedReason));
                account = acc;
                return acc;
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public void register(String username, String password, String registerKey) {

    }

    public void logout() {

        try {
            Socket socket = new Socket(serverIp, port);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println("LOGOUT");
            printStream.println(account.getName());
        } catch (Exception ignored) {
        }

        this.account = null;
    }


}
