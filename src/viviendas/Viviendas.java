package viviendas;

import exceptions.ServiceException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import persistence.constantes.Constantes;
import persistence.entities.TipoUsuario;
import persistence.entities.Usuario;
import persistence.entities.PermisoUsuario;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Viviendas extends Application {

    public static Usuario user;
    public static boolean confirmacion = false;
	public static List<PermisoUsuario> listaPermisos;

    public static TipoUsuario getTipoUsuario() {
        return user.getTipousuario();
    }

    @Override
    public void start(Stage stage) throws ServiceException {

        try {
            Parent root;
            URL url = getClass().getResource(Constantes.PAGINA_ROOT + "splash.fxml");
            root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream(Constantes.LOGO)));
            stage.show();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),
                    new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage.hide();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource(Constantes.PAGINA_LOGIN));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception ex) {
                        Logger.getLogger(Viviendas.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }));
            timeline.play();
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
