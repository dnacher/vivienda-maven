package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historial_monto")
public class HistorialMonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Monto monto;

    @Column
    private Date fechaActualizacion;

    @Column
    private Integer valorPesos;

    @Column
    private Boolean activo;
}
