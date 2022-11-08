//package mainpack;

public class displayer extends Thread implements Runnable {
	private static String msg;
	private boolean exit;
	
	public displayer() {
		exit = true;
	}
	@Override
	public void run() {
		while(exit)
			try {
				Inputter.sendMsg(msg);
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				try {
					Inputter.Thread1.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		}
	public static void sendMsg(String newMsg) {
		msg = newMsg;
	}
	public void exitThread() {
		this.exit = true;
	}
}

