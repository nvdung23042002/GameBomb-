package Game;

import java.io.File;
import java.util.Date;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Sound {
    //public String fileName;
    public String backgroundMusic = "F:\\Code Java\\TestGame\\res\\audio\\background_music.wav";
    public String deadMusic = "F:\\Code Java\\TestGame\\res\\audio\\dead.wav";
    public String explosionMusic = "F:\\Code Java\\TestGame\\res\\audio\\explosion.wav";
    public String nextLevelMusic = "F:\\Code Java\\TestGame\\res\\audio\\next_level.wav";
    public String placeBoomMusic = "F:\\Code Java\\TestGame\\res\\audio\\place_bomb.wav";
    
    
    public Clip clip;
    public Sound(String fileName) {
        
        try {
            // Open an audio input stream.
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    public void stop(){
        clip.stop();
    }
    
    
}
