package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.time.LocalDate;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fechaTransaccion;
    private double montoFinal;
    private double comisionInmobiliaria;
    private TipoOferta tipoOperacion; // VENTA o ARRIENDO
    private MetodoPago metodoPago;    // EFECTIVO, TARJETA, etc.
    private Comprador comprador;
    private Vendedor vendedor;
    private Inmueble inmueble;

    public Transaccion(String idTransaccion, double montoFinal, double comisionInmobiliaria, TipoOferta tipoOperacion, MetodoPago metodoPago, Comprador comprador, Vendedor vendedor, Inmueble inmueble) {
        this.idTransaccion = idTransaccion;
        this.montoFinal = montoFinal;
        this.comisionInmobiliaria = comisionInmobiliaria;
        this.tipoOperacion = tipoOperacion;
        this.metodoPago = metodoPago;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.inmueble = inmueble;
        this.fechaTransaccion = LocalDate.now();
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDate fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(double montoFinal) {
        this.montoFinal = montoFinal;
    }

    public double getComisionInmobiliaria() {
        return comisionInmobiliaria;
    }

    public void setComisionInmobiliaria(double comisionInmobiliaria) {
        this.comisionInmobiliaria = comisionInmobiliaria;
    }

    public TipoOferta getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOferta tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public String toString() {
        return "Transaccion [" +
                "ID: " + idTransaccion +
                ", Operación: " + tipoOperacion +
                ", Pago: " + metodoPago +
                ", Monto: $" + montoFinal +
                "]";
    }
}