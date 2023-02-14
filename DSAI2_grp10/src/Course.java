import java.util.*;


public class Course {
	private int NumOfIndex;
	private String code;
	private String name;
	private String au;
	private String sch;
	private ArrayList<IndexNumber> Indexes;
	private ArrayList<Classes> classes;
	
	public Course() { }
	
	public Course(ArrayList<IndexNumber> Indexes, String code, String name, String au, String sch, ArrayList<Classes> classes) {
		this.Indexes = Indexes;
		this.code = code;
		this.name = name;
		this.au = au;
		this.sch = sch;
		this.classes = classes;
	}
	
	/**
	 * <p>Stores each course's information in a formatted order to be written to 
	 * individual course's file in ArrayLists.</p>
	 * @return ArrayList containing this course's information
	 */
	public ArrayList<String> createArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add(getName());
		array.add(getCode());
		array.add(getAu());
		array.add(getSch());
		ArrayList<IndexNumber> indices = getIndexes();
		ArrayList<Classes> classes = getClasses();
		for (int i=0; i<indices.size(); i++) {
			array.add("courseIndex"+indices.get(i).getCourseIndex()
					+ ",Total Size:" + indices.get(i).getVacancy() 
					+ ",Vacancy:"+indices.get(i).getVacancy() 
					+ ",Lecture Day:" + classes.get(i).getLectDay() 
					+ ",Lecture Start Time:" + classes.get(i).getLectStartTime() 
					+ ",Lecture End Time:" + classes.get(i).getLectEndTime() 
					+ ",Tutorial Day:" + classes.get(i).getTutDay() 
					+ ",Tutorial Start Time:" + classes.get(i).getTutStartTime() 
					+ ",Tutorial End Time:" + classes.get(i).getTutEndTime() 
					+ ",Lab Day:" + classes.get(i).getLabDay() 
					+ ",Lab Start Time:" + classes.get(i).getLabStartTime() 
					+ ",Lab End Time:" + classes.get(i).getLabEndTime());	
			}		
		for (int j=0; j<indices.size(); j++)
			array.add("CourseId"+indices.get(j).getCourseIndex());
			
		return array;
	}
	
	
	public int getNumOfIndex() { return NumOfIndex; }
	public void setNumOfIndex(int NumOfIndex) { this.NumOfIndex = NumOfIndex; }
	
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getAu() { return au; }
	public void setAu(String au) { this.au = au; }
	
	public String getSch() { return sch; }
	public void setSch(String sch) { this.sch = sch; }
	
	public ArrayList<IndexNumber> getIndexes() { return Indexes; }
	public void setIndexes(ArrayList<IndexNumber> indexes) { this.Indexes = indexes; }
	
	public ArrayList<Classes> getClasses() { return classes; }
	public void setClasses(ArrayList<Classes> classes) { this.classes = classes; }
	
}
