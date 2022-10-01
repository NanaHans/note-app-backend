-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               8.0.27 - MySQL Community Server - GPL
-- Server Betriebssystem:        Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Exportiere Datenbank Struktur für notepad
CREATE DATABASE IF NOT EXISTS `notepad` /*!40100 DEFAULT CHARACTER SET utf32 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `notepad`;

-- Exportiere Struktur von Tabelle notepad.flyway_schema_history
CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

-- Exportiere Daten aus Tabelle notepad.flyway_schema_history: ~0 rows (ungefähr)
DELETE FROM `flyway_schema_history`;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', 'createTable', 'SQL', 'V1__createTable.sql', 1471439577, 'root', '2022-04-22 18:39:36', 41, 1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle notepad.note
CREATE TABLE IF NOT EXISTS `note` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `body` mediumtext,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf32;

-- Exportiere Daten aus Tabelle notepad.note: ~6 rows (ungefähr)
DELETE FROM `note`;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` (`id`, `title`, `body`, `timestamp`) VALUES
	(1, 'CORS', 'Origin Error ', '2022-06-06 12:57:17'),
	(10, 'ertretret', 'rgrghera', '2022-06-06 12:57:17'),
	(12, 'earhaerh', 'ahreheah', '2022-06-06 12:57:17'),
	(14, 'Spring Boot', 'Spring Security', '2022-06-06 21:24:52'),
	(16, NULL, NULL, '2022-09-18 20:50:49'),
	(17, NULL, NULL, '2022-09-18 20:58:42');
/*!40000 ALTER TABLE `note` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle notepad.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `PASSWORD` varchar(64) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32;

-- Exportiere Daten aus Tabelle notepad.user: ~0 rows (ungefähr)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `email`, `username`, `PASSWORD`, `firstname`, `lastname`, `timestamp`) VALUES
	(1, 'arsenmajeste@gmail.com', 'tirone007', 'fceafb43d1014b9ecac8ee1c45a7ca2b09f30616222eaafe5092c0da5a912e85', 'Arsen', 'Nana', '2022-05-29 12:46:06'),
	(2, 'fridanana@gmail.com', 'alaine', 'frida@007', 'Frida', 'Nana', '2022-06-06 20:05:31');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Exportiere Struktur von Tabelle notepad.user_note
CREATE TABLE IF NOT EXISTS `user_note` (
  `user_id` int NOT NULL,
  `note_id` int NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`note_id`),
  KEY `user_note_note_id` (`note_id`),
  CONSTRAINT `user_note_note_id` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`),
  CONSTRAINT `user_note_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

-- Exportiere Daten aus Tabelle notepad.user_note: ~3 rows (ungefähr)
DELETE FROM `user_note`;
/*!40000 ALTER TABLE `user_note` DISABLE KEYS */;
INSERT INTO `user_note` (`user_id`, `note_id`, `timestamp`) VALUES
	(1, 1, '2022-06-06 21:24:52'),
	(1, 12, '2022-06-06 21:24:52'),
	(1, 14, '2022-06-06 21:24:52');
/*!40000 ALTER TABLE `user_note` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
