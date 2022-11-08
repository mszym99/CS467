//package edu.ewu.ytian.prime;

public class Counter {
	private int c = 0;

    public synchronized void increment(int n) {
        // write me here
    	c = c + n;
    }

    public synchronized int total() {
        // write me here
    	return c;
    }
}
