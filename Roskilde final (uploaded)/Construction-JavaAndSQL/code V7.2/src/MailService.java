import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {


    public static void sendMail(String recipient, String myMessage) throws Exception{
        System.out.println("Preparing to send invoice to client...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "roskildedcc@gmail.com";
        String password = "ros112233";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
    });

        Message message = prepareMessage(session, myAccountEmail, recipient, myMessage);
        Transport.send(message);
        System.out.println("Invoice sent!");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient, String myMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient) );
            message.setSubject("Roskilde Daycare Invoice");
            message.setText(myMessage);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
