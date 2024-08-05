CREATE DATABASE minimarket;
USE minimarket;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Cajero', 'Admin') NOT NULL
);

select * from usuarios;

INSERT INTO usuarios (username, password, role) VALUES ('cajero1', 'passwordCajero1', 'Cajero');
INSERT INTO usuarios (username, password, role) VALUES ('Josue', 'passwordCajero1', 'Admin');

CREATE TABLE cajeros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(16) NOT NULL
);
select * from cajeros;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM cajeros WHERE usuario = 'caj2';
SET SQL_SAFE_UPDATES = 1;



CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    cantidad INT NOT NULL DEFAULT 0
);
select * from productos;

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    precio_total DECIMAL(10, 2) NOT NULL,
    productos_vendidos TEXT NOT NULL,
    precio_por_producto TEXT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    cajero_id INT,
    FOREIGN KEY (cajero_id) REFERENCES cajeros(id)
);

CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    cedula VARCHAR(10) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    pago DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL
);

CREATE TABLE stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL DEFAULT 100
);

select * from stock;





