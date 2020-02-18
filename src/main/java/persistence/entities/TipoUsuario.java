package persistence.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class TipoUsuario implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private boolean activo;

    private List<PermisoUsuario> permisosusuarios = new ArrayList<>();


   
	// <editor-fold defaultstate="collapsed" desc="Getters and Setters"> 
	

	
	// </editor-fold>

    @Override
    public String toString() {
        return nombre;
    }

}


