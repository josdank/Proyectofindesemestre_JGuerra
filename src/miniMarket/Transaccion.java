package miniMarket;

import java.util.Date;

public class Transaccion {
    private String id;
    private Date date;
    private Usuario cashier;
    private Producto product;
    private int quantity;
    private double totalPrice;

    public Transaccion(String id, Date date, Usuario cashier, Producto product, int quantity, double totalPrice) {
        this.id = id;
        this.date = date;
        this.cashier = cashier;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Usuario getCashier() {
        return cashier;
    }

    public Producto getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
