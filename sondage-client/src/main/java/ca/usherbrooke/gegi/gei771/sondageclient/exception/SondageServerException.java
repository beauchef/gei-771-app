package ca.usherbrooke.gegi.gei771.sondageclient.exception;

/**
 * @author beauchef on 2018-02-25.
 */
public class SondageServerException extends Exception {

    public SondageServerException(String message) {
        super(message);
    }

    public SondageServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
