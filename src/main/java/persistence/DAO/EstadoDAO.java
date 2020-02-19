package persistence.DAO;

import persistence.entities.CuotaConvenio;
import persistence.entities.Estado;
import persistence.repository.EstadoRepository;

import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {

    @Autowired
    public EstadoRepository repository;

    public List<Estado> getEstados(){
        List<Estado> estados = new ArrayList<>();
        repository.findAll().forEach(estado -> {
            estados.add(estado);
        });
        return estados;
    }

    public CuotaConvenio saveEstado(Estado estado){
        return repository.save(estado);
    }

    public Estado updateEstado(Estado estado) throws Exception{
        if(estado!=null && estado.getId()!=null){
            return repository.save(estado);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteEstado(Integer id){
        repository.deleteById(id);
    }

    public CuotaConvenio getEstadoById(Integer id){
        return repository.findById(id).get();
    }
}
