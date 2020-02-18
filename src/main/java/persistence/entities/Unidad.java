package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "unidad")
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String block;

    @Column
    private Integer torre;

    @Column
    private Integer puerta;

    @Column
    private Integer habitaciones;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private Integer telefono;

    @Column
    private String mail;

    @Column
    private Boolean propietarioInquilino;

    @Column
    private Date fechaIngreso;

    @Column
    private Boolean activo;

    @Column
    private Boolean esEdificio;
}
