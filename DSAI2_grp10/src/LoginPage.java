import java.util.Scanner;
import java.io.* ;


public class LoginPage {
	private DateTimeManager dateTimeManager = new DateTimeManager();
	protected String userid;
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		LoginInfoManager loginManager = new LoginInfoManager();
		String  username, password;
		int choice;
		
		dateTimeManager.printCurrentTime();
		System.out.println("Welcome to Student Automated Registration System (STARS)!");
		do {
			System.out.println("\n----- Login Page -----");
			System.out.println("(1) Student");
			System.out.println("(2) Admin");
			
			System.out.print("Choose your domain: ");
			choice = sc.nextInt();
			sc.nextLine(); //to read \n that is entered after inputting choice
			
			System.out.print("Enter your username: ");
			username = sc.nextLine();
			
			System.out.print("Enter your password: ");
			PasswordReader p = new PasswordReader();
			password = p.getPassword();
			
			switch(choice) {
			case 1: 
				if (loginManager.verifyUsername(username, password, "student_login_info.txt")) {
					if (!dateTimeManager.verifyAccessPeriod(username))
						break;
					else {
						StudentPage stu = new StudentPage(username);
						stu.studentPage();
					}
				}
				else
					choice = -1;
				break;
			case 2:
				if (loginManager.verifyUsername(username, password, "admin_login_info.txt")) {
					AdminPage admin = new AdminPage();
					admin.adminPage();
				}
				else
					choice = -1;
				break;
			default:
				System.out.println("Error: Invalid Option.");
			}
		} while(choice != 1 && choice != 2);
	}
	
}
