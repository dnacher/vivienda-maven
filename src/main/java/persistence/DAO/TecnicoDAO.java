package persistence.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.Tecnico;
import persistence.repository.TecnicoRepository;

import java.util.ArrayList;
import java.util.List;

public class TecnicoDAO {

    @Autowired
    public TecnicoRepository repository;

    public List<Tecnico> getTecnicos(){
        List<Tecnico> tecnicos = new ArrayList<>();
        repository.findAll().forEach(tecnico -> {
            tecnicos.add(tecnico);
        });
        return tecnicos;
    }

    public Tecnico saveTecnico(Tecnico tecnico){
        return repository.save(tecnico);
    }

    public Tecnico updateTecnico(Tecnico tecnico) throws Exception{
        if(tecnico!=null && tecnico.getId()!=null){
            return repository.save(tecnico);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteTecnico(Integer id){
        repository.deleteById(id);
    }

    public Tecnico getTecnicoById(Integer id){
        return repository.findById(id).get();
    }
}
