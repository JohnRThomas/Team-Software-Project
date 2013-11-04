package tsp.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import tsp.gui.MainWindow;

public class MusicDirector {

	private Clip currentMusic;
	private File currentFile;
	private MainWindow mainWindow;
	
	public MusicDirector(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	public void playMusic(String path){
		if (null != currentMusic){
			currentMusic.stop();
		}
		try{
			currentFile = new File(path);
			AudioInputStream audio = AudioSystem.getAudioInputStream(currentFile);
			currentMusic = AudioSystem.getClip();
			currentMusic.open(audio);
			currentMusic.start();
			currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception ex){
			System.out.println("Error with playing sound at: " + path);
			ex.printStackTrace();
		}
	}
}
