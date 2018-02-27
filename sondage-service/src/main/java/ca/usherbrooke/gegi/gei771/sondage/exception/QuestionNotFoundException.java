package ca.usherbrooke.gegi.gei771.sondage.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author beauchef on 2018-02-01.
 */
public class QuestionNotFoundException extends SondageException {

    private static final long serialVersionUID = 1325250568870578545L;

    public QuestionNotFoundException(String message) {
        super(NOT_FOUND, "Question introuvable.", message);
    }
}
