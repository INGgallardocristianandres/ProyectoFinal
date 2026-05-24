package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Inmueble;
import co.edu.uniquindio.poo.proyectofinaljfx.model.TipoInmueble;
import co.edu.uniquindio.poo.proyectofinaljfx.model.TipoOferta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PublicarInmuebleController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtDireccion;
    @FXML private ComboBox<TipoInmueble> cbTipoInmueble;
    @FXML private ComboBox<TipoOferta> cbTipoOferta;
    @FXML private TextField txtValor;
    @FXML private TextField txtArea;
    @FXML private TextArea txtDescripcion;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        cbTipoInmueble.getItems().setAll(TipoInmueble.values());
        cbTipoOferta.getItems().setAll(TipoOferta.values());
    }

    @FXML
    private void guardarInmueble() {
        try {
            validarCampos();

            Inmueble inmueble = new Inmueble(
                    txtCodigo.getText().trim(),
                    txtNombre.getText().trim(),
                    txtCiudad.getText().trim(),
                    txtDireccion.getText().trim(),
                    cbTipoInmueble.getValue(),
                    cbTipoOferta.getValue(),
                    Double.parseDouble(txtValor.getText().trim()),
                    Double.parseDouble(txtArea.getText().trim()),
                    txtDescripcion.getText().trim()
            );

            controller.publicarInmueble(inmueble);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Inmueble publicado", "El inmueble fue agregado al inventario.");
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Valores invalidos", "Valor y area deben ser numeros validos.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "No fue posible publicar", e.getMessage());
        }
    }

    @FXML
    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCiudad.clear();
        txtDireccion.clear();
        cbTipoInmueble.setValue(null);
        cbTipoOferta.setValue(null);
        txtValor.clear();
        txtArea.clear();
        txtDescripcion.clear();
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "Dashboard.fxml", "InmoSmart");
    }

    private void validarCampos() {
        if (estaVacio(txtCodigo.getText()) || estaVacio(txtNombre.getText()) || estaVacio(txtCiudad.getText())
                || estaVacio(txtDireccion.getText()) || cbTipoInmueble.getValue() == null
                || cbTipoOferta.getValue() == null || estaVacio(txtValor.getText())
                || estaVacio(txtArea.getText()) || estaVacio(txtDescripcion.getText())) {
            throw new IllegalArgumentException("Debe completar todos los campos.");
        }

        if (Double.parseDouble(txtValor.getText().trim()) <= 0 || Double.parseDouble(txtArea.getText().trim()) <= 0) {
            throw new IllegalArgumentException("Valor y area deben ser mayores que cero.");
        }
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
