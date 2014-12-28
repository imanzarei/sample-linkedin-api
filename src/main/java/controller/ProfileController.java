package controller;


import bean.Profile;
import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;
import com.google.code.linkedinapi.schema.Person;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ProfileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Cotroller class for login and show profile information
 */
@Controller
public class ProfileController {

    private static final Logger logger = Logger.getLogger(ProfileController.class);
    //injecting class ProfileService into ProfileController
    @Autowired
    ProfileService profileService;
    @Value("${linkedin.callBack.url}")
    private String callBackUrl;
    @Value("${linkedin.consumer.key.value}")
    private String consumerKeyValue;
    @Value("${linkedin.consumer.secret.value}")
    private String consumerSecretValue;

    /**
     * Forwarding user in to the LinkedIn login page and after get access token key from
     * linkedIn,forward the User to CallBackUrl
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileController.login ");
        //get Request token from linkedIn
        LinkedInRequestToken requestToken = profileService.getRequestToken(callBackUrl);
        HttpSession session = request.getSession();
        //Save Request token to session
        session.setAttribute("requestToken", requestToken);
        //get Authorization URL from Linked in
        String authUrl = requestToken.getAuthorizationUrl();
        if (logger.isDebugEnabled())
            logger.debug("authUrl is: " + authUrl);
        //Redirect to Linkedin Login page
        response.sendRedirect(authUrl);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileController.login");
    }

    /**
     * Call Back URL after authentication and get access token key.
     * get datas from LinkedIn api and save to MongoDB.
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/callBack", method = RequestMethod.GET)
    public String callBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileController.callBack");

        HttpSession session = request.getSession();
        LinkedInRequestToken requestToken = (LinkedInRequestToken) session.getAttribute("requestToken");
        //get oauth verifier
        String oauthVerifier = request.getParameter("oauth_verifier");
        session.setAttribute("oauthVerifier", oauthVerifier);
        if (logger.isDebugEnabled())
            logger.debug("oauthVerifier is: " + oauthVerifier);

        LinkedInOAuthService oAuthService = profileService.createOAuthService();
        LinkedInAccessToken accessToken = profileService.getAccessToken(requestToken, oauthVerifier);
        //Fetch data from linked in with consumer Key Value,consumer Secret Value and access token key.
        Person person = profileService.getProfileForCurrentUser(consumerKeyValue, consumerSecretValue, accessToken);
        //Check if this profile didn't exist,save it to DB.
        Profile profile = profileService.showProfileById(person.getId());
        if (profile == null) {
            //Saving Profile in DB.
            profileService.saveProfile(person);
        }
        session.setAttribute("personId", person.getId());

        if (logger.isDebugEnabled()) {
            logger.debug("Person Id is: " + person.getId());
            logger.debug("Exit ProfileController.callBack");
        }
        return "redirect:profile.jsp";

    }

    /**
     * Show Profile data in JSON format
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/showProfile", method = RequestMethod.GET)
    public
    @ResponseBody
    bean.Profile showProfile(HttpServletRequest request) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileController.showProfile");
        HttpSession session = request.getSession();
        String personId = (String) session.getAttribute("personId");
        if (logger.isDebugEnabled())
            logger.debug("Person Id is: " + personId);
        //Fetch profile data from MongoDB
        Profile profile = profileService.showProfileById(personId);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileController.showProfile");
        return profile;
    }

    /**
     * Invalidate the current session.
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileController.logout ");
        HttpSession session = request.getSession();
        session.invalidate();
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileController.logOut");
        return "redirect:/";


    }
}