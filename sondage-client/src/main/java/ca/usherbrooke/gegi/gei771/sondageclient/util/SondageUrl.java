package ca.usherbrooke.gegi.gei771.sondageclient.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author beauchef on 2018-02-25.
 */
@Component
public class SondageUrl {

    @Value("${sondage.service.baseUrl}")
    private String sondageBasePath;

    public String connexion() {
        return String.format("%s/connexion", sondageBasePath);
    }
}
