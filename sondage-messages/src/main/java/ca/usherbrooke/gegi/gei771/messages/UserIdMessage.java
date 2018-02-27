package ca.usherbrooke.gegi.gei771.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author beauchef on 2018-02-25.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIdMessage {

    private UUID id;
}
