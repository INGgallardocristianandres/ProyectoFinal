package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtCorreoRegistro;

    @FXML
    private TextField txtUsuarioRegistro;

    @FXML
    private TextField txtTelefono;

    @FXML
    private PasswordField txtPasswordRegistro;

    @FXML
    private PasswordField txtConfirmarPassword;

    @FXML
    private RadioButton rbComprador;

    @FXML
    private RadioButton rbVendedor;

    @FXML
    private Hyperlink hplInicioSesion;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        hplInicioSesion.setOnAction(this::irALogin);
    }

    @FXML
    private void registrar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String correo = txtCorreoRegistro.getText();
        String userName = txtUsuarioRegistro.getText();
        String telefono = txtTelefono.getText();
        String contrasenia = txtPasswordRegistro.getText();
        String confirmarContrasenia = txtConfirmarPassword.getText();

        if (estaVacio(nombre) || estaVacio(identificacion) || estaVacio(correo)
                || estaVacio(userName) || estaVacio(telefono)
                || estaVacio(contrasenia) || estaVacio(confirmarContrasenia)) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe completar todos los campos.");
            return;
        }

        if (!contrasenia.equals(confirmarContrasenia)) {
            mostrarAlerta(Alert.AlertType.WARNING, "Contrasenas diferentes", "La confirmacion no coincide con la contrasena.");
            return;
        }

        if (!rbComprador.isSelected() && !rbVendedor.isSelected()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Rol requerido", "Seleccione si el usuario es comprador o vendedor.");
            return;
        }

        try {
            if (rbComprador.isSelected()) {
                controller.registrarComprador(
                        nombre.trim(),
                        identificacion.trim(),
                        telefono.trim(),
                        correo.trim(),
                        userName.trim(),
                        contrasenia
                );
            } else {
                controller.registrarVendedor(
                        nombre.trim(),
                        identificacion.trim(),
                        telefono.trim(),
                        correo.trim(),
                        userName.trim(),
                        contrasenia
                );
            }
        } catch (IllegalArgumentException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Registro no disponible", e.getMessage());
            return;
        }

        mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso", "La cuenta fue creada. Use su nombre de usuario para iniciar sesion.");
        cambiarVista(event, "Login.fxml", "Inicio de sesion");
    }

    private void irALogin(ActionEvent event) {
        cambiarVista(event, "Login.fxml", "Inicio de sesion");
    }

    private void cambiarVista(ActionEvent event, String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinaljfx/" + fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegacion", "No fue posible cargar la vista " + fxml + ".");
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
