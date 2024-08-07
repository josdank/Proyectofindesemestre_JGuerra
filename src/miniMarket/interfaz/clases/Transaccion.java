package miniMarket.interfaz.clases;

import java.util.Date;

/**
 * Clase Transaccion que representa una transacción en el sistema de miniMarket.
 */
public class Transaccion {
    private final String id;
    private Date date;
    private Usuario cashier;
    private Producto product;
    private int quantity;
    private double totalPrice;

    /**
     * Constructor de la clase Transaccion.
     *
     * @param id el identificador de la transacción
     * @param date la fecha de la transacción
     * @param cashier el cajero que realiza la transacción
     * @param product el producto vendido en la transacción
     * @param quantity la cantidad de producto vendido
     * @param totalPrice el precio total de la transacción
     */
    public Transaccion(String id, Date date, Usuario cashier, Producto product, int quantity, double totalPrice) {
        this.id = id;
        this.date = date;
        this.cashier = cashier;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    /**
     * Obtiene el identificador de la transacción.
     *
     * @return el identificador de la transacción
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene la fecha de la transacción.
     *
     * @return la fecha de la transacción
     */
    public Date getDate() {
        return date;
    }

    /**
     * Obtiene el cajero que realiza la transacción.
     *
     * @return el cajero que realiza la transacción
     */
    public Usuario getCashier() {
        return cashier;
    }

    /**
     * Obtiene el producto vendido en la transacción.
     *
     * @return el producto vendido en la transacción
     */
    public Producto getProduct() {
        return product;
    }

    /**
     * Obtiene la cantidad de producto vendido.
     *
     * @return la cantidad de producto vendido
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Obtiene el precio total de la transacción.
     *
     * @return el precio total de la transacción
     */
    public double getTotalPrice() {
        return totalPrice;
    }
}
