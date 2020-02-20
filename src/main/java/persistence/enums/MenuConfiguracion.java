package persistence.enums;

/**
 *
 * @author Dani-Fla-Mathi
 */
public enum MenuConfiguracion {

    Inicio("Inicio", "Inicio", "Configuracion"),
    Grupo("Grupo", "Grupo", "Configuracion"),
    Urgencia("Urgencia", "Urgencia", "Configuracion"),
    Concepto("Concepto", "Concepto", "Configuracion"),
    Usuarios("Usuario", "Usuario", "Configuracion"),
    TipoUsuario("TipoUsuario", "Tipo Usuario", "Configuracion"),
    TipoDuracion("TipoDuracion", "Tipo Duracion", "Configuracion"),
    ImportardeExcel("ImportarExcel", "Importar Excel", "Configuracion"),
    Seguridad("Seguridad", "Seguridad", "Configuracion");

    private final String pagina;
	private final String menu;
	private final String carpeta;

	MenuConfiguracion(String pagina, String menu, String carpeta) {
		this.pagina = pagina;
		this.menu = menu;
		this.carpeta = carpeta;
	}

	public String getPagina() {
		return pagina;
	}

	public String getMenu() {
		return menu;
	}

	public String getCarpeta() {
		return carpeta;
	}
	
}
