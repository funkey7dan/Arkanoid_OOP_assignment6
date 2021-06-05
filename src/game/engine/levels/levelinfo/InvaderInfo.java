package game.engine.levels.levelinfo;

import game.engine.accessories.CreateInvaderBlocks;
import game.engine.actors.Block;
import game.engine.actors.Velocity;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.backgrounds.InvaderBackground;
import game.ui.shapes.Point;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type Invader info.
 */
public class InvaderInfo implements LevelInformation {
    private static final int GUI_HEIGHT = 600;
    private static final int GUI_WIDTH = 800;
    private final int numberOfBalls;
    private final List<Velocity> initialBallVelocities = new ArrayList<>();
    private final int paddleSpeed;
    private final int paddleWidth;
    private final String levelName;
    private final Sprite background;
    private final List<Block> blocks;
    private final int numberOfBlocksToRemove;
    private static final int BLOCK_WIDTH = 15;
    private static final int BLOCK_HEIGHT = 15;


    /**
     * Instantiates a new Invader info.
     */
    public InvaderInfo() {
        this.numberOfBalls = 2;
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(180 + 120 * (Math.pow(-1, i)), 3));
        }
        this.paddleSpeed = 3;
        this.paddleWidth = 100;
        this.levelName = "Invaders!";
        this.background = new InvaderBackground(new Point(-1, -1),
                GUI_WIDTH + 5, GUI_HEIGHT + 5, Color.black);
        // create blocks from text file
        //generateBlocks();
        blocks = CreateInvaderBlocks.create();
        for (Block b : blocks) {
            b.setColor(Color.GREEN);
        }
        numberOfBlocksToRemove = blocks.size();
    }

    /**
     * Generate blocks.
     */
    public void generateBlocks() {

        int y = 0;

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("spaceinvader.txt");
            List<String> allLines = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(is))).lines().collect(Collectors.toList());
            //List<String> allLines = Files.readAllLines(Paths.get("src/game/engine/accessories/eldritch.txt"));


            for (String line : allLines) {
                int x = 105;
                int width = 0;
                int height = 0;
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        Block block = new Block(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT);
                        block.setColor(Color.GREEN);
                        blocks.add(block);
//                        System.out.println("Block block =
//                        new Block(new Point(" + x + "," + y + "), " + BLOCK_WIDTH + ", " +
//                                "" + BLOCK_HEIGHT + ");");
                    }
                    x += BLOCK_WIDTH;
                }
                y += BLOCK_HEIGHT;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
