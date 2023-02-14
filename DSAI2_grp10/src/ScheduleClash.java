import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ScheduleClash {
	
	private String username;
	private int[] day = new int[3]; // 0,1,2 is lect, tut and lab respectively
	private int[][] time = new int [3][2]; // second array 0 is start time, 1 is end time
	
	public ScheduleClash(String username, String fileName, String indexNo) throws FileNotFoundException {
		this.username = username;
		
		String line;
		String buffer;
	       
		try {
			FileReader fr = new FileReader(fileName+ ".txt");
	        BufferedReader br = new BufferedReader(fr);
	        
	        while ((line = br.readLine()) != null) {
	        	if (line.contains("courseIndex" + indexNo)) {
			        String[] arrOfDetails = line.split(",");
			        buffer = (arrOfDetails[3].split(":"))[1];
			        day[0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[4].split(":"))[1];
			        time[0][0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[5].split(":"))[1];
			        time[0][1]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[6].split(":"))[1];
			        day[1] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[7].split(":"))[1];
			        time[1][0]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[8].split(":"))[1];
			        time[1][1]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[9].split(":"))[1];
			        day[2] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[10].split(":"))[1];
			        time[2][0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[11].split(":"))[1];
			        time[2][1]  = Integer.parseInt(buffer);
			        break;
	        	}
	        }
		
	        fr.close();
			br.close();
		}
		catch (FileNotFoundException e) {
            e.printStackTrace();
        }
		catch (IOException e) {
            e.printStackTrace();
		}
	}
	
	/**
	 * @return boolean; whether this course index timetable clashes with this student's existing courses timetable
	 */
	public boolean checkClash() {
		int[] _day = new int[3];
		int[][] _time = new int [3][2];
		String buffer;
		String line;
		boolean check = false;
		
		try {
			FileReader fr = new FileReader(username+ ".txt");
	        BufferedReader br = new BufferedReader(fr);
	        br.readLine();
	        
		    while ((line = br.readLine()) != null) {
		    	if (line.contains("Lecture")) {
			    	String[] arrOfDetails = line.split(",");
			        buffer = (arrOfDetails[3].split(":"))[1];
			        _day[0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[4].split(":"))[1];
			        _time[0][0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[5].split(":"))[1];
			        _time[0][1]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[6].split(":"))[1];
			        _day[1] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[7].split(":"))[1];
			        _time[1][0]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[8].split(":"))[1];
			        _time[1][1]  = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[9].split(":"))[1];
			        _day[2] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[10].split(":"))[1];
			        _time[2][0] = Integer.parseInt(buffer);
			        buffer = (arrOfDetails[11].split(":"))[1];
			        _time[2][1]  = Integer.parseInt(buffer);
			        check = checkDayClash(_day, _time);
			        if (check) {
			        	fr.close();
			        	br.close();
			        	return check;
			        }
		    	}
		    	
		    }
		    fr.close();
			br.close();
		    return check;
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: Username not found.");
		}
		catch (IOException e) {
            e.printStackTrace();
		}
		return check;
	}
	
	/**
	 * <p>If this course index's class' day clashes with an existing courses' class, check time clash.</p>
	 * @param _day this course index's day of lecture, tutorial, lab
	 * @param _time this course index's time of lecture, tutorial, lab
	 * @return boolean; whether this course index's day clashes with this student's existing courses' days
	 */
	public boolean checkDayClash(int[] _day, int[][] _time) {
		boolean check = false;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (_day[j] == 0)
					continue;
				else if (day[i] == _day[j]) {
					check = checkTimeClash(i,j, _time);
					if (check)
						return check;
				}
			} 
		}
		
		return check;
	}
	
	/**
	 * <p>Check for time clash if there is a clash in courses' days.</p>
	 * @param i for loop index
	 * @param j for loop index
	 * @param _time this course index's time of lecture, tutorial, lab
	 * @return whether this course index's time clashes with this student's existing courses' time
	 */
	public boolean checkTimeClash(int i, int j, int[][] _time) {
		if (time[i][0] < _time[j][0] && _time[j][0] < time[i][1]) // current start time is between the duration of to be added course
			return true;
		else if (time[i][0] < _time[j][1] && _time[j][1] < time[i][1]) // current end time is between the duration of to be added course
			return true;
		else if (time[i][0] > _time[j][0] && time[i][1] < _time[j][1]) // to be added is between current duration
			return true;
		else if (time[i][0]==_time[j][0] && time[i][1]==_time[j][1]) // to be added is the same as current duration
			return true;
		else
			return false;
	}
	
}
