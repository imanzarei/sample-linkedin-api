package config;

import dao.ProfileDao;
import dao.ProfileImplDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iman on 12/23/2014.
 */
@Configuration
@ComponentScan(basePackages = {
        "dao",
        "service"
})
public class TestConfig {

    @Bean
    public ProfileDao profileImplDao() {
        return new ProfileImplDao();
    }

}