package Utils;

import ejb.services.TecnicoBean;
import exceptions.ServiceException;
import javafx.scene.image.ImageView;
import persistence.constantes.Constantes;
import persistence.entities.Tecnico;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class TecnicoImage {

	private int idTecnico;
	private String nombre;
	private String apellido;
	private String telefono;
	private String mail;
	private ImageView calificacion;
	private Integer estado;
	private Date fechaInicioEstado;
	private Date fechaFinEstado;
	private Boolean activo;

	public TecnicoImage() {
	}

	// <editor-fold defaultstate="collapsed" desc="Getters and Setters">

	public int getIdTecnico() {
		return idTecnico;
	}

	public void setIdTecnico(int idTecnico) {
		this.idTecnico = idTecnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ImageView getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(ImageView calificacion) {
		this.calificacion = calificacion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaInicioEstado() {
		return fechaInicioEstado;
	}

	public void setFechaInicioEstado(Date fechaInicioEstado) {
		this.fechaInicioEstado = fechaInicioEstado;
	}

	public Date getFechaFinEstado() {
		return fechaFinEstado;
	}

	public void setFechaFinEstado(Date fechaFinEstado) {
		this.fechaFinEstado = fechaFinEstado;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// </editor-fold>


	public static List<TecnicoImage> devuelveTecnicoConImagenEstado() throws ServiceException {
		List<TecnicoImage> listaFinal = new ArrayList<>();
		TecnicoBean tb = new TecnicoBean();
		List<Tecnico> lista = tb.traerTodos();
		for (Tecnico t : lista) {
			TecnicoImage ti = new TecnicoImage();
			ti.setIdTecnico(t.getId());
			ti.setActivo(t.getActivo());
			ti.setApellido(t.getApellido());
			ImageView iv = new ImageView(traeEstrellas(t.getCalificacion()));
			iv.setFitHeight(18);
			iv.setFitWidth(98);
			ti.setCalificacion(iv);
			ti.setEstado(t.getEstado());
			ti.setFechaFinEstado(t.getFechaFinEstado());
			ti.setFechaInicioEstado(t.getFechaInicioEstado());
			ti.setIdTecnico(t.getId());
			ti.setMail(t.getMail());
			ti.setNombre(t.getNombre());
			ti.setTelefono(t.getTelefono());
			listaFinal.add(ti);
		}
		return listaFinal;
	}

	public static String traeEstrellas(int calificacion) {
		String root = "";
		switch (calificacion) {
			case 0:
				root = Constantes.STAR0;
				break;
			case 1:
				root = Constantes.STAR0M;
				break;
			case 2:
				root = Constantes.STAR1;
				break;
			case 3:
				root = Constantes.STAR1M;
				break;
			case 4:
				root = Constantes.STAR2;
				break;
			case 5:
				root = Constantes.STAR2M;
				break;
			case 6:
				root = Constantes.STAR3;
				break;
			case 7:
				root = Constantes.STAR3M;
				break;
			case 8:
				root = Constantes.STAR4;
				break;
			case 9:
				root = Constantes.STAR4M;
				break;
			case 10:
				root = Constantes.STAR5;
				break;
			default:
				root = Constantes.STAR0;
		}
		return root;
	}

}
