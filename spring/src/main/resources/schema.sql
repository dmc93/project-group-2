SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `buyer` CASCADE;
  DROP TABLE IF EXISTS `seller` CASCADE;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `buyer` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `firstname` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL
  );



  CREATE TABLE `seller` (
      `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
      `firstname` VARCHAR(255) NOT NULL,
      `surname` VARCHAR(255) NOT NULL
    );