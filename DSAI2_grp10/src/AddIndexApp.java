import java.util.ArrayList;
import java.util.Scanner;


public class AddIndexApp {
	
	Scanner sc = new Scanner(System.in);
    private Classes course;
    private String fileName, courseIndex, type;
    private int indexChoice, choice;
    private int vacancy, waitList, lectDay, lectStartTime, lectEndTime, tutDay, tutStartTime, tutEndTime, labDay, labStartTime, labEndTime;

    public AddIndexApp() {
        System.out.print("Enter course name: ");
        fileName = sc.nextLine() + ".txt";
        course = new Classes(fileName);
   
    }

    public void mainMenu() {
        do {
            System.out.println("\n------- Edit Course Index -------");
            System.out.println("(1) Add Course Index");
            System.out.println("(2) Update Course Index Details");
            System.out.println("(3) Remove Course Index");
            System.out.println("(4) Exit App");
            
            System.out.print("Enter choice: ");
            indexChoice = Integer.parseInt(sc.nextLine());

            switch(indexChoice) {
                case 1:                 
  					System.out.print("Enter index number: ");
  					courseIndex = sc.nextLine();					
					course.createIndex(courseIndex);
                    
                    break;
                case 2:
                	System.out.print("Enter course index: ");
                    courseIndex = sc.nextLine();
                    course.locateIndex(courseIndex, fileName);
                    if(course.indexFound) {
                    	updateIndex(courseIndex);
                    }
                    else {
                    	System.out.println("Error: Invalid Course Index.");
                    }
                    break;
                case 3:
                	System.out.print("Enter course index: ");
                    courseIndex = sc.nextLine();
                    course.removeIndex(courseIndex);
                    break;
                case 4:
                    System.out.println("Terminating...");
                    break;
                default:
                    System.out.println("Error: Invalid Option.");
                    break;
            } //end switch-case
        } while(indexChoice >= 1 && indexChoice < 4);

    }


    /**
     * @param courseIndex the course index to be updated
     */
    public void updateIndex(String courseIndex) {
    	ErrorChecker ec = new ErrorChecker();
        do {
	        System.out.println("\n---- Update Course Index Details ----");
	        System.out.println("(1) Vacancy");
	        System.out.println("(2) Lecture Day");
	        System.out.println("(3) Lecture Start Time");
	        System.out.println("(4) Lecture End Time");
	        System.out.println("(5) Tutorial Day");
	        System.out.println("(6) Tutorial Start Time");
	        System.out.println("(7) Tutorial End Time");
	        System.out.println("(8) Lab Day");
	        System.out.println("(9) Lab Start Time");
	        System.out.println("(10) Lab End Time");
	        System.out.println("(11) Back");
	        					
	        System.out.print("Enter choice: ");
	        choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    vacancy = ec.posIntChecker("Enter index vacancy: ");
                    type = "Vacancy";
                    course.setVacancy(vacancy);
                    break;
                case 2:
                    lectDay = ec.dayChecker("Enter index lecture day: ");
                    type = "Lecture Day";
                    course.setLectDay(lectDay);
                    break;
                case 3:
                	lectStartTime = ec.timeChecker("Enter index lecture start timing: ");
                    type = "Lecture Start Time";
                    course.setLectStartTime(lectStartTime);
                    break;
                case 4:
                    lectEndTime = ec.timeChecker("Enter index lecture end timing: ");
                    type = "Lecture End Time";
                    course.setLectEndTime(lectEndTime);
                    break;
                case 5:
                    tutDay = ec.dayChecker("Enter index tutorial day: ");
                    type = "Tutorial Day";
                    course.setTutDay(tutDay);
                    break;
                case 6:
                    tutStartTime = ec.timeChecker("Enter index tutorial start timing: ");
                    type = "Tutorial Start Time";
                    course.setTutStartTime(tutStartTime);
                    break;
                case 7:
                	tutEndTime = ec.timeChecker("Enter index tutorial end timing: ");
                    type = "Tutorial End Time";
                    course.setTutEndTime(tutEndTime);
                    break;
                case 8:
                    labDay = ec.dayChecker("Enter index lab day: ");
                    type = "Lab Day";
                    course.setLabDay(labDay);
                    break;
                case 9:
                    labStartTime = ec.timeChecker("Enter index lab start timing: ");
                    type = "Lab Start Time";
                    course.setLabStartTime(labStartTime);
                    break;
                case 10:
                    labEndTime = ec.timeChecker("Enter index lab end timing: ");
                    type = "Lab End Time";
                    course.setLabEndTime(labEndTime);
                    break;
                case 11:
                	System.out.println("Returning to previous page...");
                	break;
                default:
                    System.out.println("Error: Invalid Option.");
                    break;
            } //end switch-case
            course.updateIndexInfo(type, courseIndex);

        } while(choice != 11);
    }

}
