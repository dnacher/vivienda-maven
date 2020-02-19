package persistence.DAO;

import persistence.entities.ListaPrecio;

import java.util.ArrayList;
import java.util.List;

public class ListaPrecioDAO {

    @Autowired
    public ListaPrecio repository;

    public List<ListaPrecio> getHistorialTrabajos(){
        List<ListaPrecio> listaPrecios = new ArrayList<>();
        repository.findAll().forEach(listaPrecio -> {
            listaPrecios.add(listaPrecio);
        });
        return listaPrecios;
    }

    public ListaPrecio saveListaPrecio(ListaPrecio listaPrecio){
        return repository.save(listaPrecio);
    }

    public ListaPrecio updateListaPrecio(ListaPrecio listaPrecio) throws Exception{
        if(listaPrecio!=null && listaPrecio.getId()!=null){
            return repository.save(listaPrecio);
        }else{
            throw new Exception("el id no puede estar vacio");
        }
    }

    public void deleteListaPrecio(Integer id){
        repository.deleteById(id);
    }

    public ListaPrecio getListaPrecioById(Integer id){
        return repository.findById(id).get();
    }
}
