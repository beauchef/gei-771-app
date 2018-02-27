package ca.usherbrooke.gegi.gei771.sondage.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author beauchef on 2018-02-01.
 */
public class SondageNotFoundException extends SondageException {

    private static final long serialVersionUID = 1789382213659857835L;

    public SondageNotFoundException(String message) {
        super(NOT_FOUND, "Sondage introuvable.", message);
    }
}
