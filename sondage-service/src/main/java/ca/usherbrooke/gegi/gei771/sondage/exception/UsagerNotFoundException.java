package ca.usherbrooke.gegi.gei771.sondage.exception;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author beauchef on 2018-02-04.
 */
public class UsagerNotFoundException extends SondageException {

    private static final long serialVersionUID = 4383290483806178750L;

    public UsagerNotFoundException(UUID userId) {
        super(NOT_FOUND, "Usager introuvable.",
            String.format("Impossible de trouver l'usager avec l'identifiant '%s'.", userId.toString()));
    }
}
