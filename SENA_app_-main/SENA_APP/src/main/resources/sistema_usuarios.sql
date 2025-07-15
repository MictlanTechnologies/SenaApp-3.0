-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema LSM
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema lsm
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `lsm` ;

-- -----------------------------------------------------
-- Schema lsm
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lsm` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
SHOW WARNINGS;
USE `lsm` ;

-- -----------------------------------------------------
-- Table `lsm`.`administradores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`administradores` (
  `Administrador_ID` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Correo_electronico` VARCHAR(100) NOT NULL,
  `Contraseña` VARCHAR(255) NOT NULL,
  `Fecha_registro` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Administrador_ID`),
  UNIQUE INDEX `Correo_electronico` (`Correo_electronico` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`lecciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`lecciones` (
  `Leccion_ID` INT NOT NULL AUTO_INCREMENT,
  `Título` VARCHAR(255) NOT NULL,
  `Descripción` TEXT NULL DEFAULT NULL,
  `Nivel` ENUM('básico', 'intermedio', 'avanzado') NOT NULL,
  `Tipo` ENUM('teoría', 'práctica', 'juego', 'evaluación') NOT NULL,
  `Enlace_multipmedia` VARCHAR(255) NULL DEFAULT NULL,
  `Fecha_creación` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `Administrador_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Leccion_ID`),
  INDEX `Administrador_ID` (`Administrador_ID` ASC) VISIBLE,
  CONSTRAINT `lecciones_ibfk_1`
    FOREIGN KEY (`Administrador_ID`)
    REFERENCES `lsm`.`administradores` (`Administrador_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`evaluaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`evaluaciones` (
  `Evaluacion_ID` INT NOT NULL AUTO_INCREMENT,
  `Título` VARCHAR(255) NOT NULL,
  `Descripción` TEXT NULL DEFAULT NULL,
  `Fecha_publicación` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `Tipo` ENUM('quiz', 'tarea', 'examen') NOT NULL,
  `Tiempo_limite` INT NULL DEFAULT NULL,
  `Nivel_dificultad` ENUM('básico', 'intermedio', 'avanzado') NOT NULL,
  `Leccion_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Evaluacion_ID`),
  INDEX `Leccion_ID` (`Leccion_ID` ASC) VISIBLE,
  CONSTRAINT `evaluaciones_ibfk_1`
    FOREIGN KEY (`Leccion_ID`)
    REFERENCES `lsm`.`lecciones` (`Leccion_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`usuarios` (
  `Usuario_ID` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Correo_electronico` VARCHAR(100) NOT NULL,
  `Contraseña` VARCHAR(255) NOT NULL,
  `Rol` ENUM('usuario', 'administrador') NOT NULL,
  `Fecha_registro` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `Nivel_dificultad_actual` ENUM('básico', 'intermedio', 'avanzado') NOT NULL,
  PRIMARY KEY (`Usuario_ID`),
  UNIQUE INDEX `Correo_electronico` (`Correo_electronico` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`logros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`logros` (
  `Logro_ID` INT NOT NULL AUTO_INCREMENT,
  `Descripción` TEXT NULL DEFAULT NULL,
  `Puntos_asociados` INT NULL DEFAULT NULL,
  `Fecha_obtención` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `Usuario_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Logro_ID`),
  INDEX `Usuario_ID` (`Usuario_ID` ASC) VISIBLE,
  CONSTRAINT `logros_ibfk_1`
    FOREIGN KEY (`Usuario_ID`)
    REFERENCES `lsm`.`usuarios` (`Usuario_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`modulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`modulos` (
  `Modulo_ID` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(255) NOT NULL,
  `Descripción` TEXT NULL DEFAULT NULL,
  `Leccion_ID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Modulo_ID`),
  INDEX `Leccion_ID` (`Leccion_ID` ASC) VISIBLE,
  CONSTRAINT `modulos_ibfk_1`
    FOREIGN KEY (`Leccion_ID`)
    REFERENCES `lsm`.`lecciones` (`Leccion_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `lsm`.`progreso_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lsm`.`progreso_usuarios` (
  `Progreso_ID` INT NOT NULL AUTO_INCREMENT,
  `Usuario_ID` INT NULL DEFAULT NULL,
  `Leccion_ID` INT NULL DEFAULT NULL,
  `Estado` ENUM('no iniciado', 'en progreso', 'completado') NOT NULL,
  `Fecha_inicio` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `Fecha_finalización` DATETIME NULL DEFAULT NULL,
  `Puntuación` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Progreso_ID`),
  INDEX `Usuario_ID` (`Usuario_ID` ASC) VISIBLE,
  INDEX `Leccion_ID` (`Leccion_ID` ASC) VISIBLE,
  CONSTRAINT `progreso_usuarios_ibfk_1`
    FOREIGN KEY (`Usuario_ID`)
    REFERENCES `lsm`.`usuarios` (`Usuario_ID`),
  CONSTRAINT `progreso_usuarios_ibfk_2`
    FOREIGN KEY (`Leccion_ID`)
    REFERENCES `lsm`.`lecciones` (`Leccion_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
