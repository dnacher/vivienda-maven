package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Grupo;

public interface GrupoRepository extends CrudRepository<Grupo,Integer> {
}
