import java.util.*;
import java.io.*;


public class StudentPage extends LoginPage {

	public StudentPage(String username) {
		this.userid = username;
	}
	
	public void studentPage() {
		Scanner sc = new Scanner(System.in);
		int choice = 0; 
		String name, courseIdAdd, courseIdDrop;
		
		do {
			System.out.println("\n---------- Student Page ----------");
			System.out.println("(1) Add Course");
			System.out.println("(2) Drop Course");
			System.out.println("(3) Check Course Registered");
			System.out.println("(4) Check Vacancies Available");
			System.out.println("(5) Change Index Number of Course");
			System.out.println("(6) Swap Index Number with Another Student");
			System.out.println("(7) Change Password");
			System.out.println("(8)Log Out");
			StudentManager student = new StudentManager();
			
			try {
				System.out.print("Enter choice: ");
				choice = sc.nextInt();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				sc.nextLine();
				choice = 10;
			}
			
			switch(choice) {
			case 1: 
				
				student.setUserid(userid);
				System.out.print("Enter course name: ");
				name = sc.nextLine();
				if(!CourseNameExist(name)) {
					break;
				}
				System.out.print("Enter course index you wish to add: ");
				courseIdAdd = sc.nextLine();
				
				student.addCourse(name, courseIdAdd, true);

				break;
			
			case 2:
				student.setUserid(userid);
				System.out.print("Enter course name: ");
				name = sc.nextLine();
				if(!CourseNameExist(name)) {
					break;
				}
				System.out.print("Enter course index you wish to drop: ");
				courseIdDrop = sc.nextLine();
				
				student.dropCourse(name, courseIdDrop);
				break;
			
			case 3:
				student.setUserid(userid);
				student.printCourse();
				break;
				
			case 4:
				StudentList studentListVac = new StudentList();
				studentListVac.printStudentListVacancy();
				break;
				
			case 5:
				student.setUserid(userid);
				System.out.print("Enter course name: ");
				name = sc.nextLine();				
				if(!CourseNameExist(name)) {
					break;
				}
				IndexNumber id = new IndexNumber();
				System.out.print("Enter course index you wish to drop: ");
				courseIdDrop = sc.nextLine();
				
				id.locateIndex(courseIdDrop,userid+".txt");
				if (!id.indexFound) {
					System.out.println("Error: Invalid Course Index");
					break;
				}
				
				System.out.print("Enter course index you wish to add: ");
				courseIdAdd = sc.nextLine();
				id.locateIndex(courseIdAdd,name+".txt");
				if (!id.indexFound) {
					System.out.println("Error: Invalid Course Index");
					break;
				}
				
				student.dropCourse(name, courseIdDrop);
				
				try {
					ScheduleClash clash = new ScheduleClash(userid, name, courseIdAdd);
	            	if(clash.checkClash()) {
	            		System.out.println("Timetable clash, unable to change index.");
	            		student.addCourse(name, courseIdDrop, true);
	            		break;
	            	}
				}
				catch(FileNotFoundException e) {
					System.out.println("File not found");
				}
				
        		student.addCourse(name, courseIdAdd, true);	
				break;
				
			case 6:
				student.setUserid(userid);
				System.out.print("Enter course name: ");
				name = sc.nextLine();
			
				while(CourseNameExist(name)) {
					
					System.out.print("Enter your peer's username: ");
					String username = sc.nextLine();
					System.out.print("Enter your peer's password: ");
					PasswordReader p = new PasswordReader();
					String password = p.getPassword();
					
					LoginInfoManager loginManager = new LoginInfoManager();
					if (!loginManager.verifyUsername(username, password, "student_login_info.txt")) {
						System.out.println("Invalid username or password! Try again.");
					}
					else {
						System.out.print("Enter your course index: ");
						courseIdAdd = sc.nextLine();
						IndexNumber num = new IndexNumber();
						num.locateIndex(courseIdAdd,userid+".txt");
						if (!num.indexFound) {
							System.out.println("You are not registered for this course index.");
							break;
						}
						System.out.print("Enter your peer's course index: ");
						courseIdDrop = sc.nextLine();
						num.locateIndex(courseIdDrop,username+".txt");
						if (!num.indexFound) {
							System.out.println("Your peer is not registered for this course index.");
							break;
						}
						student.dropCourse(name, courseIdAdd);
						
						student.setUserid(username);
						student.dropCourse(name, courseIdDrop);
						
						student.setUserid(userid);
						student.addCourse(name, courseIdDrop, true);
						
						student.setUserid(username);
						student.addCourse(name, courseIdAdd, true);
						
						break;
					}
				}
				break;
				
			case 7:
				PasswordModifier pwm = new PasswordModifier(userid);
				pwm.start();
				break;
								
			case 8:
				System.out.println("Logging out...");
				break;
				
			default:
				System.out.println("Invalid choice");
				break;
		}
	}while(choice!=8);
}
	
	/**
	 * @param name this course name
	 * @return whether this course in the list of courses in Courses.txt
	 */
	public boolean CourseNameExist(String name) {

		try {
			File f1 = new File("Courses.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String string;
            while ((string = br.readLine()) != null) {
            	if (string.equals(name)) {
            		return true;
            	}
            }           
            fr.close();
            br.close();
            System.out.println("Error: Invalid Course Name.");
            return false;
		} // end-try block
		catch (Exception ex) {
            ex.printStackTrace();
            return false;
		}
	}
	
}
