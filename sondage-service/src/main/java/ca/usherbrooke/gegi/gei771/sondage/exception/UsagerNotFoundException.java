package ca.usherbrooke.gegi.gei771.sondage.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author beauchef on 2018-02-04.
 */
public class UsagerNotFoundException extends SondageException {

    private static final long serialVersionUID = 4383290483806178750L;

    public UsagerNotFoundException(String message) {
        super(NOT_FOUND, "Usager introuvable.", message);
    }
}
