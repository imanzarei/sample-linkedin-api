package service;

import bean.Profile;
import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import converter.ProfileConverter;
import dao.ProfileDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

/**
 * Profile Service for LinkedInOAuthService and save Profile data to DB and retrieve from it.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = Logger.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileDao profileDao;

    @Value("${linkedin.consumer.key.value}")
    private String consumerKeyValue;
    @Value("${linkedin.consumer.secret.value}")
    private String consumerSecretValue;

    /**
     * Find profile by id from DB and return the Profile Object.
     *
     * @param id
     * @return Profile object.
     */
    public Profile showProfileById(String id) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.showProfile ");
        Profile profile = profileDao.findById(id);
        if (logger.isDebugEnabled()) {
            logger.debug("id is: " + id);
            logger.debug("Exit ProfileServiceImpl.showProfile ");
        }
        return profile;
    }

    /**
     * Convert Person object to Profile object and save it in DB.
     *
     * @param person
     */
    public void saveProfile(Person person) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.saveProfile ");
        ProfileConverter profileConverter = new ProfileConverter();
        Profile profile = profileConverter.convertProfileOfPerson(person);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.saveProfile ");
        profileDao.saveProfile(profile);

    }

    /**
     * Create OAuth Service
     *
     * @return LinkedInOAuthService object.
     */
    public LinkedInOAuthService createOAuthService() {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter ProfileServiceImpl.createOAuthService ");
            logger.debug("Consumer key value : " + consumerKeyValue);
            logger.debug("Consumer secret value : " + consumerSecretValue);
        }
        LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().createLinkedInOAuthService(consumerKeyValue, consumerSecretValue);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.createOAuthService ");
        return oauthService;
    }

    /**
     * return requested token
     *
     * @param callbackUrl
     * @return LinkedInRequestToken
     */
    public LinkedInRequestToken getRequestToken(String callbackUrl) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.getRequestToken ");
        LinkedInOAuthService oauthService = createOAuthService();
        LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(callbackUrl);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.getRequestToken ");
        return requestToken;
    }

    /**
     * Return Access Token
     *
     * @param requestToken
     * @param oauthVerifier
     * @return LinkedInAccessToken
     */
    public LinkedInAccessToken getAccessToken(LinkedInRequestToken requestToken, String oauthVerifier) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.getAccessToken ");
        logger.debug("oauthVerifier : " + oauthVerifier);
        LinkedInOAuthService oauthService = createOAuthService();
        LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.getAccessToken ");
        return accessToken;
    }

    /**
     * get Profile information For Current User.
     *
     * @param consumerKeyValue
     * @param consumerSecretValue
     * @param accessToken
     * @return Person
     */
    public Person getProfileForCurrentUser(String consumerKeyValue, String consumerSecretValue, LinkedInAccessToken accessToken) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.getProfileForCurrentUser ");
        final LinkedInApiClientFactory factory = LinkedInApiClientFactory.newInstance(consumerKeyValue, consumerSecretValue);
        final LinkedInApiClient client = factory.createLinkedInApiClient(accessToken);
        Person person = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.getProfileForCurrentUser ");
        return person;
    }
}
