package persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "activo")
    private boolean activo;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "permiso_usuario")
    private PermisoUsuario permisosusuarios;

    public TipoUsuario(){}
   
	// <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public PermisoUsuario getPermisosusuarios() {
        return permisosusuarios;
    }

    public void setPermisosusuarios(PermisoUsuario permisosusuarios) {
        this.permisosusuarios = permisosusuarios;
    }


    // </editor-fold>

}


