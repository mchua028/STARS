import java.util.Scanner;


public class PasswordModifier {
	private String username;
	
	public PasswordModifier(String username) {
		this.username = username;
	}
	
	/**
	 * <p>Change this student's password.</p>
	 */
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your current password: ");
		String currentPassword = sc.nextLine();
		
		LoginInfoManager verifyLoginInfo = new LoginInfoManager();
		String password1, password2;
		if (verifyLoginInfo.verifyUsername(username, currentPassword, "student_login_info.txt")) {
			do {
				System.out.print("Enter your new password: ");
				password1 = sc.nextLine();
				System.out.print("Enter your new password again: ");
				password2 = sc.nextLine();
				if (password1.equals(password2)) {
					PasswordSecurity passwordSecurity = new PasswordSecurity();
					String encoded = passwordSecurity.encrypt(password1);
					LoginInfoFileEditor editPassword = new LoginInfoFileEditor();
					editPassword.writePassword(username, encoded);
					System.out.println("The password is successfully changed.");
				}
				else {
					System.out.println("The password does not match.");
				}
			} while (!password1.equals(password2));
		}
	}
	
}
