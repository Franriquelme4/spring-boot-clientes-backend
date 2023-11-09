CREATE TABLE `cliente` (
                           `id_cliente` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
                           `nombre` varchar(255) NOT NULL,
                           `apellido` varchar(255) NOT NULL,
                           `correo` varchar(255) NOT NULL,
                           `fecha_registro` date DEFAULT NULL,
                           PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      user_name VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      first_name VARCHAR(255),
                      last_name VARCHAR(255),
                      country VARCHAR(255),
                      role VARCHAR(50) -- Ajusta el tamaño según tus necesidades
);