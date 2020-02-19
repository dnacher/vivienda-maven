package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.TipoUsuario;

public interface TipoUsuarioRepository extends CrudRepository<TipoUsuario,Integer> {
}
