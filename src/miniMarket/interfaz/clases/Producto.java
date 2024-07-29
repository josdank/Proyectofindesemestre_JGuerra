package miniMarket.interfaz.clases;

public class Producto {
    String id;
    String name;
    int stock;
    double price;

    public Producto(String id, int stock, double price) {
        this.id = id;
        this.name = getName();
        this.stock = stock;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
}
