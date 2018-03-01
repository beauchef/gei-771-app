package ca.usherbrooke.gegi.gei771.sondageclient.service;

import ca.usherbrooke.gegi.gei771.messages.QuestionMessage;
import ca.usherbrooke.gegi.gei771.messages.SondageMessage;
import ca.usherbrooke.gegi.gei771.sondageclient.exception.SondageServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Scanner;

/**
 * @author beauchef on 2018-02-25.
 */
@Service
public class CommandLineService implements CommandLineRunner {

    private final SondageService sondageService;

    @Autowired
    public CommandLineService (final SondageService sondageService) {
        Assert.notNull(sondageService, "sondageService cannot be null.");
        this.sondageService = sondageService;
    }

    @Override
    public void run(String... strings) {
        Scanner keyboard = new Scanner(System.in);
        try {
            String userId = sondageService.connexion().getId().toString();
            System.out.println("Connexion d'un utilisateur avec l'identifiant: " + userId);
            List<SondageMessage> sondages = sondageService.getSondages(userId);
            int numeroDeSondage = 1;
            for (SondageMessage sondage : sondages) {
                System.out.println();
                System.out.println(String.format("Sondage %d/%d: %s", numeroDeSondage, sondages.size(), sondage.getDescription()));
                int numeroDeQuestion = 1;
                while (numeroDeQuestion <= sondage.getQuestions().size()) {
                    QuestionMessage question = sondage.getQuestions().get(numeroDeQuestion-1);
                    System.out.println(String.format("  Question %d/%d: %s", numeroDeQuestion, sondage.getQuestions().size(), question.getText()));
                    System.out.print("  > ");
                    String answer = keyboard.next();
                    try {
                        sondageService.setReponse(userId, sondage.getId(), question.getId(), answer);
                        numeroDeQuestion++;
                    } catch (HttpStatusCodeException ex) {
                        // traitement très rudimentaire des exceptions... je devrais plutôt me baser sur des
                        // codes d'erreurs pour savoir comment traîter le problème.
                        if (ex.getStatusCode().value() == 400) {
                            System.err.println("Réponse invalide (la réponse devrait être une seule lettre en minuscule)");
                        } else {
                            throw ex; // rethrow
                        }
                    }
                }
                numeroDeSondage++;
            }
        } catch (RestClientException ex) {
            System.err.println("Impossible de rejoindre le service de sondage: " + ex.getMessage());
        } catch (SondageServerException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
