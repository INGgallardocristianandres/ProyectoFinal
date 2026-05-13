package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class InmoSmart {
    private String nombre;
    private List<MiembroInmobiliario> listaMiembroInmobiliarios;
    private List<Inmueble> inventarioInmuebles;
    private List<Transaccion> registroTransacciones;
    private List<INotificable> canalesNotificacion;

    public InmoSmart(String nombre) {
        this.nombre = nombre;
        this.listaMiembroInmobiliarios = new ArrayList<>();
        this.inventarioInmuebles = new ArrayList<>();
        this.registroTransacciones = new ArrayList<>();
        this.canalesNotificacion = new ArrayList<>();
    }

    public void registrarUsuario(MiembroInmobiliario miembroInmobiliario) {
        if (miembroInmobiliario != null) {
            boolean existe = false;
            for (MiembroInmobiliario user : listaMiembroInmobiliarios) {
                if (user.getIdentificacion().equals(miembroInmobiliario.getIdentificacion())) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                listaMiembroInmobiliarios.add(miembroInmobiliario);
            }
        }
    }

    public MiembroInmobiliario iniciarSesion(String userName, String contrasenia) {
        for (MiembroInmobiliario miembro : listaMiembroInmobiliarios) {
            Usuario cuenta = miembro.getUsuario();
            if (cuenta != null &&
                    cuenta.getUserName().equals(userName) &&
                    cuenta.getContrasenia().equals(contrasenia)) {

                return miembro;
            }
        }
        return null;
    }

    public void registrarTransaccion(Transaccion transaccion) {
        if (transaccion != null) {
            registroTransacciones.add(transaccion);
            Inmueble inmueble = transaccion.getInmueble();
            if (inmueble.getTipoOferta() == TipoOferta.VENTA) {
                inmueble.setEstado(EstadoInmueble.VENDIDO);
            } else if (inmueble.getTipoOferta() == TipoOferta.ARRIENDO) {
                inmueble.setEstado(EstadoInmueble.ARRENDADO);
            }
            notificarTransaccion(transaccion.getComprador(), "La transacción ha sido registrada.");
        }
    }

    public void generarReporteMensual() {
    }
    public void agregarCanalNotificacion(INotificable canal) {
        if (canal != null) {
            this.canalesNotificacion.add(canal);
        }
    }
    public void notificarTransaccion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        for (INotificable canal : canalesNotificacion) {
            canal.enviarNotificacion(miembroInmobiliario, mensaje);
        }
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MiembroInmobiliario> getListaUsuarios() {
        return Collections.unmodifiableList(listaMiembroInmobiliarios);
    }

    public List<Inmueble> getInventarioInmuebles() {
        return Collections.unmodifiableList(inventarioInmuebles);
    }

    public List<Transaccion> getRegistroTransacciones() {
        return Collections.unmodifiableList(registroTransacciones);
    }

    public void agregarInmuebleAlInventario(Inmueble inmueble) {
        if (inmueble != null) {
            this.inventarioInmuebles.add(inmueble);
        }
    }

    @Override
    public String toString() {
        return "InmoSmart [" + nombre + "]";
    }
}