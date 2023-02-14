import java.io.*;
import java.util.*;


public class AdminPage {
	
	public void adminPage() {
		Scanner sc = new Scanner(System.in);
		String FileName;
		int choice = 0; 
		do {
			System.out.println("\n---------------- Admin Page ----------------");
			System.out.println("(1) Edit Student Access Period");
			System.out.println("(2) Add Student");
			System.out.println("(3) Add Course");
			System.out.println("(4) Print Student List by Course");
			System.out.println("(5) Print Student List by Index Number");
			System.out.println("(6) Check Availability Slot for an Index Number");
			System.out.println("(7) Update Course");
			System.out.println("(8) Log Out");
			
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
				StudentAccessPeriodEditor edit = new StudentAccessPeriodEditor();
				edit.editStudentAccessPeriod(true, null);
				break;
				
			case 2:
				NewStudent student = new NewStudent();
				student.start();
				break;
				
			case 3:
				NewCourse course = new NewCourse();
				course.addCourse();
				break;
				
			case 4:
				StudentList studentList = new StudentList();
				studentList.printStudentList(false);
				break;
				
			case 5:
				StudentList studentListIndex = new StudentList();
				studentListIndex.printStudentList(true);
				break;
				
			case 6:
				StudentList studentListVac = new StudentList();
				studentListVac.printStudentListVacancy();
				break;
				
			case 7:
				CourseUpdate cu = new CourseUpdate();
				cu.start();
				break;
			
			case 8:
				System.out.println("Logging out...");
				break;
			default:
				System.out.println("Error: Invalid Choice.");
				break;
			}
		} while(choice != 8);
	}
	
}
