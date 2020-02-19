package persistence.DAO;


import persistence.entities.PermisoUsuario;
import persistence.repository.PermisoUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class PermisoUsuarioDAO {

    @Autowired
    public PermisoUsuarioRepository repository;

    public List<PermisoUsuario> getPermisoUsuarios(){
        List<PermisoUsuario> permisoUsuarios = new ArrayList<>();
        repository.findAll().forEach(permisoUsuario -> {
            permisoUsuarios.add(permisoUsuario);
        });
        return permisoUsuarios;
    }

    public PermisoUsuario savePermisoUsuario(PermisoUsuario permisoUsuario){
        return repository.save(permisoUsuario);
    }

    public PermisoUsuario updatePermisoUsuario(PermisoUsuario permisoUsuario) throws Exception{
        if(permisoUsuario!=null && permisoUsuario.getId()!=null){
            return repository.save(permisoUsuario);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deletePermisoUsuario(Integer id){
        repository.deleteById(id);
    }

    public PermisoUsuario getPermisoUsuarioById(Integer id){
        return repository.findById(id).get();
    }
}
