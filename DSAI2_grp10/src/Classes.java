import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class Classes extends IndexNumber {

    private int lectDay, lectStartTime, lectEndTime, tutDay, tutStartTime, tutEndTime, labDay, labStartTime, labEndTime, vac;
    

    public Classes (int lectDay, int lectStartTime, int lectEndTime, int tutDay, int tutStartTime, int tutEndTime, int labDay, int labStartTime, int labEndTime) {
    	this.lectDay = lectDay;
    	this.lectStartTime = lectStartTime;
    	this.lectEndTime = lectEndTime;
    	this.tutDay = tutDay;
    	this.tutStartTime = tutStartTime;
    	this.tutEndTime = tutEndTime;
    	this.labDay = labDay;
    	this.labStartTime = labStartTime;
    	this.labEndTime = labEndTime;
    }
    
    public Classes(String fileName) {
        super(fileName);
    }
    
    /**
     * @param type the update type for specified course index
     * @return formatted String to be written to individual course file
     */
    public String getIndexInfo(String type) {
    	if(type == "Vacancy") { return type + ":" + getVacancy(); }
    	if(type == "Lecture Day") { return type + ":" + getLectDay(); }
    	if(type == "Lecture Start Time") { return type + ":" + getLectStartTime(); }
    	if(type == "Lecture End Time") { return type + ":" + getLectEndTime(); }
    	if(type == "Tutorial Day") { return type + ":" + getTutDay(); }
    	if(type == "Tutorial Start Time") { return type + ":" + getTutStartTime(); }
    	if(type == "Tutorial End Time") { return type + ":" + getTutEndTime(); }
    	if(type == "Lab Day") { return  type + ":" + getLabDay(); }
    	if(type == "Lab Start Time") { return type + ":" + getLabStartTime(); }
    	if(type == "Lab End Time") { return type + ":" + getLabEndTime(); }
    	else { return ""; }
        
    }

    /**
     * @param type the update type for specified course index
     * @param targetIndex the course index with information to be updated
     */
    public void updateIndexInfo(String type, String targetIndex) {
    	setCourseIndex(targetIndex);
        locateIndex("courseIndex"+targetIndex, super.fileName);
        try {
            System.out.println("Updating index details...");
            FileWriter writer = new FileWriter(super.fileName);
            
            String[] oldInfo2 = super.oldInfo.split(",");
            String newInfo = oldInfo2[0];
            for(int i=1; i<oldInfo2.length; i++) {
            	if(oldInfo2[i].contains(type)) {
            		oldInfo2[i] = getIndexInfo(type);
            	}
            	newInfo = newInfo + ","+ oldInfo2[i];
            }
            String updatedInfo = super.fileContents.replaceAll(super.oldInfo, newInfo);

            writer.append(updatedInfo);
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <p>Adds new index by locating the specified course index in the individual course file 
     * and updating all details of this index.</p>
     * <p>Calls addIndex(targetIndex).</p>
     * @param targetIndex the course index with information to be updated
     */
    public void createIndex(String targetIndex) {
    	ErrorChecker ec = new ErrorChecker();
    	setCourseIndex(targetIndex);
    	locateIndex(targetIndex, fileName);
    	
    	if(!indexFound) {
			setVacancy(ec.posIntChecker("Enter number of vacancy: "));
			
			setLectDay(ec.dayChecker("Enter lecture day: "));
			setLectStartTime(ec.timeChecker("Enter index lecture start timing: "));
			setLectEndTime(ec.timeChecker("Enter index lecture end timing: "));
			setTutDay(ec.dayChecker("Enter tutorial day: "));
			setTutStartTime(ec.timeChecker("Enter index tutorial start timing: "));
	      	setTutEndTime(ec.timeChecker("Enter index tutorial end timing: "));
			setLabDay(ec.dayChecker("Enter lab day: "));
	        setLabStartTime(ec.timeChecker("Enter index lab start timing: "));
	        setLabEndTime(ec.timeChecker("Enter index lab end timing: "));
	        
			addIndex(targetIndex);
    	}
    	
    	else {
    		System.out.println("Index already exist!");
    	}
    }
    
    /**
     * @param targetIndex the course index with information to be updated
     */
    public void addIndex(String targetIndex) {
        try {
        	System.out.println("Adding course index ...");
            FileWriter fileWriter = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write("courseIndex" + courseIndex + ",Total Size:" + getVacancy() + ",Vacancy:" + getVacancy() +
            		",Lecture Day:" + getLectDay() + ",Lecture Start Time:" + getLectStartTime() + 
            		",Lecture End Time:" + getLectEndTime() +
            		",Tutorial Day:" + getTutDay() + ",Tutorial Start Time:" + getTutStartTime() +
            		",Tutorial End Time:" + getTutEndTime() +
            		",Lab Day:" + getLabDay() + ",Lab Start Time:" + getLabStartTime() +
            		",Lab End Time:" + getLabEndTime()+"\n" +
            		"CourseId" + courseIndex + "\n");
            AllCourse allcourse = new AllCourse("Waitlist.txt");
            String [] name = fileName.split("[.]");
    		allcourse.addCourse(name[0]+", CourseId"+ courseIndex);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * <p>Removes index by locating the specified course index in the individual course file 
     * and removing index if located.</p>
     * @param targetIndex the course index to be removed
     */
    public void removeIndex(String targetIndex) {
    	locateIndex("courseIndex" + targetIndex, fileName);
    	if(indexFound) {
	        try {
	            System.out.println("Removing course index ...");
	            FileWriter writer = new FileWriter(fileName);
	            
	            String updatedInfo = fileContents.replaceAll(super.oldInfo + "\n", "");
	            writer.append(updatedInfo); 
	            
	            writer.flush();
	            writer.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        else {
        	System.out.println("Error: Index does not exist.");
        }
    	
    	locateIndex("CourseId" + targetIndex, fileName);
    	if(indexFound) {
	        try {
	            FileWriter writer = new FileWriter(fileName);
	            
	            String updatedInfo = fileContents.replaceAll(super.oldInfo + "\n", "");
	            writer.append(updatedInfo);
	            
	            writer.flush();
	            writer.close();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
        }
        else {
        	System.out.println("Error: Index not found, cannot remove index.");
        }
    }

    public void setLectDay(int lectDay) { this.lectDay = lectDay; }
    public int getLectDay() { return lectDay; }

    public void setLectStartTime(int lectStartTime) { this.lectStartTime = lectStartTime; }
    public int getLectStartTime() { return lectStartTime; }

    public void setLectEndTime(int lectEndTime) { this.lectEndTime = lectEndTime; }
    public int getLectEndTime() { return lectEndTime; }
    
    public void setTutDay(int tutDay) { this.tutDay = tutDay; }
    public int getTutDay() { return tutDay; }

    public void setTutStartTime(int tutStartTime) { this.tutStartTime = tutStartTime; }
    public int getTutStartTime() { return tutStartTime; }

    public void setTutEndTime(int tutEndTime) { this.tutEndTime = tutEndTime; }
    public int getTutEndTime() { return tutEndTime; }
    
    public void setLabDay(int labDay) { this.labDay = labDay; }
    public int getLabDay() { return labDay; }

    public void setLabStartTime(int labStartTime) { this.labStartTime = labStartTime; }
    public int getLabStartTime() { return labStartTime; }

    public void setLabEndTime(int labEndTime) { this.labEndTime = labEndTime; }
    public int getLabEndTime() { return labEndTime; }
    
}
