package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import persistence.entities.CuotaConvenio;
import persistence.repository.CuotaConvenioRepository;

import java.util.ArrayList;
import java.util.List;

public class CuotaConvenioDAO {

    @Autowired
    public CuotaConvenioRepository repository;

    public List<CuotaConvenio> getCuotaConvenios(){
        List<CuotaConvenio> cuotaConvenios = new ArrayList<>();
        repository.findAll().forEach(cuotaConvenio -> {
            cuotaConvenios.add(cuotaConvenio);
        });
        return cuotaConvenios;
    }

    public CuotaConvenio saveCuotaConvenio(CuotaConvenio cuotaConvenio){
        return repository.save(cuotaConvenio);
    }

    public CuotaConvenio updateCuotaConvenio(CuotaConvenio cuotaConvenio) throws Exception{
        if(cuotaConvenio!=null && cuotaConvenio.getId()!=null){
            return repository.save(cuotaConvenio);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteCuotaConvenio(Integer id){
        repository.deleteById(id);
    }

    public CuotaConvenio getCuotaConvenioById(Integer id){
        return repository.findById(id).get();
    }
}
