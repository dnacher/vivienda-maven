package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "monto")
public class Monto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descripcion;

    @Column
    private String simbolo;

    @Column
    private String tipoMonto;

    @Column
    private double valorPesos;

    @Column
    private Date fechaActualizacion;

    @Column
    private Boolean activo;
}
