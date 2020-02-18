package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "tipo_duracion")
public class TipoDuracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private boolean activo;

    @Column
    private String descripcion;
}
