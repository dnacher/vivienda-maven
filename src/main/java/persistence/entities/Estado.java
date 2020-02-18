package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Integer orden;

    @Column
    private boolean ultimoEstado;

    @Column
    private boolean activo;
}
