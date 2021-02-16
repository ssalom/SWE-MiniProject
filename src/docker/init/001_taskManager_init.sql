CREATE DATABASE `taskManager`;
USE `taskManager`;

-- -----------------------------------------------------
-- Table `taskManager`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taskManager`.`task` ;

CREATE TABLE IF NOT EXISTS `taskManager`.`task` (
    `nr` INT NULL,
    `shortDescription` VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `state` INT NULL,
    `priority` INT NULL,
    PRIMARY KEY (`nr`))
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;