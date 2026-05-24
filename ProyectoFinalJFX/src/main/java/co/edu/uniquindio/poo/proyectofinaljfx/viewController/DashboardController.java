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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private TextField txtBuscar;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private Label lblRolUsuario;

    @FXML
    private Label lblTituloDashboard;

    @FXML
    private Label lblSubtituloDashboard;

    @FXML
    private Button btnCatalogoMenu;

    @FXML
    private Button btnFavoritosMenu;

    @FXML
    private Button btnOfertasCompradorMenu;

    @FXML
    private Button btnPublicarMenu;

    @FXML
    private Button btnMisInmueblesMenu;

    @FXML
    private Button btnOfertasVendedorMenu;

    @FXML
    private VBox cardCatalogo;

    @FXML
    private VBox cardFavoritos;

    @FXML
    private VBox cardOfertasComprador;

    @FXML
    private VBox cardPublicar;

    @FXML
    private VBox cardMisInmuebles;

    @FXML
    private VBox cardOfertasVendedor;

    private final Controller controller = Controller.getInstancia();

    @FXML
    private void initialize() {
        MiembroInmobiliario usuarioActual = controller.getUsuarioActual();

        if (usuarioActual == null) {
            configurarInvitado();
            return;
        }

        lblNombreUsuario.setText(usuarioActual.getNombre());

        if (usuarioActual instanceof Comprador) {
            configurarComprador();
        } else if (usuarioActual instanceof Vendedor) {
            configurarVendedor();
        } else {
            configurarInvitado();
        }
    }

    @FXML
    private void abrirCatalogo() {
        cambiarVista("Catalogo.fxml", "Catalogo de inmuebles");
    }

    @FXML
    private void abrirFavoritos() {
        cambiarVista("Favoritos.fxml", "Favoritos");
    }

    @FXML
    private void abrirOfertasComprador() {
        cambiarVista("OfertasComprador.fxml", "Mis ofertas");
    }

    @FXML
    private void abrirPublicarInmueble() {
        cambiarVista("PublicarInmueble.fxml", "Publicar inmueble");
    }

    @FXML
    private void abrirMisInmuebles() {
        cambiarVista("MisInmuebles.fxml", "Mis inmuebles");
    }

    @FXML
    private void abrirOfertasRecibidas() {
        cambiarVista("OfertasRecibidas.fxml", "Ofertas recibidas");
    }

    @FXML
    private void abrirPerfil() {
        cambiarVista("Perfil.fxml", "Perfil");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        controller.cerrarSesion();
        cambiarVista(event, "Login.fxml", "Inicio de sesion");
    }

    private void configurarComprador() {
        lblRolUsuario.setText("Comprador");
        lblTituloDashboard.setText("Panel de comprador");
        lblSubtituloDashboard.setText("Explora inmuebles, guarda favoritos y revisa tus ofertas.");
        txtBuscar.setPromptText("Buscar inmuebles por ciudad, tipo o codigo");

        mostrarElemento(btnCatalogoMenu, true);
        mostrarElemento(btnFavoritosMenu, true);
        mostrarElemento(btnOfertasCompradorMenu, true);
        mostrarElemento(btnPublicarMenu, false);
        mostrarElemento(btnMisInmueblesMenu, false);
        mostrarElemento(btnOfertasVendedorMenu, false);

        mostrarElemento(cardCatalogo, true);
        mostrarElemento(cardFavoritos, true);
        mostrarElemento(cardOfertasComprador, true);
        mostrarElemento(cardPublicar, false);
        mostrarElemento(cardMisInmuebles, false);
        mostrarElemento(cardOfertasVendedor, false);
    }

    private void configurarVendedor() {
        lblRolUsuario.setText("Vendedor");
        lblTituloDashboard.setText("Panel de vendedor");
        lblSubtituloDashboard.setText("Publica inmuebles, administra tus propiedades y responde ofertas.");
        txtBuscar.setPromptText("Buscar entre tus inmuebles publicados");

        mostrarElemento(btnCatalogoMenu, false);
        mostrarElemento(btnFavoritosMenu, false);
        mostrarElemento(btnOfertasCompradorMenu, false);
        mostrarElemento(btnPublicarMenu, true);
        mostrarElemento(btnMisInmueblesMenu, true);
        mostrarElemento(btnOfertasVendedorMenu, true);

        mostrarElemento(cardCatalogo, false);
        mostrarElemento(cardFavoritos, false);
        mostrarElemento(cardOfertasComprador, false);
        mostrarElemento(cardPublicar, true);
        mostrarElemento(cardMisInmuebles, true);
        mostrarElemento(cardOfertasVendedor, true);
    }

    private void configurarInvitado() {
        lblNombreUsuario.setText("Invitado");
        lblRolUsuario.setText("Sin sesion");
        lblTituloDashboard.setText("Panel principal");
        lblSubtituloDashboard.setText("Inicie sesion para ver las opciones disponibles.");

        mostrarElemento(btnFavoritosMenu, false);
        mostrarElemento(btnOfertasCompradorMenu, false);
        mostrarElemento(btnPublicarMenu, false);
        mostrarElemento(btnMisInmueblesMenu, false);
        mostrarElemento(btnOfertasVendedorMenu, false);

        mostrarElemento(cardFavoritos, false);
        mostrarElemento(cardOfertasComprador, false);
        mostrarElemento(cardPublicar, false);
        mostrarElemento(cardMisInmuebles, false);
        mostrarElemento(cardOfertasVendedor, false);
    }

    private void mostrarElemento(Node node, boolean visible) {
        node.setVisible(visible);
        node.setManaged(visible);
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

    private void cambiarVista(String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/proyectofinaljfx/" + fxml));
            Stage stage = (Stage) txtBuscar.getScene().getWindow();
            App.cambiarVista(stage, root, titulo);
            stage.show();
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de navegacion", "No fue posible cargar la vista " + fxml + ".");
        }
    }

    private void mostrarPendiente(String titulo, String mensaje) {
        mostrarAlerta(Alert.AlertType.INFORMATION, titulo, mensaje);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

