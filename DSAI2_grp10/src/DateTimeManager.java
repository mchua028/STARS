import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTimeManager {
    private Date now = new Date(); // obtain current date (Date objectType)
    private Calendar calNow = Calendar.getInstance(); // obtain current date (Calendar objectType)
    private Calendar[] date = new Calendar[2]; // array to store the start and end date and time of the student's access period
    
	public void printCurrentTime() {
	    System.out.println(now.toString());
	    System.out.println();
	}
	
	/**
	 * <p>Gets this student's access period.</p>
	 * @param username this student's username
	 */
	public void getAccessPeriod(String username) {
		try {
			FileReader readLoginInfoFile = new FileReader("student_login_info.txt");
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		
		    String line;
		
		    while ((line = bufferedReader.readLine()) != null) {
		        if (line.equals(username) == true) {
		        	bufferedReader.readLine(); // read away the student's encrypted password
		        	
		        	String lineDateTime = bufferedReader.readLine(); // reading the student access period
		        	String[] arrOfLine = lineDateTime.split("[|]",4);
		        	
		        	String[] arrOfStartDate = arrOfLine[0].split("/",3);
		        	int startDay = Integer.parseInt(arrOfStartDate[0]);
		           	int startMonth = Integer.parseInt(arrOfStartDate[1])-1;
		           	int startYear = Integer.parseInt(arrOfStartDate[2]);
		           	
		        	String[] arrOfStartTime = arrOfLine[1].split(":",2);
		        	int startHour = Integer.parseInt(arrOfStartTime[0]);
		           	int startMin = Integer.parseInt(arrOfStartTime[1]);
		        	
		        	String[] arrOfEndDate = arrOfLine[2].split("/",3);
		        	int endDay = Integer.parseInt(arrOfEndDate[0]);
		           	int endMonth = Integer.parseInt(arrOfEndDate[1])-1;
		           	int endYear = Integer.parseInt(arrOfEndDate[2]);
		           	
		        	String[] arrOfEndTime = arrOfLine[3].split(":",2);
		        	int endHour = Integer.parseInt(arrOfEndTime[0]);
		           	int endMin = Integer.parseInt(arrOfEndTime[1]);
		        	
		  	        date[0] = new GregorianCalendar(startYear, startMonth, startDay, startHour, startMin); 
		  	        date[1] = new GregorianCalendar(endYear, endMonth, endDay, endHour, endMin);
		  	        
		  	        break; 	
		        }
		       
		    }
		    readLoginInfoFile.close();
		   
		}
		catch (FileNotFoundException e) {
			System.out.println( "File Error!" + e.getMessage() );
			System.exit( 0 );
		}
		catch (IOException e) {
			System.out.println( "IO Error!" + e.getMessage() );
			e.printStackTrace();
			System.exit( 0 );
		}		
	}
	
	/**
	 * @param username this student's username
	 * @return boolean; whether the student is allowed to access during current system time
	 */
	public boolean verifyAccessPeriod(String username) {
		getAccessPeriod(username);
		
		// compare current date and time against the access period date and time
		if (calNow.after(date[0]) && calNow.before(date[1]))
			return true;
		else {
			System.out.println("You are not allowed to access the system now.");
			System.out.println("Your access period is: ");
			System.out.println("From: " + date[0].getTime());
			System.out.println("To: " + date[1].getTime());
			return false; 
		}
			
	}

}
