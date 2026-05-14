package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class NotificacionCorreo implements INotificable {
    /**
     * Envía una notificación formal a un miembro de la red inmobiliaria a través de correo electrónico.
     * <p>
     * Este método simula la integración con un servicio de mensajería externa,
     * utilizando la dirección de correo registrada en el perfil del miembro.
     *
     * @param miembroInmobiliario El destinatario de la notificación. Debe tener un correo electrónico válido.
     * @param mensaje            El cuerpo del mensaje o contenido que se desea comunicar.
     * @see MiembroInmobiliario#getCorreo()
     */
    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por correo electronico a " + miembroInmobiliario.getCorreo() + "con el mensaje: " + mensaje);

    }
}
