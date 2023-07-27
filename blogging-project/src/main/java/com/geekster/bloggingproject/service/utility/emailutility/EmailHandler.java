package com.geekster.bloggingproject.service.utility.emailutility;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {
    private static final String emailId = "gsreenath78@gmail.com";
    private static final String emailPassword = "ezemkitioocnhlba";

    public static void sendEmail(String toEmail,String subject,String body) throws Exception{

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailId,emailPassword);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailId));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Authentication Token sent successfully to " + toEmail);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
