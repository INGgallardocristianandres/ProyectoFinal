package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class NotificacionWhatsApp implements INotificable{
    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por WhatsApp a: " + miembroInmobiliario.getTelefono() + "con el mensaje: " + mensaje);
    }
}