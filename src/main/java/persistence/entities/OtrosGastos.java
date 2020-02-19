package persistence.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otros_gastos")
public class OtrosGastos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "concepto")
    private Concepto concepto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "monto")
    private Monto monto;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name = "unidad")
    private Unidad unidad;

    @Column
    private Integer secuencia;

    @Column
    private String descripcion;

    @Column
    private Integer monto_1;

    @Column
    private Date fecha;

    @Column
    private Boolean pago;

    @Column
    private boolean activo;

    public OtrosGastos(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
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

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMonto_1() {
        return monto_1;
    }

    public void setMonto_1(Integer monto_1) {
        this.monto_1 = monto_1;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
