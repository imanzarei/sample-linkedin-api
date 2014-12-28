package dao;

import bean.Profile;
import org.springframework.stereotype.Repository;

/**
 * Created by iman on 12/22/2014.
 */
@Repository
public interface ProfileDao {
    Profile findById(String id);

    public void saveProfile(Profile profile);
}
