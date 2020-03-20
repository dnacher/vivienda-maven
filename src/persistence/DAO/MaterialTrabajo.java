package persistence.DAO;

public class MaterialTrabajo {


    private String nombre;
    private String descripcion;
    private int cantidad;

    public MaterialTrabajo(){
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // </editor-fold>

}
