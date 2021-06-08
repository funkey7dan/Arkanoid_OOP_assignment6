//XXXXXXXXX
package game.engine.accessories;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Sound player.
 */
public class SoundPlayer {
    // flag to check if the loop is already running
    private static boolean isRunning = false;
    // the theme music loop
    private static Clip loop;
    private static boolean isMuted = false;

    /**
     * The enum Effects.
     * Each effect corresponds in name and index to the list.
     */
    public enum Effects {
        silence,
        gamestart,
        impact,
        paddlehitmid,
        paddlehitright,
        paddlehitleft,
        bonus,
        lifeloss,
        ballkill,
        gameover,
        gamewin
    }

    /**
     * Play a sound effects.
     *
     * @param fileIndex the index of the file in the list of the sound effects.
     *                  passed via the file name, according to the enum "Effects".
     */
    public static void playSound(int fileIndex) {
        if (isMuted) {
            return;
        }
        try {
            // Open an audio input stream.
            ArrayList<File> sounds = new ArrayList<>();
            sounds.add(new File("src/game/engine/accessories/sounds/silence.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/gamestart.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/impact.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/paddlehitmid.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/paddlehitright.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/paddlehitleft.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/bonus.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/lifeloss.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/ballkill.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/gameover.wav"));
            sounds.add(new File("src/game/engine/accessories/sounds/gamewin.wav"));
            File soundFile = sounds.get(fileIndex);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the theme music in a loop.
     */
    public static void loopTheme() {


        if (loop != null || isMuted) {
            return;
        }
        try {
            File soundFile = new File("src/game/engine/accessories/sounds/theme.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            // Get a sound clip resource.
            loop = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            loop.open(audioIn);
            FloatControl gainControl = (FloatControl) loop.getControl(FloatControl.Type.MASTER_GAIN);
            // reduce the volume of the clip
            gainControl.setValue(-10.0f);
            if (!isRunning) {
                isRunning = true;
                loop.loop(loop.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the loop of the theme song.
     */
    public static void stopTheme() {
        // holy music stops
        loop.stop();
        loop = null;
    }

    /**
     * Mutes the theme music.
     */
    public static void muteTheme() {
        if (loop == null) {
            return;
        }
        FloatControl gainControl = (FloatControl) loop.getControl(FloatControl.Type.MASTER_GAIN);
        // reduce the volume of the clip
        gainControl.setValue(-80.0f);
    }

    /**
     * Mutes the theme music.
     */
    public static void unmuteTheme() {
        if (loop == null) {
            return;
        }
        FloatControl gainControl = (FloatControl) loop.getControl(FloatControl.Type.MASTER_GAIN);
        // increase the volume of the clip
        gainControl.setValue(-10.0f);
    }

    /**
     * Toggle mute on and off.
     */
    public static void muteOnOff() {
        if (isMuted) {
            isMuted = false;
            unmuteTheme();
        } else {
            isMuted = true;
            muteTheme();
        }

    }

}


