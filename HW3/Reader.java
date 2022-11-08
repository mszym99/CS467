import java.io.BufferedReader;
import java.io.IOException;

public class Reader extends Thread{
	private BufferedReader reader;
    private SharedQueue q;
    private static int lineCount = 0;
    
    public Reader(SharedQueue h, BufferedReader reader) {
        this.q = h;
        this.reader = reader;
    }

    
    @Override
    public synchronized void run(){
       
    	try {
        	
            String curline = reader.readLine();
            
            while(curline != null){
            	
                if (!curline.trim().isEmpty()) {
                	q.enqueue(curline);
                    
                }
                
                lineCount++;
                curline = reader.readLine();
                //for testing
                //System.out.println(lineCount);
            }
            	q.setDoneReading(true); //set to true since completed
            	//close reader
            	reader.close();
            
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    	
    }
    
    
    
    //get linecount
    public static int getCount(){
        return lineCount;
    }
}