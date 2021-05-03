import javax.swing.SwingUtilities;

import controllers.Game;
import controllers.SelectedSettler;
import views.GameFrame;

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
