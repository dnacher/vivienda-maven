package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.CuotaConvenio;
import persistence.entities.HistorialTrabajo;
import persistence.repository.HistorialTrabajoRepository;

import java.util.ArrayList;
import java.util.List;

public class HistorialTrabajoDAO {

    @Autowired
    public HistorialTrabajoRepository repository;

    public List<HistorialTrabajo> getHistorialTrabajos(){
        List<HistorialTrabajo> historialTrabajos = new ArrayList<>();
        repository.findAll().forEach(historialTrabajo -> {
            historialTrabajos.add(historialTrabajo);
        });
        return historialTrabajos;
    }

    public HistorialTrabajo saveHistorialTrabajo(HistorialTrabajo historialTrabajo){
        return repository.save(historialTrabajo);
    }

    public HistorialTrabajo updateHistorialTrabajo(HistorialTrabajo historialTrabajo) throws Exception{
        if(historialTrabajo!=null && historialTrabajo.getId()!=null){
            return repository.save(historialTrabajo);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteHistorialTrabajo(Integer id){
        repository.deleteById(id);
    }

    public HistorialTrabajo getHistorialTrabajoById(Integer id){
        return repository.findById(id).get();
    }
}
