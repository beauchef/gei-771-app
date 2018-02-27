package ca.usherbrooke.gegi.gei771.messages;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

import static java.time.LocalDateTime.now;
import static java.time.ZoneOffset.UTC;

/**
 * @author beauchef on 2018-02-01.
 */
@Getter
@Builder
public class ApiError implements Serializable {

    private static final long serialVersionUID = 4114149462752957760L;

    @Builder.Default private long timestamp = now().toEpochSecond(UTC);
    private int status;
    private String error;
    private String message;
    private String debugMessage;
    private String path;
}
