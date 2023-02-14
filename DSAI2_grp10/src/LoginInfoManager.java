import java.io.* ;


public class LoginInfoManager {
	
	/**
	 * @param username this user's username input
	 * @param password this user's password input
	 * @param filename if student access: student_login_info.txt; if admin access: admin_login_info.txt
	 * @return boolean; whether user's username input is found in filename
	 */
	public boolean verifyUsername(String username, String password, String filename) {
		try {
			FileReader readLoginInfoFile = new FileReader(filename);
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		    
		    String line;
			
		    while ((line = bufferedReader.readLine()) != null) {
		        if (line.equals(username)) {
		        	if (password == null) {
		        		return true;
		        	}
		        	else {
			        	if (verifyPassword(password, bufferedReader)) {
			        		bufferedReader.close();
			        		return true;		 
			        	}
			        	else 
			        		return false;
		        	}
		        }
		        bufferedReader.readLine(); // read away the encrypted password
		        bufferedReader.readLine(); // read away the student's access period
		        bufferedReader.readLine(); // read away the divider empty line
		    }
        	if (password == null)
        		return false;
		    System.out.println("Error: Username not found.");
        	bufferedReader.close();
        	return false;

		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param password this user's password input
	 * @param bufferedReader buffer reader
	 * @return whether this user's password input matches input username's corresponding password
	 */
	public boolean verifyPassword(String password, BufferedReader bufferedReader) {
		PasswordSecurity passwordSecurity = new PasswordSecurity();
		try {
			String encodedPassword = bufferedReader.readLine();
			String decryptedPassword = passwordSecurity.decrypt(encodedPassword);
			
			if (decryptedPassword.equals(password))
				return true;
			else {
				System.out.println("Error: Incorrect Password.");
				return false;
			}
			
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
		
}
