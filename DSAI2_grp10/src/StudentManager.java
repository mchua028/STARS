import java.util.*;
import java.io.*;

public class StudentManager extends IndexNumber {
	
	private String name;
	private String gender;
	private String nationality;
	private String userid;
	protected Waitlist waitlist = new Waitlist();

	public StudentManager() { }
	public StudentManager(String fileName) { super(fileName); }
	public String getIndexInfo() { return "Student:" + getName() + "," + getGender() + "," + getNationality(); }
	public String getName() { return name; }
	public String getGender() { return gender; }
	public String getNationality() { return nationality; }
	public String getUserid() { return userid; }
	public void setName(String name) { this.name = name; }
	public void setGender(String gender) { this.gender = gender; }	
	public void setNationality( String n) { this.nationality = n; }
	public void setUserid(String id) { this.userid = id; }
	
	/**
	 * <p>Driver for student to add course.</p>
	 * @param name this course name
	 * @param courseid this course index
	 * @param print boolean; if true then print "Adding...", else do nothing
	 */
	public void addCourse(String name, String courseid, boolean print) {
		StudentNewCourse snc = new StudentNewCourse(getUserid());
		snc.addCourse(name, courseid, print);
	}
	
	/**
	 * <p>Driver for student to drop course.</p>
	 * @param name this course name
	 * @param courseid this course index
	 */
	public void dropCourse(String name, String courseid) {
		StudentDropCourse sdc = new StudentDropCourse(getUserid());
		if(sdc.removeCourse(name, courseid)) {
			removeWaitlist(name, courseid);
		}	
	}
	
	/**
	 * <p>Removes this student from waitlist if found on course waitlist.</p>
	 * @param courseName this course name
	 * @param courseid this course index
	 */
	public void removeWaitlist(String courseName, String courseid) {
		String originalId = getUserid();
			
		locateIndex(courseid, "Waitlist.txt");
		String currWaitlist = oldInfo;
//		if no student on waitlist, return
		if(!currWaitlist.contains("#Student:")) {
			return;
		}
		
		String[] arr = currWaitlist.split("#Student:");
		
		if(arr.length > 1) {
			String userid = arr[1].split(",")[0];
			setUserid(userid);
			
			String email = "\n\nYou have been added to:" 
							+ "\nCourse: " + courseName 
							+ "\nIndex Number: " + courseid;
			waitlist.notifyMe(Integer.parseInt(arr[1].split(",")[1]),
					getUserid(), "Successfully added to course " + courseName, email);
			
//        	updating studentCourse file
			EditFile edfile5 = new EditFile();
        	super.locateIndex(courseName, userid+".txt");
    		String droppingIndex = super.oldInfo;
			edfile5.replaceVerti(userid, droppingIndex, "removed");
			
//			remove student from waitlist
			String arr1 = arr[0];
			for(int i=2; i<arr.length-1; i++) {
				arr1 += arr[i];
			}
        	EditFile edfile6 = new EditFile();
			edfile6.replaceVerti("Waitlist", currWaitlist, arr1);

			addCourse(courseName, courseid, false);
		}
		setUserid(originalId);
	}
	
	/**
	 * <p>Prints courses registered by this student.</p>
	 */
	public void printCourse() {
		try {
			File file3 = new File(getUserid() + ".txt");
	        FileReader fr = new FileReader(file3);
	        BufferedReader br = new BufferedReader(fr);
	        String string = "";
	        while ((string = br.readLine()) != null) {
	        	if (string.contains("Lecture"))
	        		System.out.println(string);
	        }
	                        
		} 
		catch (Exception ex) {
            ex.printStackTrace();
		}		
	}
	
	/**
	 * @param courseName this course name
	 * @return number of AUs for this course
	 */
	public int getAU(String courseName) {
		int au =0;
		try {
			File f1 = new File(courseName + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            br.readLine();
            au = Integer.parseInt(br.readLine());
            fr.close();
            br.close();
		} // end-try block
		catch (FileNotFoundException e) {}
		catch (IOException e) {
            e.printStackTrace();
		}
		return au;	
	}

}
