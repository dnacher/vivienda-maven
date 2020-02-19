package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Permiso_usuario")
public class PermisoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_usuario")
    private TipoUsuario tipousuario;

    @Column(name = "permiso")
    private Integer permiso;

    public PermisoUsuario(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoUsuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }
}
