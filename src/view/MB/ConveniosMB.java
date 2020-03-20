package view.MB;

import utils.UtilsGenerals;
import exceptions.ServiceException;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.DAO.ConvenioDAO;
import persistence.DAO.MontoDAO;
import persistence.DAO.ReglaBonificacionDAO;
import persistence.DAO.UnidadDAO;
import persistence.constantes.Constantes;
import persistence.constantes.ConstantesErrores;
import persistence.constantes.ConstantesEtiquetas;
import persistence.entities.Convenio;
import persistence.entities.Monto;
import persistence.entities.ReglaBonificacion;
import persistence.entities.Unidad;
import control.ControlVentana;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import view.animations.FadeInUpTransition;

import java.util.HashMap;

public class ConveniosMB implements Initializable {

    @FXML
    private ComboBox<Monto> cmbMoneda;

    @FXML
    private ComboBox<ReglaBonificacion> cmbReglaBonificacion;

    @FXML
    private TableView<Unidad> tblUnidades;

    @FXML
    private AnchorPane paneStep1;

    @FXML
    private AnchorPane paneStep2;

    @FXML
    private AnchorPane paneStep3;

    @FXML
    private Button btnStepAtras;

    @FXML
    private ImageView imgProgressTracker;

    @FXML
    private TextField txtSaldoInicial;

    @FXML
    private ComboBox<String> cmbTipoConvenio;

    @FXML
    private Label lblCuotas;

    @FXML
    private Button btnStepAdelante;

    @FXML
    private ComboBox<Integer> cmbTorre;

    @FXML
    private ComboBox<String> cmbBlock;

    @FXML
    private Label lblDeudaTotal;

    @FXML
    private Text txtUnidad;

    @FXML
    private Text txtUnidad2;

    @FXML
    private Label lblInfo;

    @FXML
    private Label lblSimbolo;

    @FXML
    private DatePicker cmbFechaTipoConvenio;

    @FXML
    private TextField txtTipoConvenio;

    @FXML
    private Label lblTipoConvenio;

    @FXML
    private Button btnRefresh;

    @FXML
    private CheckBox chkActivo;

    @FXML
    private TextArea txtDescripcion;

    @Autowired
    public UnidadDAO unidadDAO;

    @Autowired
    public ReglaBonificacionDAO reglaBonificacionDAO;

    @Autowired
    public MontoDAO montoDAO;

    @Autowired
    public ConvenioDAO convenioDAO;


