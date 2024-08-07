package miniMarket.interfaz.clases;

/**
 * Clase Usuario que representa un usuario en el sistema de miniMarket.
 */
public class Usuario {
    private String id;
    private String username;
    private String password;
    private String role;

    /**
     * Constructor de la clase Usuario.
     *
     * @param id el identificador del usuario
     * @param username el nombre de usuario
     * @param password la contraseña del usuario
     * @param role el rol del usuario
     */
    public Usuario(String id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return el identificador del usuario
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario
     */
    public String getRole() {
        return role;
    }
}
