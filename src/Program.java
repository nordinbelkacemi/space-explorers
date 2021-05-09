import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.SwingUtilities;

import view.GameFrame;

public class Program {

	public static void main(String[] args) {
		playSound();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GameFrame();
			}
		});
	}

	public static synchronized void playSound() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res/moonmen.wav"));
					clip.open(inputStream);
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float range = gainControl.getMaximum() - gainControl.getMinimum();
					float gain = (range * 0.5f) + gainControl.getMinimum();
					gainControl.setValue(gain);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
