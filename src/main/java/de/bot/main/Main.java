package de.bot.main;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.FollowList;
import com.netflix.hystrix.HystrixCommand;
import de.bot.handler.UpdateHandler;
import de.bot.handler.WindowHandler;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public class Main {

    static UpdateHandler updateHandler = new UpdateHandler();
    static final WindowHandler windowHandler = new WindowHandler();

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

    static final String accessToken = "yn8k7e7c0bxs62fc26oo4xhapr0ylj";
    final String refreshToken = "qb5g3dvrrz3medmxxrdq5iz5wi8w56oht0z2wb5tfun69i9xun";

    public static void main(String[] args) {
        // TwitchClient benderbaer = TwitchClientBuilder.builder().withEnableHelix(Boolean.TRUE).withClientId("gp762nuuoqcoxypju8c569th9wz7q5").withChatAccount(new OAuth2Credential("twitch", accessToken)).withEnableChat(Boolean.TRUE).build();

        windowHandler.openWindow(WindowHandler.WindowType.DASHBOARD);
    }
}
