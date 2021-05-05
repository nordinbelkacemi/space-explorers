package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends GamePanel {

	private JTextArea text;
    private JScrollPane log;

    public LogPanel() {
    	super(new Dimension(300,200));
    	text = new JTextArea();
    	text.setEditable(false);
    	text.setLineWrap(true);
    	text.setBackground(getBackground());
    	text.setForeground(getForeground());
    	log = new JScrollPane(text);
    	add(log);
		setVisible(true);
    }
    
    private void updateSize() {
    	log.setLocation(0, 40);
    	log.setSize(getWidth(), getHeight()-40);
    	text.setMinimumSize(log.getSize());
    	text.setCaretPosition(text.getDocument().getLength());
    }
    
    public void log(String s) {
    	text.append(s);
    }

    public void update() {
        repaint();
    }
    
    @Override
    public void paint(Graphics g) {
    	updateSize();
    	super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("LOG", 10, 30);
    }
}
