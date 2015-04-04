package org.progress.crm.util;

import java.util.Date;
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
import org.progress.crm.controllers.SettingsController;
import org.progress.crm.logic.Customers;
import org.progress.crm.logic.Workers;

public class JavaMail {

    public static void sendMail(String userEmail, String subject, String text) throws NoSuchProviderException, MessagingException {
        String SMTP_AUTH_USER = SettingsController.parameters.get("smtp.server.login");
        String SMTP_AUTH_PWD = SettingsController.parameters.get("smtp.server.password");
        String SMTP_HOST = SettingsController.parameters.get("smtp.server.host");
        String SMTP_PORT = SettingsController.parameters.get("smtp.server.port");

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
        if (userEmail.indexOf(',') > 0) {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
        } else {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        }
        message.setSentDate(new Date());
        message.setSender(new InternetAddress(SMTP_AUTH_USER));
        message.setFrom("ProgressCRM");
        Transport transport = session.getTransport("smtp");
        transport.connect(SMTP_HOST, Integer.valueOf(SMTP_PORT), SMTP_AUTH_USER, SMTP_AUTH_PWD);
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    public static void apartamentsEmailNotifyAction(Customers customer, String userSourceCode) {
        StringBuilder bodyTemplate = new StringBuilder();
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Выполненное Вами задание ");

        String subjectTemplate = "Задание принято";
        try {
            sendMail(customer.getCustomersEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void evaluateTask(Customers customer, boolean flag) {
        StringBuilder bodyTemplate = new StringBuilder();
        String subjectTemplate = "";
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        try {
            sendMail(customer.getCustomersEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sucessReg(Workers worker) {
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
            sendMail(worker.getEmail(), subjectTemplate, bodyTemplate.toString());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
