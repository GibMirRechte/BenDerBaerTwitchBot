package de.bot.events;

import com.github.philippheuer.events4j.simple.domain.EventSubscriber;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import de.bot.handler.AccountHandler;
import de.bot.handler.UpdateHandler;
import de.bot.utils.Account;

public class OnChannelMessageEvent {

    AccountHandler accountHandler = AccountHandler.getInstance();
    UpdateHandler updateHandler = UpdateHandler.getInstance();

    @EventSubscriber
    public void printChannelMessage(ChannelMessageEvent event) {
        if (!updateHandler.getToolData().isMaintenance_autoshout() && accountHandler.getAccount() != null) {
            Account account = accountHandler.getAccount();
            if (!account.getAutoshout_Cache().contains(event.getUser().getName())) {
                if (account.getAutoShoutSettings().getUserList().contains(event.getUser().getName())) {
                    account.getTwitchClient().getHelix().sendShoutout(account.getAccessToken(), account.getChannelID(), event.getUser().getId(), account.getChannelID());
                    event.getTwitchChat().sendMessage(event.getChannel().getName(), account.getAutoShoutSettings().getShoutoutMessage().replace("{@user}", event.getUser().getName()));
                    account.getAutoshout_Cache().add(event.getUser().getName());
                }
            }
        }
    }
}
