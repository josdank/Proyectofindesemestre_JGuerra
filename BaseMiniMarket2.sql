CREATE DATABASE minimarket;
USE minimarket;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('Cajero', 'Admin') NOT NULL
);

DELETE FROM usuarios;
ALTER TABLE usuarios AUTO_INCREMENT = 1;
select * from usuarios;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM usuarios WHERE id = 8;
SET SQL_SAFE_UPDATES = 1;
INSERT INTO usuarios (username, password, role) VALUES ('admin1', 'password1', 'Admin');

CREATE TABLE cajeros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    telefono VARCHAR(16) NOT NULL
);
select * from cajeros;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    imagen VARCHAR(255),
    cantidad INT NOT NULL DEFAULT 0
);
select * from productos;
select * from stock;

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
    fecha DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL
);
select*from ventas;
ALTER TABLE facturas DROP COLUMN pago;
ALTER TABLE facturas
ADD COLUMN pdf_path VARCHAR(255),
ADD COLUMN xml_path VARCHAR(255);

CREATE TABLE stock (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad INT NOT NULL DEFAULT 100
);
ALTER TABLE stock ADD COLUMN precio DECIMAL(10, 2) NOT NULL;
select * from usuarios;
TRUNCATE TABLE stock;
SET SQL_SAFE_UPDATES = 0;
DELETE FROM stock;
SET SQL_SAFE_UPDATES = 1;









