package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.engine.accessories.Counter;
import game.engine.animation.AnimationRunner;
import game.engine.animation.KeyPressStoppableAnimation;
import game.engine.animation.LoseScreen;
import game.engine.animation.WinScreen;
import game.engine.levels.GameLevel;
import game.engine.levels.levelinfo.LevelInformation;

import java.util.List;

public class GameFlow {
    private final GUI gui;
    private AnimationRunner ar;
    private KeyboardSensor ks;
    private boolean noDeath;
    private Counter lives = new Counter();
    protected final Counter totalScore = new Counter();

    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        lives.setValue(7);
    }

    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.totalScore, this.gui, this.lives);
            level.initialize();
            do {
                level.doCycle();
                if (level.getCurrentBlocks().getValue() > 0) {
                    lives.decrease(1);
                }
                if (lives.getValue() <= 0) {
                    totalScore.increase(level.getCurrentScore().getValue());
                    ar.run(new KeyPressStoppableAnimation(ks, "space", new LoseScreen(totalScore)));
                    gui.close();
                    return;
                }
                if (level.getCurrentBlocks().getValue() == 0) {
                    totalScore.increase(100 + level.getCurrentScore().getValue());
                }
            } while (!level.gameShouldStop());
        }
        ar.run(new KeyPressStoppableAnimation(ks, "space", new WinScreen(totalScore)));
        gui.close();
        return;
    }

}
