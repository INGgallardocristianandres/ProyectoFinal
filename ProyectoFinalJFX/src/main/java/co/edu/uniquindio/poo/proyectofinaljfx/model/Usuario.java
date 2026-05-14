package co.edu.uniquindio.poo.proyectofinaljfx.model;

public class Usuario {
    private String userName;
    private String contrasenia;

    public Usuario(String userName, String contrasenia) {
        this.userName = userName;
        this.contrasenia = contrasenia;
    }

    public String getUserName() {
        return userName;
    }

    public void actualizarUserName(String userName) {
        this.userName = userName;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    /**
     * Actualiza la contraseña del usuario.
     * <p>
     * @param contrasenia La nueva cadena de texto que se establecerá como contraseña. 
     *                    No debe ser {@code null} ni estar vacía.
     */
    public void actualizarContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
