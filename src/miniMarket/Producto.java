package miniMarket;

public class Producto extends Entidad {
    String nombre;
    int stock;
    double precio;
    String rutaImagen;

    public Producto() {
    }

    public Producto(String nombre, int stock, double precio, String rutaImagen) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    public Producto(int id, String nombre, int stock, double precio, String rutaImagen) {
        super(id);
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.rutaImagen = rutaImagen;
    }

    public Producto(int id) {
        super(id);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}
