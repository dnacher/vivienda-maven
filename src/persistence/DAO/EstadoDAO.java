package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Estado saveEstado(Estado estado){
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

    public Estado getEstadoById(Integer id){
        return repository.findById(id).get();
    }
}
