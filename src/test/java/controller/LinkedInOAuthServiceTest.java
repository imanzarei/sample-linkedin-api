package controller;

import com.google.code.linkedinapi.client.LinkedInApiClient;
import com.google.code.linkedinapi.client.LinkedInApiClientFactory;
import com.google.code.linkedinapi.client.enumeration.ProfileField;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.schema.Person;
import config.TestConstants;
import junit.framework.TestCase;
import org.junit.*;

import java.util.EnumSet;

/**
 * Created by iman on 12/24/2014.
 */
public class LinkedInOAuthServiceTest extends TestCase {


    protected LinkedInApiClientFactory factory;
    protected LinkedInAccessToken accessToken;
    protected LinkedInApiClient client;


    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        assertNotNull(TestConstants.LINKED_IN_TEST_CONSUMER_KEY);
        assertNotNull(TestConstants.LINKED_IN_TEST_CONSUMER_SECRET);
        factory =
                LinkedInApiClientFactory.newInstance(TestConstants.LINKED_IN_TEST_CONSUMER_KEY,
                        TestConstants.LINKED_IN_TEST_CONSUMER_SECRET);
        assertNotNull(TestConstants.LINKED_IN_TEST_ACCESS_TOKEN_KEY);
        assertNotNull(TestConstants.LINKED_IN_TEST_ACCESS_TOKEN_SECRET);
        accessToken =
                new LinkedInAccessToken(TestConstants.LINKED_IN_TEST_ACCESS_TOKEN_KEY,
                        TestConstants.LINKED_IN_TEST_ACCESS_TOKEN_SECRET);
        client = factory.createLinkedInApiClient(accessToken);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        factory = null;
        accessToken = null;
    }

    /**
     * Test method for {@link com.google.code.linkedinapi.client.impl.LinkedInApiJaxbClient#getProfileForCurrentUser()}.
     */
    @Test
    public void testGetProfileForCurrentUser() {
        Person profile = client.getProfileForCurrentUser();
        assertNotNull("Profile should never be null.", profile);
    }

    /**
     * Test method for {@link com.google.code.linkedinapi.client.impl.LinkedInApiJaxbClient#getProfileForCurrentUser(java.util.Set)}.
     */
    @Test
    public void testGetProfileForCurrentUserSetOfProfileField() {
        Person profile = client.getProfileForCurrentUser(EnumSet.allOf(ProfileField.class));
        assertNotNull("Profile should never be null.", profile);
    }

}
