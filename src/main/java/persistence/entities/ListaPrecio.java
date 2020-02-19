package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lista_precio")
public class ListaPrecio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "material")
    private Material material;

    @Column
    private Integer precio;

    @Column
    private Integer cantidad;

    @Column
    private Date fecha;

    @Column
    private Boolean activo;
}
