package co.edu.uniquindio.poo.proyectofinaljfx.model;
/**
 * Implementación del servicio de notificaciones a través de la plataforma WhatsApp.
 * <p>
 * Esta clase se encarga de gestionar el envío de mensajes instantáneos utilizando el número telefónico del miembro inmobiliario como identificador único.
 *
 * @see INotificable
 */
public class NotificacionWhatsApp implements INotificable{
    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por WhatsApp a: " + miembroInmobiliario.getTelefono() + "con el mensaje: " + mensaje);
    }
}