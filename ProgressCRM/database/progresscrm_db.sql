CREATE SCHEMA IF NOT EXISTS `progresscrm` DEFAULT CHARACTER SET utf8;
USE `progresscrm`;

-- -----------------------------------------------------
-- Table `progresscrm`.`Filespace`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progresscrm`.`Filespace` (
`id` INT NOT NULL AUTO_INCREMENT ,
`UUID` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Name` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,
`Deleted` TINYINT(1) NOT NULL DEFAULT false ,
PRIMARY KEY (`id`) ,
UNIQUE INDEX `UUIDIndex` (`UUID` ASC) );

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

`idFilespace` VARCHAR(50) CHARACTER SET utf8 NOT NULL ,

FOREIGN KEY (idFilespace) REFERENCES Filespace(id),
FOREIGN KEY (idWorker) REFERENCES Workers(id),
FOREIGN KEY (idCustomer) REFERENCES Customers(id),
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

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('expertfName', 'expertsName','expertlName', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 1, 'expert@progress55.com', false, true);

-- real user--
INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Андрей', 'Геннадьевич','Бармашов', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, 'abar71@progress55.com', false, true);

INSERT INTO progresscrm.Workers (FName, MName, LName, PwdHash, Permissions, Email, Deleted, IsActive) 
	VALUES ('Жанна', 'Витальевна','Тутубалина', 'f9a7c6df341325822e3ea264cfe39e5ef8c73aa4', 3, 'zvt@progress55.com', false, true);

/*
-- Query: SELECT * FROM progresscrm.Customers
LIMIT 0, 1000
*/

INSERT INTO `progresscrm`.`Customers`
(`id`,`customersLname`,`customersFname`,`customersMname`,`customersMonthOfBirthday`,`customersDayOfBirthday`,`customersYearOfBirthday`,
`customersSex`,`customersPhone`,`customersEmail`,`customersAddress`,`customersExtra`,`Deleted`)
VALUES(1,'fname','customersFname','customersMname',4,26,1986,1,'customersPhone','ss@ss.ss','customersAddress','customersExtra',0);

