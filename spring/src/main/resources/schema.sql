DROP TABLE IF EXISTS `buyer` CASCADE;

CREATE TABLE `buyer` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `firstname` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL
  );