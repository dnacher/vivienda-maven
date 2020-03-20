package view.MB;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import persistence.constantes.Constantes;
import persistence.constantes.ConstantesEtiquetas;
import viviendas.Viviendas;

public class MainMB implements Initializable { 
    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button resize;
    @FXML
    private Button fullscreen;      
    
    Stage stage;
    Rectangle2D rec2;
    Double w,h;
    @FXML
    private ListView<String> listMenu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    };    
        
    
    @FXML
    private void aksiMaximized(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove(ConstantesEtiquetas.DECORATION_BUTTON_RESTORE);
                resize.setVisible(true);
            }else{
                stage.setMaximized(false);
                maximize.getStyleClass().remove(ConstantesEtiquetas.DECORATION_BUTTON_RESTORE);
                resize.setVisible(true);
            }
            
        }else{
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add(ConstantesEtiquetas.DECORATION_BUTTON_RESTORE);
            resize.setVisible(false);
        }
    }

    @FXML
    private void aksiminimize(ActionEvent event) {
        stage = (Stage) minimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        }else{
            stage.setIconified(true);
        }        
    }

    @FXML
    private void aksiResize(ActionEvent event) {
    }

    @FXML
    private void aksifullscreen(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void aksiClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void theListMenu(MouseEvent event) {        
        creaRuta(listMenu.getSelectionModel().getSelectedItem());        
    }
    
    public String creaRuta(String ruta){
        String rutaNueva=ConstantesEtiquetas.VACIO;
        rutaNueva= Constantes.PAGINA_ROOT + ruta.trim() + Constantes.EXTENSION_FXML;
        System.out.println(rutaNueva);
        return rutaNueva;
    }

    @FXML
    private void aksiLogout(ActionEvent event) {      
        Viviendas.user=null;        
    }
    
}
