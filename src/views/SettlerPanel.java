package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import controllers.SelectedSettler;
import models.settler.Settler;

public class SettlerPanel extends UpdatablePanel {
    private List<JLabel> inventory;
    private List<JButton> actionButtons;
    private Settler settler;
    private List<BufferedImage> settlerImages = new ArrayList<>();

    SettlerPanel() {
    	try {
			InputStream in = new FileInputStream("res/redbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/bluebig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/greenbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/yellowbig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/purplebig.png");
			settlerImages.add(ImageIO.read(in));

			in = new FileInputStream("res/orangebig.png");
			settlerImages.add(ImageIO.read(in));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	setBorder(BorderFactory.createLineBorder(Color.white));
		setBackground(Color.black);
		setForeground(Color.white);
		setPreferredSize(new Dimension(300,600));
		setVisible(true);

		update();
    }

    public void update() {
		settler = SelectedSettler.getInstance().getSelectedSettler();
        repaint();
    }
    
    public void paint(Graphics g) {
		super.paint(g);
    	g.setFont(new Font(getFont().getFontName(), Font.BOLD, 30));
    	g.drawString("SETTLER", 10, 30);
		if (settler != null) {
			int id = settler.getId();
			g.drawImage(settlerImages.get(id - 1), 100, 50, null);
		}
	}
}
