-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.10.1-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para moto_rental
DROP DATABASE IF EXISTS `moto_rental`;
CREATE DATABASE IF NOT EXISTS `moto_rental` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `moto_rental`;

-- Volcando estructura para tabla moto_rental.alquiler
DROP TABLE IF EXISTS `alquiler`;
CREATE TABLE IF NOT EXISTS `alquiler` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id`,`username`,`fecha`) USING BTREE,
  KEY `FK_usuario` (`username`),
  CONSTRAINT `FK_motocicleta` FOREIGN KEY (`id`) REFERENCES `motocicleta` (`id`),
  CONSTRAINT `FK_usuario` FOREIGN KEY (`username`) REFERENCES `usuario` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla moto_rental.alquiler: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `alquiler` DISABLE KEYS */;
/*!40000 ALTER TABLE `alquiler` ENABLE KEYS */;

-- Volcando estructura para tabla moto_rental.motocicleta
DROP TABLE IF EXISTS `motocicleta`;
CREATE TABLE IF NOT EXISTS `motocicleta` (
  `id` int(11) NOT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `cilindraje` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `exclusiva` tinyint(4) DEFAULT NULL,
  `novedad` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla moto_rental.motocicleta: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `motocicleta` DISABLE KEYS */;
INSERT INTO `motocicleta` (`id`, `marca`, `cilindraje`, `modelo`, `categoria`, `exclusiva`, `novedad`) VALUES
	(1, 'Akt Ttr', '150cc', '2016', 'Off road', 0, 1),
	(2, 'Suzuki Drx', '200cc', '2019', 'Off road', 0, 1),
	(3, 'Ktm exc Six days', '250cc', '2018', 'Enduro', 1, 1),
	(4, 'Yamaha nmax', '160cc', '2020', 'Scooter', 0, 1),
	(5, 'Honda Supercub ', '90cc', '1998', 'Scooter', 0, 1),
	(6, 'Kawasaki Klr', '650cc', '2019', 'Alto Cilindraje', 1, 0),
	(7, 'Akt Nkd', '125cc', '2022', 'Naked', 0, 1),
	(8, 'Honda CBr', '190cc', '2021', 'Naked', 0, 0),
	(9, 'Suzuki Vstrom', '1000cc', '2022', 'Alto Cilindraje', 1, 1),
	(10, 'Bajaj Boxer', '100cc', '2017', 'Naked', 0, 1);
/*!40000 ALTER TABLE `motocicleta` ENABLE KEYS */;

-- Volcando estructura para tabla moto_rental.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `username` varchar(100) NOT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `saldo` double(22,2) DEFAULT NULL,
  `premium` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla moto_rental.usuario: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
