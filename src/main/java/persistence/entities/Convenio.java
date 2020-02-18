package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "convenio")
public class Convenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "monto")
    private Monto monto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "regla_bonificacion")
    private ReglaBonificacion reglabonificacion;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    @Column
    private Integer deudaTotal;

    @Column
    private Integer cuotas;

    @Column
    private Integer saldoInicial;

    @Column
    private String descripcion;

    @Column
    private Boolean activo;
}
