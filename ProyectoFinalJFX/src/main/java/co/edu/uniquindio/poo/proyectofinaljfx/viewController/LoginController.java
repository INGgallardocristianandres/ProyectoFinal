package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MiembroInmobiliario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtCorreoUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Hyperlink hplRegistro;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        hplRegistro.setOnAction(this::irARegistro);
    }

    @FXML
    private void ingresar(ActionEvent event) {
        String usuario = txtCorreoUsuario.getText();
        String contrasenia = txtPassword.getText();

        if (estaVacio(usuario) || estaVacio(contrasenia)) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Ingrese usuario y contraseña.");
            return;
        }

        MiembroInmobiliario miembro = controller.iniciarSesion(usuario.trim(), contrasenia);
        if (miembro == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Credenciales incorrectas", "El usuario o la contraseña no son válidos.");
            return;
        }

        cambiarVista(event, "Dashboard.fxml", "InmoSmart");
    }

    private void irARegistro(ActionEvent event) {
        cambiarVista(event, "Registro.fxml", "Crear cuenta");
    }

    private void cambiarVista(ActionEvent event, String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinaljfx/" + fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegación", "No fue posible cargar la vista " + fxml + ".");
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
