package miniMarket;

public abstract class Entidad {
    int id;

    public Entidad() {
    }

    public Entidad(int id) {
        this.id = id;
    }

    // Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

