CREATE DATABASE `taskManager`;
USE `taskManager`;

-- -----------------------------------------------------
-- Table `taskManager`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taskManager`.`task` ;

CREATE TABLE IF NOT EXISTS `taskManager`.`task` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `nr` INT NULL,
    `shortDescription` VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `state` INT NULL,
    `priority` INT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- -----------------------------------------------------
-- Table `taskManager`.`choices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taskManager`.`choices` ;

CREATE TABLE `taskManager`.`choices` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `value` VARCHAR(50) NOT NULL DEFAULT 'NewChoice',
    `order` INT NULL DEFAULT 1,
    `fieldName` VARCHAR(50) NOT NULL,
    `defaultValue` TINYINT(1) NULL DEFAULT 0,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;