//Matthew Szymanski 00894656 CSCD467 HW2

//package edu.ewu.ytian.prime;

public class MyPrimeTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		if (args.length < 3) {
			System.out.println("Usage: MyPrimeTest numThread low high \n");
			return;
		}
		
		int nthreads = Integer.parseInt(args[0]);
		int low = Integer.parseInt(args[1]);
		int high = Integer.parseInt(args[2]);
		Counter c = new Counter();
		
		//test cost of serial code
		long start = System.currentTimeMillis();
		int numPrimeSerial = SerialPrime.numSerailPrimes(low, high);
		long end = System.currentTimeMillis();
		long timeCostSer = end - start;
		System.out.println("Time cost of serial code: " + timeCostSer + " ms.");
		
		//test of concurrent code
		// **************************************
        // Write me here
		//Need to divide up into segments
		int sizeOfSegment = high / nthreads;
		int lowerBound = low;
		int higherBound;
		
		ThreadPrime[] Thread = new ThreadPrime[nthreads];
		//System.out.println("checkmark");
		long conStart = System.currentTimeMillis();

		for(int i = 0; i < nthreads; i++) {
			//higher gets lower + segment size - 1
			// end total had +1 of what it actually had. 
			higherBound =  lowerBound + sizeOfSegment - 1;

			Thread[i] = new ThreadPrime(lowerBound, higherBound, c);
			//for testing purpose checking for nullpointer
			//System.out.println("checkmark" + i);
			lowerBound = higherBound + 1;
			Thread[i].start();
			
		}
		
		for(int i = 0; i < nthreads; i++) {
			Thread[i].join();
			//for testing purpose
			//System.out.println("checkmark" + i);

		}
		long conEnd = System.currentTimeMillis();
		long timeCostCon = conEnd - conStart;
		
		// **************************************
		System.out.println("Time cost of parallel code: " + timeCostCon + " ms.");
		//speedup ratio = timeCostSerialCode / timeCostConcurrentCode.
		System.out.format("The speedup ration is by using concurrent programming: %5.2f. %n", (double)timeCostSer / timeCostCon);
		
		System.out.println("Number prime found by serial code is: " + numPrimeSerial);
		System.out.println("Number prime found by parallel code is " + c.total());
	}
}
