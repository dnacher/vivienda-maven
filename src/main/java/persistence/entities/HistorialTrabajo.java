package persistence.entities;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historial_trabajo")
public class HistorialTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Tecnico tecnico;

    @Column
    private Trabajo trabajo;

    @Column
    private String descripcion;

    @Column
    private Date fecha;

    @Column
    private Estado estado;
}
