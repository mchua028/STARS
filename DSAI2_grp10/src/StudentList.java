import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class StudentList {
	private String fileName;
	Scanner sc = new Scanner(System.in);
	
	public StudentList() {
		setFileName();
	}
	
	/**
	 * <p>Prints students by either course or index, depending on byIndex parameter.</p>
	 * @param byIndex boolean; true if print by index
	 */
	public void printStudentList (boolean byIndex) {
		String string = "";
		String courseid = "";
		int noOfStds = -1;
		
		if (byIndex) {
			System.out.print("Enter course index: ");
			courseid = sc.nextLine();
		}
		
		try {
			File file = new File(fileName + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                if (string.contains("CourseId" + courseid)) {
                	String [] arrOfStds = string.split("#");
                	noOfStds = arrOfStds.length;
                	for (int i = 1; i < noOfStds; i++) {
                		String [] stdList = arrOfStds[i].split(":");
                		System.out.println(stdList[1]);
                	}
                }
            }
            
            if (noOfStds == -1 && byIndex) { // if index number is typed in wrongly
            	System.out.println("Error: Course index not found.");
            	printStudentList(byIndex);
            }
            
            if (noOfStds == 1 && byIndex) // if no students in that index
            	System.out.println("This index number has no student.");
            
            fr.close();
            br.close();
		}
		catch (FileNotFoundException e) {
        	System.out.println("Error: Course not found.");
        	setFileName();
        	printStudentList(byIndex);
        }
		catch (IOException e) {
			System.out.println("IO Error!" + e.getMessage());
		}
	}
	
	public void setFileName () {
		System.out.print("Enter course name: ");
		fileName = sc.nextLine();
	}
	
	/**
	 * <p>Prints this course index's vacancy.</p>
	 */
	public void printStudentListVacancy() {
		String string = "";
		boolean found = false;
		
		System.out.print("Enter course index: ");
		String courseid = sc.nextLine();
		try {
			File file = new File(fileName + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((string = br.readLine()) != null) {
                if (string.contains("courseIndex"+courseid)){
                	found = true;
                	String [] arrOfLine = string.split(",");
                	String [] capacity = arrOfLine[1].split(":");
                	String [] vacancy = arrOfLine[2].split(":");
                	System.out.println("Number of Vacancies in " + courseid + ": " +
                						vacancy[1] + "/" + capacity[1]);
                }
            }
            fr.close();
            br.close();
            
            if (!found) {
            	System.out.println("Error: Course index not found.");
            	printStudentListVacancy();
            }
            
		}catch (FileNotFoundException e) {
        	System.out.println("Error: Course not found.");
        	setFileName();
        	printStudentListVacancy();
        }
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
