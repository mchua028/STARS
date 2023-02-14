import java.io.*;
import java.util.*; 


public class EditFile {
	List<String> lines = new ArrayList<String>();
    String line = null;
    
    public void write(List<String> lines, String fileName){
        try{
          FileWriter fwStream = new FileWriter(fileName + ".txt");
          BufferedWriter bwStream = new BufferedWriter(fwStream);
          PrintWriter pwStream = new PrintWriter(bwStream);
          for (int i=0;i<lines.size();i++ ) {
        	  String info = (String)lines.get(i);
        	  pwStream.println(info);
          }
          
          pwStream.close();
        }
        catch (IOException e) {
          System.out.println("ERROR");
          e.printStackTrace();
          System.exit(0);
        }
      }

    public void  replaceHori(String fileName,String searchstr, String replacestr) {
        try {
            File f1 = new File(fileName + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(searchstr)){
                    line = line.replace(searchstr, replacestr);
                    lines.add(line);
                    lines.add("\n");
                    }
                else{
                    lines.add(line);
                    lines.add("\n");
                  }
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                 out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void  replaceVerti(String fileName,String searchstr, String replacestr) {
        try {
            File f1 = new File(fileName + ".txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(searchstr)){
                    line = line.replace(searchstr, replacestr+"\n");
                    lines.add(line);}
                else{
                    lines.add(line);
                    lines.add("\n");
                  }
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                 out.write(s);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	public void add(String fileName,String index) {
	try {
		FileWriter fwStream = new FileWriter(fileName + ".txt", true);
        BufferedWriter bwStream = new BufferedWriter(fwStream);
        PrintWriter pwStream = new PrintWriter(bwStream);
    	pwStream.println(index+",");
		pwStream.close();
		}
	catch (IOException e) {
          System.out.println("ERROR");
          e.printStackTrace();
          System.exit(0);
	        }
	}
	
   	
}
