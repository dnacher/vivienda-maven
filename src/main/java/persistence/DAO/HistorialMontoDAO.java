package persistence.DAO;

import persistence.entities.CuotaConvenio;
import persistence.entities.HistorialMonto;
import persistence.repository.HistorialMontoRepository;

import java.util.ArrayList;
import java.util.List;

public class HistorialMontoDAO {

    @Autowired
    public HistorialMontoRepository repository;

    public List<HistorialMonto> getHistorialMontos(){
        List<HistorialMonto> historialMontos = new ArrayList<>();
        repository.findAll().forEach(historialMonto -> {
            historialMontos.add(historialMonto);
        });
        return historialMontos;
    }

    public HistorialMonto saveHistorialMonto(HistorialMonto historialMonto){
        return repository.save(historialMonto);
    }

    public HistorialMonto updateHistorialMonto(HistorialMonto historialMonto) throws Exception{
        if(historialMonto!=null && historialMonto.getId()!=null){
            return repository.save(historialMonto);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteHistorialMonto(Integer id){
        repository.deleteById(id);
    }

    public HistorialMonto getHistorialMontoById(Integer id){
        return repository.findById(id).get();
    }
}
