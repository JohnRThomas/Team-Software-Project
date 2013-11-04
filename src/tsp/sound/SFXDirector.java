package tsp.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import tsp.gui.MainWindow;

public class SFXDirector {
	
	private MainWindow mainWindow; // Possibly irrelevant
	
	public SFXDirector(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void playSound(String path){
		try{
			File currentFile = new File(path);
			AudioInputStream audio = AudioSystem.getAudioInputStream(currentFile);
			Clip sound = AudioSystem.getClip();
			sound.open(audio);
			sound.start();
		}catch(Exception ex){
			System.out.println("Error with playing sound at: " + path);
			ex.printStackTrace();
		}
	}
}
