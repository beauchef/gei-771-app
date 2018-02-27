package ca.usherbrooke.gegi.gei771.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author beauchef on 2018-02-25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseTextMessage implements Serializable {

    private static final long serialVersionUID = -130763430300692512L;

    private String text;
}
