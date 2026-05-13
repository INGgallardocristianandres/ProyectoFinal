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
    public double calcularComisionInmobiliaria(double porcentaje) {
        double porcentajeComision = porcentaje;
        return this.montoOfrecido * porcentajeComision;
    }

    public void aceptar() {
        this.estadoOferta = EstadoOferta.ACEPTADA;
    }

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