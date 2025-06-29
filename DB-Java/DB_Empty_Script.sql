-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydbfeliz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydbfeliz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydbfeliz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `mydbfeliz` ;

-- -----------------------------------------------------
-- Table `mydbfeliz`.`privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`privileges` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `privilege` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`users` (
  `created_at` TIMESTAMP NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `privileges_id` BIGINT NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK6dotkott2kjsp8vw4d0m25fb7` (`email` ASC) VISIBLE,
  INDEX `FK7b5vqf0bmgul76i8rl8q2109j` (`privileges_id` ASC) VISIBLE,
  CONSTRAINT `FK7b5vqf0bmgul76i8rl8q2109j`
    FOREIGN KEY (`privileges_id`)
    REFERENCES `mydbfeliz`.`privileges` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`shopping_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`shopping_cart` (
  `shipment` DECIMAL(10,2) NOT NULL,
  `subtotal` DECIMAL(10,2) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `users_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKficef7nslpf9w6k6ops7l73g7` (`users_id` ASC) VISIBLE,
  CONSTRAINT `FKficef7nslpf9w6k6ops7l73g7`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydbfeliz`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`products` (
  `discount` DECIMAL(10,2) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `category_id` BIGINT NOT NULL,
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `stock` BIGINT NOT NULL,
  `description` TEXT NOT NULL,
  `product_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id` ASC) VISIBLE,
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9`
    FOREIGN KEY (`category_id`)
    REFERENCES `mydbfeliz`.`categories` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`cart_items` (
  `price_at_purchase` DECIMAL(10,2) NOT NULL,
  `quantity` INT NOT NULL,
  `products_id` BIGINT NOT NULL,
  `shopping_cart_id` BIGINT NOT NULL,
  PRIMARY KEY (`products_id`, `shopping_cart_id`),
  INDEX `FKfbjrcn87oo0j82ojyeh5tfbtp` (`shopping_cart_id` ASC) VISIBLE,
  CONSTRAINT `FKfbjrcn87oo0j82ojyeh5tfbtp`
    FOREIGN KEY (`shopping_cart_id`)
    REFERENCES `mydbfeliz`.`shopping_cart` (`id`),
  CONSTRAINT `FKmb92cy9bs4b82pteh73r7ais8`
    FOREIGN KEY (`products_id`)
    REFERENCES `mydbfeliz`.`products` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydbfeliz`.`user_addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydbfeliz`.`user_addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `users_id` BIGINT NOT NULL,
  `zip_code` BIGINT NOT NULL,
  `neighborhood` TEXT NOT NULL,
  `state` TEXT NOT NULL,
  `street_and_number` TEXT NOT NULL,
  `town` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK78b6fry8xdcr01eqvegxrcpsv` (`users_id` ASC) VISIBLE,
  CONSTRAINT `FK78b6fry8xdcr01eqvegxrcpsv`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydbfeliz`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
