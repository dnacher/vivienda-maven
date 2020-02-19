package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otros_gastos")
public class OtrosGastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "concepto")
    private Concepto concepto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "monto")
    private Monto monto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    @Column
    private Integer secuencia;

    @Column
    private String descripcion;

    @Column
    private Integer monto_1;

    @Column
    private Date fecha;

    @Column
    private Boolean pago;

    @Column
    private boolean activo;
}
