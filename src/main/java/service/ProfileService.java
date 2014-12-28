package service;

import bean.Profile;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;

/**
 * Created by iman on 12/22/2014.
 */
public interface ProfileService {
    public Profile showProfileById(String id);

    public void saveProfile(Person person);

    public LinkedInOAuthService createOAuthService();

    public LinkedInRequestToken getRequestToken(String callbackUrl);

    public LinkedInAccessToken getAccessToken(LinkedInRequestToken requestToken, String oauthVerifier);

    public Person getProfileForCurrentUser(String consumerKeyValue, String consumerSecretValue, LinkedInAccessToken accessToken);

}
