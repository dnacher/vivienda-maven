package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.GastosComunes;

public interface GastosComunesRepository extends CrudRepository<GastosComunes,Integer> {
}
