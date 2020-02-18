package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cuota_convenio")
public class CuotaConvenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private Convenio convenio;

    @Column
    private Monto monto;

    @Column
    private Integer numeroCuota;

    @Column
    private String descripcion;

    @Column
    private Integer pago;

    @Column
    private Boolean tieneBonificacion;

    @Column
    private Date fechaPago;
}
