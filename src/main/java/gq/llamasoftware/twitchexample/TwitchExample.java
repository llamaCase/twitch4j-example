package gq.llamasoftware.twitchexample;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;
import com.github.twitch4j.helix.domain.Follow;

import java.util.List;

public class TwitchExample {

    private TwitchClient client;
    private OAuth2Credential credential;

    public void launch(String password){

        CredentialManager credentialManager = CredentialManagerBuilder.builder().build();
        credentialManager.registerIdentityProvider(new TwitchIdentityProvider("gp762nuuoqcoxypju8c569th9wz7q5", null, null));

        credential = new OAuth2Credential("twitch", password);
        client = TwitchClientBuilder
                .builder()
                .withDefaultAuthToken(credential)
                .withCredentialManager(credentialManager)
                .withEnableChat(true)
                .withEnableHelix(true)
                .withEnableTMI(true)
                .withEnablePubSub(true)
                .withChatAccount(credential)
                .build();

        String id = client.getHelix().getUsers(credential.getAccessToken(),
                null, null).execute().getUsers().get(0).getId();

        getFollowers(id);
    }

    public void getFollowers(String id){
        List<Follow> follows = client.getHelix().getFollowers(credential.getAccessToken(),
                null, id, null, null).execute().getFollows();

        follows.forEach(follow -> System.out.println(follow.getFromName()));
    }

}