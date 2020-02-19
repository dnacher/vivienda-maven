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

    public Monto(){}

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

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTipoMonto() {
        return tipoMonto;
    }

    public void setTipoMonto(String tipoMonto) {
        this.tipoMonto = tipoMonto;
    }

    public double getValorPesos() {
        return valorPesos;
    }

    public void setValorPesos(double valorPesos) {
        this.valorPesos = valorPesos;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
