package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Monto;

public interface MontoRepository extends CrudRepository<Monto,Integer> {
}
