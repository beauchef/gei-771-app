package ca.usherbrooke.gegi.gei771.sondage.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author beauchef on 2018-02-01.
 */
public class QuestionNotFoundException extends SondageException {

    private static final long serialVersionUID = 1325250568870578545L;

    public QuestionNotFoundException(int sondageId, int questionId) {
        super(NOT_FOUND, "Question introuvable.",
            String.format("Impossible de trouver la question %d pour le sondage %d.", questionId, sondageId));
    }
}
