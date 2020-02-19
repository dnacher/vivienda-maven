package persistence.DAO;


import persistence.entities.Monto;
import persistence.repository.MontoRepository;

import java.util.ArrayList;
import java.util.List;

public class MontoDAO {

    @Autowired
    public MontoRepository repository;

    public List<Monto> getMontos(){
        List<Monto> montos = new ArrayList<>();
        repository.findAll().forEach(monto -> {
            montos.add(monto);
        });
        return montos;
    }

    public Monto saveMonto(Monto monto){
        return repository.save(monto);
    }

    public Monto updateMonto(Monto monto) throws Exception{
        if(monto!=null && monto.getId()!=null){
            return repository.save(monto);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteMonto(Integer id){
        repository.deleteById(id);
    }

    public Monto getMontoById(Integer id){
        return repository.findById(id).get();
    }
}
