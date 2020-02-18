package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "Permiso_usuario")
public class PermisoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    private TipoUsuario tipousuario;

    @Column(name = "permiso")
    private Integer permiso;
}
