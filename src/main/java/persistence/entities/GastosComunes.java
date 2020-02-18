package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gastos_comunes")
public class GastosComunes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Monto monto;

    @Column
    private Unidad unidad;

    @Column
    private Integer monto_1;

    @Column
    private Boolean isBonificacion;

    @Column
    private Integer descuento;

    @Column
    private Integer estado;

    @Column
    private Date fechaPago;

    @Column
    private Integer periodo;

    @Column
    private String descripcion;

    @Column
    private Boolean activo;
}
