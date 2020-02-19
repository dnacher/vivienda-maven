package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Tecnico;

public interface TecnicoRepository extends CrudRepository<Tecnico,Integer> {
}
