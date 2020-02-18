package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otros_gastos")
public class OtrosGastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Concepto concepto;

    @Column
    private Monto monto;

    @Column
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
