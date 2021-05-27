package game.engine.levels.levelinfo;

import game.engine.actors.Block;
import game.engine.actors.Velocity;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.backgrounds.DirectHitBackground;
import game.ui.shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DebugLevelInfo implements LevelInformation {
    private static final int GUI_HEIGHT = 600;
    private static final int GUI_WIDTH = 800;
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities = new ArrayList<>();
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks = new ArrayList<>();
    private int numberOfBlocksToRemove;


    public DebugLevelInfo() {
        this.numberOfBalls = 1;
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(180+i, 7));
        }
        this.paddleSpeed = 7;
        this.paddleWidth = 100;
        //this.levelName = "?̵̢̞̭̙̥̩̫͉̑̓?̩̭̘̱̘̝́̈̉͊́͠?̶̖̗̜̱͔̙̰ͮ́̿͟ͅ?̧͍̺̰̞̖̦̮̼͇ͫ̋̂?̢͇̠̘͇͍̲ͤ͌ͮ̅̎̂ͅͅ";
        this.levelName = "DEBUG";
        this.background = new DirectHitBackground(new Point(-1, -1),
                GUI_WIDTH + 5, GUI_HEIGHT + 5, Color.black);
        Block block = new Block(new Point(385, 150), 30, 30);
        block.setColor(Color.RED);
        blocks.add(block);
        numberOfBlocksToRemove = 2;
    }


    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return paddleWidth;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return numberOfBlocksToRemove;
    }
}
