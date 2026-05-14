package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class NotificacionSMS implements INotificable{
    /**
     * Envía una alerta rápida al miembro inmobiliario a través de un mensaje de texto (SMS).
     * <p>
     * Este método utiliza el número telefónico asociado al perfil del miembro para realizar el envío
     *
     * @param miembroInmobiliario El destinatario que recibirá el SMS. Se requiere que
     *                            tenga un número de teléfono válido registrado.
     * @param mensaje            El contenido de la notificación
     * @see MiembroInmobiliario#getTelefono()
     */
    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por SMS a: " + miembroInmobiliario.getTelefono() + "con el mensaje: "+ mensaje);
    }
}