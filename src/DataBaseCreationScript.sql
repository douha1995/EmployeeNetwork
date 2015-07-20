CREATE DATABASE  IF NOT EXISTS `bootcamp_project` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bootcamp_project`;


DROP TABLE IF EXISTS `employee_entities`;
CREATE TABLE IF NOT EXISTS `bootcamp_project`.`employee_entities` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstName` CHAR(32) NOT NULL,
  `lastName` CHAR(32) NOT NULL,
  `middleInitial` CHAR(32) NOT NULL,
  `level` CHAR(32) NOT NULL,
  `workForce` CHAR(32) NOT NULL,
  `enterpriseID` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB;