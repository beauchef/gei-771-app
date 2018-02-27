package ca.usherbrooke.gegi.gei771.sondage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author beauchef on 2018-02-01.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public UrlPathHelper urlPathHelper() {
        return new UrlPathHelper();
    }
}
