package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Comprador extends MiembroInmobiliario {
    private List<Inmueble> favoritos;
    private List<Oferta> ofertasRealizadas;
    private List<Busqueda> historialBusquedas;

    public Comprador(String id, String nombre, String identificacion, String telefono, String correo, String contrasenia, InmoSmart ownedByInmoSmart) {
        super(id, nombre, identificacion, telefono, correo, contrasenia, ownedByInmoSmart);
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
    public void agregarAFavoritos(Inmueble inmueble) {
        if (inmueble != null && !favoritos.contains(inmueble)) {
            favoritos.add(inmueble);
        }
    }

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