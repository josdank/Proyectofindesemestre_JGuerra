package miniMarket.interfaz.clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase DatabaseConnection que proporciona métodos para conectarse a la base de datos.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/minimarket";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return una conexión a la base de datos
     * @throws SQLException si ocurre un error al intentar conectarse a la base de datos
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
