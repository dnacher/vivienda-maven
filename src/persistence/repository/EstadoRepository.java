package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Estado;

public interface EstadoRepository extends CrudRepository <Estado,Integer> {
}
