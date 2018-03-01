package ca.usherbrooke.gegi.gei771.sondage.exception;

import ca.usherbrooke.gegi.gei771.messages.ApiError;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author beauchef on 2018-02-01.
 */
@ControllerAdvice
@Log4j
public class RestExceptionHandler {

    private final UrlPathHelper urlPathHelper;
    private final HttpServletRequest request;

    @Autowired
    public RestExceptionHandler(UrlPathHelper urlPathHelper, HttpServletRequest request) {
        this.urlPathHelper = urlPathHelper;
        this.request = request;
    }

    @ExceptionHandler(SondageException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processSondageException(SondageException ex) {
        log.error(ex.getDebugMessage(), ex);
        ApiError error = ApiError.builder()
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .message(ex.getMessage())
                .debugMessage(ex.getDebugMessage())
                .path(urlPathHelper.getPathWithinApplication(request))
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = ApiError.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Opération invalide.")
                .debugMessage("L'opération effectuée était invalide. Vérifiez les paramètres d'entrée.")
                .path(urlPathHelper.getPathWithinApplication(request))
                .build();
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = ApiError.builder()
            .status(status.value())
            .error(status.getReasonPhrase())
            .message("Requête invalide.")
            .debugMessage("La requête effectuée était invalide. Vérifiez le contenu de la requête.")
            .path(urlPathHelper.getPathWithinApplication(request))
            .build();
        return new ResponseEntity<>(error, status);
    }
}
