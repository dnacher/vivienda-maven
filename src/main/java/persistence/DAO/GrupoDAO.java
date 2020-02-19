package persistence.DAO;

import persistence.entities.CuotaConvenio;
import persistence.entities.Grupo;
import persistence.repository.GrupoRepository;

import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    @Autowired
    public GrupoRepository repository;

    public List<Grupo> getGrupos(){
        List<Grupo> grupos = new ArrayList<>();
        repository.findAll().forEach(grupo -> {
            grupos.add(grupo);
        });
        return grupos;
    }

    public Grupo saveGrupo(Grupo grupo){
        return repository.save(grupo);
    }

    public Grupo updateGrupo(Grupo grupo) throws Exception{
        if(grupo!=null && grupo.getId()!=null){
            return repository.save(grupo);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteGrupo(Integer id){
        repository.deleteById(id);
    }

    public CuotaConvenio getGrupoById(Integer id){
        return repository.findById(id).get();
    }
}
