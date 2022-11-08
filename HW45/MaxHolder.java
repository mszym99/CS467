//package CSCD467HW45;

public class MaxHolder 
{
	private int max;
	private float[][] normalized;
	
	public MaxHolder()
	{
		
	}
	public MaxHolder(int x, int y)
	{
		this.normalized = new float[x][y];
		this.max = 0;
	}
	public void setMax(int newMax)
	{
		this.max = newMax;
	}
	public int getMax()
	{
		return this.max;
	}
	public synchronized void testMax(int test)
	{
		if(this.max < test)
		{
			this.max = test;
		}
	}
	public synchronized void setNormalized(float[] newRow, int row)
	{
	
		for(int i = 0; i < newRow.length; i++)
		{
			this.normalized[row][i] = newRow[i];
		}
	}
	public void display()
	{
		for(int i = 0; i < normalized.length; i++)
		{
			for(int x = 0; x < normalized[i].length; x++)
			{
				System.out.printf("%.6f  ", normalized[i][x]);
			}
			System.out.println();
		}
	}
}