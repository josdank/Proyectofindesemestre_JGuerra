# Editor.md
# MiniMarket

Este repositorio contiene el código fuente del sistema MiniMarket, un sistema de gestión para un supermercado que incluye funcionalidades para manejar productos, transacciones, usuarios y más.

## Estructura del Proyecto

### Clases Principales

#### Producto.java

```java
package miniMarket;

/**
 * Clase Producto que representa un producto en el sistema miniMarket.
 */
public class Producto {
    private String id;
    private String name;
    private int stock;
    private double price;

    /**
     * Constructor de la clase Producto.
     *
     * @param id el identificador del producto
     * @param stock la cantidad en stock del producto
     * @param price el precio del producto
     */
    public Producto(String id, int stock, double price) {
        this.id = id;
        this.name = name;
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
     * Obtiene la cantidad en stock del producto.
     *
     * @return la cantidad en stock del producto
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
     * Reduce la cantidad en stock del producto.
     *
     * @param quantity la cantidad a reducir del stock
     */
    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    /**
     * Incrementa la cantidad en stock del producto.
     *
     * @param quantity la cantidad a incrementar del stock
     */
    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
}

