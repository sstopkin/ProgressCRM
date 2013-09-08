package org.progress.crm.api;
//@Path("search")

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public class LiveSearchApi {

    @GET
    public Response news(@QueryParam("query") String request) {
        StringBuilder str = new StringBuilder();
//        str.append("[");
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        List list = session.createSQLQuery("SELECT * FROM progresscrm.Streets WHERE StreetName like '%" + request + "%'").addEntity(Streets.class).list();
//        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//            Streets streets = (Streets) iterator.next();
//            str.append("'")
//                    .append("<a href=").append("http://omsk.gorodskaya-spravka.com/")
//                    .append(streets.getStreetUrl())
//                    .append(">")
//                    .append(streets.getStreetName())
//                    .append("</a>")
//                    .append("',");
//        }
        str.deleteCharAt(str.length() - 1);
        str.append("]");

//        Streets street = new Streets();
//        street.setStreetName("123");
//        session.save(street);
//        session.getTransaction().commit();
        return Response.ok(str.toString()).build();
    }
}