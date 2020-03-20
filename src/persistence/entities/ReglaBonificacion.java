package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "regla_bonificacion")
public class ReglaBonificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String descripcion;

    @Column
    private Integer diaApagar;

    @Column
    private Integer tipoBonificacion;

    @Column
    private Integer valor;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "monto")
    private Monto monto;

    @Column
    private Integer habitaciones;

    @Column
    private boolean activo;

    public ReglaBonificacion(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiaApagar() {
        return diaApagar;
    }

    public void setDiaApagar(Integer diaApagar) {
        this.diaApagar = diaApagar;
    }

    public Integer getTipoBonificacion() {
        return tipoBonificacion;
    }

    public void setTipoBonificacion(Integer tipoBonificacion) {
        this.tipoBonificacion = tipoBonificacion;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Monto getMonto() {
        return monto;
    }

    public void setMonto(Monto monto) {
        this.monto = monto;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
