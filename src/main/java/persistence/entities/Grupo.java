package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private boolean activo;
}
