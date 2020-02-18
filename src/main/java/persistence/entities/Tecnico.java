package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tecnico")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column
    private String mail;

    @Column
    private Integer calificacion;

    @Column
    private Integer estado;

    @Column
    private Date fechaInicioEstado;

    @Column
    private Date fechaFinEstado;

    @Column
    private Boolean activo;
}
