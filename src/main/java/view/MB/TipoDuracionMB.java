package view.MB;

import UtilsGeneral.ConfiguracionControl;
import control.ControlVentana;
import ejb.services.TipoDuracionBean;
import entities.constantes.ConstantesErrores;
import entities.persistence.entities.Tipoduracion;
import exceptions.ServiceException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.constantes.ConstantesEtiquetas;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import web.animations.FadeInUpTransition;

public class TipoDuracionMB implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private AnchorPane paneCrud;

    @FXML
    private AnchorPane paneTabel;

    @FXML
    private TableView<Tipoduracion> tableData;

    @FXML
    private TextField txtNombre;

    @FXML
    private ProgressBar bar;

    @FXML
    private TextArea TxtDescripcion;

    @FXML
    private CheckBox ChkActivo;

    @FXML
    private Label LblNombre;

    ObservableList<Tipoduracion> listaTipoUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        task();
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
                aksiNew(null);
                cargaTabla();
            }
        });
        bar.progressProperty().bind(longTask.progressProperty());
        new Thread(longTask).start();
    }

    private void clear() {
        txtNombre.clear();
        TxtDescripcion.clear();

    }

    @FXML
    private void aksiNew(ActionEvent event) {
        paneTabel.setOpacity(0);
        new FadeInUpTransition(paneCrud).play();
        Platform.runLater(() -> {
            clear();
        });
    }

    @FXML
    private void aksiSave(ActionEvent event) {
        LblNombre.setText(ConstantesEtiquetas.VACIO);
        ControlVentana cv = new ControlVentana();
        if (txtNombre.getText().isEmpty()) {
            LblNombre.setText(ConstantesErrores.FALTA_NOMBRE);
        } else {
            try {
                Tipoduracion tipoDuracion = new Tipoduracion();
                int ind = ConfiguracionControl.traeUltimoId(ConstantesEtiquetas.TIPO_DURACION);
                tipoDuracion.setIdtipoDuracion(ind);
                tipoDuracion.setActivo(ChkActivo.isSelected());
                tipoDuracion.setNombre(txtNombre.getText());
                tipoDuracion.setDescripcion(TxtDescripcion.getText());
                TipoDuracionBean tb = new TipoDuracionBean();
                tb.guardar(tipoDuracion);
                cv.creaVentanaNotificacionCorrecto();
                clear();
                llenaTabla();
                aksiBack(null);
            } catch (Exception ex) {
                cv.creaVentanaNotificacionError(ex.getMessage());
            }
        }
    }

    public void llenaTabla() {
        try {
            TipoDuracionBean tdb = new TipoDuracionBean();
            List<Tipoduracion> lista;
            lista = tdb.traerTodos();
            listaTipoUsuario = FXCollections.observableList(lista);
            tableData.setItems(listaTipoUsuario);
        } catch (ServiceException ex) {
            Logger.getLogger(TipoUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaTabla() {
        try {
            TipoDuracionBean tdb = new TipoDuracionBean();
            List<Tipoduracion> lista;
            lista = tdb.traerTodos();
            listaTipoUsuario = FXCollections.observableList(lista);
            TableColumn id = new TableColumn(ConstantesEtiquetas.ID_UPPER);
            TableColumn Nombre = new TableColumn(ConstantesEtiquetas.NOMBRE_UPPER);
            TableColumn Descripcion = new TableColumn(ConstantesEtiquetas.DESCRIPCION_UPPER);

            id.setMinWidth(100);
            id.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.ID_TIPO_DURACION));

            Nombre.setMinWidth(100);
            Nombre.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.NOMBRE));

            Descripcion.setMinWidth(100);
            Descripcion.setCellValueFactory(new PropertyValueFactory<>(ConstantesEtiquetas.DESCRIPCION));

            tableData.getColumns().addAll(id, Nombre, Descripcion);
            tableData.setItems(listaTipoUsuario);
        } catch (ServiceException ex) {
            Logger.getLogger(UrgenciaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void aksiBack(ActionEvent event) {
        paneCrud.setOpacity(0);
        new FadeInUpTransition(paneTabel).play();
    }

}
