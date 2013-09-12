CREATE SCHEMA IF NOT EXISTS `progresscrm` DEFAULT CHARACTER SET utf8;
USE `progresscrm`;

-- -----------------------------------------------------
-- Table `progresscrm`.`Workers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Workers` (
`id` INT NOT NULL AUTO_INCREMENT ,
`FName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`MName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`LName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`PwdHash` VARCHAR(40) CHARACTER SET utf8 NOT NULL ,
`Permissions` INT NOT NULL DEFAULT 1,
`Email` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `EmailIndex` (`Email` ASC) );

-- -----------------------------------------------------
-- Table `progresscrm`.`Customers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Customers` (
`id` INT NOT NULL AUTO_INCREMENT ,
`FName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`MName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`LName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`Apartaments`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Apartaments` (
`id` INT NOT NULL AUTO_INCREMENT ,

`CityName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`StreetName` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`HouseNumber` VARCHAR(50) CHARACTER SET utf8,
`BuildingNumber` VARCHAR(50) CHARACTER SET utf8,
`KladrId` VARCHAR(50) CHARACTER SET utf8,

`TypeOfSales` TINYINT(1) NOT NULL DEFAULT 0,
`Price` INT NOT NULL ,
`CityDistrict`INT NOT NULL ,
`Floor` INT NOT NULL,
`Floors` INT NOT NULL,
`Material` INT NOT NULL ,
`SizeApartament` INT NOT NULL ,
`SizeLiving` INT NOT NULL ,
`SizeKitchen` INT NOT NULL ,
`Balcony` INT NOT NULL ,
`Loggia` INT NOT NULL ,
`YearOfConstruction` INT NOT NULL,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`MethodOfPurchase_PureSale` TINYINT(1) NOT NULL DEFAULT false ,
`MethodOfPurchase_Mortgage` TINYINT(1) NOT NULL DEFAULT false ,
`MethodOfPurchase_Exchange` TINYINT(1) NOT NULL DEFAULT false ,
`MethodOfPurchase_Rent` TINYINT(1) NOT NULL DEFAULT false ,
`RePlanning` TINYINT(1) NOT NULL DEFAULT false ,

`ClientPhone` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`ClientDescription` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,

`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`LastModify` TIMESTAMP NOT NULL, 
`idWorker` INT NOT NULL ,
`IsApproved` TINYINT(1) NOT NULL DEFAULT false ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,

FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`) ,
INDEX `idCustomerIndex` (`idWorker` ASC));

-- -----------------------------------------------------
-- Table `progresscrm`.`ApartamentsPhoto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progresscrm`.`ApartamentsPhoto` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Filename` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Description` VARCHAR(50) CHARACTER SET utf8,
`ApartamentsId` INT NOT NULL ,
FOREIGN KEY (ApartamentsId) REFERENCES Apartaments(id),
PRIMARY KEY (`id`) ,
UNIQUE INDEX `FilenameIndex` (`Filename` ASC) );

-- -----------------------------------------------------
-- Table `progresscrm`.`HelpDesk`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`HelpDesk` (
`id` INT NOT NULL AUTO_INCREMENT ,
`idWorker` INT NOT NULL ,
`Request` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Text` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Status` TINYINT(1) NOT NULL DEFAULT 0,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`LastModify` TIMESTAMP NOT NULL,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`) ,
INDEX `idWorkerIndex` (`idWorker` ASC));


-- -----------------------------------------------------
-- Table `progresscrm`.`Calls`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Calls` (
`id` INT NOT NULL AUTO_INCREMENT ,
`ApartamentsId` INT NOT NULL ,
`Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
FOREIGN KEY (ApartamentsId) REFERENCES Apartaments(id),
PRIMARY KEY (`id`) ,
INDEX `ApartamentsIdIndex` (`ApartamentsId` ASC));

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted) 
	VALUES ('fName', 'sName','lName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, 'user@progress55.com', false);
