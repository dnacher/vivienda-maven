package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "concepto")
public class Concepto {

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
