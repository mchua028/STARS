import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class StudentAccessPeriodEditor {
	
	/**
	 * <p>If admin is editing access period for a current student, editStudentAccessPeriod() 
	 * edits access period via prompting for student’s username</p>
	 * <p>If admin is editing access period a new student, editStudentAccessPeriod() 
	 * edits access period via using username parameter passed method and adds student into the system
	 * @param edit if true, admin is editing access period for a current student; else editing for a new student
	 * @param _username the student's username
	 */
	public void editStudentAccessPeriod(boolean edit, String _username){
		Scanner sc = new Scanner (System.in);
		LoginInfoFileEditor accessPeriod = new LoginInfoFileEditor();
		String username = null;
		int accessPeriodLineNo;
		
		if (edit && _username == null) {
			System.out.println("\n---- Student Access Period Editor ----");
		}
			
		do {
			if (edit && _username == null) {
				System.out.print("Enter the username of the student: ");
				username = sc.nextLine();
			}	
			
			else if (!edit && _username != null){
				username = _username;
			}
			
			accessPeriodLineNo = accessPeriod.lineNo(username);
		} while (accessPeriodLineNo == -1);
		
		
		Calendar startTime, endTime;
		ErrorChecker ec = new ErrorChecker();
		
		do {
			String [] arrOfSDate = ec.dateChecker("Enter the start date in the format DD/MM/YYYY: ");
			int startDay = Integer.parseInt(arrOfSDate[0]);
	       	int startMonth = Integer.parseInt(arrOfSDate[1]);
	       	int startYear = Integer.parseInt(arrOfSDate[2]);
	       	
	       	String [] arrOfSTime = ec.HHMMTimeChecker("Enter the start time in the 24-HR format HH:MM: ");
	       	int startHour = Integer.parseInt(arrOfSTime[0]);
	       	int startMin = Integer.parseInt(arrOfSTime[1]);
	       	
	       	startTime = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMin);
	       	
	       	String [] arrOfEDate = ec.dateChecker("Enter the end date in the format DD/MM/YYYY: ");
	       	int endDay = Integer.parseInt(arrOfEDate[0]);
	        int endMonth = Integer.parseInt(arrOfEDate[1]);
	        int endYear = Integer.parseInt(arrOfEDate[2]);
	        
	        String [] arrOfETime = ec.HHMMTimeChecker("Enter the end time in the 24-HR format HH:MM: ");
	       	int endHour = Integer.parseInt(arrOfETime[0]);
	       	int endMin = Integer.parseInt(arrOfETime[1]);
	       	
	       	endTime = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMin);
	       	
	       	if (startTime.after(endTime)){
	       		System.out.println("Error: Start time cannot be after end time.");
	       		System.out.println("Please enter again.");
			}
	       	else {
	       		accessPeriod.writeAccessPeriod(accessPeriodLineNo,arrOfSDate[3],arrOfSTime[2],arrOfEDate[3],arrOfETime[2]);
	       		break;
		     }
		} while (startTime.after(endTime)); 	
	}    	

}
