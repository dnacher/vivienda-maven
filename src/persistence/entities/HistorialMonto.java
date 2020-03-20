package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "historial_monto")
public class HistorialMonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "monto")
    private Monto monto;

    @Column
    private Date fechaActualizacion;

    @Column
    private Integer valorPesos;

    @Column
    private Boolean activo;

    public HistorialMonto(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Monto getMonto() {
        return monto;
    }

    public void setMonto(Monto monto) {
        this.monto = monto;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getValorPesos() {
        return valorPesos;
    }

    public void setValorPesos(Integer valorPesos) {
        this.valorPesos = valorPesos;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
