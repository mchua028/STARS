import java.util.Scanner;
import java.io.*;


public class AllCourse {
	
    private String courseCode, courseName, fileName;
    private boolean courseFound;
    private String courseInfo, fileContents;
    
    
    public AllCourse(String fileName) { this.fileName = fileName; }

    public void locateCourse() {
        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            courseFound = false;

            while(in.hasNextLine()) {
                String line = in.nextLine();
                buffer.append(line).append("\n");
                if(line.contains(courseName)) {
                    courseFound = true;
                }
            }
            fileContents = buffer.toString();
        }
        catch (FileNotFoundException e) {
            System.out.println(fileName + " is created.");
        }
    }

    /**
     * @param FileName the course name of course to be added
     */
    public void addCourse(String FileName) {
    	setCourseName(FileName);
    	locateCourse();
    	if(!courseFound) {
	        try {
	            System.out.println("Adding to course...");
	            FileWriter fileWriter = new FileWriter(fileName,true);
	            BufferedWriter bw = new BufferedWriter(fileWriter);
	
	            bw.write(courseName + "\n");
	            bw.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    }
    
    /**
     * @param filename the course name of course to be added
     */
    public void printAllCourses(String filename) {
    	try {
            System.out.println("List of all courses: ");
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
    		
		    while ((line = br.readLine()) != null) {
		    	System.out.println(line);
		    }
            
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    /**
     * @param courseCode this course code
     */
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    /**
     * @return the course code
     */
    public String getCourseCode() { return courseCode; }
    
    /**
     * @param courseName this course name
     */
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    /**
     * @return the course name
     */
    public String getCourseName() { return courseName; }
    
    /**
     * @return boolean; whether course is found in Courses.txt containing all courses
     */
    public boolean getCourseFound() { return courseFound; }

}
