package miniMarket.interfaz.clases;

/**
 * Clase Role que representa un rol en el sistema de miniMarket.
 */
public class Role {
    int idRol;
    String nombreRol;

    /**
     * Constructor por defecto de la clase Role.
     */
    public Role() {
    }

    /**
     * Constructor de la clase Role.
     *
     * @param idRol el identificador del rol
     * @param nombreRol el nombre del rol
     */
    public Role(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    /**
     * Obtiene el identificador del rol.
     *
     * @return el identificador del rol
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Establece el identificador del rol.
     *
     * @param idRol el nuevo identificador del rol
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * Obtiene el nombre del rol.
     *
     * @return el nombre del rol
     */
    public String getNombreRol() {
        return nombreRol;
    }

    /**
     * Establece el nombre del rol.
     *
     * @param nombreRol el nuevo nombre del rol
     */
    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
