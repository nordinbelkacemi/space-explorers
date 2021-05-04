package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controllers.Game;
import model.playfield.AsteroidField;

public class FieldPanel extends GamePanel {
    private List<JLabel> asteroids;
    private List<JLabel> teleportGates;
    private AsteroidField field;
    private String index;

    public FieldPanel() {
    	super(new Dimension(250,300));
		setVisible(true);
		update();
    }

    public void update() {
        field = Game.getInstance().getSelectedField();
        if (field != null) {
            index = String.valueOf(Game.getInstance().getSolarSystem().getBelt().indexOf(field));
        }
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("FIELD", 10, 30);
    	if(field != null)
    		g.drawString(index, 10, 60);
	}
}
