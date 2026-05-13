package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Vendedor extends MiembroInmobiliario {
    private int totalInmueblesPublicados;
    private int totalVentasFinalizadas;
    private List<Inmueble> listaInmuebles;

    public Vendedor(String id, String nombre, String identificacion, String telefono, String correo, String contrasenia, InmoSmart ownedByInmoSmart) {
        super(id, nombre, identificacion, telefono, correo, contrasenia, ownedByInmoSmart);
        this.totalInmueblesPublicados = 0;
        this.totalVentasFinalizadas = 0;
        this.listaInmuebles = new ArrayList<>();
    }

    public void publicarInmueble(Inmueble inmueble) {
        if (inmueble != null) {
            listaInmuebles.add(inmueble);
            totalInmueblesPublicados++;
            this.actualizarPuntos(10);
        }
    }

    public void finalizarVenta(Inmueble inmueble) {
        if (listaInmuebles.contains(inmueble)) {
            this.totalVentasFinalizadas++;
            this.actualizarPuntos(100);
            System.out.println("Muy bien, la venta finalizó para la propiedad: " + inmueble.getNombre());
        } else {
            System.out.println("Error: El inmueble no pertenece a este vendedor.");
        }
    }

    @Override
    public double calcularBeneficioPorRango() {
        double descuentoComision = 0.0;

        switch (this.rangoUsuario) {
            case MAGNATE_INMOBILIARIO:
                descuentoComision = 0.02;
                break;
            case EXPERTO_INMOBILIARIO:
                descuentoComision = 0.015;
                break;
            case INVERSIONISTA:
                descuentoComision = 0.01;
                break;
            default:
                descuentoComision = 0.0;
                break;
        }
        return descuentoComision;
    }
    public int getTotalInmueblesPublicados() {
        return totalInmueblesPublicados;
    }

    public int getTotalVentasFinalizadas() {
        return totalVentasFinalizadas;
    }

    public List<Inmueble> getListaInmuebles() {
        return Collections.unmodifiableList(listaInmuebles);
    }
}