import java.util.Scanner;


public class Waitlist {
	
	/**
	 * @return this student's preferred type to notification for course index vacancy
	 */
	public int notifyMode() {
		Scanner sc = new Scanner(System.in);
		int choice;
		
		do {
			System.out.println("\nNo vacancy currently. How would you like to be notified if there is vacancy?");
			System.out.println("(1) Email");
			
			System.out.print("Choose the notification mode: ");
			choice = sc.nextInt();
		} while(choice != 1);
		
		return choice;
	}

	/**
	 * <p>Sends notification to this student via student's preferred mode.</p>
	 * @param mode this student's preferred mode
	 * @param username this student name
	 * @param subject email subject
	 * @param message email body text
	 */
	public void notifyMe(int mode, String username, String subject, String message) {
		switch (mode) {
		case 1: 
			StudentNotification studentNoti = new StudentNotification();
			NotificationMode emailNoti = new EmailNotification(username, subject, message); 
			//include username, subject, message
			studentNoti.notifyStudent(emailNoti);
			break;
		default:
			System.out.println("Error: Invalid Choice.");
		}
	}
	
}
