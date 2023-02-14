import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NewCourse {
	private String fileName;
	private String code;
	private int numOfIndex;
	Scanner sc = new Scanner(System.in);
	AllCourse addcourse = new AllCourse("Courses.txt");
	
	
	public NewCourse() {
		ErrorChecker ec = new ErrorChecker();
		System.out.print("Enter course name: ");
		fileName = sc.nextLine();
		System.out.print("Enter course code: ");
		code = sc.nextLine();
		numOfIndex = ec.posIntChecker("Enter total number of index numbers: ");
	}
	
	/**
	 * <p>Admin adds new course.</p>
	 * <p>New course name Courses.txt and Waitlist.txt.</p>
	 * <p>Individual course file corresponding to this new course created.</p>
	 * <p>New course information added.</p>
	 */
	public void addCourse() {
		AllCourse addcourse2 = new AllCourse("Waitlist.txt");
		ArrayList<IndexNumber> indexes = new ArrayList<IndexNumber>();
		ArrayList<Classes> classes = new ArrayList<Classes>();
		ErrorChecker ec = new ErrorChecker();
		int lectDay, lectStartTime, lectEndTime, tutDay, tutStartTime, tutEndTime, labDay, labStartTime, labEndTime; 
		
		addcourse.setCourseName(fileName);
		addcourse.locateCourse();
		if (addcourse.getCourseFound()) {
			System.out.println("Course already exist.");
			return;
		}
		
		for (int i=0; i<numOfIndex; i++) {
			System.out.print("Enter index number: ");
			String id = sc.nextLine();
			addcourse2.addCourse(fileName+", CourseId"+id);
			int vac = ec.posIntChecker("Enter number of vacancy: ");
			IndexNumber index = new IndexNumber(id, vac);
			indexes.add(index);
			do {
				lectDay = ec.dayChecker("Enter lecture day: ");
				lectStartTime = ec.timeChecker("Enter lecture start timing: ");
				lectEndTime = ec.timeChecker("Enter lecture end timing: ");
			} while (!ec.beforeAfterChecker(lectStartTime, lectEndTime));
			
			do {
				tutDay = ec.dayChecker("Enter tutorial day: ");
				tutStartTime = ec.timeChecker("Enter tutorial start timing: ");
				tutEndTime = ec.timeChecker("Enter tutorial end timing: ");
			} while (!ec.beforeAfterChecker(tutStartTime,tutEndTime));
			
			do {
				labDay = ec.dayChecker("Enter lab day: ");
				labStartTime = ec.timeChecker("Enter lab start timing: ");
				labEndTime = ec.timeChecker("Enter lab end timing: ");
			} while (!ec.beforeAfterChecker(labStartTime,labEndTime));
	    	
			Classes class1 = new Classes(lectDay, lectStartTime, lectEndTime, tutDay, tutStartTime, tutEndTime, labDay, labStartTime, labEndTime);
			classes.add(class1);
		}
		
		System.out.print("Enter school of course: ");
		String sch = sc.nextLine();
		int au_int = ec.posIntChecker("Enter number of AUs for course: ");
		String au = Integer.toString(au_int);
		Course course = new Course(indexes, code, fileName, au, sch, classes);
		List<String> array = new ArrayList<String>();
		array = course.createArray();
		EditFile edfile = new EditFile();
		edfile.write(array, fileName);	
		addcourse.addCourse(fileName);
		addcourse.printAllCourses("Courses.txt");
	}
	
}
