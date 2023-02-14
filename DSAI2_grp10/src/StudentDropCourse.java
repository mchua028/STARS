import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
public class StudentDropCourse extends StudentManager {

	private String username;
	private String indexDetails;
	private String [] studDetailArr = new String[5];
	boolean removeFromList = false;

	
	public StudentDropCourse(String username) { this.username = username; }
	
	
	/**
	 * @param courseName this course name
	 * @param courseid this course index
	 * @return boolean; whether this course is registered for this student
	 */
	public boolean courseRegistered(String courseName, String courseid) {
		super.locateIndex("CourseId" + courseid, courseName + ".txt");
		indexDetails = super.oldInfo;
		if(!super.indexFound) {
			System.out.println("Course index not found.");
			return false;
		}
		String fileName = "student_details.txt";
		super.locateIndex(username, fileName);
		String studentDetails = super.oldInfo;
		studDetailArr = studentDetails.split("[|]");
		
		super.locateIndex("CourseId"+courseid, courseName+".txt");
		if(!super.oldInfo.contains(studDetailArr[0])) {
			System.out.println("Username not found in course index.");
			return false;
		}
		return true;
	}
	
	/**
	 * <p>Checking conditions before removing this student from this course.</p>
	 * <p>Removes this student from this course, gets and changes this course's vacancy.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 * @return boolean; whether this student is removed from waitlist
	 */
	public boolean removeCourse(String courseName, String courseid) {
		// check whether index in course && user in index
		if(!courseRegistered(courseName, courseid)) {
			return removeFromList;
		}
		
		// removing student from course
		removeStudent(courseName, courseid);
		
		// get and change vacancy
		changeVac(courseName,courseid);
		return removeFromList;
	}
	
	/**
	 * <p>De-registers this course for this student.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 */
	public void removeStudent(String courseName, String courseid) {
		// retrieving student details
		String fileName = "student_details.txt";
		super.locateIndex(username, fileName);
		String studentDetails = super.oldInfo;
		studDetailArr = studentDetails.split("[|]");	
		
		// retrieving index details
		String [] indexDetailsArr = indexDetails.split("#");
		String newDetailsIndex = "CourseId"+courseid;
		for(int i =1; i<indexDetailsArr.length; i++) {
			if(!indexDetailsArr[i].contains(studDetailArr[0])) {
				newDetailsIndex += "#" + indexDetailsArr[i];
			}
		}
		
		try {
			File f1 = new File(username + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            int TotalAu = Integer.parseInt(br.readLine().split(":")[1]);
            int currentTotalAu = TotalAu - getAU(courseName);
            EditFile ed = new EditFile();
            ed.replaceVerti(username, "AU:"+TotalAu, "AU:"+Integer.toString(currentTotalAu));
            fr.close();
            br.close();
		} // end-try block
		catch (FileNotFoundException e) {
			System.out.println("Error: Invalid Course Name.");
		}
		catch (IOException e) {
            e.printStackTrace();
		}
		
		System.out.println("Dropping course for " + username + "...");
        EditFile edfile = new EditFile();
		edfile.replaceVerti(courseName, indexDetails, newDetailsIndex);
	}
	
	/**
	 * <p>Updates vacancy for this course.</p>
	 * <p>Updates both this course's file and individual student file.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 */
	public void changeVac(String courseName, String courseid) {
		try {
			String string = "";
			String [] array4 = new String[12];
			String [] array5 = new String[2];
			File file1 = new File(courseName + ".txt");
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            
            while ((string = br.readLine()) != null) {
                if (string.contains("courseIndex" + courseid)) {
                	array4 = string.split(",");	
                	array5 = array4[2].split(":");
                	int vac = Integer.parseInt(array5[1]);
                	vac++;

                	// updating Course file
                	array5[1] = Integer.toString(vac);
                	String string5 = array5[0] + ":" + array5[1];
                	EditFile ed = new EditFile();
                	ed.replaceHori(courseName, array4[2], string5);
                	
                	// updating studentCourse file
                	EditFile edfile5 = new EditFile();
                	super.locateIndex(courseName, username+".txt");
            		String droppingIndex = super.oldInfo;
        			edfile5.replaceVerti(username, droppingIndex, "removed");
        			
        			if(vac == 1) {
                		removeFromList = true;
                	}
        			
                }
            }
           
            fr.close();
            br.close();
		} // end-try block
		catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
}
