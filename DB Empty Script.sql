-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `description` TEXT NOT NULL,
  `category_id` BIGINT NOT NULL,
  `stock` BIGINT NOT NULL,
  `discount` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_categories_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_categories`
    FOREIGN KEY (`category_id`)
    REFERENCES `mydb`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`privileges`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`privileges` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `privilege` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `privileges_id` BIGINT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `privileges_id`),
  INDEX `fk_users_privileges1_idx` (`privileges_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_privileges1`
    FOREIGN KEY (`privileges_id`)
    REFERENCES `mydb`.`privileges` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cart_items` (
  `quantity` VARCHAR(45) NOT NULL,
  `products_id` BIGINT NOT NULL,
  INDEX `fk_cart_items_products1_idx` (`products_id` ASC) VISIBLE,
  PRIMARY KEY (`quantity`),
  CONSTRAINT `fk_cart_items_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `mydb`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`shopping_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`shopping_cart` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `subtotal` DECIMAL(10,2) NOT NULL,
  `shipment` DECIMAL(10,2) NOT NULL,
  `total` DECIMAL(10,2) NOT NULL,
  `users_id` BIGINT NOT NULL,
  `cart_items_quantity` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  INDEX `fk_shopping_cart_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_shopping_cart_cart_items1_idx` (`cart_items_quantity` ASC) VISIBLE,
  CONSTRAINT `fk_shopping_cart_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shopping_cart_cart_items1`
    FOREIGN KEY (`cart_items_quantity`)
    REFERENCES `mydb`.`cart_items` (`quantity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user_addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `street_and_number` TEXT NOT NULL,
  `neighborhood` TEXT NOT NULL,
  `zip_code` BIGINT NOT NULL,
  `town` TEXT NOT NULL,
  `state` TEXT NOT NULL,
  `users_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_addresses_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_addresses_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `mydb`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
