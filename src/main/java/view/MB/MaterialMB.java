package view.MB;

import UtilsGeneral.ConfiguracionControl;
import control.ControlVentana;
import ejb.services.MaterialBean;
import entities.constantes.Constantes;
import entities.constantes.ConstantesEtiquetas;
import entities.enums.MenuMantenimiento;
import entities.persistence.entities.Material;
import exceptions.ServiceException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import web.animations.FadeInUpTransition;

public class MaterialMB implements Initializable {

	@FXML
	private AnchorPane paneCrud;

	@FXML
	private AnchorPane paneTabel;

	@FXML
	private TableView<Material> tableData;

	@FXML
	private TextField txtNombre;

	@FXML
	private ProgressBar bar;

	@FXML
	private TextArea txtDescripcion;

	@FXML
	private TextField txtCantidad;

	@FXML
	private Label LblNombre;

	@FXML
	private Button btnAgregar;

	public ObservableList<Material> lista;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			btnAgregar.setDisable(ConfiguracionControl.traePermisos(MenuMantenimiento.Materiales.getPagina(), Constantes.PERMISO_OPERADOR));
			task();
			atras(null);
		} catch (Exception ex) {
			Logger.getLogger(MaterialMB.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void task() {
		Task longTask = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				int max = 50;
				for (int i = 1; i <= max; i++) {
					if (isCancelled()) {
						break;
					}
					updateProgress(i, max);
					Thread.sleep(20);
				}
				return null;
			}
		};

		longTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				try {
					MaterialBean mb = new MaterialBean();
					lista = FXCollections.observableArrayList(mb.traerTodos());
					cargaTabla();
				} catch (ServiceException ex) {
					Logger.getLogger(MaterialMB.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		bar.progressProperty().bind(longTask.progressProperty());
		new Thread(longTask).start();
	}

	private void clear() {
		try {
			txtNombre.clear();
			txtCantidad.clear();
			txtDescripcion.clear();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void nuevoMaterial(ActionEvent event) {
		paneTabel.setOpacity(0);
		new FadeInUpTransition(paneCrud).play();
		Platform.runLater(() -> {
			clear();
		});
	}

	@FXML
	private void guardar(ActionEvent event) {
		LblNombre.setText(ConstantesEtiquetas.VACIO);
		ControlVentana cv = new ControlVentana();
		try {
			Material material = new Material();
			int ind = ConfiguracionControl.traeUltimoId(ConstantesEtiquetas.MATERIAL_UPPER);
			material.setIdmaterial(ind);
			material.setActivo(true);
			material.setNombre(txtNombre.getText());
			material.setDescripcion(txtDescripcion.getText());
			material.setEntrada(Integer.valueOf(txtCantidad.getText()));
			material.setSalida(0);
			material.setCantidad(material.getEntrada());
			MaterialBean mb = new MaterialBean();
			mb.guardar(material);
			cv.creaVentanaNotificacionCorrecto();
			clear();
			llenaTabla();
		} catch (Exception ex) {
			cv.creaVentanaNotificacionError(ex.getMessage());
		}
	}

	@FXML
	private void atras(ActionEvent event) {
		paneCrud.setOpacity(0);
		new FadeInUpTransition(paneTabel).play();
	}

	public void cargaTabla() {
		TableColumn Nombre = new TableColumn(ConstantesEtiquetas.NOMBRE_UPPER);
		TableColumn Descripcion = new TableColumn(ConstantesEtiquetas.DESCRIPCION_UPPER);
		TableColumn Cantidad = new TableColumn(ConstantesEtiquetas.CANTIDAD_UPPER);
		TableColumn Entrada = new TableColumn(ConstantesEtiquetas.ENTRADA_UPPER);
		TableColumn Salida = new TableColumn(ConstantesEtiquetas.SALIDA_UPPER);
		TableColumn Activo = new TableColumn(ConstantesEtiquetas.ACTIVO_UPPER);

		Nombre.setMinWidth(150);
		Nombre.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.NOMBRE));

		Descripcion.setMinWidth(150);
		Descripcion.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.DESCRIPCION));

		Entrada.setMinWidth(100);
		Entrada.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.ENTRADA));

		Salida.setMinWidth(100);
		Salida.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.SALIDA));

		Cantidad.setMinWidth(100);
		Cantidad.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.CANTIDAD));

		Activo.setMinWidth(100);
		Activo.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.ACTIVO));

		tableData.getColumns().addAll(Nombre, Descripcion, Entrada, Salida, Cantidad, Activo);
		tableData.setItems(lista);

	}

	public void llenaTabla() {
		try {
			MaterialBean mb = new MaterialBean();
			lista = FXCollections.observableArrayList(mb.traerTodos());
			tableData.setItems(lista);
		} catch (ServiceException ex) {
			Logger.getLogger(MaterialMB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
