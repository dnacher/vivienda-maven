package persistence.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.ReglaBonificacion;
import persistence.repository.ReglaBonificacionRepository;

import java.util.ArrayList;
import java.util.List;

public class ReglaBonificacionDAO {

    @Autowired
    public ReglaBonificacionRepository repository;

    public List<ReglaBonificacion> getReglasBonificacion(){
        List<ReglaBonificacion> reglasBonificacion = new ArrayList<>();
        repository.findAll().forEach(reglaBonificacion -> {
            reglasBonificacion.add(reglaBonificacion);
        });
        return reglasBonificacion;
    }

    public ReglaBonificacion saveReglaBonificacion(ReglaBonificacion reglaBonificacion){
        return repository.save(reglaBonificacion);
    }

    public ReglaBonificacion updateReglaBonificacion(ReglaBonificacion reglaBonificacion) throws Exception{
        if(reglaBonificacion!=null && reglaBonificacion.getId()!=null){
            return repository.save(reglaBonificacion);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteReglaBonificacion(Integer id){
        repository.deleteById(id);
    }

    public ReglaBonificacion getReglaBonificacionById(Integer id){
        return repository.findById(id).get();
    }
}
