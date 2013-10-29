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
`customersLname` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersFname` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersMname` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersMonthOfBirthday` INT NOT NULL,
`customersDayOfBirthday` INT NOT NULL,
`customersYearOfBirthday` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersSex` INT NOT NULL,
`customersPhone` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersEmail` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersAddress` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`customersExtra` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
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
`ShortAddress` VARCHAR(100) CHARACTER SET utf8,


`TypeOfSales` TINYINT(1) NOT NULL DEFAULT 0,
`Rooms` INT NOT NULL ,
`Price` INT NOT NULL ,
`CityDistrict`INT NOT NULL ,
`Floor` INT NOT NULL,
`Floors` INT NOT NULL,
`RoomNumber` INT NOT NULL,
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
`ClientDescription` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,

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
`Description` MEDIUMTEXT CHARACTER SET utf8,
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
`idWorker` INT NOT NULL ,
FOREIGN KEY (ApartamentsId) REFERENCES Apartaments(id),
PRIMARY KEY (`id`) ,
INDEX `ApartamentsIdIndex` (`ApartamentsId` ASC));

-- -----------------------------------------------------
-- Table `progresscrm`.`News`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`News` (
`id` INT NOT NULL AUTO_INCREMENT ,
`idWorker` INT NOT NULL ,
`Header` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Text` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`LastModify` TIMESTAMP NOT NULL,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`) ,
INDEX `idWorkerIndex` (`idWorker` ASC));

-- -----------------------------------------------------
-- Table `progresscrm`.`LogService`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`LogService` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`idWorker` INT NOT NULL ,
`ActionCode` INT NOT NULL,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
PRIMARY KEY (`id`) ,
INDEX `idWorkerIndex` (`idWorker` ASC));

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted) 
	VALUES ('null', 'null','null', 'null', 0, 'null', true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted) 
	VALUES ('adminFName', 'adminSName','adminLName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, 'admin@progress55.com', false);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted) 
	VALUES ('userfName', 'usersName','userlName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 2, 'user@progress55.com', false);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted) 
	VALUES ('expertfName', 'expertsName','expertlName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 1, 'expert@progress55.com', false);

/*
-- Query: SELECT * FROM progresscrm.Apartaments
LIMIT 0, 1000

-- Date: 2013-09-23 12:45
*/
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (1,'г. Омск','ул. Пушкина','д. 67','','123','г. Омск, ул. Пушкина, д. 67',1,1,123,5,6,6,23,3,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:40:49','2013-09-23 12:40:49',2,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (2,'г. Омск','пр-кт. Культуры','д. 2','','123','644029, г. Омск, пр-кт. Культуры, д. 2',1,2,123,5,1,5,24,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:43:34','2013-09-23 12:43:34',2,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (3,'г. Омск','пр-кт. Культуры','д. 2','','123','644029, г. Омск, пр-кт. Культуры, д. 2',1,2,123,5,1,5,34,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:43:34','2013-09-23 12:43:34',2,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (4,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,3,123,5,1,5,1,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:44:16','2013-09-23 12:44:16',2,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (5,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,2,123,5,1,5,12,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:44:16','2013-09-23 12:44:16',2,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`ClientPhone`,`ClientDescription`,`CreationDate`,`LastModify`,`idWorker`,`IsApproved`,`Deleted`) VALUES (6,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,3,123,5,1,5,3,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','Клиент','2013-09-23 12:44:16','2013-09-23 12:44:16',2,0,0);

/*
-- Query: SELECT * FROM progresscrm.Calls
LIMIT 0, 1000

-- Date: 2013-09-23 12:48
*/
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (1,1,'2013-09-23 12:47:55','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (2,4,'2013-09-23 12:47:59','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (3,1,'2013-09-23 12:48:02','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (4,3,'2013-09-23 12:48:05','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (5,1,'2013-09-23 12:48:07','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (6,5,'2013-09-23 12:48:10','asd',2);
INSERT INTO `Calls` (`id`,`ApartamentsId`,`Date`,`Description`,`idWorker`) VALUES (7,1,'2013-09-23 12:48:13','asd',2);

INSERT INTO `progresscrm`.`News` (`id`, `idWorker`, `Header`, `Text`, `CreationDate`, `LastModify`, `Deleted`) VALUES (1, 1, 'News', 'text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text ', '2013-12-20', '2013-12-20', false);
INSERT INTO `progresscrm`.`News` (`id`, `idWorker`, `Header`, `Text`, `CreationDate`, `LastModify`, `Deleted`) VALUES (2, 2, 'News news news', '2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20 2013-12-20', '2013-12-20', '2013-12-20', false);

/*
-- Query: SELECT * FROM progresscrm.HelpDesk
LIMIT 0, 1000

-- Date: 2013-09-23 12:59
*/
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (1,2,'Text text text text text text text text text text text text text text',' text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text',1,'2013-09-23 12:58:21','2013-09-23 12:58:21',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (2,2,' text text text text text text text text text text text text',' text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text',1,'2013-09-23 12:58:41','2013-09-23 12:58:41',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (3,2,' text text text',' text text text text text text',1,'2013-09-23 12:58:47','2013-09-23 12:58:47',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (4,2,' text',' text',1,'2013-09-23 12:58:50','2013-09-23 12:58:50',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (5,2,' text text text text',' text text text text',1,'2013-09-23 12:58:55','2013-09-23 12:58:55',0);