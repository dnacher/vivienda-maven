package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.Urgencia;
import persistence.repository.UrgenciaRepository;

import java.util.ArrayList;
import java.util.List;

public class UrgenciaDAO {

    @Autowired
    public UrgenciaRepository repository;

    public List<Urgencia> getUrgencias(){
        List<Urgencia> urgencias = new ArrayList<>();
        repository.findAll().forEach(urgencia -> {
            urgencias.add(urgencia);
        });
        return urgencias;
    }

    public Urgencia saveUrgencia(Urgencia urgencia){
        return repository.save(urgencia);
    }

    public Urgencia updateUrgencia(Urgencia urgencia) throws Exception{
        if(urgencia!=null && urgencia.getId()!=null){
            return repository.save(urgencia);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteUrgencia(Integer id){
        repository.deleteById(id);
    }

    public Urgencia getUrgenciaById(Integer id){
        return repository.findById(id).get();
    }
}
