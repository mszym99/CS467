//package mainpack;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


	public class Inputter extends JFrame implements KeyListener {

		private static final long serialVersionUID = 1L;
		private static JTextArea output;
		private static String msg = "";
		public static Thread Thread1;
		private static Thread Thread2;
		public static boolean enterPressed = false;
		public static boolean isT1 = true;
		
	public Inputter(String name) {	
		super(name);
		//JTextArea output = new JTextArea(20,30) // Can this statement replace the next one?
		output = new JTextArea(20,30);                      //create JTextArea in which all messages are shown.
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  // JTextArea always set focus on the last message appended.
		
		//
		add(new JScrollPane(output)); // add a Scroll bar to JFrame, scrolling associated with JTextArea object
		setSize(500, 500);            // when lines of messages exceeds the line capacity of JFrame, scroll bar scroll down.
		setVisible(true);
		
		output.addKeyListener(this);  // Adds the specified key listener to receive key events from this component.
	}
//		
	
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
		
		if(msg.equals("exit")) {
			System.exit(0);
		}
		
		if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
			msg += e.getKeyChar();
			displayer.sendMsg(msg);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(isT1) {
				if(enterPressed) {
					enterPressed = false;
					Thread1.interrupt();
					msg = "";
					isT1 = false;
				
			}
				else{
					enterPressed = true;
					Thread1 = new displayer();
					Thread1.start();
			}
			}
			else {
				if(enterPressed) {
					enterPressed = false;
					Thread2.interrupt();
					msg = "";
					isT1 = true;
				
			}
				else{
					enterPressed = true;
					Thread2 = new displayer();
					Thread2.start();
			}
			}
		}
	}

	@Override 
	public void keyReleased(KeyEvent e) {
		
	}
	
	public static void sendMsg(String msg) {
		output.append(msg);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        Inputter inp = new Inputter("A JFrame and KeyListener Demo");
	        inp.addWindowListener(
				new WindowAdapter() {
					public void windowClosing( WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}
