GRANT ALL PRIVILEGES ON *.* TO root@localhost IDENTIFIED BY 'abcd1234' WITH GRANT OPTION;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sef_schema` ;
USE `sef_schema` ;

-- -----------------------------------------------------
-- Table `sef_schema`.`employees`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`employees` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`employees` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `FIRST_NAME` VARCHAR(45) NOT NULL ,
  `LAST_NAME` VARCHAR(45) NOT NULL ,
  `MIDDLE_INITIAL` VARCHAR(1) NOT NULL ,
  `LEVEL` VARCHAR(45) NOT NULL ,
  `WORKFORCE` VARCHAR(45) NOT NULL ,
  `ENTERPRISE_ID` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;



/*!40000 ALTER TABLE `sef_schema`.`employees` DISABLE KEYS */;
INSERT INTO `sef_schema`.`employees` (`ID`,`FIRST_NAME`,`LAST_NAME`,`MIDDLE_INITIAL`,`LEVEL`,`WORKFORCE`,`ENTERPRISE_ID`) VALUES 
 (1,'Anna','Hrunova','A','Senior Java developer','Solutions','Accenture'),
 (2,'Viktorija','Moskalonoka','V','Senior Java developer','Solutions','Accenture'),
 (3,'Sergii','Putintsev','S','Senior Java developer','Solutions','Accenture'),
 (4,'Yurii','Bohomaz','Y','Senior Java developer','Solutions','Accenture');
/*!40000 ALTER TABLE `sef_schema`.`employees` ENABLE KEYS */;



-- -----------------------------------------------------
-- Table `sef_schema`.`projects`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`projects` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`projects` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(45) NOT NULL ,
  `DESCRIPTION` VARCHAR(255) NOT NULL ,
  `CLIENT` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = latin1;



/*!40000 ALTER TABLE `sef_schema`.`projects` DISABLE KEYS */;
INSERT INTO `sef_schema`.`projects` (`ID`,`NAME`,`DESCRIPTION`,`CLIENT`) VALUES 
 (1,'Employee Network','Developing of social network for employees','Accenture'),
 (3,'Facebook','Developing a huge social network','Facebook Latvia'),
 (4,'Gmail','Developing e-mail system','Google Latvia');
/*!40000 ALTER TABLE `sef_schema`.`projects` ENABLE KEYS */;




-- -----------------------------------------------------
-- Table `sef_schema`.`employee_project_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`employee_project_map` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`employee_project_map` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `EMPLOYEE_ID` INT(10) UNSIGNED NOT NULL ,
  `PROJECT_ID` INT(10) UNSIGNED NOT NULL ,
  `EMPLOYEE_ROLE` VARCHAR(255) NOT NULL ,
  `START_DATE` DATE NOT NULL ,
  `END_DATE` DATE NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_employee_project_map_employee` (`EMPLOYEE_ID` ASC) ,
  INDEX `fk_employee_project_map_projects1` (`PROJECT_ID` ASC) ,
  CONSTRAINT `fk_employee_project_map_employee`
    FOREIGN KEY (`EMPLOYEE_ID` )
    REFERENCES `sef_schema`.`employees` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_project_map_projects1`
    FOREIGN KEY (`PROJECT_ID` )
    REFERENCES `sef_schema`.`projects` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;



/*!40000 ALTER TABLE `sef_schema`.`employee_project_map` DISABLE KEYS */;
INSERT INTO `sef_schema`.`employee_project_map` (`ID`,`EMPLOYEE_ID`,`PROJECT_ID`,`EMPLOYEE_ROLE`,`START_DATE`,`END_DATE`) VALUES 
 (2,'1','1','Course Designer','2004-08-16','2005-08-16'),
 (3,'1','3','Team Lead for the development team','2006-01-09','2007-02-13'),
 (4,'1','4','Application architect for the SOS team','2007-03-14','2008-09-01'),
 (5,'4','4','Team Lead for the SOS team','2007-04-15','2008-09-03'),
 (6,'1','4','SME for SOS','2007-03-14','2008-09-01');
/*!40000 ALTER TABLE `sef_schema`.`employee_project_map` ENABLE KEYS */;



-- -----------------------------------------------------
-- Table `sef_schema`.`skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`skills` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`skills` (
  `ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `NAME` VARCHAR(45) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;


/*!40000 ALTER TABLE `sef_schema`.`skills` DISABLE KEYS */;
INSERT INTO `sef_schema`.`skills` (`ID`,`NAME`,`DESCRIPTION`) VALUES 
 (1,0x4A325345,0x4A6176612070726F6772616D6D696E67207573696E6720746865207374616E646172642065646974696F6E),
 (2,0x4A324D45,0x4A6176612070726F6772616D6D696E67207573696E67206D6F62696C65206C6962726172696573),
 (3,0x4A324545,0x4A6176612070726F6772616D6D696E67206F6E2061204A32454520706C6174666F726D207573696E67204A324545206C69627261726965732C207374616E646172647320616E6420746563686E6F6C6F67696573),
 (4,0x48544D4C,0x446576656C6F706D656E74206F6620737461746963207061676573207573696E672048544D4C),
 (5,0x4A617661736372697074,0x5363726970742070726F6772616D6D696E67207573696E67204A617661736372697074),
 (6,0x41646F62652050686F746F73686F70,0x557365206F662074686520746F6F6C20666F72206372656174696F6E20616E64206170706C69636174696F6E206F6620677261706869632065666665637473206F6E2073746174696320696D61676573);
/*!40000 ALTER TABLE `sef_schema`.`skills` ENABLE KEYS */;


-- -----------------------------------------------------
-- Table `sef_schema`.`employee_skill_map`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`employee_skill_map` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`employee_skill_map` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `employee_id` INT(10) UNSIGNED NOT NULL ,
  `skill_id` INT(10) UNSIGNED NOT NULL ,
  `rating` INT(10) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_employee_skill_map_employee1` (`employee_id` ASC) ,
  INDEX `fk_employee_skill_map_skill1` (`skill_id` ASC) ,
  CONSTRAINT `fk_employee_skill_map_employee1`
    FOREIGN KEY (`employee_id` )
    REFERENCES `sef_schema`.`employee` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_skill_map_skill1`
    FOREIGN KEY (`skill_id` )
    REFERENCES `sef_schema`.`skill` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


/*!40000 ALTER TABLE `sef_schema`.`employee_skill_map` DISABLE KEYS */;
INSERT INTO `sef_schema`.`employee_skill_map` (`id`,`employee_id`,`skill_id`,`rating`) VALUES 
 (1,'1','1',4),
 (2,'1','2',2),
 (3,'1','3',3),
 (4,'1','4',3),
 (5,'1','5',4),
 (8,'2','1',1),
 (9,'4','1',2);
/*!40000 ALTER TABLE `sef_schema`.`employee_skill_map` ENABLE KEYS */;

-- -----------------------------------------------------
-- Table `sef_schema`.`userregister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sef_schema`.`userregister` ;

CREATE  TABLE IF NOT EXISTS `sef_schema`.`userregister` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;

/*!40000 ALTER TABLE `sef_schema`.`userregister` DISABLE KEYS */;
INSERT INTO `sef_schema`.`userregister` (`id`,`username`,`password`) VALUES
  (1,'admin','admin'); 
/*!40000 ALTER TABLE `sef_schema`.`userregister` ENABLE KEYS */;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
