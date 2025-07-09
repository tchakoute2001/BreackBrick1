package tchakoute;

import javax.sound.sampled.*;
import java.net.URL;

public class Sounds {

    private Clip backgroundClip;

    // Suona musica di sfondo in loop
    public void playBackgroundMusic(String fileName) {
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            if (url == null) {
                System.err.println("File audio non trovato: " + fileName);
                return;
            }

            AudioInputStream stream = AudioSystem.getAudioInputStream(url);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(stream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Suona effetto audio singolo (es. collisione)
    public void playSoundEffect(String fileName) {
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            if (url == null) {
                System.err.println("File audio non trovato: " + fileName);
                return;
            }

            AudioInputStream stream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
