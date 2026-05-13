package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class NotificacionCorreo implements INotificable {
    @Override
    public void enviarNotificacion(MiembroInmobiliario miembroInmobiliario, String mensaje) {
        System.out.println("Enviando notificacion por correo electronico a " + miembroInmobiliario.getCorreo() + "con el mensaje: " + mensaje);

    }
}
