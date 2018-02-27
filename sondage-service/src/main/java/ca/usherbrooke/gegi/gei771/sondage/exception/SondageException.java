package ca.usherbrooke.gegi.gei771.sondage.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author beauchef on 2018-02-01.
 */
public class SondageException extends Exception {

    @Getter
    private final HttpStatus status;
    @Getter
    private final String debugMessage;

    public SondageException(HttpStatus status, String displayMessage, String debugMessage) {
        super(displayMessage);
        this.status = status;
        this.debugMessage = debugMessage;
    }
}
