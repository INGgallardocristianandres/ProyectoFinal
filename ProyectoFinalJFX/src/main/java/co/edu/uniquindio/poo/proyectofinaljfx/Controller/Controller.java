package co.edu.uniquindio.poo.proyectofinaljfx.Controller;

import co.edu.uniquindio.poo.proyectofinaljfx.model.Comprador;
import co.edu.uniquindio.poo.proyectofinaljfx.model.EstadoInmueble;
import co.edu.uniquindio.poo.proyectofinaljfx.model.EstadoOferta;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Inmueble;
import co.edu.uniquindio.poo.proyectofinaljfx.model.InmoSmart;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MetodoPago;
import co.edu.uniquindio.poo.proyectofinaljfx.model.MiembroInmobiliario;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Oferta;
import co.edu.uniquindio.poo.proyectofinaljfx.model.TipoInmueble;
import co.edu.uniquindio.poo.proyectofinaljfx.model.TipoOferta;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Transaccion;
import co.edu.uniquindio.poo.proyectofinaljfx.model.Vendedor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Controller {

    private static final Controller INSTANCIA = new Controller();

    private final InmoSmart inmoSmart;
    private MiembroInmobiliario usuarioActual;
    private Oferta ofertaSeleccionada;

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

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public void publicarInmueble(Inmueble inmueble) {
        if (!(usuarioActual instanceof Vendedor vendedor)) {
            throw new IllegalStateException("Solo un vendedor puede publicar inmuebles.");
        }

        validarCodigoInmuebleDisponible(inmueble.getCodigo());
        vendedor.publicarInmueble(inmueble);
        inmoSmart.agregarInmuebleAlInventario(inmueble);
    }

    public List<Inmueble> obtenerMisInmuebles() {
        if (usuarioActual instanceof Vendedor vendedor) {
            return new ArrayList<>(vendedor.getListaInmuebles());
        }

        return new ArrayList<>();
    }

    public List<Inmueble> obtenerInmueblesCatalogo() {
        return new ArrayList<>(inmoSmart.getInventarioInmuebles());
    }

    public List<Inmueble> buscarInmuebles(String ciudad, TipoInmueble tipoInmueble, TipoOferta tipoOferta,
                                          Double precioMinimo, Double precioMaximo, Double areaMinima) {
        List<Inmueble> resultado = new ArrayList<>();

        for (Inmueble inmueble : inmoSmart.getInventarioInmuebles()) {
            if (inmueble.getEstado() != EstadoInmueble.DISPONIBLE) {
                continue;
            }

            if (!estaVacio(ciudad) && !inmueble.getCiudad().toLowerCase().contains(ciudad.trim().toLowerCase())) {
                continue;
            }

            if (tipoInmueble != null && inmueble.getTipoInmueble() != tipoInmueble) {
                continue;
            }

            if (tipoOferta != null && inmueble.getTipoOferta() != tipoOferta) {
                continue;
            }

            if (precioMinimo != null && inmueble.getValor() < precioMinimo) {
                continue;
            }

            if (precioMaximo != null && inmueble.getValor() > precioMaximo) {
                continue;
            }

            if (areaMinima != null && inmueble.getArea() < areaMinima) {
                continue;
            }

            resultado.add(inmueble);
        }

        return resultado;
    }

    public void agregarInmuebleAFavoritos(Inmueble inmueble) {
        if (!(usuarioActual instanceof Comprador comprador)) {
            throw new IllegalStateException("Solo un comprador puede agregar favoritos.");
        }

        comprador.agregarAFavoritos(inmueble);
    }

    public List<Inmueble> obtenerFavoritos() {
        if (usuarioActual instanceof Comprador comprador) {
            return new ArrayList<>(comprador.getFavoritos());
        }

        return new ArrayList<>();
    }

    public Oferta realizarOferta(Inmueble inmueble, double montoOfrecido, String mensaje) {
        if (!(usuarioActual instanceof Comprador comprador)) {
            throw new IllegalStateException("Solo un comprador puede realizar ofertas.");
        }

        if (inmueble == null || inmueble.getEstado() != EstadoInmueble.DISPONIBLE) {
            throw new IllegalArgumentException("El inmueble no esta disponible para recibir ofertas.");
        }

        Oferta oferta = new Oferta(generarId(), montoOfrecido, mensaje, comprador, inmueble);
        comprador.realizarOferta(oferta);
        inmueble.registrarOferta(oferta);
        return oferta;
    }

    public List<Oferta> obtenerMisOfertasRealizadas() {
        if (usuarioActual instanceof Comprador comprador) {
            return new ArrayList<>(comprador.getOfertasRealizadas());
        }

        return new ArrayList<>();
    }

    public List<Oferta> obtenerOfertasRecibidas() {
        List<Oferta> ofertas = new ArrayList<>();

        if (usuarioActual instanceof Vendedor vendedor) {
            for (Inmueble inmueble : vendedor.getListaInmuebles()) {
                ofertas.addAll(inmueble.getOfertasRecibidas());
            }
        }

        return ofertas;
    }

    public void aceptarOferta(Oferta oferta) {
        if (oferta == null) {
            throw new IllegalArgumentException("Seleccione una oferta.");
        }

        oferta.aceptar();
    }

    public void rechazarOferta(Oferta oferta) {
        if (oferta == null) {
            throw new IllegalArgumentException("Seleccione una oferta.");
        }

        oferta.rechazar();
    }

    public void seleccionarOferta(Oferta oferta) {
        this.ofertaSeleccionada = oferta;
    }

    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public Transaccion registrarTransaccionOferta(MetodoPago metodoPago, double comisionInmobiliaria) {
        if (ofertaSeleccionada == null) {
            throw new IllegalStateException("No hay una oferta seleccionada.");
        }

        if (ofertaSeleccionada.getEstadoOferta() != EstadoOferta.ACEPTADA) {
            throw new IllegalStateException("Solo se puede registrar una transaccion para una oferta aceptada.");
        }

        Vendedor vendedor = buscarVendedorPorInmueble(ofertaSeleccionada.getInmueble());
        if (vendedor == null) {
            throw new IllegalStateException("No se encontro el vendedor del inmueble.");
        }

        Transaccion transaccion = new Transaccion(
                generarId(),
                ofertaSeleccionada.getMontoOfrecido(),
                comisionInmobiliaria,
                ofertaSeleccionada.getInmueble().getTipoOferta(),
                metodoPago,
                ofertaSeleccionada.getComprador(),
                vendedor,
                ofertaSeleccionada.getInmueble()
        );

        inmoSmart.registrarTransaccion(transaccion);
        vendedor.finalizarVenta(ofertaSeleccionada.getInmueble());
        ofertaSeleccionada = null;
        return transaccion;
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

    private void validarCodigoInmuebleDisponible(String codigo) {
        for (Inmueble inmueble : inmoSmart.getInventarioInmuebles()) {
            if (inmueble.getCodigo().equals(codigo)) {
                throw new IllegalArgumentException("Ya existe un inmueble con ese codigo.");
            }
        }
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private Vendedor buscarVendedorPorInmueble(Inmueble inmueble) {
        for (MiembroInmobiliario miembro : inmoSmart.getListaUsuarios()) {
            if (miembro instanceof Vendedor vendedor && vendedor.getListaInmuebles().contains(inmueble)) {
                return vendedor;
            }
        }

        return null;
    }
}
