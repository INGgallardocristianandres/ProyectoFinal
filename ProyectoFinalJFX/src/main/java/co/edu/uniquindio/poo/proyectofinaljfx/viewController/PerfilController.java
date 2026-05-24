package co.edu.uniquindio.poo.proyectofinaljfx.viewController;

import co.edu.uniquindio.poo.proyectofinaljfx.App;
import co.edu.uniquindio.poo.proyectofinaljfx.Controller.Controller;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Comprador;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MiembroInmobiliario;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilController {

    @FXML private Label lblNombre;
    @FXML private Label lblRol;
    @FXML private Label lblIdentificacion;
    @FXML private Label lblUsuario;
    @FXML private Label lblCorreo;
    @FXML private Label lblTelefono;
    @FXML private Label lblFechaRegistro;
    @FXML private Label lblPuntos;
    @FXML private Label lblRango;
    @FXML private Label lblBeneficio;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        MiembroInmobiliario usuario = controller.getUsuarioActual();
        if (usuario == null) {
            limpiarPerfil();
            return;
        }

        lblNombre.setText(usuario.getNombre());
        lblRol.setText(obtenerRol(usuario));
        lblIdentificacion.setText(usuario.getIdentificacion());
        lblUsuario.setText(usuario.getUsuario() != null ? usuario.getUsuario().getUserName() : "-");
        lblCorreo.setText(usuario.getCorreo());
        lblTelefono.setText(usuario.getTelefono());
        lblFechaRegistro.setText(String.valueOf(usuario.getFechaRegistro()));
        lblPuntos.setText(String.valueOf(usuario.getPuntosReputacion()));
        lblRango.setText(String.valueOf(usuario.getRangoUsuario()));
        lblBeneficio.setText(formatearPorcentaje(usuario.calcularBeneficioPorRango()));
    }

    @FXML
    private void volver(ActionEvent event) {
        cambiarVista(event, "Dashboard.fxml", "InmoSmart");
    }

    private String obtenerRol(MiembroInmobiliario usuario) {
        if (usuario instanceof Comprador) {
            return "Comprador";
        }

        if (usuario instanceof Vendedor) {
            return "Vendedor";
        }

        return "Usuario";
    }

    private String formatearPorcentaje(double valor) {
        return String.format("%.2f%%", valor * 100);
    }

    private void limpiarPerfil() {
        lblNombre.setText("-");
        lblRol.setText("-");
        lblIdentificacion.setText("-");
        lblUsuario.setText("-");
        lblCorreo.setText("-");
        lblTelefono.setText("-");
        lblFechaRegistro.setText("-");
        lblPuntos.setText("-");
        lblRango.setText("-");
        lblBeneficio.setText("-");
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

