import java.util.Scanner;


public class CourseUpdate {
	Scanner sc = new Scanner(System.in);
	private String fileName;
	
	public void start() {	
		int choice;	
		
		do {
			System.out.println("\n------- Update Course -------");
			System.out.println("(1) Change Course Code");
			System.out.println("(2) Edit Course Index Information");
			System.out.println("(3) Replace Course Index");
			System.out.println("(4) Change School");
			System.out.println("(5) Back");	
			
			System.out.print("Enter choice: ");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				changeCourse();
				break;
			case 2: 
				AddIndexApp test1 = new AddIndexApp();
		        test1.mainMenu();
				break;
			case 3:
				replaceCourseIndex();
				break;
			case 4:
				changeSch();
				break;
			case 5:
				System.out.println("Returning to previous page..");
				break;
			default:
				System.out.println("Error: Invalid Choice.");
				break;					
			}
		} while(choice != 5);
	}
	
	/**
	 * <p>Updates the course code of specified course.</p>
	 */
	public void changeCourse() {
		System.out.print("Enter course name: ");
		sc.nextLine();
		fileName = sc.nextLine();
		System.out.print("Enter original course code: ");
		String oldCode = sc.nextLine();
		System.out.print("Enter new course code: ");
		String newCode = sc.nextLine();
		EditFile edfile2 = new EditFile();
		edfile2.replaceVerti(fileName, oldCode, newCode);
	}
	
	/**
	 * <p>Updates the course index of specified course</p>
	 */
	public void replaceCourseIndex() {
		System.out.print("Enter course name: ");
		sc.nextLine();
		fileName = sc.nextLine();
		System.out.print("Enter original index number: ");
		String oldindex = sc.nextLine();
		IndexNumber number = new IndexNumber();
		number.locateIndex("courseIndex"+oldindex, fileName+".txt");
		String [] array7 = new String[8];
		array7 = number.oldInfo.split(",");
		System.out.print("Enter new index number: ");
		String newindex= sc.nextLine();
		String newinfo = "courseIndex" + newindex;
		for(int j=1; j < array7.length; j++) {
			newinfo = newinfo + "," + array7[j];
		}
		EditFile edfile4 = new EditFile();
		edfile4.replaceVerti(fileName, number.oldInfo, newinfo);
		IndexNumber number2 = new IndexNumber();
		number2.locateIndex("CourseId"+oldindex, fileName+".txt");						
		String [] array8 = number2.oldInfo.split(",");
		String newinfo2 = "CourseId" + newindex;
		for(int j=1; j < array8.length; j++) {
			newinfo2 = newinfo2 + ","+array8[j] ;
		}	
		EditFile edfile6 = new EditFile();
		edfile6.replaceVerti(fileName, number2.oldInfo, newinfo2);
		
		IndexNumber number3 = new IndexNumber();
		number3.locateIndex("CourseId"+oldindex, "Waitlist.txt");						
		String [] array9 = number3.oldInfo.split(",");
		array9[1]="CourseId"+newindex;
		String newinfo3 = fileName;
		for(int j=1; j < array9.length; j++) {
			newinfo3 = newinfo3 + ", "+array9[j] ;
		}	
		EditFile edfile7 = new EditFile();
		edfile7.replaceVerti("Waitlist", number3.oldInfo, newinfo3);
	}
	
	/**
	 * <p>Updates the school of specified course</p>
	 */
	public void changeSch() {
		System.out.print("Enter course name: ");
		sc.nextLine();
		fileName = sc.nextLine();
		System.out.print("Enter original school: ");
		String oldsch = sc.nextLine();
		System.out.print("Enter new school: ");
		String newsch = sc.nextLine();
		EditFile edfile5 = new EditFile();
		edfile5.replaceVerti(fileName, oldsch, newsch);
	}
	
}
