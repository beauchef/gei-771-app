package ca.usherbrooke.gegi.gei771.sondage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Point d'entrée dans le service web.
 *
 * Recherche les contrôleurs potentiels dans le package spécifié, puis démarre
 * le serveur avec tous les contrôleurs trouvés.
 *
 */
@SpringBootApplication(scanBasePackages = { "ca.usherbrooke.gegi.gei771" })
public class Application {

    /**
     * @param args aucun argument n'est nécessaire ici.
     */
    public static void main(String[] args) {
        run(args);
    }

    public static ConfigurableApplicationContext run(String[] args) {
        return SpringApplication.run(Application.class, args);
    }
}
