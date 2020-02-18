package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "urgencia")
public class Urgencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private boolean activo;
}
