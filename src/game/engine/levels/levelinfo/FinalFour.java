package game.engine.levels.levelinfo;

import game.engine.actors.Block;
import game.engine.actors.Velocity;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.backgrounds.FinalFourBackground;
import game.engine.levels.backgrounds.Green3Background;
import game.ui.shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class FinalFour implements LevelInformation {
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
    private static final int BLOCK_ROWS = 15;
    private static final int BLOCK_COLUMNS = 7;
    // block dimensions
    private static final int BLOCK_WIDTH = 53;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_START_X = 150;
    private static final int BLOCK_START_Y = 100;


    public FinalFour() {
        this.numberOfBalls = 3;
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(180, 7));
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(180 - 230 * (Math.pow(-1, i)), 7));
        }
        this.paddleSpeed = 7;
        this.paddleWidth = 100;
        this.levelName = "Final Four";
        this.background = new FinalFourBackground(new Point(-1, -1),
                GUI_WIDTH + 5, GUI_HEIGHT + 5, Color.black);
        for (int i = 0; i < BLOCK_COLUMNS; i++) {
            for (int j = 0; j < BLOCK_ROWS; j++) {
                Block block;
                block = new Block(new Point(3 + j * BLOCK_WIDTH,
                        50 + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT);
                switch (i) {
                    case 0:
                        block.setColor(Color.ORANGE);
                        break;
                    case 1:
                        block.setColor(Color.DARK_GRAY);
                        break;
                    case 2:
                        block.setColor(new Color(250, 100, 100));
                        break;
                    case 3:
                        block.setColor(Color.CYAN);
                        break;
                    case 4:
                        block.setColor(Color.MAGENTA);
                        break;
                    case 5:
                        block.setColor(Color.WHITE);
                        break;
                    default:
                        block.setColor(Color.GREEN);
                        break;
                }
                blocks.add(block);
                numberOfBlocksToRemove = blocks.size();
            }
        }
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
