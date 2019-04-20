package ro.sda.booking.commons;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {

    public void sendEmail(String message, String emailAddress, String subject) {
        //Setting up configurations for the email connection to the Google SMTP server using TLS

        final String username = "java2Iasi@gmail.com";
        final String password = "JavaIasi2018";

        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message mes = new MimeMessage(session);
            mes.setFrom(new InternetAddress("java2Iasi@gmail.com"));
            mes.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailAddress)
            );
            mes.setSubject(subject);
            mes.setText(message);

            Transport.send(mes);

            System.out.println("Email was sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}



