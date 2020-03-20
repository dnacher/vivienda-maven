package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Trabajo;

public interface TrabajoRepository extends CrudRepository<Trabajo,Integer> {
}
