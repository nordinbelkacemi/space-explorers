package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import controllers.Game;
import model.settler.Settler;
import view.actionbuttons.ActionButton;


public class TeamPanel extends UpdatablePanel {

	private List<Settler> selectableSettlers;
    private JButton nextTurnButton;
	private List<BufferedImage> settlerImages = new ArrayList<>();
	private ArrayList<String> settlerImagePaths = new ArrayList<>(Arrays.asList(
		"res/redsmall.png",
		"res/bluesmall.png",
		"res/greensmall.png",
		"res/yellowsmall.png",
		"res/purplesmall.png",
		"res/orangesmall.png",
		"res/graysmall.png"
	));

    public TeamPanel() {
		loadImages(settlerImages, settlerImagePaths);

    	addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int x = (int) e.getPoint().getX(), y = (int) e.getPoint().getY();
				int startX = (getSize().width - 575) / 2;
				if (y < 125 && y > 50 && x < startX + 575 && x > startX) {
					x -= startX;
					x /= 96;
					Settler settler = selectableSettlers.get(x);
					if (x < selectableSettlers.size() && settler != null)
						Game.getInstance().selectSettler(settler.getId());
				}
			}
		});
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,150));
		nextTurnButton = new ActionButton("next turn");
        nextTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Game.getInstance().endTurn();
            }
		});

		add(nextTurnButton);
		
		setVisible(true);
		update();
    }

    public void update() {
    	selectableSettlers = Game.getInstance().getSelectableSettlers();
        repaint();
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	nextTurnButton.setLocation(getSize().width - 150, 10);
    	int startX = (getSize().width - 575)/2;
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLERS", 10, 30);
    	for (int i = 0; i < selectableSettlers.size(); i++) {
    		if(selectableSettlers.get(i) != null) {
	    		int imgIndex = selectableSettlers.get(i).getId();
	    		g.drawImage(settlerImages.get(imgIndex-1),startX +i*100,50,null);
	    	}
    		else
    			g.drawImage(settlerImages.get(6),startX +i*100,50,null);
    	}
	}
}
