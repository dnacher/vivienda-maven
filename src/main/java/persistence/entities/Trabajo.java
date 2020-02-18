package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "trabajo")
public class Trabajo {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private Estado estado;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "grupo")
    private Grupo grupo;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_duracion")
    private TipoDuracion tipoduracion;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "urgencia")
    private Urgencia urgencia;

    @Column
    private String descripcion;

    @Column
    private Date fechaCreacion;

    @Column
    private Date fechaVisita;

    @Column
    private Integer calificacion;

    @Column
    private Integer duracionEstimada;

    @Column
    private Integer duracionReal;

    @Column
    private Boolean activo;

}
