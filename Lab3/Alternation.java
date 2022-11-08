//package ytian.alternate;

public class Alternation {
	
	boolean isT1turn;
	final Object lock = new Object();
	Thread t1;
	Thread t2;
	
	public Alternation() {
		
		isT1turn = true;
		t1 = new Thread(new Runnable() {
	        @Override
	        public void run() {
                for (int i = 1; i <= 50; i += 2) {
                	
	                	while( !getSync());  //guarded block
	                    System.out.println("T1=" + i);
	                    isT1turn = false;
	                    try {
	                    	Thread.sleep(1000);
	                    } catch (InterruptedException e) {}
                	}//end of sync
                }//end of for
	        }
	    );
	    t2 = new Thread(new Runnable() {

	        @Override
	        public void run() {
                for (int i = 2; i <= 50; i += 2) {
                	
	                    while (getSync());   //guarded block
	                        try {
	                        	Thread.sleep(1000);
	                        } catch (InterruptedException e) {}
	                    System.out.println("T2=" + i);
	                    isT1turn = true;
                	}//end of sync
                }
	        }
	   );
	    t1.start();
	    t2.start();
	}
	
	public synchronized boolean getSync() {
		return isT1turn;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Alternation();
	}
}


