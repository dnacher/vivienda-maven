package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Material;

public interface MaterialRepository extends CrudRepository<Material,Integer> {
}
