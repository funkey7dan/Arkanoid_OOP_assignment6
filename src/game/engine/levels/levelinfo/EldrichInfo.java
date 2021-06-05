package game.engine.levels.levelinfo;

import game.engine.accessories.CreateEldritchBlocks;
import game.engine.actors.Block;
import game.engine.actors.Velocity;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.backgrounds.EldrichBackground;
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
 * The type Eldrich info.
 */
public class EldrichInfo implements LevelInformation {
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
     * Instantiates a new Eldrich info.
     */
    public EldrichInfo() {
        this.numberOfBalls = 1;
        for (int i = 0; i < numberOfBalls; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(180, 7));
        }
        initialBallVelocities.add(Velocity.fromAngleAndSpeed(180, 7));
        this.paddleSpeed = 7;
        this.paddleWidth = 65;
        this.levelName = "Nightmare";
        this.background = new EldrichBackground(new Point(-1, -1),
                GUI_WIDTH + 5, GUI_HEIGHT + 5, Color.black);
        blocks = CreateEldritchBlocks.create();
        numberOfBlocksToRemove = blocks.size();
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

    /**
     * Generate blocks.
     */
    public void generateBlocks() {

        int y = 0;

        try {


            //List<String> allLines = Files.readAllLines(Paths.get("src/game/engine/accessories/spiral.txt"));
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream is = classLoader.getResourceAsStream("spiral.txt");
            List<String> allLines = new BufferedReader(
                    new InputStreamReader(Objects.requireNonNull(is))).lines().collect(Collectors.toList());


            for (String line : allLines) {
                int x = 0;
                int r = 255;

                for (char c : line.toCharArray()) {
                    Color color = new Color(r, 255, 20);
                    if (r > 0) {
                        r -= 3;
                    }
                    if (c == '1') {
                        Block block = new Block(new Point(x, y), BLOCK_WIDTH, BLOCK_HEIGHT);
                        block.setColor(color);
                        blocks.add(block);
//                        System.out.println("blocks.add
//                        (new Block(new Point(" + x + "," + y + "), " + BLOCK_WIDTH + "," +
//                                " " +
//                                "" + BLOCK_HEIGHT + ");");
                        //blocks.add(new Block(new Point(255, 45), 15, 15));
                    }
                    x += BLOCK_WIDTH;
                }
                y += BLOCK_HEIGHT;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
