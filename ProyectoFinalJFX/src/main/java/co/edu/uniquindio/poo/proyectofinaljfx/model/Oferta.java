package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.time.LocalDate;
public class Oferta {
    private String idOferta;
    private double montoOfrecido;
    private LocalDate fecha;
    private String mensaje;
    private EstadoOferta estadoOferta;
    private Comprador comprador;
    private Inmueble inmueble;

    public Oferta(String idOferta, double montoOfrecido, String mensaje,Comprador comprador, Inmueble inmueble) {
        this.idOferta = idOferta;
        this.montoOfrecido = montoOfrecido;
        this.mensaje = mensaje;
        this.comprador = comprador;
        this.inmueble = inmueble;
        this.fecha = LocalDate.now();
        this.estadoOferta = EstadoOferta.PENDIENTE;
    }

    public String getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public double getMontoOfrecido() {
        return montoOfrecido;
    }

    public void setMontoOfrecido(double montoOfrecido) {
        this.montoOfrecido = montoOfrecido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public EstadoOferta getEstadoOferta() {
        return estadoOferta;
    }

    public void setEstadoOferta(EstadoOferta estadoOferta) {
        this.estadoOferta = estadoOferta;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }
    /**
     * Calcula el monto de la comisión inmobiliaria basada en un porcentaje aplicado
     * al valor de la oferta.
     * <p>
     * El cálculo se realiza multiplicando el {@code montoOfrecido} actual del inmueble
     * por el factor porcentual proporcionado.
     *
     * @param porcentaje El factor de comisión (por ejemplo, 0.05 para un 5%).
     * @return El valor total de la comisión en la misma moneda que el monto ofrecido.
     * @see #montoOfrecido
     */
    public double calcularComisionInmobiliaria(double porcentaje) {
        double porcentajeComision = porcentaje;
        return this.montoOfrecido * porcentajeComision;
    }
    /**
     * Cambia formalmente el estado de la oferta a aceptada.
     * <p>
     * Al ejecutar este método, se confirma que el propietario está de acuerdo con los términos propuestos. Este cambio suele ser el paso previo a la finalización del contrato o al cierre de la publicación.
     *
     * @see EstadoOferta#ACEPTADA
     */
    public void aceptar() {
        this.estadoOferta = EstadoOferta.ACEPTADA;
    }
    /**
     * Cambia formalmente el estado de la oferta a rechazada.
     * <p>
     * Al ejecutar este método, se confirma que el propietario no acepto la oferta.
     *
     * @see EstadoOferta#RECHAZADA
     */
    public void rechazar() {
        this.estadoOferta = EstadoOferta.RECHAZADA;
    }


    @Override
    public String toString() {
        return "Oferta [" +
                "ID: " + idOferta +
                ", Monto: " + montoOfrecido +
                ", Estado: " + estadoOferta +
                ", Inmueble: " + inmueble.getNombre() +
                "]";
    }
}