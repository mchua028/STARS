import java.util.*;
import java.io.*;

public class StudentNewCourse extends StudentManager {
	
	private String username;
	private String [] studentDetails = new String[5];
	private static final int MAX_AU = 10;
	
	public StudentNewCourse(String username) {
		this.username = username;
	}
	
	/**
	 * @param courseName this course name
	 * @return boolean; whether student is registered/on waitlist for this course
	 */
	public boolean courseRegistered(String courseName) {
		Scanner sc = new Scanner(System.in);

		String fileName = "student_details.txt";
		super.locateIndex(username, fileName);
		studentDetails = super.oldInfo.split("[|]");	
		
		try {
			File f1 = new File(username + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String string;
            while ((string = br.readLine()) != null) {
            	if (string.contains(courseName)) {
            		System.out.println("You are registered/on the waitlist for this course.");
            		return true;
            	}
            }
            fr.close();
            br.close();
		} // end-try block
		catch (Exception ex) {
            ex.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * <p>Checking conditions before adding this student to this course.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 * @param print boolean; whether it is adding course to this user
	 */
	public void addCourse(String courseName, String courseid, boolean print) {
		try {
			if(courseRegistered(courseName)) {
				return;
			}
			if(exceedAu(courseName)) {
				return;
			}
		
			String string = "";
			String [] oldcourseIndexInfo = new String[12];
			String [] vacArray = new String[2];
			File file1 = new File(courseName + ".txt");
            FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr);
            
            while ((string = br.readLine()) != null) {
				  // search for lines containing courseIndex in Course file
                if (string.contains("courseIndex" + courseid)) {
                	oldcourseIndexInfo = string.split(",");	
                	vacArray = oldcourseIndexInfo[2].split(":");
                	int vac = Integer.parseInt(vacArray[1]);
                	if (vac!=0) {
	                	vac--;		                	
	                	vacArray[1] = Integer.toString(vac);
	                	String vacancyStr = vacArray[0] + ":" + vacArray[1];
	                	EditFile ed = new EditFile();
	                	String newLine = "courseIndex"+courseid;
	                	for (int j =1;j<oldcourseIndexInfo.length;j++) {
	                		if (oldcourseIndexInfo[j].contains("Vacancy"))
	                			oldcourseIndexInfo[j]=vacancyStr;
	                		newLine += ","+oldcourseIndexInfo[j];
	                	}
	                	ed.replaceHori(courseName, string, newLine);
	                	
	                	// check for clash in timetable
	                	ScheduleClash clash = new ScheduleClash(username, courseName, courseid);
	                	if(clash.checkClash()) {
	                		System.out.println("Timetable clash, unable to add course.");
	                		return;
	                	}
	                	
						registerStudent(courseName, courseid, print);
                	}

					// add student to waitlist
                	else {
						addToWaitlist(courseName, courseid);
                	}
                	break;
            	} // end-if                
            } // end-while
            
            if(string == null) {
            	System.out.println("Error: Invalid Course Index.");
            }
 
	            fr.close();
	            br.close();
        } // end-try block
		catch (FileNotFoundException e) {
			System.out.println("Error: Invalid Course Name.");
		}
		catch (IOException ex) {
            ex.printStackTrace();
		}
}
	
	/**
	 * @param courseName this course name
	 * @return boolean; whether student is exceeding maximum number of AUs
	 */
	public boolean exceedAu(String courseName) {
		int auOfNewCourse = getAU(courseName);
		int currentTotalAu = 0;
		try {
			File f1 = new File(username + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            currentTotalAu = Integer.parseInt(br.readLine().split(":")[1]);
            fr.close();
            br.close();
		} // end-try block
		catch (FileNotFoundException e) {
			System.out.println("Error: Invalid Course Name.");
		}
		catch (IOException e) {
            e.printStackTrace();
		}
		if ((currentTotalAu+auOfNewCourse)>MAX_AU) {
			System.out.println("You have exceeded the maximum number of AUs. Unable to add current course.");
			return true;
		}
		return false;
	}
	
	/**
	 * <p>Registers course for student.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 * @param print boolean; whether it is registering course to this user
	 */
	public void registerStudent(String courseName, String courseid, boolean print) {
		// check whether courseid is in Course file
		super.locateIndex("CourseId"+courseid, courseName+".txt");
		String oldCourseIdInfo = super.oldInfo;
		String newCourseIdInfo = oldCourseIdInfo + "#" + "Student:" + studentDetails[0] 
							+ "," + studentDetails[3] + "," + studentDetails[4];
		
		// if index found, add student to Course file => register student
        if(super.indexFound) {
        	if (print)
        		System.out.println("Adding course for " + username + "...");
            EditFile edfile = new EditFile();
			edfile.replaceVerti(courseName, oldCourseIdInfo, newCourseIdInfo);
			EditFile edfile2 = new EditFile();

			// locating courseIndex in Course file
			locateIndex("courseIndex"+courseid, courseName+".txt");	        	            
            String[] oldCourseIndexInfo = super.oldInfo.split(",");
            String newCourseIndexInfo = ",Registered," + oldCourseIndexInfo[0];
            for(int i=3; i<oldCourseIndexInfo.length; i++) {
            	newCourseIndexInfo += "," + oldCourseIndexInfo[i];	        	           
            }
			edfile2.add(username, courseName + newCourseIndexInfo);
			try {
				File f1 = new File(username + ".txt");
	            FileReader fr = new FileReader(f1);
	            BufferedReader br = new BufferedReader(fr);
	            int TotalAu = Integer.parseInt(br.readLine().split(":")[1]);
	            int currentTotalAu = TotalAu + getAU(courseName);
	            EditFile ed = new EditFile();
	            ed.replaceVerti(username, "AU:"+TotalAu, "AU:"+Integer.toString(currentTotalAu));
	            fr.close();
	            br.close();
			} // end-try block
			catch (Exception ex) {
	            ex.printStackTrace();
			}
        }
	}
	
	/**
	 * @param courseName this course name
	 * @param courseid this course index
	 */
	public void addToWaitlist(String courseName, String courseid) {
		System.out.println("Adding to waitlist...");
		
		int notifMode = waitlist.notifyMode();
		
		// check whether courseId is in Waitlist
		super.locateIndex("CourseId"+courseid, "Waitlist.txt");
		String array6 = super.oldInfo;
		String array7 = array6 + "#Student:" + studentDetails[1] + "," + notifMode;
		
        if(super.indexFound) {
            System.out.println("Adding to waitlist...");
            EditFile edfile3 = new EditFile();
			edfile3.replaceVerti("Waitlist", super.oldInfo, array7);
			
			EditFile edfile4 = new EditFile();
			
			locateIndex("courseIndex"+courseid, courseName+".txt");	        	            
            String[] oldInfo2 = super.oldInfo.split(",");
            String newInfo = ",Waitlist," + oldInfo2[0];
            for(int i=3; i<oldInfo2.length; i++) {
            	newInfo += "," + oldInfo2[i];
            }
			edfile4.add(username, courseName + newInfo);
        }
	}
	
}
