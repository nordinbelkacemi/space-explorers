import javax.swing.SwingUtilities;

import controller.Game;
import controller.SelectedSettler;
import view.GameFrame;

public class Program {

	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	new GameFrame();         
	        }
	    });
	}
}
