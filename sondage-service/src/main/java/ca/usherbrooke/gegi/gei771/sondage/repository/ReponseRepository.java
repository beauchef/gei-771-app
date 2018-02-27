package ca.usherbrooke.gegi.gei771.sondage.repository;

import org.springframework.data.repository.CrudRepository;

import ca.usherbrooke.gegi.gei771.sondage.model.Reponse;

/**
 * Interface qui définit que le repository utilisé pour manipuler des entités de
 * type {@link Reponse} est un repository CRUD (Create, Read, Update, Delete)
 * standard). Il n'y a aucun code à implémenter ici, car Spring injecte
 * automatiquement toutes les fonctionnalités requises dans les cas standards
 * comme celui-ci.
 */
public interface ReponseRepository extends CrudRepository<Reponse, Integer> {

}