    ObservableList listaUnidades;
    Image img1 = new Image(Constantes.STEP1);
    Image img2 = new Image(Constantes.STEP2);
    Image img3 = new Image(Constantes.STEP3);
    int paneActual = 1;
    Unidad unidad;
    public int i;
    Long deudaPesos = 0L;
    Monto monto = null;
    ReglaBonificacion reglaBonificacion = null;
    double deudaOtraMoneda = 0;
    double cuotas = 0;
    int saldoInicial = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        btnStepAdelante.setDisable(ConfiguracionControl.traePermisos(MenuAdministracion.Convenios.getPagina(), Constantes.PERMISO_OPERADOR));
        btnRefresh.setVisible(false);
        txtTipoConvenio.setVisible(false);
        cmbFechaTipoConvenio.setVisible(false);
        cargaTabla();
        btnStepAtras.setDisable(true);
        atras();
        cargarComboBlock();
        cargarComboTorre();
        cargarComboTipoConvenio();
        try {
            cargaComboMonto();
            cargaComboReglaBonificacion();
            cmbReglaBonificacion.getSelectionModel().selectFirst();
        } catch (ServiceException ex) {
            Logger.getLogger(ConveniosMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarComboBlock() {
        ObservableList<String> options
                = FXCollections.observableArrayList(Constantes.LISTA_BLOCKS);
        cmbBlock.setItems(options);
    }

    public void cargarComboTipoConvenio() {
        ObservableList<String> options
                = FXCollections.observableArrayList(Constantes.LISTA_TIPO_CONVENIOS);
        cmbTipoConvenio.setItems(options);
        cmbTipoConvenio.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                calculaTipoConvenio(cmbTipoConvenio.getValue());
            }

        });
    }

    public void calculaTipoConvenio(String valor) {
        btnRefresh.setVisible(true);
        switch (valor) {
            case "Limite Cuotas":
                txtTipoConvenio.setVisible(true);
                cmbFechaTipoConvenio.setVisible(false);
                lblTipoConvenio.setText(ConstantesEtiquetas.CUOTAS);
                break;
            case "Limite Fecha":
                txtTipoConvenio.setVisible(false);
                cmbFechaTipoConvenio.setVisible(true);
                lblTipoConvenio.setText(ConstantesEtiquetas.FECHA_UPPER);
                break;
            case "Limite Monto":
                txtTipoConvenio.setVisible(true);
                cmbFechaTipoConvenio.setVisible(false);
                lblTipoConvenio.setText(ConstantesEtiquetas.MONTO);
                break;
        }
    }

    public void calculaTipoConveniotext() {
        ControlVentana cv = new ControlVentana();
        switch (cmbTipoConvenio.getValue()) {
            case "Limite Cuotas":
                if (!txtTipoConvenio.getText().isEmpty()) {
                    try {
                        cuotas = Double.valueOf(txtTipoConvenio.getText());
                        double total = deudaPesos;
                        if (!txtSaldoInicial.getText().isEmpty()) {
                            saldoInicial = Integer.valueOf(txtSaldoInicial.getText());
                            double monedaSaldo = saldoInicial;
                            total -= monedaSaldo;
                        }
                        double informacion = total / cuotas;
                        //formatea el valor a 2 digitos
                        DecimalFormat df = new DecimalFormat(Constantes.DECIMAL_FORMAT);
                        lblCuotas.setText(ConstantesEtiquetas.CUOTA_APROXIMADA + df.format(informacion));
                    } catch (Exception ex) {
                        cv.creaVentanaNotificacionError(ConstantesErrores.VALOR_NUMERICO);
                    }
                } else {
                    cv.creaVentanaNotificacionError(ConstantesErrores.NO_VACIO);
                }
                break;
            case "Limite Fecha":
                if (cmbFechaTipoConvenio.getValue() != null) {
                    int currentyear = Calendar.getInstance().get(Calendar.YEAR);
                    int currentmonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                    Calendar cal = Calendar.getInstance();
                    Date date = UtilsGenerals.TraeFecha(cmbFechaTipoConvenio.getValue());
                    cal.setTime(date);
                    int finalYear = cal.get(Calendar.YEAR);
                    int finalMonth = cal.get(Calendar.MONTH) + 1;
                    cuotas = UtilsGenerals.devuelveCuotas(currentmonth, finalMonth, currentyear, finalYear);
                    double monto = deudaPesos / (double) cuotas;
                    DecimalFormat df = new DecimalFormat(Constantes.DECIMAL_FORMAT);
                    lblCuotas.setText((int) cuotas + ConstantesEtiquetas.CUOTA_APROXIMADA + df.format(monto));
                } else {
                    cv.creaVentanaNotificacionError(ConstantesErrores.NO_FECHA);
                }
                break;
            case "Limite Monto":
                if (!txtTipoConvenio.getText().isEmpty()) {
                    try {
                        double monto = Integer.valueOf(txtTipoConvenio.getText());
                        double total = deudaPesos;
                        if (!txtSaldoInicial.getText().isEmpty()) {
                            saldoInicial = Integer.valueOf(txtSaldoInicial.getText());
                            double monedaSaldo = saldoInicial;
                            total -= monedaSaldo;
                        }
                        //redondea hacia arriba siempre
                        cuotas = (int) Math.ceil(total / monto);
                        lblCuotas.setText(ConstantesEtiquetas.CUOTAS_APROX + (int) cuotas);
                    } catch (Exception ex) {
                        cv.creaVentanaNotificacionError(ConstantesErrores.VALOR_NUMERICO);
                    }
                } else {
                    cv.creaVentanaNotificacionError(ConstantesErrores.NO_VACIO);
                }
                break;
        }
    }

    public void cargarComboTorre() {
        ObservableList<Integer> listaTorres;
        listaTorres = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
        cmbTorre.setItems(listaTorres);
    }

    public void agregarGastosComunes() throws InterruptedException {

        switch (paneActual) {
            case 1:
                btnStepAdelante.setDisable(false);
                btnStepAtras.setDisable(true);
                imgProgressTracker.setImage(null);
                new FadeInUpTransition(imgProgressTracker).play();
                imgProgressTracker.setImage(img1);
                break;
            case 2:
                btnStepAdelante.setDisable(false);
                btnStepAtras.setDisable(false);
                imgProgressTracker.setImage(null);
                new FadeInUpTransition(imgProgressTracker).play();
                imgProgressTracker.setImage(img2);
                break;
            case 3:
                btnStepAdelante.setDisable(true);
                btnStepAtras.setDisable(false);
                imgProgressTracker.setImage(null);
                new FadeInUpTransition(imgProgressTracker).play();
                imgProgressTracker.setImage(img3);
                break;
        }
        manageSteps();
    }

    public void manageSteps() {
        switch (paneActual) {
            case 1:
                paneStep2.setOpacity(0);
                paneStep3.setOpacity(0);
                new FadeInUpTransition(paneStep1).play();
                break;
            case 2:
                paneStep1.setOpacity(0);
                paneStep3.setOpacity(0);
                new FadeInUpTransition(paneStep2).play();
                break;
            case 3:
                paneStep1.setOpacity(0);
                paneStep2.setOpacity(0);
                new FadeInUpTransition(paneStep3).play();
                break;
        }
    }

    public void btnAdelante() throws InterruptedException {

        switch (paneActual) {
            case 1:
                lblInfo.setText(ConstantesEtiquetas.VACIO);
                paneActual = 2;
                unidad = tblUnidades.getSelectionModel().getSelectedItem();
                txtUnidad.setText(unidad.getNombre() + ConstantesEtiquetas.ESPACIO + unidad.getApellido());
                txtUnidad2.setText(unidad.getNombre() + ConstantesEtiquetas.ESPACIO + unidad.getApellido());
//                deudaPesos = unidadDAO.TraeTotalImporteXUnidadParaConvenio(unidad);
                lblDeudaTotal.setText(String.valueOf(deudaPesos));
                agregarGastosComunes();
                break;
            case 2:
                paneActual = 3;
                agregarGastosComunes();
                break;
        }
    }

    public void btnAtras() throws InterruptedException {
        switch (paneActual) {
            case 3:
                paneActual = 2;
                agregarGastosComunes();
                break;
            case 2:
                paneActual = 1;
                agregarGastosComunes();
                break;
        }
    }

    public void atras() {
        paneStep2.setOpacity(0);
        paneStep3.setOpacity(0);
        new FadeInUpTransition(paneStep1).play();
        paneActual = 1;
        btnStepAdelante.setDisable(false);
        btnStepAtras.setDisable(true);
        imgProgressTracker.setImage(null);
        new FadeInUpTransition(imgProgressTracker).play();
        imgProgressTracker.setImage(img1);
    }

    public void cargaComboMonto() throws ServiceException {
        ObservableList<Monto> montos = FXCollections.observableArrayList(montoDAO.getMontos());
        cmbMoneda.setItems(montos);
        cmbMoneda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                monedaCombo();
            }

        });
        cmbMoneda.getSelectionModel().selectFirst();
    }

    public void cargaComboReglaBonificacion() throws ServiceException {
        ObservableList<ReglaBonificacion> bonificaciones = FXCollections.observableArrayList(reglaBonificacionDAO.getReglasBonificacion());
        cmbReglaBonificacion.setItems(bonificaciones);
        cmbReglaBonificacion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                reglaBonificacion = cmbReglaBonificacion.getValue();
            }

        });
    }

    public void cargaTabla() {
        TableColumn Nombre = new TableColumn(ConstantesEtiquetas.NOMBRE);
        TableColumn Apellido = new TableColumn(ConstantesEtiquetas.APELLIDO);
        TableColumn Block = new TableColumn(ConstantesEtiquetas.BLOCK);
        TableColumn Torre = new TableColumn(ConstantesEtiquetas.TORRE);
        TableColumn Puerta = new TableColumn(ConstantesEtiquetas.PUERTA);

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

        tblUnidades.getColumns().addAll(Nombre, Apellido, Block, Torre, Puerta);
//        List<Unidad> lista = unidadDAO.TraeUnidadesConvenio();
//        List<Unidad> lista = unidadDAO.traeUnidadesXEstadoXBlockXTorre(null, null, Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, Constantes.SIN_EDIFICIO);
//        listaUnidades = FXCollections.observableList(lista);
        lblInfo.setText(String.valueOf(listaUnidades.size()));
        tblUnidades.setItems(listaUnidades);
    }

    public void monedaCombo() {
        monto = cmbMoneda.getSelectionModel().getSelectedItem();
        lblSimbolo.setText(monto.getSimbolo());
        if (monto.getValorPesos() != 1) {
            deudaOtraMoneda = deudaPesos * monto.getValorPesos();
            DecimalFormat df = new DecimalFormat(Constantes.DECIMAL_FORMAT);
            lblDeudaTotal.setText(df.format(deudaOtraMoneda));
        } else {
            lblDeudaTotal.setText(String.valueOf(deudaPesos));
        }

    }

    public void mostrar(ActionEvent event) {
        lblInfo.setText(ConstantesEtiquetas.VACIO);
        try {
            lblInfo.setText(ConstantesEtiquetas.VACIO);
            List<Unidad> listaTorreBlock;
            if (cmbBlock.getValue() != null) {
                if (cmbTorre.getValue() != null) {
//                    listaTorreBlock = unidadDAO.traeUnidadesXEstadoXBlockXTorre(cmbBlock.getValue(), cmbTorre.getValue(), Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, false);
                } else {
//                    listaTorreBlock = unidadDAO.traeUnidadesXEstadoXBlockXTorre(cmbBlock.getValue(), null, Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, false);
                }
            } else if (cmbTorre.getValue() != null) {
//                listaTorreBlock = unidadDAO.traeUnidadesXEstadoXBlockXTorre(null, cmbTorre.getValue(), Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, false);
            } else {
//                listaTorreBlock = unidadDAO.traeUnidadesXEstadoXBlockXTorre(null, null, Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, false);
            }
//            listaUnidades = FXCollections.observableList(listaTorreBlock);
            tblUnidades.setItems(null);
            lblInfo.setText(String.valueOf(listaUnidades.size()));
            tblUnidades.setItems(listaUnidades);
        } catch (Exception ex) {
            lblInfo.setText(ConstantesErrores.BLOCKYTORRE);
        }
    }

    public void mostrarTodos() {
        lblInfo.setText(ConstantesEtiquetas.VACIO);
//        List<Unidad> listaTotal = unidadDAO.traeUnidadesXEstadoXBlockXTorre(null, null, Constantes.IN, Constantes.NO_PAGO, ConfiguracionControl.devuelvePeriodoActual(), Constantes.COMPARA_LESS_THAN, false);
//        listaUnidades = FXCollections.observableList(listaTotal);
        lblInfo.setText(String.valueOf(listaUnidades.size()));
        tblUnidades.setItems(null);
        tblUnidades.setItems(listaUnidades);
    }

    public void guardaConvenio() {
        ControlVentana cv = new ControlVentana();
        boolean correcto = true;
//        try {
            if (cmbTipoConvenio.getSelectionModel().getSelectedItem() != null) {
                if (cmbTipoConvenio.getSelectionModel().getSelectedItem().equals("Limite Cuotas") || cmbTipoConvenio.getSelectionModel().getSelectedItem().equals("Limite Monto")) {
                    if (txtTipoConvenio.getText().isEmpty()) {
                        correcto = false;
//                        ConfiguracionControl.notifier.notify(new Notification(ConstantesEtiquetas.ERROR, ConstantesErrores.SIN_CUOTA, Notification.ERROR_ICON));
                    }
                } else {
                    if (cmbFechaTipoConvenio.getValue() == null) {
                        correcto = false;
//                        ConfiguracionControl.notifier.notify(new Notification(ConstantesEtiquetas.ERROR, ConstantesErrores.SIN_FECHA, Notification.ERROR_ICON));
                    }
                }
                if (correcto) {
                    calculaTipoConveniotext();
                    Convenio convenio = new Convenio();
                    convenio.setActivo(chkActivo.isSelected());
                    convenio.setCuotas((int) cuotas);
                    if (!txtDescripcion.getText().isEmpty()) {
                        convenio.setDescripcion(txtDescripcion.getText());
                    }
                    if (cmbMoneda.getValue().getSimbolo().equals(ConstantesEtiquetas.PESOS)) {
                        double deu = deudaPesos;
                        convenio.setDeudaTotal((int) deu);
                    } else {
                        convenio.setDeudaTotal((int) deudaOtraMoneda);
                    }
                    convenio.setMonto(monto);
                    convenio.setReglabonificacion(reglaBonificacion);
                    convenio.setSaldoInicial(saldoInicial);
                    convenio.setUnidad(unidad);
                    convenioDAO.saveConvenio(convenio);
                    HashMap parameters = new HashMap();
                    parameters.put(ConstantesEtiquetas.ID_USUARIO, convenio.getUnidad().getId());
//                    cc.generarReporteConParametros(ConstantesEtiquetas.CONVENIO_IMPRESION, parameters);
                    cv.creaVentanaNotificacionCorrecto();
                }
            } else {
//                ConfiguracionControl.notifier.notify(new Notification(ConstantesEtiquetas.ERROR, ConstantesErrores.SIN_TIPO_CONVENIO, Notification.ERROR_ICON));
            }

//        } catch (ServiceException ex) {
//            cv.creaVentanaNotificacionError(ex.getMessage());
//            Logger.getLogger(ConveniosMB.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
