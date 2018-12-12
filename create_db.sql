-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema campo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema campo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `campo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `campo` ;

-- -----------------------------------------------------
-- Table `campo`.`Estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campo`.`Estado` (
  `id_estado` INT UNSIGNED NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_estado`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campo`.`TipoSuelo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campo`.`TipoSuelo` (
  `id_tipo_suelo` INT UNSIGNED NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_tipo_suelo`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campo`.`Campo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campo`.`Campo` (
  `id_campo` INT UNSIGNED NOT NULL COMMENT '',
  `nombre` VARCHAR(45) NOT NULL COMMENT '',
  `superficie` DECIMAL NOT NULL COMMENT '',
  `id_estado` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id_campo`)  COMMENT '',
  INDEX `fk_Campo_Estado1_idx` (`id_estado` ASC)  COMMENT '',
  CONSTRAINT `fk_Campo_Estado1`
    FOREIGN KEY (`id_estado`)
    REFERENCES `campo`.`Estado` (`id_estado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `campo`.`Lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `campo`.`Lote` (
  `id_lote` INT UNSIGNED NOT NULL COMMENT '',
  `numero` INT NOT NULL COMMENT '',
  `superficie` DECIMAL NOT NULL COMMENT '',
  `id_campo` INT UNSIGNED NOT NULL COMMENT '',
  `id_tipo_suelo` INT UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id_lote`)  COMMENT '',
  INDEX `fk_Lote_Campo_idx` (`id_campo` ASC)  COMMENT '',
  INDEX `fk_Lote_TipoSuelo1_idx` (`id_tipo_suelo` ASC)  COMMENT '',
  CONSTRAINT `fk_Lote_Campo`
    FOREIGN KEY (`id_campo`)
    REFERENCES `campo`.`Campo` (`id_campo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lote_TipoSuelo1`
    FOREIGN KEY (`id_tipo_suelo`)
    REFERENCES `campo`.`TipoSuelo` (`id_tipo_suelo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
