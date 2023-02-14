import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.lang.ArrayIndexOutOfBoundsException;


public class ErrorChecker {
	Scanner sc = new Scanner(System.in);
	
	public int timeChecker(String qn) {
		boolean correct = false;
		int time;
		
		while (!correct) {
			try {
				System.out.print(qn);
				time = sc.nextInt();
				if (time < 0 || time > 2359)
	    			System.out.println("Error: Time must be in 24H format.");
				else if (time%100 > 59) // check the MM of HHMM
	    			System.out.println("Error: Time must be in 24H format.");
				else {
					correct = true;
					return time;
				}		
			}
			catch (InputMismatchException e) {
				sc.next();
    			System.out.println("Error: Time must be in 24H format.");
			}
		}
		return -1;
	}
	
	public boolean beforeAfterChecker(int startTime, int endTime) {
		if (startTime < endTime || (startTime == 0 && endTime == 0)) 
			return true;
		else {
			System.out.println("Error: Start time must be before end time.");
       		System.out.println("Please enter again.");
			return false;
		}
		
	}
	
	public int dayChecker(String qn) {
		boolean correct = false;
		int day;
		
		while (!correct) {
			try {
				System.out.print(qn);
				day = sc.nextInt();
				if (day < 0 || day > 6)
					System.out.println("Error: Day must be from 1 to 6.");
				else {
					correct = true;
					return day;
				}		
			}
			catch (InputMismatchException e) {
				sc.next();
				System.out.println("Error: Day must be from 1 to 6.");
			}
		}
		return -1;
	}
	
	public int posIntChecker(String qn) {
		boolean correct = false;
		int num;
		
		while (!correct) {
			try {
				System.out.print(qn);
				num = sc.nextInt();
				if (num <= 0)
					System.out.println("Error: Input must be a positive integer.");
				else {
					correct = true;
					return num;
				}		
			}
			catch (InputMismatchException e) {
				sc.next();
				System.out.println("Error: Input must be a positive integer.");
			}
		}
		return -1;
	}
	
	public String[] dateChecker(String qn) {
		boolean correct = false;
		int[] num = new int[3];
		String [] arrOfDate = {"", "", "", ""};

		while (!correct) {
			try {
				System.out.print(qn);
				String dateInput = sc.nextLine();
				String[] arrOfDate1 = dateInput.split("/", 3);
				for (int i=0; i<3; i++)
					arrOfDate[i] = arrOfDate1[i];
				num[0] = Integer.parseInt(arrOfDate[0]);
		       	num[1] = Integer.parseInt(arrOfDate[1]);
		       	num[2] = Integer.parseInt(arrOfDate[2]);

		       	switch (num[1]) {
		       	case 1: case 3: case 5: case 8: case 10: case 12:
		       		if (num[0] < 1 || num[0] > 31)
		       			throw new NumberFormatException();
		       		correct = true;
		       		arrOfDate[3] = dateInput;
		       		return arrOfDate;
		       		
		       	case 2:
		       		if (num[0] < 1 || num[0] > 28)
		       			throw new NumberFormatException();
		       		arrOfDate[3] = dateInput;
		       		return arrOfDate;
		       		
		       	case 4: case 6: case 7: case 9: case 11:
		       		if (num[0] < 1 || num[0] > 30)
		       			throw new NumberFormatException();
		       		arrOfDate[3] = dateInput;
		       		return arrOfDate;
		       		
		       	default:
		       		throw new NumberFormatException();
		       	}
			}
			catch (NumberFormatException e1) {
				System.out.println("Error: Date must be in DD/MM/YYYY format");
			}
			catch(ArrayIndexOutOfBoundsException e2) {
				System.out.println("Error: Date must be in DD/MM/YYYY format");
			}
		}
		return arrOfDate;
	}
	
	public String[] HHMMTimeChecker(String qn) {
		boolean correct = false;
		int[] num = new int[2];
		String [] arrOfTime = {"", "", ""};
		
		while (!correct) {
			try {
				System.out.print(qn);
				String timeInput = sc.nextLine();
				String[] arrOfTime1 = timeInput.split(":", 2);
				for (int i=0; i<2; i++)
					arrOfTime[i] = arrOfTime1[i];
				num[0] = Integer.parseInt(arrOfTime[0]);
		       	num[1] = Integer.parseInt(arrOfTime[1]);
		       	if ((num[0] >= 0 && num[0] <= 23) && (num[1] >= 0 && num[1] <= 59)) {
		       		correct = true;
		       		arrOfTime[2] = timeInput;
		       		return arrOfTime;
		       	}
		       	throw new NumberFormatException();
			}		
			catch (NumberFormatException e1) {
    			System.out.println("Error: Time must be in 24H format.");
			}
			catch(ArrayIndexOutOfBoundsException e2) {
    			System.out.println("Error: Time must be in 24H format.");
			}
		}
		return arrOfTime;
	}
	
}
