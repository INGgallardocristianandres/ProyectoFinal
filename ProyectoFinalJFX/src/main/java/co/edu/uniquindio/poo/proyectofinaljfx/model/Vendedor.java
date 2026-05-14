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
    /**
     * Registra un nuevo inmueble en el sistema y actualiza las estadísticas del usuario.
     * <p>
     * Cuando se publica un inmueble de forma exitosa:
     * <ul>
     *     <li>Se añade el {@code inmueble} a la lista general de publicaciones.</li>
     *     <li>Se incrementa el contador total de inmuebles publicados.</li>
     *     <li>Se otorgan 10 puntos de reputación al usuario mediante {@link #actualizarPuntos(int)}.</li>
     * </ul>
     *
     * @param inmueble El objeto {@link Inmueble} que se desea poner a la venta o alquiler.
     *                 Si es {@code null}, la operación se ignora.
     * @see #listaInmuebles
     * @see #totalInmueblesPublicados
     */
    public void publicarInmueble(Inmueble inmueble) {
        if (inmueble != null) {
            listaInmuebles.add(inmueble);
            totalInmueblesPublicados++;
            this.actualizarPuntos(10);
        }
    }
    /**
     * Concluye el proceso de venta de un inmueble y premia al vendedor.
     * <p>
     * Este método verifica primero si el inmueble forma parte de la lista del vendedor.
     * De ser así, realiza las siguientes acciones:
     * <ul>
     *     <li>Incrementa el contador general de ventas finalizadas.</li>
     *     <li>Otorga una bonificación de <b>100 puntos</b> de reputación.</li>
     *     <li>Muestra un mensaje de confirmación por consola.</li>
     * </ul>
     * Si el inmueble no se encuentra en la lista de publicaciones del vendedor,
     * se emite un mensaje de error y no se aplican cambios.
     *
     * @param inmueble El objeto {@link Inmueble} cuya venta ha sido completada.
     * @see #actualizarPuntos(int)
     * @see #totalVentasFinalizadas
     */
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