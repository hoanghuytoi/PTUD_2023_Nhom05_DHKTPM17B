package UI;

import java.awt.EventQueue;

public class Application {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                Login_GUI frame = new Login_GUI();
	                frame.setVisible(true);
	                frame.setResizable(false);
	                frame.setLocationRelativeTo(null);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}
