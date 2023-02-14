import java.lang.Math;


public class RandomPasswordGenerator {
	
	public String getRandomPassword() {
		String charToInclude = "ABCDEFGHJKLMNOPQRSTUVWXYZ" //uppercase I and lowercase l are removed to avoid confusion
								+"abcdefghijkmnopqrstuvwxyz"
								+"1234567890"
								+"!@#$%^&*()_+-={}[]/:'<;>?,.'";
		StringBuilder password = new StringBuilder(8);
		for (int i=0;i<8;i++) {
			int charIndex = (int)(Math.random() * charToInclude.length());
			password.append(charToInclude.charAt(charIndex));
		}
		return password.toString();
	}
	
}
