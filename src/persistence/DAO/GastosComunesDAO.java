package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.GastosComunes;
import persistence.repository.GastosComunesRepository;

import java.util.ArrayList;
import java.util.List;

public class GastosComunesDAO {

    @Autowired
    public GastosComunesRepository repository;

    public List<GastosComunes> getGastosComunes(){
        List<GastosComunes> gastosComunes = new ArrayList<>();
        repository.findAll().forEach(gc -> {
            gastosComunes.add(gc);
        });
        return gastosComunes;
    }

    public GastosComunes saveGastosComunes(GastosComunes gastosComunes){
        return repository.save(gastosComunes);
    }

    public GastosComunes updateGastosComunes(GastosComunes gastosComunes) throws Exception{
        if(gastosComunes!=null && gastosComunes.getId()!=null){
            return repository.save(gastosComunes);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteGastosComunes(Integer id){
        repository.deleteById(id);
    }

    public GastosComunes getGastosComunesById(Integer id){
        return repository.findById(id).get();
    }
}
