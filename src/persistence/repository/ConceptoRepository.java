package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Concepto;

public interface ConceptoRepository extends CrudRepository<Concepto,Integer> {
}
