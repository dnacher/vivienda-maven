package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Permiso_usuario")
public class PermisoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "permiso")
    private Integer permiso;

    public PermisoUsuario(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }
}
