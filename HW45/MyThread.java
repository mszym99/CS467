//package CSCD467HW45;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread
{
	private int[] threadArray;
	private int regionalMax;
	private float[] normalized;
	private MaxHolder maxHolder;
	private int row;
	private CyclicBarrier barrier;
	
	public MyThread()
	{
		
	}
	public MyThread(int[] inputArray, MaxHolder maxHolder, int row, CyclicBarrier barrier)
	{
		this.threadArray = new int[inputArray.length];
		for(int i = 0; i < this.threadArray.length; i++)
		{
			this.threadArray[i] = inputArray[i];
		}
		this.regionalMax = 0;
		this.normalized = new float[inputArray.length];
		this.maxHolder = maxHolder;
		this.row = row;
		this.barrier = barrier;
	}
	
	@Override
	public void run()
	{
		//Phase 1
		if(this.threadArray[0] >= 0)
		{
			this.regionalMax = threadArray[0];
		}
		for(int i = 1; i < this.threadArray.length; i++)
		{
			if(this.threadArray[i] > this.regionalMax)
			{
				this.regionalMax = this.threadArray[i];
			}
		}
		//this.maxHolder.waiter(this.row, this.regionalMax);
		this.maxHolder.testMax(this.regionalMax);
		/*
		barrier = new CyclicBarrier(10, 
									new Runnable(){ 
										public void run() {
											phaseTwo();
										}});
		*/
		try {
			this.barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Phase 2
		for(int i = 0; i < this.threadArray.length; i++)
		{
			this.normalized[i] = (float) (1.0 * this.threadArray[i]) / this.maxHolder.getMax();
		}
		maxHolder.setNormalized(normalized, row);
		
		
	}

	public int getRow()
	{
		return this.row;
	}
}