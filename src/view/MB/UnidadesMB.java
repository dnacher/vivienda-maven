package view.MB;

import control.ControlVentana;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import persistence.constantes.Constantes;
import persistence.constantes.ConstantesEtiquetas;
import persistence.entities.Unidad;
import view.animations.FadeInUpTransition;

public class UnidadesMB implements Initializable {

	@FXML
	private AnchorPane paneCrud;

	@FXML
	private AnchorPane paneTabel;

	@FXML
	private CheckBox ChkActivo;

	@FXML
	private CheckBox ChkEsEdificio;

	@FXML
	private ComboBox<String> cmbPropietarioInquilino;

	@FXML
	private TextField txtPuerta;

	@FXML
	private TableView<Unidad> tableData;

	@FXML
	private DatePicker cmbFechaIngreso;

	@FXML
	private TextField txtNombre;

	@FXML
	private ProgressBar bar;

	@FXML
	private TextField txtTelefono;

	@FXML
	private ComboBox<String> cmbBlock;

	@FXML
	private ComboBox<Integer> cmbTorre;

	@FXML
	private TextField txtMail;

	@FXML
	private TextField txtApellido;

	@FXML
	private Slider cmbHabitaciones;

	@FXML
	private Label lblHabitaciones;

	@FXML
	private Button btnAgregar;

	@FXML
	private Button btnGuardar;

