package ca.usherbrooke.gegi.gei771.sondage.repository;

import ca.usherbrooke.gegi.gei771.sondage.model.Usager;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author beauchef on 2018-02-04.
 */
public interface UsagerRepository extends CrudRepository<Usager, UUID> {
}
