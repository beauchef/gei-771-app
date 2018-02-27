package ca.usherbrooke.gegi.gei771.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author beauchef on 2018-02-26.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionMessage implements Serializable {

    private static final long serialVersionUID = -2710697051996524985L;

    private int id;
    private String text;
}
