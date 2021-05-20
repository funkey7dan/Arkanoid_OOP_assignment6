//ID: ***REMOVED***

package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.engine.accessories.Counter;
import game.engine.actors.Frame;
import game.engine.actors.Paddle;
import game.engine.actors.Velocity;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.actors.KillBlock;
import game.engine.actors.AddBallBlock;
import game.engine.actors.collidables.Collidable;
import game.engine.actors.collidables.GameEnvironment;
import game.engine.actors.sprites.Sprite;
import game.engine.actors.sprites.SpriteCollection;
import game.engine.listeners.BallAdder;
import game.engine.listeners.BallRemover;
import game.engine.listeners.BlockRemover;
import game.engine.listeners.ScoreTrackingListener;
import game.ui.gameinfo.LivesIndicator;
import game.ui.gameinfo.ScoreIndicator;
import game.ui.shapes.Point;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.util.Random;


/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object Game.Engine.Animation.Game.
 * Has attributes of the having a Game.Engine.Actors.Sprites.Sprite Collection, the relevant gui.
 * Hold the information about the size of the GUI, and the background image for the game.
 * Supports adding all the hittable objects to the game environment, and also the Sprites.
 * Can initialize the game, and run it.
 */
public class Game {
    // the collection of all our active elements of the game (aka sprites)
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    // GUI window dimensions
    private static final int GUI_HEIGHT = 600;
    private static final int GUI_WIDTH = 800;
    private static Image image;

    // the dimensions of the paddle
    private static final int PADDLE_HEIGHT = 25;
    private static final int PADDLE_WIDTH = 100;

    // the starting point of the paddle in the middle of the bottom part.
    private static final int PADDLE_START_X = ((GUI_WIDTH / 2) - (PADDLE_WIDTH / 2));
    private static final int PADDLE_START_Y = (GUI_HEIGHT - PADDLE_HEIGHT);

    // parameters for the ball
    public static final int BALL_SPEED = 7;
    private static final int BALL_ANGLE = 10;
    private static final int BALL_RADIUS = 3;
    private static final int BALL_START_X = PADDLE_START_X + (PADDLE_WIDTH / 2);
    private static final int BALL_START_Y = 450;


    // block dimensions
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_START_X = 150;
    private static final int BLOCK_START_Y = 100;

    // amount of blocks
    private static final int BLOCK_ROWS = 12;
    private static final int BLOCK_COLUMNS = 5;

    // counters
    private final Counter currentBlocks = new Counter();
    private final Counter currentBalls = new Counter();
    private final Counter currentScore = new Counter();

    //Flags
    private Boolean noDeathFlag = false;


