package AppUsed;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConfig {
		
	private static final String host = "smtp.gmail.com";
	private static final String user = "applicationautomatic@gmail.com";
	private static final String pass = "BlackDiamond";

	public static void main(String[] args) {

		System.out.println(MailConfig.emailValidate("miknguyet99@123.com"));
	}

	public static boolean emailValidate(String email) {
		
		EmailValidator validator = EmailValidator.getInstance();
		
		// Check if email's validate
		if(validator.isValid(email)) {
			try {
				MailConfig.sendEmail(email, "Verify Email - Movie Book", "We just want to confirm if your email is valid.");
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
		
	}
	
	public static void sendEmail(String toMail, String subject, String cont) throws AddressException, MessagingException {
		
		// Get system properties
	    Properties pros = System.getProperties();

	    pros.put("mail.smtp.starttls.enable", "true");
	    pros.put("mail.smtp.host", host);
	    pros.put("mail.smtp.port", "465");
	    pros.put("mail.debug", "false");
	    pros.put("mail.smtp.auth", "true");
	    pros.put("mail.smtp.starttls.required", "true");
	    pros.put("mail.smtp.ssl.enable", "true");
	    pros.put("mail.smtp.ssl.required", "true");

	    // Get the Session object.// and pass username and password
        Session session = Session.getInstance(pros, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(user));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

        // Set Subject: header field
        message.setSubject(subject);

        // Now set the actual message
        message.setText(cont);

        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
	}
}
