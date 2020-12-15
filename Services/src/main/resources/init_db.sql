DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS buckets;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS info_table;
DROP PROCEDURE IF EXISTS `info_user`;

CREATE TABLE users
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100) NOT NULL,
    PRIMARY KEY               (`id`)
);

CREATE TABLE orders
(
    `order_id`                BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `processed`               TINYINT NULL,
    `sum`                     INT NULL,
    `date_created`            TIMESTAMP NULL DEFAULT NOW(),
    PRIMARY KEY               (`order_id`),
    INDEX                     `fk_users_idx` (`users_id` ASC) VISIBLE,
    CONSTRAINT                `fk_users`
    FOREIGN KEY               (`users_id`)
    REFERENCES                `users` (`id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE countries
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100) NOT NULL,
    PRIMARY KEY               (`id`)
);

CREATE TABLE products
(
    `product_id`              BIGINT NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100) NOT NULL,
    `country_id`              BIGINT NULL,
    `price`                   INT NULL,
    PRIMARY KEY               (`product_id`),
    FOREIGN KEY               (`country_id`)
    REFERENCES                countries (`id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE order_products (
    `order_id`                BIGINT NOT NULL,
    `products_id`             BIGINT NULL,
    `count`                   INT NULL,
    INDEX                     `fk_products_id_idx` (`products_id` ASC) VISIBLE,
    CONSTRAINT                `fk_order_id`
    FOREIGN KEY               (`order_id`)
    REFERENCES                orders (`order_id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION,
    CONSTRAINT                `fk_products_id`
    FOREIGN KEY               (`products_id`)
    REFERENCES                products (`product_id`)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE buckets
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `products_id`             BIGINT NULL,
    `count`                   INTEGER NULL,
    PRIMARY KEY               (`id`),
    FOREIGN KEY               (users_id)
    REFERENCES                users (id)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION,
    FOREIGN KEY               (products_id)
    REFERENCES                products (product_id)
    ON DELETE                 NO ACTION
    ON UPDATE                 NO ACTION
);

CREATE TABLE info_table
(
    `id`                      BIGINT NOT NULL AUTO_INCREMENT,
    `users_id`                BIGINT NOT NULL,
    `order_id`                BIGINT NULL,
    `processed`               TINYINT NULL,
    `order_sum`               INT NULL,
    `order_created`           TIMESTAMP NULL DEFAULT NOW(),
    PRIMARY KEY               (`id`)
);

INSERT INTO users (`name`) VALUES ('admin');
INSERT INTO users (`name`) VALUES ('Bob');
INSERT INTO countries (`name`) VALUES ('Russia');
INSERT INTO countries (`name`) VALUES ('Brazil');
INSERT INTO countries (`name`) VALUES ('Belarus');
INSERT INTO countries (`name`) VALUES ('Ukraine');
INSERT INTO countries (`name`) VALUES ('Finland');
INSERT INTO countries (`name`) VALUES ('China');
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Apple', '30', 1);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Coffee', '80', 2);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Cookies', '10', 3);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Parmalat', '25', 4);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Chair', '50', 5);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Computer', '250', 6);
INSERT INTO products (`name`, `price`, `country_id`) VALUES ('Table', '70', 5);


DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `orders_AFTER_INSERT`
AFTER INSERT ON `orders` FOR EACH ROW
BEGIN
INSERT INTO info_table SET
                             order_id = NEW.order_id,
                             users_id = NEW.users_id,
                             order_created = NEW.`date_created`,
                             processed = NEW.processed,
                             order_sum = NEW.sum;
END$$
DELIMITER ;


USE `my_shop`;
DROP procedure IF EXISTS `info_user`;
DELIMITER $$
USE `my_shop`$$
CREATE PROCEDURE `info_user`(IN id BIGINT, OUT param1 INT, OUT param2 INT)
BEGIN
    SELECT count(order_id) INTO param1 FROM info_table WHERE users_id = id;
    SELECT sum(order_sum) INTO param2 FROM info_table WHERE users_id = id;
END$$
DELIMITER ;
