//ID: ***REMOVED***

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.GameFlow;
import game.engine.accessories.SoundPlayer;
import game.engine.animation.AnimationRunner;
import game.engine.levels.levelinfo.DirectHitInfo;
import game.engine.levels.levelinfo.EldrichInfo;
import game.engine.levels.levelinfo.FinalFour;
import game.engine.levels.levelinfo.Green3Info;
import game.engine.levels.levelinfo.InvaderInfo;
import game.engine.levels.levelinfo.LevelInformation;
import game.engine.levels.levelinfo.WideEasyInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Our main driver class for the game.
 *
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Ball.
 * Has attributes of center, radius, color , velocity and the limits of the frame it's located in.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Ass6Game {

    /**
     * The main function for our game, creates a game object,initializes it and runs.
     * We add levels to the levels list and run from it.
     * We can pass level number (1-6) to run the levels in a custom order.
     *
     * @param args - the arguments we get from the console.
     */
    public static void main(String[] args) {

        List<LevelInformation> levels = new ArrayList<>();
        List<Integer> argInts = new ArrayList<>();
        List<LevelInformation> customLevels = new ArrayList<>();

        //levels.add((new DebugLevelInfo())); //TODO remove before submit
        levels.add((new DirectHitInfo()));
        levels.add((new WideEasyInfo()));
        levels.add((new Green3Info()));
        levels.add((new FinalFour()));
        levels.add(new EldrichInfo());
        levels.add(new InvaderInfo());

        GUI gui = new GUI("Ass6: Playing with balls", 800, 600); //TODO change this name maybe?
        KeyboardSensor ks = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        AnimationRunner ar = new AnimationRunner(60, gui, sleeper);
        GameFlow game = new GameFlow(ar, ks, gui);

        if (args.length != 0) {
            for (String s : args) {
                for (String string : args) {
                    if (string.contains("noDie")) {
                        game.setNoDeath(true);
                    }
                    if (string.contains("mute")) {
                        SoundPlayer.muteOnOff();
                    }
                }
                SoundPlayer.playSound(SoundPlayer.Effects.silence.ordinal());
                SoundPlayer.playSound(SoundPlayer.Effects.gamestart.ordinal());
                try {
                    Integer intVal = Integer.parseInt(s) - 1;
                    if (intVal >= 0 && intVal < levels.size()) {
                        argInts.add(intVal);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (argInts.isEmpty()) {
                game.runLevels(levels);
            } else {
                for (Integer i : argInts) {
                    customLevels.add(levels.get(i));
                }
                game.runLevels(customLevels);
            }

        } else {
            game.runLevels(levels);
        }

    }
}
