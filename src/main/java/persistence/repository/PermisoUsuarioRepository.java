package persistence.repository;

import org.springframework.data.repository.CrudRepository;
import persistence.entities.PermisoUsuario;

public interface PermisoUsuarioRepository extends CrudRepository<PermisoUsuario,Integer> {
}
