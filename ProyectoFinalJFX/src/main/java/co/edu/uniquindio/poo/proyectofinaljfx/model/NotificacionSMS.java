package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class NotificacionSMS implements INotificable{

    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por SMS a: " + miembroInmobiliario.getTelefono() + "con el mensaje: "+ mensaje);
    }
}