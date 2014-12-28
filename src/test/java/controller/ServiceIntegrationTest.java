package controller;

import bean.Profile;
import config.TestConfig;
import dao.ProfileDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import service.ProfileService;
import service.ProfileServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by iman on 12/23/2014.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TestConfig.class})
@RunWith(MockitoJUnitRunner.class)
public class ServiceIntegrationTest {

    @Mock
    private ProfileDao profileDao;
    @InjectMocks
    private ProfileService profileService = new ProfileServiceImpl();

    @Test
    public void testShowProfile() {
        Profile profile = new Profile();
        profile.setId("10");
        when(profileDao.findById(profile.getId())).thenReturn(profile);
        Profile found = profileService.showProfileById(profile.getId());
        assertEquals(profile, found);
    }

}
