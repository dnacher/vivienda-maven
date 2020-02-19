package persistence.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import persistence.entities.Concepto;
import persistence.repository.ConceptoRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ConceptoDAO {

    @Autowired
    public ConceptoRepository repository;

    public List<Concepto> getConceptos(){
        List<Concepto> conceptos = new ArrayList<>();
        repository.findAll().forEach(concepto -> {
            conceptos.add(concepto);
        });
        return conceptos;
    }

    public Concepto saveConcepto(Concepto concepto){
        return repository.save(concepto);
    }

    public Concepto updateConcepto(Concepto concepto) throws Exception{
        if(concepto!=null && concepto.getId()!=null){
            return repository.save(concepto);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteConcepto(Integer conceptoId){
        repository.deleteById(conceptoId);
    }

    public Concepto getConceptoById(Integer conceptoId){
        return repository.findById(conceptoId).get();
    }
}
