SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `buyer` CASCADE;
DROP TABLE IF EXISTS `seller` CASCADE;
DROP TABLE IF EXISTS `property` CASCADE;
DROP TABLE IF EXISTS `appointments` CASCADE;

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

    CREATE TABLE property (
        id INT AUTO_INCREMENT PRIMARY KEY,
        street VARCHAR(255) NOT NULL,
        town VARCHAR(255) NOT NULL,
        bedrooms INT NOT NULL,
        bathrooms INT NOT NULL,
        garden VARCHAR(255),
        image_url VARCHAR(255),
        state VARCHAR(255) NOT NULL,
        price INT NOT NULL,
        seller_id INT,
        FOREIGN KEY (seller_id) REFERENCES seller(id)
    );

    CREATE TABLE `appointments` (
            `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
            `buyer_id` INTEGER,
            `first_name` VARCHAR(255),
            `surname` VARCHAR (255),
            `property_id` INTEGER,
            `date` DATE,
            `time_slot` VARCHAR(255)
    );
