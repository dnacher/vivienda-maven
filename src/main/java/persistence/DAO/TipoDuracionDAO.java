package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.TipoDuracion;
import persistence.repository.TipoDuracionRepository;

import java.util.ArrayList;
import java.util.List;

public class TipoDuracionDAO {

    @Autowired
    public TipoDuracionRepository repository;

    public List<TipoDuracion> getTipoDuracion(){
        List<TipoDuracion> tipoDuracionList = new ArrayList<>();
        repository.findAll().forEach(tipoDuracion -> {
            tipoDuracionList.add(tipoDuracion);
        });
        return tipoDuracionList;
    }

    public TipoDuracion saveTipoDuracion(TipoDuracion tipoDuracion){
        return repository.save(tipoDuracion);
    }

    public TipoDuracion updateTipoDuracion(TipoDuracion tipoDuracion) throws Exception{
        if(tipoDuracion!=null && tipoDuracion.getId()!=null){
            return repository.save(tipoDuracion);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteTipoDuracion(Integer id){
        repository.deleteById(id);
    }

    public TipoDuracion getTipoDuracionById(Integer id){
        return repository.findById(id).get();
    }
}
