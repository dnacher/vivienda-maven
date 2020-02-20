package persistence.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.OtrosGastos;
import persistence.repository.OtrosGastosRepository;

import java.util.ArrayList;
import java.util.List;

public class OtrosGastosDAO {

    @Autowired
    public OtrosGastosRepository repository;

    public List<OtrosGastos> getOtrosGastos(){
        List<OtrosGastos> otrosGastos = new ArrayList<>();
        repository.findAll().forEach(otroGasto -> {
            otrosGastos.add(otroGasto);
        });
        return otrosGastos;
    }

    public OtrosGastos saveOtrosGastos(OtrosGastos otrosGastos){
        return repository.save(otrosGastos);
    }

    public OtrosGastos updateMonto(OtrosGastos otrosGastos) throws Exception{
        if(otrosGastos!=null && otrosGastos.getId()!=null){
            return repository.save(otrosGastos);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteOtrosGastos(Integer id){
        repository.deleteById(id);
    }

    public OtrosGastos getOtrosGastosById(Integer id){
        return repository.findById(id).get();
    }
}
