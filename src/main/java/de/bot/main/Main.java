package de.bot.main;

import com.github.twitch4j.helix.domain.FollowList;
import com.netflix.hystrix.HystrixCommand;
import de.bot.handler.AccountHandler;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.time.LocalDate;

public class Main {

    static UpdateHandler updateHandler = new UpdateHandler();
    static final WindowHandler windowHandler = new WindowHandler();
    static final AccountHandler accountHandler = AccountHandler.getInstance();

    @RequestLine("GET /users/follows?from_id={from_id}&to_id={to_id}&after={after}&first={first}")
    @Headers("Authorization: Bearer {token}")
    HystrixCommand<FollowList> getFollowers(
            @Param("token") String authToken,
            @Param("from_id") String fromId,
            @Param("to_id") String toId,
            @Param("after") String after,
            @Param("first") Integer limit
    ) {
        return null;
    }

    public static void main(String[] args) {
        accountHandler.loadConfig();

        System.out.println(LocalDate.now().minusMonths(12));
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.parse("2022-09-24").minusDays(2));
        updateHandler.checkForUpdate();

        if (updateHandler.hasNewUpdate()) {
            windowHandler.openWindow(WindowHandler.WindowType.UPDATE);
        } else {
            windowHandler.openWindow(WindowHandler.WindowType.LOGIN);
        }
    }
}
