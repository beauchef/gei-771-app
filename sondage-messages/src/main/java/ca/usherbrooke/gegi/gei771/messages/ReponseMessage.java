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
public class ReponseMessage implements Serializable {

    private static final long serialVersionUID = 8415268922597149578L;

    private int id;
    private String text;
    private QuestionMessage question;
}
