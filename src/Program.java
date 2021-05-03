import controllers.Game;
import controllers.SelectedSettler;
import views.GameFrame;

public class Program {

	public static void main(String[] args) {
		GameFrame gameFrame = new GameFrame();
		Game.getInstance().setGui(gameFrame);
		SelectedSettler.getInstance().setGui(gameFrame);
		gameFrame.setVisible(true);
	}
}
