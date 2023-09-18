package game.engine.accessories;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SoundPlayer {
    private static boolean isRunning = false;
    private static Clip loop;
    private static boolean isMuted = false;
    private static boolean isLooped = false;

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
        gamewin,
        paused,
        resumed,
        gravity,
        whispers1
    }

    private static long loopTime;
    private static ArrayList<String> soundPaths;

    public SoundPlayer() {
        soundPaths = new ArrayList<>();
        soundPaths.add("/game/engine/accessories/sounds/silence.wav");
        soundPaths.add("/game/engine/accessories/sounds/gamestart.wav");
        soundPaths.add("/game/engine/accessories/sounds/impact.wav");
        soundPaths.add("/game/engine/accessories/sounds/paddlehitmid.wav");
        soundPaths.add("/game/engine/accessories/sounds/paddlehitright.wav");
        soundPaths.add("/game/engine/accessories/sounds/paddlehitleft.wav");
        soundPaths.add("/game/engine/accessories/sounds/bonus.wav");
        soundPaths.add("/game/engine/accessories/sounds/lifeloss.wav");
        soundPaths.add("/game/engine/accessories/sounds/ballkill.wav");
        soundPaths.add("/game/engine/accessories/sounds/gameover.wav");
        soundPaths.add("/game/engine/accessories/sounds/gamewin.wav");
        soundPaths.add("/game/engine/accessories/sounds/paused.wav");
        soundPaths.add("/game/engine/accessories/sounds/resumed.wav");
        soundPaths.add("/game/engine/accessories/sounds/gravity.wav");
        soundPaths.add("/game/engine/accessories/sounds/whisper2.wav");
    }

    public static void playSound(int fileIndex) {
        if (isMuted) {
            return;
        }
        try {
            String soundPath = soundPaths.get(fileIndex);
            // Load the sound file from the classpath
            InputStream inputStream = SoundPlayer.class.getResourceAsStream(soundPath);
            if (inputStream == null) {
                throw new IOException("Sound file not found: " + soundPath);
            }

            // Create a buffered input stream that supports mark/reset
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            LineListener listener = event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            };
            clip.addLineListener(listener);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }


    public static void loopTheme() {
        if (loop != null || isMuted) {
            return;
        }
        try {
            String soundPath = "/game/engine/accessories/sounds/theme.wav";

            InputStream inputStream = SoundPlayer.class.getResourceAsStream(soundPath);
            if (inputStream == null) {
                throw new IOException("Sound file not found: " + soundPath);
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);
            loop = AudioSystem.getClip(null);
            loop.open(audioIn);
            FloatControl gainControl = (FloatControl) loop.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            if (!isRunning) {
                isRunning = true;
                loop.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    /**
     * Stops the loop of the theme song.
     */
    public static void stopTheme() {
        if (loop == null) {
            return;
        }
        // holy music stops
        loop.stop();
        loop = null;
    }

    /**
     * Pauses the loop of the theme song, while saving the position.
     */
    public static void pauseTheme() {
        if (loop == null) {
            return;
        }
        loopTime = loop.getMicrosecondPosition();
        loop.stop();
    }

    /**
     * Resumes the loop of the theme song from the position.
     */
    public static void resumeTheme() {
        if (loop == null) {
            return;
        }
        loop.setMicrosecondPosition(loopTime);
        loop.loop(loop.LOOP_CONTINUOUSLY);
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
            System.out.println("mute toggled off.");
            isMuted = false;
            //unmuteTheme();
            resumeTheme();
        } else {
            System.out.println("mute toggled on.");
            isMuted = true;
            //muteTheme();
            pauseTheme();
        }

    }

}


