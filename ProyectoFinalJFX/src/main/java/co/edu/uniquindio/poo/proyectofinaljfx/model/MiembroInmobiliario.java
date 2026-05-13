package co.edu.uniquindio.poo.proyectofinaljfx.model;

import java.time.LocalDate;

public abstract class MiembroInmobiliario implements IPuntuable, IBeneficiable{
    protected String id;
    protected String nombre;
    protected String identificacion;
    protected String telefono;
    protected String correo;
    protected LocalDate fechaRegistro;
    protected int puntosReputacion;
    protected RangoUsuario rangoUsuario;
    protected InmoSmart ownedByInmoSmart;
    protected Usuario usuario;
    public MiembroInmobiliario(String id, String nombre, String identificacion, String telefono, String correo,String userName, String contrasenia, InmoSmart ownedByInmoSmart) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
        this.ownedByInmoSmart = ownedByInmoSmart;
        this.fechaRegistro = LocalDate.now();
        this.puntosReputacion = 0;
        this.rangoUsuario = RangoUsuario.PRINCIPIANTE;
        this.usuario = new Usuario(userName, contrasenia);
    }

    public MiembroInmobiliario(String id, String nombre, String identificacion, String telefono, String correo, String contrasenia, InmoSmart ownedByInmoSmart) {
    }

    @Override
    public void actualizarPuntos(int puntosNuevos) {
        if (puntosNuevos < 0) {
            throw new IllegalArgumentException("ERROR: No es posible agregar este valor, tiene que ser positivo " + puntosNuevos);
        }
        this.puntosReputacion += puntosNuevos;
        this.verificarRango();
    }
    @Override
    public void verificarRango() {
        if (this.puntosReputacion >= 3000) {
            this.rangoUsuario = RangoUsuario.MAGNATE_INMOBILIARIO;
        } else if (this.puntosReputacion >= 1000) {
            this.rangoUsuario = RangoUsuario.EXPERTO_INMOBILIARIO;
        } else if (this.puntosReputacion >= 500) {
            this.rangoUsuario = RangoUsuario.INVERSIONISTA;
        } else {
            this.rangoUsuario = RangoUsuario.PRINCIPIANTE;
        }
    }

    @Override
    public abstract double calcularBeneficioPorRango();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getPuntosReputacion() {
        return puntosReputacion;
    }

    public void setPuntosReputacion(int puntosReputacion) {
        this.puntosReputacion = puntosReputacion;
    }

    public RangoUsuario getRangoUsuario() {
        return rangoUsuario;
    }

    public void setRangoUsuario(RangoUsuario rangoUsuario) {
        this.rangoUsuario = rangoUsuario;
    }

    public InmoSmart getOwnedByInmoSmart() {
        return ownedByInmoSmart;
    }

    public void setOwnedByInmoSmart(InmoSmart ownedByInmoSmart) {
        this.ownedByInmoSmart = ownedByInmoSmart;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public MiembroInmobiliario(String id, String nombre, String identificacion, String telefono, String correo, LocalDate fechaRegistro, int puntosReputacion, RangoUsuario rangoUsuario, InmoSmart ownedByInmoSmart, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.puntosReputacion = puntosReputacion;
        this.rangoUsuario = rangoUsuario;
        this.ownedByInmoSmart = ownedByInmoSmart;
        this.usuario = usuario;
    }
}