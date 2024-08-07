package miniMarket.interfaz.clases;

/**
 * Clase Producto que representa un producto en el sistema de miniMarket.
 */
public class Producto {
    String id;
    String name;
    int stock;
    double price;

    /**
     * Constructor de la clase Producto.
     *
     * @param id el identificador del producto
     * @param stock la cantidad de stock disponible del producto
     * @param price el precio del producto
     */
    public Producto(String id, int stock, double price) {
        this.id = id;
        this.name = getName();
        this.stock = stock;
        this.price = price;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return el identificador del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return el nombre del producto
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la cantidad de stock disponible del producto.
     *
     * @return la cantidad de stock disponible
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return el precio del producto
     */
    public double getPrice() {
        return price;
    }

    /**
     * Reduce la cantidad de stock del producto.
     *
     * @param quantity la cantidad a reducir del stock
     */
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    /**
     * Incrementa la cantidad de stock del producto.
     *
     * @param quantity la cantidad a incrementar del stock
     */
    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
}
