package co.edu.uniquindio.poo.proyectofinaljfx.Controller;

import co.edu.uniquindio.poo.proyectofinaljfx.model.Comprador;
import co.edu.uniquindio.poo.proyectofinaljfx.model.InmoSmart;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MiembroInmobiliario;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Vendedor;

import java.util.UUID;

public class Controller {

    private static final Controller INSTANCIA = new Controller();

    private final InmoSmart inmoSmart;
    private MiembroInmobiliario usuarioActual;

    private Controller() {
        this.inmoSmart = new InmoSmart("InmoSmart");
    }

    public static Controller getInstancia() {
        return INSTANCIA;
    }

    public MiembroInmobiliario iniciarSesion(String userName, String contrasenia) {
        usuarioActual = inmoSmart.iniciarSesion(userName, contrasenia);
        return usuarioActual;
    }

    public Comprador registrarComprador(String nombre, String identificacion, String telefono, String correo,
                                        String userName, String contrasenia) {
        validarUsuarioDisponible(identificacion, userName);

        Comprador comprador = new Comprador(
                generarId(),
                nombre,
                identificacion,
                telefono,
                correo,
                userName,
                contrasenia,
                inmoSmart
        );
        inmoSmart.registrarUsuario(comprador);
        return comprador;
    }

    public Vendedor registrarVendedor(String nombre, String identificacion, String telefono, String correo,
                                      String userName, String contrasenia) {
        validarUsuarioDisponible(identificacion, userName);

        Vendedor vendedor = new Vendedor(
                generarId(),
                nombre,
                identificacion,
                telefono,
                correo,
                userName,
                contrasenia,
                inmoSmart
        );
        inmoSmart.registrarUsuario(vendedor);
        return vendedor;
    }

    public MiembroInmobiliario getUsuarioActual() {
        return usuarioActual;
    }

    public InmoSmart getInmoSmart() {
        return inmoSmart;
    }

    private String generarId() {
        return UUID.randomUUID().toString();
    }

    private void validarUsuarioDisponible(String identificacion, String userName) {
        for (MiembroInmobiliario miembro : inmoSmart.getListaUsuarios()) {
            if (miembro.getIdentificacion().equals(identificacion)) {
                throw new IllegalArgumentException("Ya existe un usuario con esa identificacion.");
            }

            if (miembro.getUsuario() != null && miembro.getUsuario().getUserName().equals(userName)) {
                throw new IllegalArgumentException("Ya existe un usuario con ese nombre de usuario.");
            }
        }
    }
}
