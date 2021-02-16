-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema taskManager
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `taskManager`;

-- -----------------------------------------------------
-- Schema taskManager
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `taskManager` DEFAULT CHARACTER SET utf8mb4;
USE `taskManager`;

-- -----------------------------------------------------
-- Table `taskManager`.`choices`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taskManager`.`choices`;

CREATE TABLE IF NOT EXISTS `taskManager`.`choices`
(
    `id`           INT(11)     NOT NULL AUTO_INCREMENT,
    `value`        VARCHAR(50) NOT NULL DEFAULT 'NewChoice',
    `order`        INT(11)     NULL     DEFAULT 1,
    `fieldName`    VARCHAR(50) NOT NULL,
    `defaultValue` TINYINT(1)  NULL     DEFAULT 0,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 27
    DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `taskManager`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `taskManager`.`task`;

CREATE TABLE IF NOT EXISTS `taskManager`.`task`
(
    `id`               INT(11)     NOT NULL AUTO_INCREMENT,
    `nr`               INT(11)     NULL DEFAULT NULL,
    `shortDescription` VARCHAR(45) NULL DEFAULT NULL,
    `description`      VARCHAR(45) NULL DEFAULT NULL,
    `state`            INT(11)     NULL DEFAULT NULL,
    `priority`         INT(11)     NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `priority_choice`
        FOREIGN KEY (`priority`)
            REFERENCES `taskManager`.`choices` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `state_choice`
        FOREIGN KEY (`state`)
            REFERENCES `taskManager`.`choices` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 16
    DEFAULT CHARACTER SET = utf8mb4;

CREATE INDEX `priority_choice_idx` ON `taskManager`.`task` (`priority` ASC) VISIBLE;

CREATE INDEX `state_choice_idx` ON `taskManager`.`task` (`state` ASC) VISIBLE;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;


INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('VERY_HIGH', '1', 'priority', '0');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('HIGH', '2', 'priority', '0');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('MEDIUM', '3', 'priority', '1');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('LOW', '4', 'priority', '0');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('OPEN', '1', 'state', '1');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('IN_PROGRESS', '2', 'state', '0');
INSERT INTO `taskManager`.`choices` (`value`, `order`, `fieldName`, `defaultValue`)
VALUES ('DONE', '3', 'state', '0');

