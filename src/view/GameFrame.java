package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.Game;
import controllers.SelectedSettler;
import model.settler.Settler;

public class GameFrame extends JFrame implements SpaceExplorersGui {
    
    private TeamPanel teamPanel;
    private SettlerPanel settlerPanel;
    private FieldPanel fieldPanel;
    private AsteroidPanel asteroidPanel;
    private LogPanel logPanel;
    private SpacePanel spacePanel;
    
    public GameFrame() {
    	Game.getInstance().setGui(this);
		SelectedSettler.getInstance().setGui(this);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setTitle("SPACE EXPLORERS");
    	setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	setMinimumSize(new Dimension(1200,800));
    	
    	// full frame layout
    	BorderLayout BL = new BorderLayout();
		setLayout(BL);
		
		//settlerpanel (westpanel)
		settlerPanel = new SettlerPanel();
		add(settlerPanel,BorderLayout.LINE_START);
		
		// centerpanel
		JPanel centerPanel = new JPanel();
		BorderLayout BL2 = new BorderLayout();
		centerPanel.setLayout(BL2);
		teamPanel = new TeamPanel();
		centerPanel.add(teamPanel,BorderLayout.PAGE_START);
		spacePanel = new SpacePanel();
		centerPanel.add(spacePanel,BorderLayout.CENTER);
		logPanel = new LogPanel();
		centerPanel.add(logPanel,BorderLayout.PAGE_END);
		centerPanel.setVisible(true);
		add(centerPanel,BorderLayout.CENTER);
		
		// eastpanel layout
		JPanel eastPanel = new JPanel();
		BoxLayout box = new BoxLayout(eastPanel,BoxLayout.Y_AXIS);
		eastPanel.setLayout(box);
		eastPanel.setBackground(Color.black);
		fieldPanel = new FieldPanel();
		eastPanel.add(fieldPanel);
		asteroidPanel = new AsteroidPanel();
		eastPanel.add(asteroidPanel);
		eastPanel.setVisible(true);
		add(eastPanel,BorderLayout.LINE_END);
	
		pack();
		setVisible(true);
	}

	public void settlerSelected(){
		settlerPanel.update();
		spacePanel.update();
    }

    public void fieldSelected(){
        fieldPanel.update();
        asteroidPanel.update();
    }

    public void asteroidSelected(){
        /* TODO GameFrame.AsteroidSelected */
    }

    public void settlerPerformedAction(){
        /* TODO GameFrame.SettlerPerformedAction */
    }

    public void log(){
        /* TODO GameFrame.log */
    }

	@Override
	public void turnEnded() {
		settlerPanel.update();
		teamPanel.update();
		spacePanel.update();
	}
}
