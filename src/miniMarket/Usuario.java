package miniMarket;

public class Usuario extends Entidad {
    String nombreUsuario;
    String contrasena;
    int idRol;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String contrasena, int idRol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    public Usuario(int id, String nombreUsuario, String contrasena, int idRol) {
        super(id);
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.idRol = idRol;
    }

    // Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
}
