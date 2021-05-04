package view;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class UpdatablePanel extends JPanel {

    /**
     * Egy adott panelen levo kepeket tarolo listajaba betolti a paths listaban
     * tarolt eleresi utak altal meghatarozott kepeket
     * 
     * @param images A panel kepeket tarolo listaja
     * @param paths A betoltendo kepek eleresi utjai
     */
    public void loadImages(List<BufferedImage> images, List<String> paths) {
        for (String path : paths) {
            try {
                InputStream in = new FileInputStream(path);
                images.add(ImageIO.read(in));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void update();
}
