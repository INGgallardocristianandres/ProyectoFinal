package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.App;
import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.EstadoOferta;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Oferta;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class OfertasRecibidasController {

    @FXML private TableView<Oferta> tblOfertas;
    @FXML private TableColumn<Oferta, String> colId;
    @FXML private TableColumn<Oferta, String> colInmueble;
    @FXML private TableColumn<Oferta, String> colComprador;
    @FXML private TableColumn<Oferta, Double> colMonto;
    @FXML private TableColumn<Oferta, LocalDate> colFecha;
    @FXML private TableColumn<Oferta, Object> colEstado;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIdOferta()));
        colInmueble.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInmueble().getNombre()));
        colComprador.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getComprador().getNombre()));
        colMonto.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMontoOfrecido()));
        colFecha.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFecha()));
        colEstado.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEstadoOferta()));
        refrescar();
    }

    @FXML
    private void aceptar() {
        Oferta oferta = obtenerSeleccionada();
        if (oferta == null) {
            return;
        }

        controller.aceptarOferta(oferta);
        refrescar();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Oferta aceptada", "La oferta fue aceptada. Puede registrar la transaccion.");
    }

    @FXML
    private void rechazar() {
        Oferta oferta = obtenerSeleccionada();
        if (oferta == null) {
            return;
        }

        controller.rechazarOferta(oferta);
        refrescar();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Oferta rechazada", "La oferta fue rechazada.");
    }

    @FXML
    private void contrapropuesta() {
        Oferta oferta = obtenerSeleccionada();
        if (oferta == null) {
            return;
        }

        oferta.setEstadoOferta(EstadoOferta.CONTRAPROPUESTA);
        refrescar();
        mostrarAlerta(Alert.AlertType.INFORMATION, "Contrapropuesta", "La oferta quedo marcada como contrapropuesta.");
    }

    @FXML
    private void registrarTransaccion(ActionEvent event) {
        Oferta oferta = obtenerSeleccionada();
        if (oferta == null) {
            return;
        }

        if (oferta.getEstadoOferta() != EstadoOferta.ACEPTADA) {
            mostrarAlerta(Alert.AlertType.WARNING, "Oferta no aceptada", "Primero debe aceptar la oferta antes de registrar la transaccion.");
            return;
        }

        controller.seleccionarOferta(oferta);
        cambiarVista(event, "RegistrarTransaccion.fxml", "Registrar transaccion");
    }

    @FXML
    private void refrescar() {
        tblOfertas.getItems().setAll(controller.obtenerOfertasRecibidas());
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "Dashboard.fxml", "InmoSmart");
    }

    private Oferta obtenerSeleccionada() {
        Oferta oferta = tblOfertas.getSelectionModel().getSelectedItem();
        if (oferta == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione una oferta", "Debe seleccionar una oferta de la tabla.");
        }
        return oferta;
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

