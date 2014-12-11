package org.progress.crm.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.logic.Customers;
import org.progress.crm.logic.Settings;
import org.progress.crm.logic.Workers;

public class JavaMail {

    public static void sendMail(org.hibernate.Session db_session, String userEmail, String subject, String text) throws NoSuchProviderException, MessagingException {
        try {
            List<Settings> list = DaoFactory.getSettingsDao().getSettingsByWorkerId(db_session, 1);
            Map<String, String> map = new HashMap<>();

            for (Settings item : list) {
                map.put(item.getParameter(), item.getValue());
            }

            String SMTP_AUTH_USER = map.get("smtp.server.login");
            String SMTP_AUTH_PWD = map.get("smtp.server.password");
            String SMTP_HOST = map.get("smtp.server.host");
            String SMTP_PORT = map.get("smtp.server.port");

            Properties props = new Properties();
            props.put("mail.smtp.from", SMTP_AUTH_USER);
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.ssl", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setSubject(subject);
            message.setText(text);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSentDate(new Date());
            message.setSender(new InternetAddress(SMTP_AUTH_USER));
            message.setFrom("ProgressCRM");

            Transport transport = session.getTransport("smtp");
            transport.connect(SMTP_HOST, Integer.valueOf(SMTP_PORT), SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (SQLException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void apartamentsEmailNotifyAction(org.hibernate.Session db_session, Customers customer, String userSourceCode) {
        StringBuilder bodyTemplate = new StringBuilder();
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Выполненное Вами задание ");

        String subjectTemplate = "Задание принято";
        try {
            sendMail(db_session, customer.getCustomersEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void evaluateTask(org.hibernate.Session db_session, Customers customer, boolean flag) {
        StringBuilder bodyTemplate = new StringBuilder();
        String subjectTemplate = "";
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        try {
            sendMail(db_session, customer.getCustomersEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sucessReg(org.hibernate.Session db_session, Workers worker) {
        StringBuilder bodyTemplate = new StringBuilder();
        String subjectTemplate = "";
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(worker.getfName()).append(" ");
        bodyTemplate.append(worker.getmName()).append(" ");
        bodyTemplate.append(worker.getlName());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Ваш логин для входа: ").append(worker.getEmail()).append("\n\n");
        bodyTemplate.append("Best regards, ");
        try {
            sendMail(db_session, worker.getEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
