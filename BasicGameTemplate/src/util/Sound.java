package util;

//Finn O'Neill 17367986

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    private Clip clip;

    public Sound(){}

    public Sound(String inputFile) {
        try {
            File file = new File(inputFile);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
                            
            }  catch (IOException | UnsupportedAudioFileException | LineUnavailableException except) {
                ((Throwable) except).printStackTrace();
            } 
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
           
    }
  
}
