package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gastos_comunes")
public class GastosComunes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Monto monto;

    @Column
    private Unidad unidad;

    @Column
    private Integer monto_1;

    @Column
    private Boolean isBonificacion;

    @Column
    private Integer descuento;

    @Column
    private Integer estado;

    @Column
    private Date fechaPago;

    @Column
    private Integer periodo;

    @Column
    private String descripcion;

    @Column
    private Boolean activo;

    public GastosComunes(){}

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

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public Integer getMonto_1() {
        return monto_1;
    }

    public void setMonto_1(Integer monto_1) {
        this.monto_1 = monto_1;
    }

    public Boolean getBonificacion() {
        return isBonificacion;
    }

    public void setBonificacion(Boolean bonificacion) {
        isBonificacion = bonificacion;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
