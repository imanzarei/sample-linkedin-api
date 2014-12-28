package dao;

import bean.Profile;
import config.SpringMongoConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 * DAO class for save and retrieve data from DB.
 */
@Repository
public class ProfileImplDao implements ProfileDao {

    private static final Logger logger = Logger.getLogger(ProfileImplDao.class);

    ApplicationContext ctx =
            new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

    /**
     * Find profile by id.
     *
     * @param id
     * @return Profile
     */
    public Profile findById(String id) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.findById ");
        Profile profile = mongoOperation.findById(id, Profile.class);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.findById ");
        return profile;
    }

    /**
     * Save Profile to DB.
     *
     * @param profile
     */
    public void saveProfile(Profile profile) {
        if (logger.isDebugEnabled())
            logger.debug("Enter ProfileServiceImpl.saveProfile ");
        mongoOperation.save(profile);
        if (logger.isDebugEnabled())
            logger.debug("Exit ProfileServiceImpl.saveProfile ");
    }
}
