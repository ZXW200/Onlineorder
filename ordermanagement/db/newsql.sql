-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ordermanagement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ordermanagement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ordermanagement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ordermanagement` ;

-- -----------------------------------------------------
-- Table `ordermanagement`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`users` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `username` VARCHAR(100) NOT NULL COMMENT 'Username',
  `password` VARCHAR(100) NOT NULL COMMENT 'Password',
  `role` VARCHAR(100) NULL DEFAULT 'Admin' COMMENT 'Role',
  `addtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation Time',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb3
COMMENT = 'User Table';


-- -----------------------------------------------------
-- Table `ordermanagement`.`tables`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`tables` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`tables` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `seating_capacity` INT NULL DEFAULT NULL,
  `status` VARCHAR(50) NULL DEFAULT 'Available',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`orders` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`orders` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `table_id` BIGINT NULL DEFAULT NULL,
  `order_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` VARCHAR(50) NULL DEFAULT 'Pending',
  `user_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `table_id` (`table_id` ASC) VISIBLE,
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ordermanagement`.`users` (`id`),
  CONSTRAINT `orders_ibfk_2`
    FOREIGN KEY (`table_id`)
    REFERENCES `ordermanagement`.`tables` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`feedback` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`feedback` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_id` BIGINT NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `rating` INT NULL DEFAULT NULL,
  `feedback_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `order_id` (`order_id` ASC) VISIBLE,
  CONSTRAINT `feedback_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ordermanagement`.`users` (`id`),
  CONSTRAINT `feedback_ibfk_2`
    FOREIGN KEY (`order_id`)
    REFERENCES `ordermanagement`.`orders` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`menu_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`menu_items` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`menu_items` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `category` VARCHAR(100) NULL DEFAULT NULL,
  `available` TINYINT(1) NULL DEFAULT '1',
  `productImage` VARCHAR(255) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`order_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`order_details` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`order_details` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT NOT NULL,
  `menu_item_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_id` (`order_id` ASC) VISIBLE,
  INDEX `menu_item_id` (`menu_item_id` ASC) VISIBLE,
  CONSTRAINT `order_details_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `ordermanagement`.`orders` (`id`),
  CONSTRAINT `order_details_ibfk_2`
    FOREIGN KEY (`menu_item_id`)
    REFERENCES `ordermanagement`.`menu_items` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`reservations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`reservations` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`reservations` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `table_id` BIGINT NOT NULL,
  `reservation_time` TIMESTAMP NOT NULL,
  `status` VARCHAR(50) NULL DEFAULT 'Pending',
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `table_id` (`table_id` ASC) VISIBLE,
  CONSTRAINT `reservations_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ordermanagement`.`users` (`id`),
  CONSTRAINT `reservations_ibfk_2`
    FOREIGN KEY (`table_id`)
    REFERENCES `ordermanagement`.`tables` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ordermanagement`.`tblu_screener_parent`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ordermanagement`.`tblu_screener_parent` ;

CREATE TABLE IF NOT EXISTS `ordermanagement`.`tblu_screener_parent` (
  `Segment_Symbol` VARCHAR(255) NULL DEFAULT NULL,
  `OWNSHP_TYP` VARCHAR(255) NULL DEFAULT NULL,
  `Subindustry` VARCHAR(255) NULL DEFAULT NULL,
  `Industry` VARCHAR(255) NULL DEFAULT NULL,
  `Sector` VARCHAR(255) NULL DEFAULT NULL,
  `Subsector` VARCHAR(255) NULL DEFAULT NULL,
  `LAST_SEEN` DATETIME NULL DEFAULT NULL,
  `PERIODENDDATE` DATETIME NULL DEFAULT NULL,
  `Metric_Name` VARCHAR(255) NULL DEFAULT NULL,
  `Metric_Type` VARCHAR(255) NULL DEFAULT NULL,
  `Panel_Type` VARCHAR(255) NULL DEFAULT NULL,
  `Segment_Type` VARCHAR(255) NULL DEFAULT NULL,
  `Correlation` FLOAT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

