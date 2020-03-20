package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Urgencia;

public interface UrgenciaRepository extends CrudRepository<Urgencia,Integer> {
}
