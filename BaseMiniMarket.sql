CREATE DATABASE minimarket;
USE minimarket;

CREATE TABLE roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL
);

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    id_rol INT,
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    ruta_imagen VARCHAR(255)
);

CREATE TABLE transacciones (
    id_transaccion INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT,
    fecha_transaccion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE detalle_transacciones (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_transaccion INT,
    id_producto INT,
    cantidad INT NOT NULL,
    FOREIGN KEY (id_transaccion) REFERENCES transacciones(id_transaccion),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);


INSERT INTO roles (nombre_rol) VALUES ('Admin'), ('Cajero');

INSERT INTO usuarios(nombre_usuario, contrasena, id_rol) VALUES ('admin', 'Control1256', 1);

select * from usuarios;
select * from roles;
