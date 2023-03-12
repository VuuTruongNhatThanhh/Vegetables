package vn.edu.hcmuaf.fit.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendingEmail {
    private String userEmail;
    private String hash;

    public SendingEmail(String userEmail, String hash) {
        this.userEmail = userEmail;
        this.hash = hash;
    }
    public void sendMail(){
        String email ="nhatthanh28012002@gmail.com";
        String pword = "zwlaajyiymdbsehu";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, pword);// Put your email
                // id and
                // password here
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));// change accordingly
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("Vertification link");
            message.setText("Your varification link:"+"http://localhost:8080/ActivateAccount?key1="+ userEmail+"&key2="+hash);
            // send message
            Transport.send(message);
            System.out.println("message sent successfully");
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
