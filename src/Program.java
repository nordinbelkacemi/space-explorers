import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.SwingUtilities;

import controller.Game;
import controller.SelectedSettler;
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
		System.out.println("Fing1");
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				System.out.println("Fing2");
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("res/moonmen.wav"));
					System.out.println("Fing3");
					clip.open(inputStream);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					System.out.println("Fing1");
					e.printStackTrace();
				}
			}
		}).start();
	}
}
