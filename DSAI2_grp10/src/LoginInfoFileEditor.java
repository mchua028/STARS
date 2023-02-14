import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LoginInfoFileEditor {
	
	public int lineNo(String username){
		int count = 0;
		try {
			FileReader readLoginInfoFile = new FileReader("student_login_info.txt");
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		
		    String line;
		
		    while ((line = bufferedReader.readLine()) != null) {
		        if (line.equals(username))
		        	return count+1;
		        else {
		        	count++;
		        }
		    }
		    
		    System.out.println("Error: Username not found.");
		   	readLoginInfoFile.close();
		   	return -1;
		    
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void writeAccessPeriod(int lineNo, String startDateInput, String startTimeInput, String endDateInput, String endTimeInput) {
		try {
			FileReader readLoginInfoFile = new FileReader("student_login_info.txt");
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		    FileWriter bufferfile = new FileWriter("bufferfile.txt");
		    BufferedWriter bufferedWriter = new BufferedWriter(bufferfile);
		   
		    for (int i=0; i<lineNo+1; i++) {
				String line = bufferedReader.readLine();
				bufferedWriter.write(line+"\n");
		    }
		    
		    bufferedReader.readLine();

		    bufferedWriter.write(startDateInput+"|"+startTimeInput+"|"+endDateInput+"|"+endTimeInput+"\n");
		    
		    String otherLines;
		    
		    while ((otherLines = bufferedReader.readLine()) != null)
		    	bufferedWriter.write(otherLines+"\n");
		    bufferedReader.close();
		    bufferedWriter.close();
		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		rewriteFile();
	}
	
	public void writePassword(String username, String password) {
		int lineNo = lineNo(username);
		
		try {
			FileReader readLoginInfoFile = new FileReader("student_login_info.txt");
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		    FileWriter bufferfile = new FileWriter("bufferfile.txt");
		    BufferedWriter bufferedWriter = new BufferedWriter(bufferfile);
		   
		    for (int i=0; i<lineNo; i++) {
				String line = bufferedReader.readLine();
				bufferedWriter.write(line+"\n");
		    }
		    
		    bufferedReader.readLine();

		    bufferedWriter.write(password+"\n");
		    
		    String otherLines;
		    
		    while ((otherLines = bufferedReader.readLine()) != null)
		    	bufferedWriter.write(otherLines+"\n");
		    
		    bufferedReader.close();
		    bufferedWriter.close();
		
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		rewriteFile();
	}
	
	
	public void rewriteFile() {
		try {
			FileReader readLoginInfoFile = new FileReader("bufferfile.txt");
		    BufferedReader bufferedReader = new BufferedReader(readLoginInfoFile);
		    FileWriter bufferfile = new FileWriter("student_login_info.txt");
		    BufferedWriter bufferedWriter = new BufferedWriter(bufferfile);
		    
		    String line;
		    while ((line = bufferedReader.readLine()) != null) {
		    	bufferedWriter.write(line+"\n");
		    }
		    
		    bufferedReader.close();
		    bufferedWriter.close();
		    
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
