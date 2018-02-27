package ca.usherbrooke.gegi.gei771.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author beauchef on 2018-02-26.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SondageMessage implements Serializable {

    private static final long serialVersionUID = -875088799024649224L;

    private int id;
    private String description;
    private List<QuestionMessage> questions;
}
