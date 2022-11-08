import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher extends Thread{
    private String pat;
    private SharedQueue q;
    
    private static int tot;
    static boolean doneReading = false; //doneReading is turned true when
   

    public Searcher(SharedQueue h, String pat){
        this.q = h;
        this.pat = pat;
    }

    
    
    public void run(){
    	
        tot = 0;
        
        while (q.getDoneReading() == false || q.isEmpty() == false) {
            String line = (String) this.q.dequeue();
            
            
            Pattern pattern = Pattern.compile(this.pat);
            Matcher matcher = pattern.matcher(line);
            
            int count = 0;
            
            //every find will increase count then combine into tot
            while(matcher.find()) {
                count++;
                
            }
            tot+=count;
            //for testing
           // System.out.println(tot);
        }
    }
    
    
    
    //get total count 
    public static int getCount(){
        return tot;
    }

    
    public static void setDone(){
        doneReading = true;
    }
}