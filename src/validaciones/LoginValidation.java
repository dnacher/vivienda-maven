package validaciones;

import eu.hansolo.enzo.notification.Notification;
import exceptions.ServiceException;
import persistence.constantes.ConstantesErrores;
import persistence.constantes.ConstantesEtiquetas;
import persistence.enums.errores;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class LoginValidation {

	public static Notification login(String nombre, String pass) {
//		if (nombre == null || nombre.isEmpty()) {
//			return new Notification(ConstantesEtiquetas.ERROR, errores.ERROR_LOGIN_FALTAN_DATOS_NOMBRE.getErrorCodigo(), Notification.ERROR_ICON);
//		} else if (pass == null || pass.isEmpty()) {
//			return new Notification(ConstantesEtiquetas.ERROR, errores.ERROR_LOGIN_FALTAN_DATOS_PASS.getErrorCodigo(), Notification.ERROR_ICON);
//		} else {
//			try {
//				Viviendas.user = ub.traerUsuarioXNombre(nombre);
//				if (Viviendas.user != null) {
//					Viviendas.user.setPassword(pass);
//					if (Viviendas.user.getNombre().equals(nombre) && Seguridad.verifyPassword(Viviendas.user)) {
//						ub = new UsuariosBean();
//						Viviendas.listaPermisos = ub.TraePermisos(Viviendas.getTipoUsuario());
//						ConfiguracionBean cb = new ConfiguracionBean();
//						listaConfiguracion = cb.traerTodos();
//						return new Notification("Correcto", "Se logueo Correctamente", Notification.SUCCESS_ICON);
//					} else {
//						return new Notification("Error", "Credenciales incorrectas", Notification.ERROR_ICON);
//					}
//				} else {
//					return new Notification(ConstantesEtiquetas.ERROR, errores.ERROR_LOGIN_DATOS_INCORRECTOS.getErrorCodigo(), Notification.ERROR_ICON);
//				}
//			} catch (ServiceException ex) {
//				return new Notification(ConstantesEtiquetas.ERROR, errores.ERROR_LOGIN_GENERAL.getErrorCodigo(), Notification.ERROR_ICON);
//			} catch (Exception ex) {
//				Logger.getLogger(LoginValidation.class.getName()).log(Level.SEVERE, null, ex);
//				return new Notification(ConstantesEtiquetas.ERROR, ConstantesErrores.CONTACTE_ADMINISTRADOR, Notification.ERROR_ICON);
//			}
//		}
		return null;
	}

}
