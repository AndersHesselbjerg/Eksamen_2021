-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema projektoplysninger
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projektoplysninger
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projektoplysninger` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `projektoplysninger` ;

-- -----------------------------------------------------
-- Table `projektoplysninger`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektoplysninger`.`user` (
                                                           `id` INT NOT NULL AUTO_INCREMENT,
                                                           `mail` VARCHAR(45) NOT NULL,
                                                           `password` VARCHAR(45) NOT NULL,
                                                           `isAdmin` INT NOT NULL DEFAULT '0',
                                                           PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 6
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projektoplysninger`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektoplysninger`.`projects` (
                                                               `id` INT NOT NULL AUTO_INCREMENT,
                                                               `name` VARCHAR(45) NULL DEFAULT NULL,
                                                               `description` VARCHAR(16000) NULL DEFAULT NULL,
                                                               `numberOfEmployees` INT NULL DEFAULT NULL,
                                                               `deadlineDate` DATE NULL DEFAULT NULL,
                                                               `DeadlineTime` TIME NULL DEFAULT NULL,
                                                               `deadline` DATE NULL DEFAULT NULL,
                                                               `projectscol` VARCHAR(45) NULL DEFAULT NULL,
                                                               `userID` INT NULL DEFAULT NULL,
                                                               PRIMARY KEY (`id`),
                                                               UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
                                                               INDEX `user_idx` (`userID` ASC) VISIBLE,
                                                               CONSTRAINT `user`
                                                                   FOREIGN KEY (`userID`)
                                                                       REFERENCES `projektoplysninger`.`user` (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projektoplysninger`.`subprojects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `projektoplysninger`.`subprojects` (
                                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                                  `name` VARCHAR(45) NULL DEFAULT NULL,
                                                                  `description` VARCHAR(16000) NULL DEFAULT NULL,
                                                                  `mainProject` VARCHAR(45) NOT NULL,
                                                                  PRIMARY KEY (`id`),
                                                                  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
