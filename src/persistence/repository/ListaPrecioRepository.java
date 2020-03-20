package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.ListaPrecio;

public interface ListaPrecioRepository extends CrudRepository<ListaPrecio,Integer> {
}
