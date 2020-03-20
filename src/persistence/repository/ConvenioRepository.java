package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Convenio;

public interface ConvenioRepository extends CrudRepository<Convenio,Integer> {
}
