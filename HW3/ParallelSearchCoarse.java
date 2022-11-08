import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParallelSearchCoarse {
	public static Thread reader;
	public static Thread searcher;
	public static void main(String args[]) throws InterruptedException, FileNotFoundException {
		if( args.length < 2) {
			System.out.println("Usage: Java ParallelSearchCoarse FileName Pattern");
			System.exit(0);
		}
		
		String fname = args[0];         // fileName = files/wikipedia2text-extracted.txt
		String pattern = args[1];       // pattern = "(John) (.+?) ";
		long start = System.currentTimeMillis();
		
		// Create your thread reader and searcher here
		// TODO
		
		// new shared queue with max of 100
		SharedQueue h = new SharedQueue(100);
		//takes in fname that is entered in cmd
		FileReader fr = new FileReader(fname);
		BufferedReader buffReader = new BufferedReader(fr);
		
		//shared queue
		Reader reader = new Reader(h, buffReader);
		Searcher searcher = new Searcher(h, pattern);
		reader.start();
		searcher.start();
		
		reader.join(); //need to figurethis out bc its not thread //just changed to Thread removed  implements runnable
		searcher.join();
		long end = System.currentTimeMillis();
		System.out.println("Total number of lines searched is " + Reader.getCount() + ".");
		System.out.println("Total occurence of pattern " + pattern + "is " + Searcher.getCount() + ".");
		System.out.println("Time cost for concurrent solution is " + (end - start) + ".");
		
	}

}
