import java.io.*;


public class PasswordReader {
	
	public String getPassword() {
		String password = ""; // empty string to store the password
		
		MaskThread maskThread = new MaskThread();
		Thread maskedPassword = new Thread(maskThread);
		maskedPassword.start(); // to mask the password so that it won't show on screen
		
		InputStreamReader ipStream = new InputStreamReader(System.in);
		BufferedReader brStream = new BufferedReader(ipStream);
			
		try {
			password = brStream.readLine();
		}
		catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		
		maskThread.stopMasking();
		return password;
	}
	
}
