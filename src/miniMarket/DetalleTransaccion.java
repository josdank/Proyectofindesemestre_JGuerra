package miniMarket;

public class DetalleTransaccion {
    int idDetalle;
    int idTransaccion;
    int idProducto;
    int cantidad;

    public DetalleTransaccion() {
    }

    public DetalleTransaccion(int idDetalle, int idTransaccion, int idProducto, int cantidad) {
        this.idDetalle = idDetalle;
        this.idTransaccion = idTransaccion;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

