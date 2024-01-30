package de.bot.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoShoutSettings {

    private final List<String> userList = new ArrayList<>();
    private String shoutoutMessage;

    public AutoShoutSettings(String userList, String shoutoutMessage) {
        if (!userList.isBlank()) {
            this.userList.addAll(Arrays.asList(userList.toLowerCase().split(",")));
        }
        this.shoutoutMessage = shoutoutMessage;
    }

    public void setShoutoutMessage(String shoutoutMessage) {
        this.shoutoutMessage = shoutoutMessage;
    }

    public String getShoutoutMessage() {
        if (this.shoutoutMessage.isBlank()) {
            return "Schaut doch mal bei {@user} vorbei! https://twitch.tv/{@user}";
        }
        return shoutoutMessage;
    }

    public String getUserListString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String user : this.userList) {
            if (stringBuilder.toString().equalsIgnoreCase("")) {
                stringBuilder.append(user);
            } else {
                stringBuilder.append(",").append(user);
            }
        }
        return stringBuilder.toString();
    }

    public void updateUserList(String userListString) {
        this.userList.clear();
        this.userList.addAll(Arrays.asList(userListString.toLowerCase().split(",")));
    }

    public List<String> getUserList() {
        return userList;
    }
}
