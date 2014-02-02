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
`IsActive` TINYINT(1) NOT NULL DEFAULT true ,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `EmailIndex` (`Email` ASC) );

-- -----------------------------------------------------
-- Table `progresscrm`.`Filespaces`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Filespaces` (
`id` INT NOT NULL AUTO_INCREMENT ,
`FilespacesUUID` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`Name` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `UUIDFilespaceIndex` (`FilespacesUUID` ASC) );

-- -----------------------------------------------------
-- Table `progresscrm`.`Files`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Files` (
`id` INT NOT NULL AUTO_INCREMENT ,
`FileUUID` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Filename` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`idFilespace` INT NOT NULL ,
`idWorker` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
FOREIGN KEY (idFilespace) REFERENCES Filespaces(id),
PRIMARY KEY (`id`) ,
UNIQUE INDEX `UUIDFilesIndex` (`FileUUID` ASC) );

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
`customersYearOfBirthday` INT NOT NULL,
`customersSex` INT NOT NULL,
`customersPhone` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersEmail` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`customersAddress` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`customersExtra` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`Announcements`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Announcements` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Street` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`HouseNumber` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Rooms` INT NOT NULL ,
`Floor` INT NOT NULL ,
`Floors` INT NOT NULL ,
`Phone` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`idWorker` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`AnnouncementsRent`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`AnnouncementsRent` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Street` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`HouseNumber` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Rooms` INT NOT NULL ,
`Floor` INT NOT NULL ,
`Floors` INT NOT NULL ,
`Price` INT NOT NULL ,
`Phone` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`idWorker` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`CustomersRent`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`CustomersRent` (
`id` INT NOT NULL AUTO_INCREMENT ,
`idCustomer` INT NOT NULL ,
`Status` INT NOT NULL ,
`Assigned` INT NOT NULL ,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`idWorker` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`LastModify` TIMESTAMP NOT NULL,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
FOREIGN KEY (idCustomer) REFERENCES Customers(id),
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`AnnouncementsCalls`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`AnnouncementsCalls` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
`idWorker` INT NOT NULL ,
`AnnouncementsId` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (AnnouncementsId) REFERENCES Announcements(id),
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `progresscrm`.`AnnouncementsRentCalls`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`AnnouncementsRentCalls` (
`id` INT NOT NULL AUTO_INCREMENT ,
`Description` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
`idWorker` INT NOT NULL ,
`AnnouncementsRentId` INT NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (AnnouncementsRentId) REFERENCES AnnouncementsRent(id),
FOREIGN KEY (idWorker) REFERENCES Workers(id),
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
`ApartamentLan` VARCHAR(10) CHARACTER SET utf8,
`ApartamentLon` VARCHAR(10) CHARACTER SET utf8,


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

`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`LastModify` TIMESTAMP NOT NULL, 
`idWorker` INT NOT NULL ,
`idCustomer` INT NOT NULL ,
`IsApproved` TINYINT(1) NOT NULL DEFAULT false ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,

`idFilespace` INT DEFAULT -1,

-- FOREIGN KEY (idFilespace) REFERENCES Filespaces(id),
FOREIGN KEY (idWorker) REFERENCES Workers(id),
FOREIGN KEY (idCustomer) REFERENCES Customers(id),
PRIMARY KEY (`id`) ,
INDEX `idCustomerIndex` (`idWorker` ASC));

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
-- Table `progresscrm`.`Planner`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Planner` (
`id` INT NOT NULL AUTO_INCREMENT ,
`idWorker` INT NOT NULL ,
`TaskType` INT NOT NULL ,
`TaskId` INT NOT NULL ,
`TaskDescription` MEDIUMTEXT CHARACTER SET utf8 NOT NULL ,
`CreationDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
`TaskDate` TIMESTAMP NOT NULL, 
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
FOREIGN KEY (idWorker) REFERENCES Workers(id),
PRIMARY KEY (`id`));

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

-- NULL user --
INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('null', 'null','null', 'null', 0, 'null', true, true);

-- test users--
INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('adminFName', 'adminSName','adminLName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, 'admin@progress55.com', false, true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('userfName', 'usersName','userlName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 2, 'user@progress55.com', false, true);

-- real user--
INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Андрей', 'Геннадьевич','Бармашов', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, '380420@progress55.com', false, true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Жанна', 'Витальевна','Тутубалина', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, '386495@progress55.com', false, true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Ксения', 'Вадимовна','Тирон', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 2, '384490@progress55.com', false, true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Екатерина', 'Николаевна','Верхозина', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 2, '634490@progress55.com', false, true);