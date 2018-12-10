package game;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame ;
public class MyScreen extends JFrame {

	public MyScreen () {
		this.setSize (1200,900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String [] args) {
		MyScreen screen = new MyScreen ();
		MyCanvas canvas = new MyCanvas ();
		screen.getContentPane().add(canvas);
		if (0==0) {
			try {
			TimeUnit.SECONDS.sleep(92);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
	}
}