    /**
     * Adds a hittable object to the game environment.
     *
     * @param c - the object we add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }


    /**
     * Removes a hittable object from the game environment.
     *
     * @param c - the object we remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Adds a movable object to the game environment.
     *
     * @param s - the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Adds a movable object to the game environment.
     *
     * @param s - the sprite we want to add.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and game.engine.actors.Ball (and game.engine.actors.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        gui = new GUI("Ass5: Playing with balls", GUI_WIDTH, GUI_HEIGHT);
        sleeper = new biuoop.Sleeper();
        environment = new GameEnvironment();
        sprites = new SpriteCollection();
        setBackground();

        //create the blocks that comprise the frame of the game.
        Frame leftWall = new Frame(new Point(0, 0), BALL_RADIUS, GUI_HEIGHT + 2 * BALL_SPEED);
        Frame rightWall = new Frame(new Point(GUI_WIDTH - BALL_RADIUS, 0),
                BALL_RADIUS, GUI_HEIGHT + 2 * BALL_SPEED);
        Block bottom = new Block(new Point(0, GUI_HEIGHT - BALL_RADIUS),
                GUI_WIDTH + 2 * BALL_SPEED, BALL_RADIUS);
        Frame top = new Frame(new Point(0, 0), GUI_WIDTH + 2 * BALL_SPEED, BALL_RADIUS);
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        top.addToGame(this);
        bottom.addToGame(this);


        // create the paddle in the middle of the screen.
        Paddle p1 = new Paddle(new Block(new Point(PADDLE_START_X, PADDLE_START_Y), PADDLE_WIDTH, PADDLE_HEIGHT));
        Ball ball1 = new Ball(BALL_START_X, BALL_START_Y, BALL_RADIUS, Color.WHITE);
        Ball ball2 = new Ball(BALL_START_X, BALL_START_Y, BALL_RADIUS, Color.WHITE);
        Velocity v1 = Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED);
        Velocity v2 = Velocity.fromAngleAndSpeed(-BALL_ANGLE, BALL_SPEED);
        ball1.setVelocity(v1);
        ball2.setVelocity(v2);
        p1.addToGame(this);
        ball1.addToGame(this);
        ball2.addToGame(this);
        currentBalls.increase(2);


        // Listeners
        LivesIndicator livesIndicator = new LivesIndicator(this);
        livesIndicator.addToGame(this);
        BlockRemover blockRemover = new BlockRemover(this, currentBlocks);
        BallRemover ballRemover = new BallRemover(this, currentBalls);
        BallAdder ballAdder = new BallAdder(this, currentBalls);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(currentScore);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this);
        if (!noDeathFlag) {
            bottom.addHitListener(ballRemover);
        }

        //Randomizer
        // create a Random generator
        Random rand1 = new Random();


        //game.engine.actors.Block creating loop
        for (int i = BLOCK_COLUMNS; i >= 0; i--) {
            for (int j = i; j <= BLOCK_ROWS; j++) {
                int randI = rand1.nextInt(101);
                int randJ = rand1.nextInt(101);
                Block block;
                if (randI == i) {
                    block = new KillBlock(new Point(BLOCK_START_X + j * BLOCK_WIDTH,
                            BLOCK_START_Y + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT);
                    block.addHitListener(ballRemover);
                } else if (randJ == j) {
                    block = new AddBallBlock(new Point(BLOCK_START_X + j * BLOCK_WIDTH,
                            BLOCK_START_Y + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT);
                    block.addHitListener(ballAdder);
                } else {
                    block = new Block(new Point(BLOCK_START_X + j * BLOCK_WIDTH,
                            BLOCK_START_Y + BLOCK_HEIGHT * i), BLOCK_WIDTH, BLOCK_HEIGHT);
                }
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
                        block.setColor(Color.GREEN);
                        break;
                    default:
                        block.setColor(Color.WHITE);
                        break;
                }
                currentBlocks.increase(1);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreListener);
            }
        }

    }

    /**
     * @return returns the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * @return the gui which we use.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * @return the GUI height.
     */
    public static int getGuiHeight() {
        return GUI_HEIGHT;
    }

    /**
     * @return the GUI width.
     */
    public static int getGuiWidth() {
        return GUI_WIDTH;
    }

    /**
     * Sets the background image field.
     */
    public void setBackground() {
        try {
            image = ImageIO.read(new File("textures/Background.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return number of current blocks.
     */
    public Counter getCurrentBlocks() {
        return currentBlocks;
    }

    /**
     * @return number of current balls.
     */
    public Counter getCurrentBalls() {
        return currentBalls;
    }

    /**
     * @return current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Turns no death mode on. (The balls don't disappear)
     */
    public void setNoDeathFlag() {
        noDeathFlag = true;
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        boolean winFlag = false;
        boolean loseFlag = false;
        while (true) {

            if (currentBlocks.getValue() == 0) {
                winFlag = true;
                currentScore.increase(100);
            }

            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.drawImage(0, 0, image);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            if (winFlag) {
                gui.getDialogManager().showInformationDialog("Game over message", "Congratulations,You won!");
                gui.close();
                return;
            }
            if (loseFlag) {
                gui.getDialogManager().showInformationDialog("Game over message", "Game over!");
                gui.close();
                return;
            }

            if (currentBalls.getValue() == 0) {
                loseFlag = true;
            }


            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

