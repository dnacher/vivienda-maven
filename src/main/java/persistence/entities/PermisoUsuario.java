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
}
