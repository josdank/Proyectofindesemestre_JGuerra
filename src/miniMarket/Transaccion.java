package miniMarket;

import java.time.LocalDateTime;

public class Transaccion {
    int idTransaccion;
    int idUsuario;
    LocalDateTime fechaTransaccion;

    public Transaccion() {
    }

    public Transaccion(int idTransaccion, int idUsuario, LocalDateTime fechaTransaccion) {
        this.idTransaccion = idTransaccion;
        this.idUsuario = idUsuario;
        this.fechaTransaccion = fechaTransaccion;
    }

    // Getters y Setters
    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
}
