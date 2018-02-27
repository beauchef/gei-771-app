package ca.usherbrooke.gegi.gei771.sondageclient.service;

import ca.usherbrooke.gegi.gei771.messages.UserIdMessage;
import ca.usherbrooke.gegi.gei771.sondageclient.exception.SondageServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

/**
 * @author beauchef on 2018-02-25.
 */
@Service
public class CommandLineService implements CommandLineRunner {

    final SondageService sondageService;

    @Autowired
    public CommandLineService (final SondageService sondageService) {
        Assert.notNull(sondageService, "sondageService cannot be null.");
        this.sondageService = sondageService;
    }

    @Override
    public void run(String... strings) {
        try {
            UserIdMessage identifiant = sondageService.connextion();
            System.out.println("Connexion d'un utilisateur avec l'identifiant: " + identifiant.getId());
        } catch (RestClientException ex) {
            System.err.println("Impossible de rejoindre le service de sondage: " + ex.getMessage());
        } catch (SondageServerException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
