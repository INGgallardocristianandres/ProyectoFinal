package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.time.LocalDate;

public class Busqueda {
    private String idBusqueda;
    private LocalDate fecha;
    private TipoInmueble tipoInmueble;
    private String ciudad;
    private double precioMinimo;
    private double precioMaximo;
    private double areaMinima;

    public Busqueda(String idBusqueda, TipoInmueble tipoInmueble, String ciudad, double precioMinimo, double precioMaximo, double areaMinima) {
        this.idBusqueda = idBusqueda;
        this.tipoInmueble = tipoInmueble;
        this.ciudad = ciudad;
        this.precioMinimo = precioMinimo;
        this.precioMaximo = precioMaximo;
        this.areaMinima = areaMinima;
        this.fecha = LocalDate.now();
    }

    public String getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(String idBusqueda) {
        this.idBusqueda = idBusqueda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecioMinimo() {
        return precioMinimo;
    }

    public void setPrecioMinimo(double precioMinimo) {
        this.precioMinimo = precioMinimo;
    }

    public double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    public double getAreaMinima() {
        return areaMinima;
    }

    public void setAreaMinima(double areaMinima) {
        this.areaMinima = areaMinima;
    }

    @Override
    public String toString() {
        return "Busqueda [" +
                "Ciudad: " + ciudad +
                ", Tipo: " + tipoInmueble +
                ", Rango: $" + precioMinimo + " - $" + precioMaximo +
                "]";
    }
}