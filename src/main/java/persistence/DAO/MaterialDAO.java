package persistence.DAO;


import persistence.entities.Material;
import persistence.repository.MaterialRepository;

import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    @Autowired
    public MaterialRepository repository;

    public List<Material> getMateriales(){
        List<Material> materiales = new ArrayList<>();
        repository.findAll().forEach(material -> {
            materiales.add(material);
        });
        return materiales;
    }

    public Material saveMaterial(Material material){
        return repository.save(material);
    }

    public Material updateMaterial(Material material) throws Exception{
        if(material!=null && material.getId()!=null){
            return repository.save(material);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteMaterial(Integer id){
        repository.deleteById(id);
    }

    public Material getMaterialById(Integer id){
        return repository.findById(id).get();
    }
}
