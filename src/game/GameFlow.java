//***REMOVED***
package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.engine.accessories.Counter;
import game.engine.animation.AnimationRunner;
import game.engine.animation.KeyPressStoppableAnimation;
import game.engine.animation.LoseScreen;
import game.engine.animation.WinScreen;
import game.engine.levels.GameLevel;
import game.engine.levels.levelinfo.LevelInformation;

import java.util.List;

/**
 * The type Game flow.
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 * The score is kept across levels, throughout the entire game.
 * It's created and kept at the GameFlow,and only passed to the game level as parameter so it can update it.
 */
public class GameFlow {
    private final GUI gui;
    private final AnimationRunner ar;
    private final KeyboardSensor ks;
    private boolean noDeath = false;
    private final Counter lives = new Counter();
    private final Counter totalScore = new Counter();

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        lives.setValue(7);
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.getTotalScore(), this.gui, this.lives);
            if (noDeath) {
                level.setNoDeathFlag();
            }
            level.initialize();
            do {
                level.doCycle();
                if (level.getCurrentBlocks().getValue() > 0) {
                    lives.decrease(1);
                }
                if (lives.getValue() <= 0) {
                    getTotalScore().increase(level.getCurrentScore().getValue());
                    ar.run(new KeyPressStoppableAnimation(ks, "space", new LoseScreen(getTotalScore())));
                    gui.close();
                    return;
                }
                if (level.getCurrentBlocks().getValue() == 0) {
                    getTotalScore().increase(100 + level.getCurrentScore().getValue());
                }
            } while (!level.gameShouldStop());
        }
        ar.run(new KeyPressStoppableAnimation(ks, "space", new WinScreen(getTotalScore())));
        gui.close();
    }

    /**
     * @return the Total score.
     */
    protected Counter getTotalScore() {
        return totalScore;
    }

    /**
     * Enables "God mode" where the balls don't disappear after hitting the bottom.
     * Used for debug purposes.
     *
     * @param noDeath1 - the parameter we set to the flag.
     */
    public void setNoDeath(boolean noDeath1) {
        this.noDeath = noDeath1;
    }
}
