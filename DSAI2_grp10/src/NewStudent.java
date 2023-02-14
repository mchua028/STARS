import java.util.Scanner;
import java.io.*;


public class NewStudent {
	
	Scanner sc = new Scanner(System.in);
	
	private String name;
	private String username;
	private String matricNo;
	private String gender;
	private String nationality;
	private String password;
	
	public NewStudent() {
		
		boolean check = true;
		
		while (check) {
			System.out.print("Enter the name of student: ");
			String _name = sc.nextLine();
			if (_name.matches("[a-zA-Z ,]+"))
				setName(_name.toUpperCase());
			else {
				System.out.println("Error: Name must not contain integer.");
				continue;
			}
			
			System.out.print("Enter the username of student: ");
			String _username = sc.nextLine();
			if (_username.matches("[a-zA-Z0-9]+"))
				setUsername(_username.toLowerCase());
			else {
				System.out.println("Error: Invalid Username.");
				continue;
			}
				
			System.out.print("Enter the matriculation number of student: ");
			String _matricNo = sc.nextLine();
			if (_matricNo.matches("[a-zA-Z0-9]+"))
				setMatricNo(_matricNo.toUpperCase());
			else {
				System.out.println("Error: Invalid Matriculation Number.");
				continue;
			}
				
			System.out.print("Enter the gender of student: ");
			String _gender = sc.nextLine();
			if (_gender.equals("male") || _gender.equals("female"))
				setGender(_gender);
			else {
				System.out.println("Error: Gender must be male or female.");
				continue;
			}
			
			System.out.print("Enter the nationality of student: ");
			String _nationality = sc.nextLine();
			if (_nationality.matches("[a-zA-Z ,]+"))
				setNationality(_nationality);
			else {
				System.out.println("Error: Nationality must not contain integer.");
				continue;
			}
			
			check = false;
		}
		
	}
	
	/**
	 * <p>Admin adds a new student to system.</p>
	 * <p>New student information added to student_login_info.txt if this student's username is not found.</p>
	 * <p>Individual file corresponding to this new student is created.</p>
	 */
	public void start() {
		LoginInfoManager verifyLoginInfo = new LoginInfoManager();
		if (verifyLoginInfo.verifyUsername(username, null, "student_login_info.txt")) {
			System.out.println("Error: This username already exists.");
			return;
		}
	
		RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();
		password = randomPasswordGenerator.getRandomPassword();
		NotificationMode sendEmailNotification = new EmailNotification(username, "Successful Addition to STARS", 
				"You have been added to STARS.\n"
				+ "Your login details are as follow: \n\n"
				+ "Username: " + username + "\n"
				+ "Password: " + password + "\n\n"
				+ "Please login to change your password.");
		sendEmailNotification.sendNotification(true);
		
		editDetailsFile();
		editInfoFile();
		System.out.println("New student " + name + " added.");
		System.out.println();
		printStudentInfo();
		try {
	          FileWriter fwStream = new FileWriter(username + ".txt");
	          BufferedWriter bwStream = new BufferedWriter(fwStream);
	          PrintWriter pwStream = new PrintWriter(bwStream);
	          pwStream.println("AU:0");
	          
	          pwStream.close();
	        }
        catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	/**
	 * <p>Updating this student's particulars.</p>
	 */
	public void editDetailsFile() {
		try {
			FileWriter fwStream = new FileWriter("student_details.txt",true);
		    BufferedWriter bwStream = new BufferedWriter(fwStream);
		    PrintWriter pwStream = new PrintWriter(bwStream);
		    
		    //pwStream.println();
		    pwStream.println(name + "|" + username + "|" + matricNo + "|" + gender + "|" + nationality);
		    pwStream.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p>Updating this student's login information and access period.</p>
	 */
	public void editInfoFile() {
		try {
			FileWriter fwStream = new FileWriter("student_login_info.txt",true);
		    BufferedWriter bwStream = new BufferedWriter(fwStream);
		    PrintWriter pwStream = new PrintWriter(bwStream);
		    PasswordSecurity passwordSecurity = new PasswordSecurity();
		    StudentAccessPeriodEditor SAPEditor = new StudentAccessPeriodEditor();
		    
		    pwStream.println();
		    pwStream.println(username);
		   
		    String encoded = passwordSecurity.encrypt(password);
		    pwStream.println(encoded);
		    
		    pwStream.println("some access period");		
		    pwStream.close();
		    
		    SAPEditor.editStudentAccessPeriod(false, username);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Print all students in system by name.</p>
	 */
	public void printStudentInfo() {
		try {
			FileReader readStudentInfoFile = new FileReader("student_details.txt");
		    BufferedReader bufferedReader = new BufferedReader(readStudentInfoFile);
		    String line;
		    
		    System.out.println("List of Student Information: ");
		    
		    while (((line = bufferedReader.readLine()) != null)) {
		    	String[] arrOfStudent = line.split("[|]");
		    	System.out.println(arrOfStudent[0]);  	
		    }
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String name) { this.name = name; }
	
	public void setUsername(String username) { this.username = username; }
	
	public void setMatricNo(String matricNo) { this.matricNo = matricNo; }
	
	public void setGender(String gender) { this.gender = gender; }
	
	public void setNationality(String nationality) { this.nationality = nationality; }
	
	public void setPassword(String password) { this.password = password; }

}
