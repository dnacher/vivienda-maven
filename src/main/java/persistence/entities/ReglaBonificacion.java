package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "regla_bonificacion")
public class ReglaBonificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descripcion;

    @Column
    private Integer diaApagar;

    @Column
    private Integer tipoBonificacion;

    @Column
    private Integer valor;

    @Column
    private Monto monto;

    @Column
    private Integer habitaciones;

    @Column
    private boolean activo;
}
