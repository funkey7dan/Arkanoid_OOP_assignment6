//XXXXXXXXX
package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.engine.animation.AnimationRunner;
import game.engine.levels.*;
import game.engine.levels.levelinfo.DebugLevelInfo;
import game.engine.levels.levelinfo.DirectHitInfo;
import game.engine.levels.levelinfo.EldrichInfo;
import game.engine.levels.levelinfo.FinalFour;
import game.engine.levels.levelinfo.Green3Info;
import game.engine.levels.levelinfo.InvaderInfo;
import game.engine.levels.levelinfo.LevelInformation;
import game.engine.levels.levelinfo.WideEasyInfo;

import java.util.ArrayList;
import java.util.List;
//TODO check shotgun bonus

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
     * The main function for Ass3game, creates a game object,initializes it and runs.
     *
     * @param args - the arguments we get from the console.
     */
    public static void main(String[] args) {

        boolean noDeath = false;
        List<LevelInformation> Levels = new ArrayList();
        List<Integer> argInts = new ArrayList();
        List<LevelInformation> CustomLevels = new ArrayList();

        Levels.add((new DebugLevelInfo()));
        Levels.add((new DirectHitInfo()));
        Levels.add((new WideEasyInfo()));
        Levels.add((new Green3Info()));
        Levels.add((new FinalFour()));
        Levels.add(new InvaderInfo());
        Levels.add(new EldrichInfo());

        GUI gui = new GUI("Ass5: Playing with balls", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        AnimationRunner ar = new AnimationRunner(60, gui, sleeper);
        GameFlow game = new GameFlow(ar, ks, gui);
        if (args.length != 0) {
            for (String s : args) {
                try {
                    Integer intVal = Integer.parseInt(s) - 1;
                    if (intVal >= 0 && intVal < Levels.size()) {
                        argInts.add(intVal);
                    }

                } catch (NumberFormatException e) {
                    e.getMessage();
                }
            }
            if (argInts.isEmpty()) {
                game.runLevels(Levels);
            } else {
                for (Integer i : argInts) {
                    CustomLevels.add(Levels.get(i));
                }
                game.runLevels(CustomLevels);
            }

        } else {
            game.runLevels(Levels);
        }

    }
}
