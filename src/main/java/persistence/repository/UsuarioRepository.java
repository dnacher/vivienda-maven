package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
}