	ObservableList<Unidad> unidades;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
//			btnAgregar.setDisable(ConfiguracionControl.traePermisos(MenuAdministracion.Unidades.getPagina(), Constantes.PERMISO_OPERADOR));
//			btnGuardar.setDisable(ConfiguracionControl.traePermisos(MenuAdministracion.Unidades.getPagina(), Constantes.PERMISO_ADMIN));
			task();
			cmbHabitacionesListener();
			chkEsEdificioListener();
		} catch (Exception ex) {
			Logger.getLogger(UnidadesMB.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void chkEsEdificioListener() {
		ChkEsEdificio.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (ChkEsEdificio.isSelected()) {
					txtNombre.setDisable(true);
					txtApellido.setDisable(true);
					txtTelefono.setDisable(true);
					txtMail.setDisable(true);
					cmbFechaIngreso.setDisable(true);
					cmbPropietarioInquilino.setDisable(true);
					txtPuerta.setDisable(true);
				} else {
					txtNombre.setDisable(false);
					txtApellido.setDisable(false);
					txtTelefono.setDisable(false);
					txtMail.setDisable(false);
					cmbFechaIngreso.setDisable(false);
					cmbPropietarioInquilino.setDisable(false);
					txtPuerta.setDisable(false);
				}
			}
		});
	}

	public void cmbHabitacionesListener() {
		cmbHabitaciones.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int i = (int) cmbHabitaciones.getValue();
				lblHabitaciones.setText(String.valueOf(i));
			}
		});
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
//				try {
//					UnidadBean ub = new UnidadBean();
//					unidades = FXCollections.observableArrayList(ub.traerTodos());
//					cargaTabla();
//					cargarComboBlock();
//					cargarComboTorre();
//					cargarPropietarioInquilino();
//					cargaHoy();
//					mostrarTabla();
//				} catch (ServiceException ex) {
//					Logger.getLogger(UnidadesMB.class.getName()).log(Level.SEVERE, null, ex);
//				}
			}
		});
		bar.progressProperty().bind(longTask.progressProperty());
		new Thread(longTask).start();
	}

	public void recargarTabla() {
//		try {
//			UnidadBean ub = new UnidadBean();
//			unidades = FXCollections.observableArrayList(ub.traerTodos());
//			tableData.setItems(unidades);
//		} catch (Exception ex) {
//
//		}
	}

	public void cargaHoy() {
		Date date = new Date();
		LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		cmbFechaIngreso.setValue(ld);
		ChkActivo.setSelected(true);
	}

	public void cargaTabla() {
		TableColumn colAction = new TableColumn(ConstantesEtiquetas.ACCION);
		TableColumn Nombre = new TableColumn(ConstantesEtiquetas.NOMBRE_UPPER);
		TableColumn Apellido = new TableColumn(ConstantesEtiquetas.APELLIDO_UPPER);
		TableColumn Block = new TableColumn(ConstantesEtiquetas.BLOCK_UPPER);
		TableColumn Torre = new TableColumn(ConstantesEtiquetas.TORRE_UPPER);
		TableColumn Puerta = new TableColumn(ConstantesEtiquetas.PUERTA_UPPER);

		colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
				return new SimpleBooleanProperty(p.getValue() != null);
			}
		});
		colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
			@Override
			public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
				return new ButtonCell(tableData);
			}
		});

		Nombre.setMinWidth(150);
		Nombre.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.NOMBRE));

		Apellido.setMinWidth(150);
		Apellido.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.APELLIDO));

		Block.setMinWidth(100);
		Block.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.BLOCK_UPPER));

		Torre.setMinWidth(100);
		Torre.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.TORRE_UPPER));

		Puerta.setMinWidth(110);
		Puerta.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.PUERTA_UPPER));

		tableData.getColumns().addAll(colAction, Nombre, Apellido, Block, Torre, Puerta);
		tableData.setItems(unidades);

	}

	private class ButtonCell extends TableCell<Object, Boolean> {

		final Hyperlink cellButtonDelete = new Hyperlink(ConstantesEtiquetas.BORRAR);
		final Hyperlink cellButtonEdit = new Hyperlink(ConstantesEtiquetas.EDITAR);
		final HBox hb = new HBox(cellButtonDelete, cellButtonEdit);

		ButtonCell(final TableView tblView) {
			hb.setSpacing(4);
			cellButtonDelete.setOnAction((ActionEvent t) -> {
				// status = 1;
				int row = getTableRow().getIndex();
				tableData.getSelectionModel().select(row);
				//  aksiKlikTableData(null);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Estas seguro que deseas borrar " + txtNombre.getText() + " ?");
				alert.initStyle(StageStyle.UTILITY);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					/*  ? p = new ?();
                    p.setCustomerId(Integer.valueOf(txtId.getText()));
                    crud.delete(p);
                    clear();
                    selectData();*/
				} else {
					clear();
					// selectData();
					//   auto();
				}
				// status = 0;
			});
			cellButtonEdit.setOnAction((ActionEvent event) -> {
				// status = 1;
				int row = getTableRow().getIndex();
				tableData.getSelectionModel().select(row);
				//  aksiKlikTableData(null);
				paneTabel.setOpacity(0);
				new FadeInUpTransition(paneCrud).play();
				//  status = 0;
			});
		}
	}

	public void cargarComboBlock() {
		ObservableList<String> options
			= FXCollections.observableArrayList(Constantes.LISTA_BLOCKS);
		cmbBlock.setItems(options);
	}

	public void cargarComboTorre() {
		ObservableList<Integer> listaTorres;
		listaTorres = FXCollections.observableArrayList(Constantes.LISTA_TORRES);
		cmbTorre.setItems(listaTorres);
	}

	public void cargarPropietarioInquilino() {
		ObservableList<String> options = FXCollections.observableArrayList(ConstantesEtiquetas.PROPIETARIO, ConstantesEtiquetas.INQUILINO);
		cmbPropietarioInquilino.setItems(options);
		cmbPropietarioInquilino.getSelectionModel().selectLast();
	}

	public void nuevaUnidad() {
		paneTabel.setOpacity(0);
		new FadeInUpTransition(paneCrud).play();
		Platform.runLater(() -> {
			clear();
			auto();
		});
	}

	public void mostrarTabla() {
		paneCrud.setOpacity(0);
		new FadeInUpTransition(paneTabel).play();
	}

	private void clear() {
		cmbBlock.getSelectionModel().select(-1);
		cmbTorre.getSelectionModel().select(-1);
		cmbPropietarioInquilino.getSelectionModel().select(-1);
		txtPuerta.setText(ConstantesEtiquetas.VACIO);
		txtNombre.setText(ConstantesEtiquetas.VACIO);
		txtApellido.setText(ConstantesEtiquetas.VACIO);
		txtMail.setText(ConstantesEtiquetas.VACIO);
		txtTelefono.setText(ConstantesEtiquetas.VACIO);
		cmbFechaIngreso.setValue(null);
		ChkEsEdificio.setSelected(false);
	}

	private void auto() {

	}

	public int validar() {
		int i = 0;
		if (cmbBlock.getValue() == null) {
			i = 1;
		} else if (cmbTorre.getValue() == null) {
			i = 2;
		} else if (txtPuerta.getText().isEmpty()) {
			if (!ChkEsEdificio.isSelected()) {
				i = 3;
			}
		} else if (txtNombre.getText().isEmpty()) {
			if (!ChkEsEdificio.isSelected()) {
				i = 4;
			}
		} else if (txtApellido.getText().isEmpty()) {
			if (!ChkEsEdificio.isSelected()) {
				i = 5;
			}
		} else if (txtTelefono.getText().isEmpty()) {
			if (!ChkEsEdificio.isSelected()) {
				i = 6;
			}
		} else if (txtMail.getText().isEmpty()) {
			if (!ChkEsEdificio.isSelected()) {
				i = 7;
			}
		} else if (cmbPropietarioInquilino.getValue() == null) {
			if (!ChkEsEdificio.isSelected()) {
				i = 8;
			}
		} else if (cmbFechaIngreso.getValue() == null) {
			if (!ChkEsEdificio.isSelected()) {
				i = 9;
			}
		}
		return i;
	}

	public void guardar() {
		ControlVentana cv = new ControlVentana();
		switch (validar()) {
			case 0:
//				try {
//					if (!ChkEsEdificio.isSelected()) {
//						if (ConfiguracionControl.esNumero(txtPuerta.getText())) {
//							if (ConfiguracionControl.esNumero(txtTelefono.getText())) {
//								Unidad unidad = new Unidad();
//								unidad.setIdUnidad(ConfiguracionControl.traeUltimoId(ConstantesEtiquetas.UNIDAD));
//								unidad.setBlock(cmbBlock.getValue());
//								unidad.setTorre(cmbTorre.getValue());
//								unidad.setPuerta(Integer.valueOf(txtPuerta.getText()));
//								unidad.setNombre(txtNombre.getText());
//								unidad.setApellido(txtApellido.getText());
//								unidad.setMail(txtMail.getText());
//								unidad.setTelefono(Integer.valueOf(txtTelefono.getText()));
//								unidad.setFechaIngreso(ConfiguracionControl.TraeFecha(cmbFechaIngreso.getValue()));
//								unidad.setHabitaciones((int) cmbHabitaciones.getValue());
//								unidad.setEsEdificio(ChkEsEdificio.isSelected());
//								if (cmbPropietarioInquilino.getValue().equals(ConstantesEtiquetas.PROPIETARIO)) {
//									unidad.setPropietarioInquilino(true);
//								} else {
//									unidad.setPropietarioInquilino(false);
//								}
//								UnidadValidationUnique uv = new UnidadValidationUnique();
//								if (!uv.validarUnidad(unidad)) {
//									UnidadBean ub = new UnidadBean();
//									ub.guardar(unidad);
//									cv.creaVentanaNotificacionCorrecto();
//									recargarTabla();
//									clear();
//								} else {
//									cv.creaVentanaNotificacionError(ConstantesErrores.YA_EXISTE_UNIDAD_APARTAMENTO);
//								}
//							} else {
//								ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.TELEFONO_NUMERICO, Notification.WARNING_ICON));
//							}
//						} else {
//							ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.PUERTA_NUMERICO, Notification.WARNING_ICON));
//						}
//					} else {
//						Unidad unidad = new Unidad();
//						unidad.setIdUnidad(ConfiguracionControl.traeUltimoId(ConstantesEtiquetas.UNIDAD));
//						unidad.setBlock(cmbBlock.getValue());
//						unidad.setTorre(cmbTorre.getValue());
//						unidad.setNombre(cmbBlock.getValue() + " " + cmbTorre.getValue());
//						unidad.setEsEdificio(ChkEsEdificio.isSelected());
//						UnidadValidationUnique uv = new UnidadValidationUnique();
//						if (!uv.validarUnidad(unidad)) {
//							UnidadBean ub = new UnidadBean();
//							ub.guardar(unidad);
//							cv.creaVentanaNotificacionCorrecto();
//							recargarTabla();
//							clear();
//						} else {
//							cv.creaVentanaNotificacionError(ConstantesErrores.YA_EXISTE_UNIDAD_APARTAMENTO);
//						}
//					}
//				} catch (ServiceException ex) {
//					cv.creaVentanaNotificacionError(ex.getMessage());
//				} catch (Exception ex) {
//					cv.creaVentanaNotificacionError(ex.getMessage());
//				}
				break;
			case 1:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_BLOCK, Notification.WARNING_ICON));
				break;
			case 2:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_TORRE, Notification.WARNING_ICON));
				break;
			case 3:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_PUERTA, Notification.WARNING_ICON));
				break;
			case 4:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_NOMBRE, Notification.WARNING_ICON));
				break;
			case 5:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_APELLIDO, Notification.WARNING_ICON));
				break;
			case 6:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_TELEFONO, Notification.WARNING_ICON));
				break;
			case 7:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_MAIL, Notification.WARNING_ICON));
				break;
			case 8:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_PROPIETARIO, Notification.WARNING_ICON));
				break;
			case 9:
//				ConfiguracionControl.notifier.notify(new Notification("Verificar", ConstantesErrores.FALTA_FECHA_INGRESO, Notification.WARNING_ICON));
				break;
		}
	}

}
