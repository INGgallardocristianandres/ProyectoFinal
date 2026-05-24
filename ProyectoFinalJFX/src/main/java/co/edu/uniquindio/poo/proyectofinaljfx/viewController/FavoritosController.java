package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.App;
import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Inmueble;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class FavoritosController {

    @FXML private TableView<Inmueble> tblFavoritos;
    @FXML private TableColumn<Inmueble, String> colCodigo;
    @FXML private TableColumn<Inmueble, String> colNombre;
    @FXML private TableColumn<Inmueble, String> colCiudad;
    @FXML private TableColumn<Inmueble, Object> colTipo;
    @FXML private TableColumn<Inmueble, Object> colOferta;
    @FXML private TableColumn<Inmueble, Double> colValor;
    @FXML private TableColumn<Inmueble, Object> colEstado;
    @FXML private TextArea txtDetalle;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colCiudad.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCiudad()));
        colTipo.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTipoInmueble()));
        colOferta.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTipoOferta()));
        colValor.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getValor()));
        colEstado.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEstado()));

        tblFavoritos.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> mostrarDetalle(selected));
        refrescar();
    }

    @FXML
    private void refrescar() {
        tblFavoritos.getItems().setAll(controller.obtenerFavoritos());
    }

    @FXML
    private void realizarOferta() {
        Inmueble inmueble = tblFavoritos.getSelectionModel().getSelectedItem();
        if (inmueble == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Seleccione un inmueble", "Debe seleccionar un favorito para ofertar.");
            return;
        }

        TextInputDialog montoDialog = new TextInputDialog();
        montoDialog.setTitle("Realizar oferta");
        montoDialog.setHeaderText(null);
        montoDialog.setContentText("Monto ofrecido:");
        Optional<String> monto = montoDialog.showAndWait();
        if (monto.isEmpty()) {
            return;
        }

        TextInputDialog mensajeDialog = new TextInputDialog();
        mensajeDialog.setTitle("Realizar oferta");
        mensajeDialog.setHeaderText(null);
        mensajeDialog.setContentText("Mensaje para el vendedor:");
        Optional<String> mensaje = mensajeDialog.showAndWait();
        if (mensaje.isEmpty()) {
            return;
        }

        try {
            controller.realizarOferta(inmueble, Double.parseDouble(monto.get().trim()), mensaje.get().trim());
            mostrarAlerta(Alert.AlertType.INFORMATION, "Oferta enviada", "La oferta fue registrada correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Monto invalido", "El monto debe ser un numero valido.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "No fue posible ofertar", e.getMessage());
        }
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "Dashboard.fxml", "InmoSmart");
    }

    private void mostrarDetalle(Inmueble inmueble) {
        if (inmueble == null) {
            txtDetalle.clear();
            return;
        }

        txtDetalle.setText(
                "Codigo: " + inmueble.getCodigo() + "\n"
                        + "Nombre: " + inmueble.getNombre() + "\n"
                        + "Ciudad: " + inmueble.getCiudad() + "\n"
                        + "Direccion: " + inmueble.getDireccion() + "\n"
                        + "Tipo: " + inmueble.getTipoInmueble() + "\n"
                        + "Oferta: " + inmueble.getTipoOferta() + "\n"
                        + "Valor: " + inmueble.getValor() + "\n"
                        + "Area: " + inmueble.getArea() + "\n"
                        + "Estado: " + inmueble.getEstado() + "\n\n"
                        + inmueble.getDescripcion()
        );
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

