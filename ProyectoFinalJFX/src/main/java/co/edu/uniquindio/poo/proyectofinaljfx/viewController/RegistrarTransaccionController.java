package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.App;
import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MetodoPago;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Oferta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrarTransaccionController {

    @FXML private Label lblInmueble;
    @FXML private Label lblComprador;
    @FXML private Label lblMonto;
    @FXML private Label lblTipoOperacion;
    @FXML private ComboBox<MetodoPago> cbMetodoPago;
    @FXML private TextField txtComision;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        cbMetodoPago.getItems().setAll(MetodoPago.values());
        Oferta oferta = controller.getOfertaSeleccionada();

        if (oferta == null) {
            lblInmueble.setText("Sin oferta seleccionada");
            return;
        }

        lblInmueble.setText(oferta.getInmueble().getNombre());
        lblComprador.setText(oferta.getComprador().getNombre());
        lblMonto.setText(String.valueOf(oferta.getMontoOfrecido()));
        lblTipoOperacion.setText(String.valueOf(oferta.getInmueble().getTipoOferta()));
        txtComision.setText(String.valueOf(oferta.calcularComisionInmobiliaria(0.03)));
    }

    @FXML
    private void confirmar(ActionEvent event) {
        try {
            if (cbMetodoPago.getValue() == null) {
                mostrarAlerta(Alert.AlertType.WARNING, "Metodo requerido", "Seleccione un metodo de pago.");
                return;
            }

            controller.registrarTransaccionOferta(cbMetodoPago.getValue(), Double.parseDouble(txtComision.getText().trim()));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Transaccion registrada", "La transaccion fue registrada correctamente.");
            cambiarVista(event, "Dashboard.fxml", "InmoSmart");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Comision invalida", "La comision debe ser un numero valido.");
        } catch (IllegalStateException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "No fue posible registrar", e.getMessage());
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "OfertasRecibidas.fxml", "Ofertas recibidas");
    }

    private void cambiarVista(ActionEvent event, String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinaljfx/" + fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            App.cambiarVista(stage, root, titulo);
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegacion", "No fue posible cargar la vista " + fxml + ".");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

