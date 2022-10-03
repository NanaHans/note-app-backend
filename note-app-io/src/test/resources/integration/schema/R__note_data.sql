
-- Exportiere Datenbank Struktur für notepad
CREATE DATABASE IF NOT EXISTS `notepad` /*!40100 DEFAULT CHARACTER SET utf32 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `notepad`;
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
INSERT INTO `note` (`id`, `title`, `body`) VALUES
	(1, 'CORS', 'Origin Error '),
	(10, 'ertretret', 'rgrghera'),
	(12, 'earhaerh', 'ahreheah'),
	(14, 'Spring Boot', 'Spring Security'),
	(16, NULL, NULL),
	(17, NULL, NULL);
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
INSERT INTO `user` (`id`, `email`, `username`, `PASSWORD`, `firstname`, `lastname`) VALUES
	(1, 'arsenmajeste@gmail.com', 'tirone007', 'fceafb43d1014b9ecac8ee1c45a7ca2b09f30616222eaafe5092c0da5a912e85', 'Arsen', 'Nana'),
	(2, 'fridanana@gmail.com', 'alaine', 'frida@007', 'Frida', 'Nana');
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
INSERT INTO `user_note` (`user_id`, `note_id`) VALUES
	(1, 1),
	(1, 12),
	(1, 14);

