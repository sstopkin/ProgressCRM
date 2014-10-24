package org.progress.crm.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Workers;
import org.progress.crm.util.PDF;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ReportGeneratorDao {

    public File priceGen(Session session, int idWorker, String status) throws SQLException {
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String reportAuthorWorkerName = worker.getlName() + " " + worker.getfName() + " " + worker.getmName();

        List<Object> reportContent = session.createSQLQuery(
                "SELECT Apartaments.id, price, Rooms, CityDistrict, CityName,StreetName,HouseNumber,BuildingNumber, "
                + "Floor, Floors, SizeApartament,SizeLiving,SizeKitchen, "
                + "Description, customersFName,customersLName,customersMName, customersPhone, YearOfConstruction, "
                + "FName, MName, Lname\n" + "FROM progresscrm.Apartaments "
                + "LEFT JOIN progresscrm.Workers ON Apartaments.idWorkerTarget=Workers.id "
                + "LEFT JOIN progresscrm.Customers ON Apartaments.idCustomer=Customers.id "
                + "WHERE Apartaments.Deleted='0' AND Apartaments.Status='" + status + "' ORDER BY Apartaments.Rooms;").
                list();
        return PDF.GeneratePrice(reportContent, reportAuthorWorkerName);
    }

    public File xmlGen(Session session) throws SQLException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        List<Object> apartObjects = session.createSQLQuery(
                "SELECT Apartaments.id, price, Rooms, CityDistrict, CityName,StreetName,HouseNumber,BuildingNumber, "
                + "Floor, Floors, SizeApartament,SizeLiving,SizeKitchen, "
                + "Description, customersFName,customersLName,customersMName, customersPhone, YearOfConstruction, "
                + "FName, MName, Lname\n" + "FROM progresscrm.Apartaments "
                + "LEFT JOIN progresscrm.Workers ON Apartaments.idWorkerTarget=Workers.id "
                + "LEFT JOIN progresscrm.Customers ON Apartaments.idCustomer=Customers.id "
                + "WHERE Apartaments.Deleted='0' AND Apartaments.Status='" + 1 + "' AND Apartaments.isAD='" + 1 + "' ORDER BY Apartaments.id;").
                list();
//starting generate
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//Формат YML в качестве корневого использует элемент <yml_catalog>. 
        Document yml_catalog = docBuilder.newDocument();
        Element rootElement = yml_catalog.createElement("yml_catalog");
        rootElement.setAttribute("date", new SimpleDateFormat("YYYY-MM-DD hh:mm").format(new Date()));
        yml_catalog.appendChild(rootElement);

        Element shop = yml_catalog.createElement("shop");
        rootElement.appendChild(shop);
//        Элемент <shop> содержит описание магазина и его товарных предложений.
//<shop>
//    <name>BestShop</name>
//    <company>Best online seller Inc.</company>
//    <url>http://best.seller.ru/</url>
//    <platform>CMS</platform>
//    <version>2.3</version>
//    <agency>Agency</agency>
//    <email>CMS@CMS.ru</email>
//    <currencies> ... </currencies>
//    <categories> ... </categories>
//    <cpa>0</cpa>
//    <offers> ... </offers>
//</shop>

        Element shopName = yml_catalog.createElement("name");
        shopName.appendChild(yml_catalog.createTextNode("Прогресс"));
        shop.appendChild(shopName);
        Element companyName = yml_catalog.createElement("company");
        companyName.appendChild(yml_catalog.createTextNode("Прогресс"));
        shop.appendChild(companyName);
        Element url = yml_catalog.createElement("url");
        url.appendChild(yml_catalog.createTextNode("http://progress55.com"));
        shop.appendChild(url);
        Element platform = yml_catalog.createElement("platform");
        platform.appendChild(yml_catalog.createTextNode("ProgressCRM"));
        shop.appendChild(platform);
        Element version = yml_catalog.createElement("version");
        version.appendChild(yml_catalog.createTextNode("1.0"));
        shop.appendChild(version);
        Element agency = yml_catalog.createElement("agency");
        agency.appendChild(yml_catalog.createTextNode("Progress"));
        shop.appendChild(agency);
        Element email = yml_catalog.createElement("email");
        email.appendChild(yml_catalog.createTextNode("progress55.com"));
        shop.appendChild(email);

        //        Элемент <currencies> задает список курсов валют магазина. Каждая из валют описывается отдельным элементом <currency>.
        Element currencies = yml_catalog.createElement("currencies");
        shop.appendChild(currencies);

        Element currency = yml_catalog.createElement("currency");
        currencies.appendChild(currency);
        Attr RUR = yml_catalog.createAttribute("id");
        RUR.setValue("RUR");
        currency.setAttributeNode(RUR);
        Attr rate = yml_catalog.createAttribute("rate");
        rate.setValue("1");
        currency.setAttributeNode(rate);

//      В элементе <categories> содержится список категорий магазина.         
        Element categories = yml_catalog.createElement("categories");
        shop.appendChild(categories);

        Element category = yml_catalog.createElement("category");
        categories.appendChild(category);
        category.appendChild(yml_catalog.createTextNode("Квартиры"));
        Attr categoryListId = yml_catalog.createAttribute("id");
        categoryListId.setValue("1");
        category.setAttributeNode(categoryListId);

//      В элементе <local_delivery_cost> указывается стоимость доставки для своего региона.
        Element localDeliveryCost = yml_catalog.createElement("local_delivery_cost");
        localDeliveryCost.appendChild(yml_catalog.createTextNode("0"));
        shop.appendChild(localDeliveryCost);

//      В элементе <offers> содержится список товарных предложений магазинов. Каждое товарное предложение описывается отдельным элементом <offer>.
        Element offers = yml_catalog.createElement("offers");
        shop.appendChild(offers);

        //foreach
        for (int i = 0; i < apartObjects.size(); i++) {
            //Цена Комнат АО Адрес Этаж Площадь Описание Контакты собственника Год Ведущий риэлтор
            //id,price,Rooms,CityDistrict,CityName,StreetName,HouseNumber,BuildingNumber,Floor,Floors,SizeApartament,SizeLiving,SizeKitchen,
            //Description,customersFName,customersLName,customersMName,customersPhone,YearOfConstruction,FName,MName,Lname
            Object[] objects = (Object[]) apartObjects.get(i);

            Element offer = yml_catalog.createElement("offer");
            offers.appendChild(offer);
            Attr offerId = yml_catalog.createAttribute("id");

            offerId.setValue(objects[0].toString());//id 0
            offer.setAttributeNode(offerId);
            Attr available = yml_catalog.createAttribute("available");
            available.setValue("true");
            offer.setAttributeNode(available);

            Element price = yml_catalog.createElement("price");
            //price 1
            price.appendChild(yml_catalog.createTextNode(objects[1].toString()));
            offer.appendChild(price);

            Element currencyId = yml_catalog.createElement("currencyId");
            currencyId.appendChild(yml_catalog.createTextNode("RUR"));
            offer.appendChild(currencyId);

            Element categoryId = yml_catalog.createElement("categoryId");
            categoryId.appendChild(yml_catalog.createTextNode("1"));
            offer.appendChild(categoryId);

            Element market_category = yml_catalog.createElement("market_category");
            market_category.appendChild(yml_catalog.createTextNode("804"));
            offer.appendChild(market_category);

            StringBuilder halfDescription = new StringBuilder();
            //rooms 2
            halfDescription.append(objects[2].toString());
            //CityDistrict 3
            halfDescription.append(objects[3].toString());
            //address 4-7
            StringBuilder address = new StringBuilder();
            address.append(objects[4]).append(" ").append(objects[5]).append(" ").append(objects[6]).append(" ").append(objects[7]);
            halfDescription.append(address.toString());
            //floor/floors 8-9
            StringBuilder floorInfo = new StringBuilder();
            floorInfo.append(" Этаж ").append(objects[8].toString()).append(" / ").append(objects[9].toString());
            halfDescription.append(floorInfo.toString());
            //size apartObjects living kitchen 10-12
            StringBuilder apartsSize = new StringBuilder();
            apartsSize.append(" Площадь О/Ж/К ").append(objects[10]).append(" / ").append(objects[11]).append(" / ").append(objects[12]);
            //YearOfConstruction 18
            halfDescription.append(" Год постройки: ").append(objects[18].toString()).append(" ");

            halfDescription.append(apartsSize.toString());

            Element name = yml_catalog.createElement("name");
            name.appendChild(yml_catalog.createTextNode(halfDescription.toString()));
            offer.appendChild(name);

            StringBuilder fullDescription = new StringBuilder();

//            //customer 14-17
//            StringBuilder customer = new StringBuilder();
//            customer.append(objects[14]).append(" ").append(objects[15]).append(" ").append(objects[16]).append(" ").
//                    append(objects[17]);
//            fullDescription.append(customer.toString());
            //Description 13
            fullDescription.append(objects[13].toString());
//            //worker 19-21
//            StringBuilder worker = new StringBuilder();
//            worker.append(objects[19]).append(" ").append(objects[20]).append(" ").append(objects[21]);
//            fullDescription.append(worker.toString());

            Element description = yml_catalog.createElement("description");
            description.appendChild(yml_catalog.createTextNode(fullDescription.toString()));
            offer.appendChild(description);

        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(yml_catalog);

        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult(outWriter);
        transformer.transform(source, result);
        StringBuffer sb = outWriter.getBuffer();
        String finalstring = sb.toString();
        System.out.println(finalstring);

        File f = new File("price.xml");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(finalstring);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
        return f;
    }

    public File apartamentsPageGen(Session session, Integer apartamentsId, int idWorker) throws SQLException {
        Apartaments apartament = DaoFactory.getApartamentsDao().getApartamentsById(session, apartamentsId);
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String workerName = worker.getlName() + " " + worker.getfName() + " " + worker.getmName();
        return PDF.GenerateApartamentsPage(apartament, workerName);
    }
}
