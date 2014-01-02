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
import org.progress.crm.logic.Customers;
import org.progress.crm.logic.Workers;

public class JavaMail {

    private static void sendMail(String userEmail, String subject, String text) throws NoSuchProviderException, MessagingException {
        String SMTP_AUTH_USER = "email@gmail.com";
        String SMTP_AUTH_PWD = "password";

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_AUTH_USER);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.sendpartial", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Transport transport = session.getTransport();
        transport.connect("smtp.gmail.com", 465, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        MimeMessage message = new MimeMessage(session);
        message.setSubject(subject);
        message.setText(text);
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSentDate(new Date());

        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
    }

    public static void taskAccepted(Customers customer, String userSourceCode) {
        StringBuilder bodyTemplate = new StringBuilder();
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Выполненное Вами задание ");
        bodyTemplate.append("принято для проверки администратором системы.\n\n");
        bodyTemplate.append("Требуется некоторое время для того, чтобы проверить Ваше задание.");
        bodyTemplate.append("Как только оно будет проверено, Вы получите сообщение на Вашу почту.\n\n");
        bodyTemplate.append("Best regards, Progress55.com");

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
        String subjectTemplate = "Задание оценено";
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(customer.getCustomersFname()).append(" ");
        bodyTemplate.append(customer.getCustomersMname()).append(" ");
        bodyTemplate.append(customer.getCustomersLname());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Выполненное Вами задание ");
        bodyTemplate.append("оценено администратором системы.\n\n");
        if (flag) {
            bodyTemplate.append("Поздравляем, Вы справились с заданием.");
        } else {
            bodyTemplate.append("Сожалеем, но Вы не справились с заданием.");
        }
        bodyTemplate.append("Best regards, ");
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
        String subjectTemplate = "Успешная регистрация";
        bodyTemplate.append("Здравствуйте, ");
        bodyTemplate.append(worker.getfName()).append(" ");
        bodyTemplate.append(worker.getmName()).append(" ");
        bodyTemplate.append(worker.getlName());
        bodyTemplate.append(", \n\n");
        bodyTemplate.append("Вы успешно зарегистрировались на сайте http://\n\n");
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
