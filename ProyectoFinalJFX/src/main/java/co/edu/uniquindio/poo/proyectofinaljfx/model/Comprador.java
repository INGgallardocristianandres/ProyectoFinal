package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Comprador extends MiembroInmobiliario {
    private List<Inmueble> favoritos;
    private List<Oferta> ofertasRealizadas;
    private List<Busqueda> historialBusquedas;

    public Comprador(String id, String nombre, String identificacion, String telefono, String correo, String userName,String contrasenia, InmoSmart ownedByInmoSmart) {
        super(id, nombre, identificacion, telefono, correo, userName, contrasenia, ownedByInmoSmart);
        this.favoritos = new ArrayList<>();
        this.ofertasRealizadas = new ArrayList<>();
        this.historialBusquedas = new ArrayList<>();
    }


    public List<Inmueble> getFavoritos() {
        return Collections.unmodifiableList(favoritos);
    }

    public List<Oferta> getOfertasRealizadas() {
        return Collections.unmodifiableList(ofertasRealizadas);
    }

    public List<Busqueda> getHistorialBusquedas() {
        return historialBusquedas;
    }

    public void setHistorialBusquedas(List<Busqueda> historialBusquedas) {
        this.historialBusquedas = historialBusquedas;
    }
    /**
     * Añade un inmueble a la lista de favoritos del usuario.
     * El método verifica que el inmueble no sea nulo y que no haya sido agregado previamente para asi evitar redundancia
     * @param inmueble El objeto {@link Inmueble} que se desea marcar como favorito.
     *                 Si es {@code null}, el método no realiza ninguna acción.
     * @see #favoritos
     */
    public void agregarAFavoritos(Inmueble inmueble) {
        if (inmueble != null && !favoritos.contains(inmueble)) {
            favoritos.add(inmueble);
        }
    }
    /**
     * Registra una nueva oferta en el historial y otorga puntos de bonificación al usuario
     * Si la oferta es válida (no nula), se añade a la colección de ofertas realizadas y se incrementa el puntaje del usuario en 5 unidades con {@link #actualizarPuntos(int)}.
     * @param oferta El objeto {@link Oferta} que se desea procesar.
     * Si el parámetro es {@code null}, el método no realiza ninguna operacion
     * @see #ofertasRealizadas
     * @see #actualizarPuntos(int)
     */
    public void realizarOferta(Oferta oferta) {
        if (oferta != null) {
            ofertasRealizadas.add(oferta);
            this.actualizarPuntos(5);
        }
    }

    @Override
    public double calcularBeneficioPorRango() {
        double beneficio = 0.0;
        switch (this.rangoUsuario) {
            case MAGNATE_INMOBILIARIO:
                beneficio = 0.05;
                break;
            case EXPERTO_INMOBILIARIO:
                beneficio = 0.03;
                break;
            case INVERSIONISTA:
                beneficio = 0.01;
                break;
            default:
                beneficio = 0.0;
                break;
        }
        return beneficio;
    }
}