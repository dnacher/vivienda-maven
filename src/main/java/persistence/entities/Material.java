package persistence.entities;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

@Entity
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Integer cantidad;

    @Column
    private Integer entrada;

    @Column
    private Integer salida;

    @Column
    private boolean activo;
}
