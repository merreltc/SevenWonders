package guiMain;

import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {

	private Clip sound;
	
	public void playMusic(String path) {
		File file = new File(path);
		try {
			this.sound = AudioSystem.getClip();
			AudioInputStream input = AudioSystem.getAudioInputStream(file);
			sound.open(input);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		sound.loop(sound.LOOP_CONTINUOUSLY);
	}

	public void endMusic(){
		sound.close();
		sound.flush();
	}
	
}
