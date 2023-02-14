import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailNotification extends NotificationMode {
	
	private String subject;
	
	public EmailNotification(String username, String subject, String sendMessage) {
		super(username, sendMessage);
		this.subject = subject;
	}
	
	public void sendNotification(boolean adminAccess) {
		final String username = "justtryingforcz2002"; // to be added
		final String password = "ilovecz2002!!"; // to be added

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("justtryingforcz2002@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(super.getUsername()+ "@e.ntu.edu.sg")); // to be added an email addr
			message.setSubject(subject);

			message.setText("Dear " + super.getUsername() + ",\n\n"
							+ super.getMessage() + "\n\n"
							+ "Please do not reply to this automated email. \nThank you.");
			
			Transport.send(message);
			if (adminAccess)
				System.out.println("Email has been sent to inform student about: " + subject + ".");
			
		} 
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
