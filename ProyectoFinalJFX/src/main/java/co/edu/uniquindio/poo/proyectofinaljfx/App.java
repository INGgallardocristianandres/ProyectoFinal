package co.edu.uniquindio.poo.proyectofinaljfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static final double ANCHO_VENTANA = 1100;
    public static final double ALTO_VENTANA = 720;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Inicio de sesión");
        cambiarVista(stage, root, "Inicio de sesion");
        stage.show();
    }

    public static void cambiarVista(Stage stage, Parent root, String titulo) {
        stage.setTitle(titulo);

        if (stage.getScene() == null) {
            stage.setScene(new Scene(root, ANCHO_VENTANA, ALTO_VENTANA));
        } else {
            stage.getScene().setRoot(root);
        }

        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
}
