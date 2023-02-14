import java.util.*;
import java.util.Scanner;
import java.io.*;


public class IndexNumber extends Course {
	
	Scanner sc = new Scanner(System.in);
    protected String fileName, courseIndex;
    private int vacancy;
    private int waitList=-1;
    protected boolean indexFound;
    protected String oldInfo, fileContents;
    
    public IndexNumber() { }
    
    public IndexNumber(String fileName) { this.fileName = fileName; }

    public IndexNumber(String courseIndex, int vacancy) { 
    	this.courseIndex = courseIndex;
    	this.vacancy = vacancy;	
    }
    
    /**
     * <p>Locates this course index and stores index's information in oldInfo.</p>
     * <p>If this course index exists in list of all courses, indexFound = true.</p>
     * @param targetIndex the course index with information to be updated
     * @param fileName this course name
     */
    public void locateIndex(String targetIndex, String fileName) {
        try {
            File file = new File(fileName);
            Scanner in = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            indexFound = false;

            while(in.hasNextLine()) {
                String line = in.nextLine();
                buffer.append(line).append("\n");
                if(line.contains(targetIndex)) {
                    oldInfo = line;
                    indexFound = true;
                }
            }
            fileContents = buffer.toString();
        }
        catch (FileNotFoundException e) {
            System.out.println(fileName + " not found.");
        }
    }
   
    public String getIndexInfo() { return null; }


    public void setCourseIndex(String courseIndex) { this.courseIndex = courseIndex; }
    public String getCourseIndex() { return courseIndex; }

    public void setVacancy(int vacancy) { this.vacancy = vacancy; }
    public int getVacancy() { return vacancy; }

    public void setWaitList(int waitList) { this.waitList = waitList; }
    public int getWaitList() { return waitList; }
    
    

}
