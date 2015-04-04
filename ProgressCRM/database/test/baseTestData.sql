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