/*
-- Query: SELECT * FROM progresscrm.Apartaments
LIMIT 0, 1000
*/
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`)  VALUES (1,'г. Омск','ул. Пушкина','д. 67','','123','г. Омск, ул. Пушкина, д. 67',1,1,123,5,6,6,23,3,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'2013-09-23 12:40:49','2013-09-23 12:40:49',2,1,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`) VALUES (2,'г. Омск','пр-кт. Культуры','д. 2','','123','644029, г. Омск, пр-кт. Культуры, д. 2',1,2,123,5,1,5,24,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'2013-09-23 12:43:34','2013-09-23 12:43:34',2,1,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`) VALUES (3,'г. Омск','пр-кт. Культуры','д. 2','','123','644029, г. Омск, пр-кт. Культуры, д. 2',1,2,123,5,1,5,34,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'2013-09-23 12:43:34','2013-09-23 12:43:34',2,1,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`) VALUES (4,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,3,123,5,1,5,1,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'124321143','2013-09-23 12:44:16',2,1,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`) VALUES (5,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,2,123,5,1,5,12,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'2013-09-23 12:44:16','2013-09-23 12:44:16',2,1,0,0);
INSERT INTO `Apartaments` (`id`,`CityName`,`StreetName`,`HouseNumber`,`BuildingNumber`,`KladrId`,`ShortAddress`,`TypeOfSales`,`Rooms`,`Price`,`CityDistrict`,`Floor`,`Floors`,`RoomNumber`,`Material`,`SizeApartament`,`SizeLiving`,`SizeKitchen`,`Balcony`,`Loggia`,`YearOfConstruction`,`Description`,`MethodOfPurchase_PureSale`,`MethodOfPurchase_Mortgage`,`MethodOfPurchase_Exchange`,`MethodOfPurchase_Rent`,`RePlanning`,`CreationDate`,`LastModify`,`idWorker`,`idCustomer`,`IsApproved`,`Deleted`) VALUES (6,'г. Омск','пр-кт. Мира','д. 92','','123','644089, г. Омск, пр-кт. Мира, д. 92',2,3,123,5,1,5,3,2,234,123,23,0,0,124,'текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст текст ',0,0,0,0,0,'2013-09-23 12:44:16','2013-09-23 12:44:16',2,1,0,0);

/*
-- Query: SELECT * FROM progresscrm.Calls
LIMIT 0, 1000
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
INSERT INTO `progresscrm`.`News` (`id`, `idWorker`, `Header`, `Text`, `CreationDate`, `LastModify`, `Deleted`) VALUES (3, 4, 'Названо пять самых дорогих коттеджей в Омске', 'На середину ноября 2013 года по самой высокой цене в Омске продают коттедж в Центральном округе на ул. Судоремонтная,14. Двухэтажный кирпичный особняк общей площадью 395,3 квадратных метров стоит 47 миллионов рублей. 

Второе место в рейтинге занимает 7-комнатный коттедж, расположенный в Советском округе, рядом с ОмГТУ. Площадь трехэтажного кирпичного дома равна 600 квадратным метрам. Из объявления следует, что интерьер выполнен в классическом стиле. В доме расположены большая гостиная с камином, гостиная-кинозал, кабинет, светлые спальни и гостевые комнаты, зимний сад, бильярдная, служебные помещения, просторная кухня-столовая. Купить такой коттедж можно за 39 миллионов рублей. 

За 35 миллионов рублей продают коттедж общей площадью 380 квадратных метров в Кировском округе, ул. 12-я Любинская. Здесь есть бассейн, баня, сауна, гараж, а сам дом, по словам продавца, с евроремонтом, теплым полом и эксклюзивной мебелью. 

На четвертом месте коттедж в Центральном округе по ул. Березовая общей площадью 432 квадратных метра. Цена на такое жилье также равна 35 миллионам рублей, однако стоимость за квадратный метр меньше, чем в предыдущем случае. В доме есть 3 спальни, столовая 25 кв.м., каминный зал. Имеется зона отдыха: бильярдная, русская баня на дровах, спортзал, помещение под кабинет, библиотеку. Гараж рассчитан на 4 автомобиля. 

Замыкает пятерку лидеров кирпичный  коттедж площадью 516 "квадратов", расположенный на пересечении улиц Вавилова/7-я Северная. В доме евроремонт, подвесные потолки, подключена сигнализация. Продавец предлагает использовать здание не только в качестве жилья, но и как помещение для бизнеса. Стоит такой особняк, по словам продавца, 28 миллионов 990 тысяч рублей. ', '2013-12-20', '2013-12-20', false);


/*
-- Query: SELECT * FROM progresscrm.HelpDesk
LIMIT 0, 1000
*/
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (1,2,'Text text text text text text text text text text text text text text',' text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text',1,'2013-09-23 12:58:21','2013-09-23 12:58:21',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (2,2,' text text text text text text text text text text text text',' text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text',1,'2013-09-23 12:58:41','2013-09-23 12:58:41',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (3,2,' text text text',' text text text text text text',1,'2013-09-23 12:58:47','2013-09-23 12:58:47',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (4,2,' text',' text',1,'2013-09-23 12:58:50','2013-09-23 12:58:50',0);
INSERT INTO `HelpDesk` (`id`,`idWorker`,`Request`,`Text`,`Status`,`CreationDate`,`LastModify`,`Deleted`) VALUES (5,2,' text text text text',' text text text text',1,'2013-09-23 12:58:55','2013-09-23 12:58:55',0);

/*
-- Query: SELECT * FROM progresscrm.Announcements
LIMIT 0, 1000
*/
INSERT INTO `Announcements` (`id`,`Street`,`HouseNumber`,`Rooms`,`Floor`,`Floors`,`Phone`,`Description`,`idWorker`,`CreationDate`,`Deleted`) VALUES (1,'Челюскинцев','12',2,5,5,'89134563211','3+',3,'2013-11-05 23:47:54',0);
INSERT INTO `Announcements` (`id`,`Street`,`HouseNumber`,`Rooms`,`Floor`,`Floors`,`Phone`,`Description`,`idWorker`,`CreationDate`,`Deleted`) VALUES (2,'Северная','7/2',1,2,16,'89081020123','текст',3,'2013-11-05 23:48:40',0);

/*
-- Query: SELECT * FROM progresscrm.AnnouncementsCalls
LIMIT 0, 1000
*/
INSERT INTO `AnnouncementsCalls` (`id`,`Description`,`Deleted`,`idWorker`,`AnnouncementsId`,`CreationDate`) VALUES (1,'отказался от услуг агенства',0,3,2,'2013-11-05 23:49:56');
INSERT INTO `AnnouncementsCalls` (`id`,`Description`,`Deleted`,`idWorker`,`AnnouncementsId`,`CreationDate`) VALUES (2,'просит время подумать',0,3,2,'2013-11-05 23:50:07');

/*
-- Query: SELECT * FROM progresscrm.AnnouncementsRent
LIMIT 0, 1000
*/
INSERT INTO `AnnouncementsRent` (`id`,`Street`,`HouseNumber`,`Rooms`,`Floor`,`Floors`,`Price`,`Phone`,`Description`,`idWorker`,`CreationDate`,`Deleted`) VALUES (1,'Енисейская','23',1,1,2,6000,'89607896523','стремная',3,'2013-11-25 23:12:44',0);
INSERT INTO `AnnouncementsRent` (`id`,`Street`,`HouseNumber`,`Rooms`,`Floor`,`Floors`,`Price`,`Phone`,`Description`,`idWorker`,`CreationDate`,`Deleted`) VALUES (2,'3й Разъезд','5',2,9,9,9000,'89874561265','сделан ремонт',3,'2013-11-25 23:29:11',0);