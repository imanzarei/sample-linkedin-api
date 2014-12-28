package controller; /**
 * Created by iman on 12/23/2014.
 */

import bean.Profile;
import config.TestConfig;
import dao.ProfileDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
public class ProfileDaoIntegrationTest {

    @Autowired
    ProfileDao profileDao;

    @Test
    public void createProfileTest() {
        Profile profile = new Profile();
        profile.setEmailAddress("unitTest@test.com");
        profile.setFirstName("unitTestFirstName");
        profile.setLastName("unitTestLastName");

        assertNull(profile.getId());
        profileDao.saveProfile(profile);
        assertNotNull(profile.getId());
        assertTrue(profile.getId().length() > 0);

    }
}