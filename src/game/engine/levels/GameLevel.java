//XXXXXXXXX

package game.engine.levels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.engine.accessories.Bonus;
import game.engine.accessories.Counter;
import game.engine.actors.Frame;
import game.engine.actors.GiftBlock;
import game.engine.actors.Paddle;
import game.engine.actors.Velocity;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.actors.collidables.Collidable;
import game.engine.actors.collidables.GameEnvironment;
import game.engine.actors.sprites.Sprite;
import game.engine.actors.sprites.SpriteCollection;
import game.engine.animation.*;
import game.engine.levels.levelinfo.LevelInformation;
import game.engine.listeners.BallRemover;
import game.engine.listeners.BlockRemover;
import game.engine.listeners.GiftAdder;
import game.engine.listeners.ScoreTrackingListener;
import game.ui.gameinfo.LevelNameIndicator;
import game.ui.gameinfo.LivesIndicator;
import game.ui.gameinfo.ScoreIndicator;
import game.ui.shapes.Backdrop;
import game.ui.shapes.Point;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
public class GameLevel implements Animation, LevelInformation {
    private final Counter lives;
    // the collection of all our active elements of the game (aka sprites)
    private SpriteCollection sprites;
    // the collection of all our collidibale elements of the game.
    private GameEnvironment environment;
    private GUI gui;
    private AnimationRunner runner;

    //LevelInformation
    protected LevelInformation levelInformation;
    private int numberOfBalls = 0;
    private List<Block> blocks = new ArrayList<>();
    private List<Ball> balls = new ArrayList<>();
    private List<Bonus> activeBonuses = new ArrayList<>();
    private Paddle p1;
    protected String levelName = "GameLevel";
    protected Sprite background;
    protected int blockToRemove = 0;


    // GUI window dimensions
    private static final int GUI_HEIGHT = 600;
    private static final int GUI_WIDTH = 800;
    private static final File BACKGROUND_FILE = new File("game/ui/resources/textures/Background.jpg");

    // the dimensions of the paddle
    private static final int PADDLE_HEIGHT = 25;
    private static final int PADDLE_WIDTH = 100;

    // the starting point of the paddle in the middle of the bottom part.
    private static final int PADDLE_START_X = ((GUI_WIDTH / 2) - (PADDLE_WIDTH / 2));
    private static final int PADDLE_START_Y = (GUI_HEIGHT - PADDLE_HEIGHT);

    // parameters for the balls
    public static final int BALL_SPEED = 7;
    private static final int BALL_ANGLE = 0;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_START_X = PADDLE_START_X + (PADDLE_WIDTH / 2);
    private static final int BALL_START_Y = 500;


    // block dimensions
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    private static final int BLOCK_START_X = 150;
    private static final int BLOCK_START_Y = 100;

    // amount of blocks
    private static final int BLOCK_ROWS = 12;
    private static final int BLOCK_COLUMNS = 5;


    // counters
    protected final Counter currentBlocks = new Counter();
    protected final Counter currentBalls = new Counter();
    // per level score
    protected final Counter currentScore = new Counter();
    // total game score
    protected final Counter totalScore;


    //Flags
    private Boolean noDeathFlag = false;
    private boolean winFlag = false;
    private boolean loseFlag = false;
    private boolean running;
    private KeyboardSensor keyboard;
    private boolean gameRunning;
    private boolean bonusesFlag;

    /**
     * Constructor based on level information.
     */
    public GameLevel(LevelInformation information, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter totalScore, GUI gui, Counter lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.levelInformation = information;
        this.background = levelInformation.getBackground();
        this.levelName = information.levelName();
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.totalScore = totalScore;
        this.gui = gui;
        this.lives = lives;
        this.gameRunning = true;
        this.bonusesFlag = true;
        currentScore.setValue(0);

    }

//    /**
//     * Default Constructor.
//     */
//    public GameLevel() {
//        this.environment = new GameEnvironment();
//        this.sprites = new SpriteCollection();
//    }

    /**
     * @return the name of the level.
     */
    public String getLevelName() {
        return levelName;
    }

    public Paddle getP1() {
        return p1;
    }

    public AnimationRunner getRunner() {
        return runner;
    }

    public List<Ball> getBalls() {
        return balls;
    }

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


    public void createFrame() {
        Frame leftWall = new Frame(new Point(0, 0), BALL_RADIUS, GUI_HEIGHT + 2 * BALL_SPEED);
        Frame rightWall = new Frame(new Point(GUI_WIDTH - BALL_RADIUS, 0),
                BALL_RADIUS, GUI_HEIGHT + 2 * BALL_RADIUS);
        Block bottom = new Block(new Point(0, GUI_HEIGHT),
                GUI_WIDTH + 2 * BALL_SPEED, 1);
        Frame top = new Frame(new Point(0, 0), GUI_WIDTH, 1);
        //Block top = new Block(new Point(0,0),GUI_WIDTH,1);
        leftWall.addToGame(this);
        rightWall.addToGame(this);
        top.addToGame(this);
        bottom.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, currentBalls);
        if (!noDeathFlag) {
            bottom.addHitListener(ballRemover);
        }
    }

    public void spawnActors() {

//        Ball ball1 = new Ball(BALL_START_X, BALL_START_Y, BALL_RADIUS, Color.WHITE);
//        Ball ball2 = new Ball(BALL_START_X, BALL_START_Y, BALL_RADIUS, Color.WHITE);
//        Velocity v1 = Velocity.fromAngleAndSpeed(BALL_ANGLE, BALL_SPEED);
//        Velocity v2 = Velocity.fromAngleAndSpeed(-BALL_ANGLE, BALL_SPEED);
//        initial.add(v1);
//        initial.add(v2);
//        ball1.setVelocity(v1);
//        ball2.setVelocity(v2);
//        ball1.addToGame(this);
//        ball2.addToGame(this);
//        currentBalls.increase(2);
        sprites.removeSprite(p1);
        balls.clear();
        environment.removeCollidable(p1);
        p1 = new Paddle(new Block(new Point((GUI_WIDTH - levelInformation.paddleWidth()) / 2, PADDLE_START_Y),
                levelInformation.paddleWidth(), PADDLE_HEIGHT));
        p1.setSpeed(levelInformation.paddleSpeed());
        p1.addToGame(this);
        generateBalls(levelInformation.numberOfBalls(), levelInformation.initialBallVelocities());
    }

    public void generateBalls(int numberOfBalls, List<Velocity> ballVelocity) {
        //BallRemover ballRemover = new BallRemover(this, currentBalls);
        //BallAdder ballAdder = new BallAdder(this, currentBalls);
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(BALL_START_X,
                    BALL_START_Y, BALL_RADIUS,
                    Color.WHITE);
            Velocity v = ballVelocity.get(i);
            ball.setVelocity(v);
            ball.addToGame(this);
            currentBalls.increase(1);

        }
    }

    public void generateBlocks() {
        //Randomizer
        // create a Random generator
        Random rand1 = new Random();

        //Create listeners
        BlockRemover blockRemover = new BlockRemover(this, currentBlocks);
        GiftAdder giftAdder = new GiftAdder(this, currentBalls);

        ScoreTrackingListener scoreListener = new ScoreTrackingListener(currentScore);
        for (Block block : levelInformation.blocks()) {
            //TODO check before
            // submit
            //if (bonusesFlag)
            if (rand1.nextInt(30) == 1 && levelInformation.blocks().size() > 10 && bonusesFlag) {
                block.setGiftIcon();
                block.addHitListener(giftAdder);
            }
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            blocks.add(block);
        }
        currentBlocks.setValue(levelInformation.numberOfBlocksToRemove());
        //currentBlocks.setValue(2);

    }


    public void initialize() {
        //gui = new GUI("Ass5: Playing with balls", GUI_WIDTH, GUI_HEIGHT);
        //sleeper = new biuoop.Sleeper();
        //runner = new AnimationRunner(60, gui, sleeper);
        //keyboard = gui.getKeyboardSensor();


        //create the blocks that comprise the frame of the game.
        createFrame();
        background.addToGame(this);
        //setBackground();

        //create the paddle and the balls.
        //spawnActors();


        //creates the blocks
        generateBlocks();


        // Listeners
        LivesIndicator livesIndicator = new LivesIndicator(this);
        livesIndicator.addToGame(this);

        LevelNameIndicator lvlName = new LevelNameIndicator(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this);


    }

    /**
     * Resets the block counters.
     */
    public void resetLevel() {
        loseFlag = false;
        //currentScore.setValue(totalScore.getValue());
        for (Block b : blocks) {
            b.removeALLHitListener();
        }
        blocks.clear();
        currentBlocks.setValue(0);
        currentBalls.setValue(0);
        sprites.clear();
        environment.clear();
        balls.clear();
        //this.initialize();
    }

    /**
     * Resets the block counters.
     */
    public void restartLevel() {
        loseFlag = false;
        currentScore.setValue(totalScore.getValue());
//        for (Block b : blocks) {
//            b.removeALLHitListener();
//        }
//        blocks.clear();
//        currentBlocks.setValue(0);
        currentBalls.setValue(0);
//        sprites.clear();
//        environment.clear();
        balls.clear();
        //this.initialize();
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
     * @return total score.
     */
    public Counter getTotalScore() {
        return totalScore;
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
//    public void run() {
//        int framesPerSecond = 60;
//        int millisecondsPerFrame = 1000 / framesPerSecond;
//
//        while (true) {
//
//            if (currentBlocks.getValue() == 0) {
//                winFlag = true;
//                currentScore.increase(100);
//            }
//
//            long startTime = System.currentTimeMillis(); // timing
//            DrawSurface d = gui.getDrawSurface();
//
//            this.sprites.drawAllOn(d);
//            gui.show(d);
//            this.sprites.notifyAllTimePassed();
//            if (winFlag) {
//                gui.getDialogManager().showInformationDialog("Game over message", "Congratulations,You won!");
//                gui.close();
//                return;
//            }
//            if (loseFlag) {
//                gui.getDialogManager().showInformationDialog("Game over message", "Game over!");
//                gui.close();
//                return;
//            }
//
//            if (currentBalls.getValue() == 0) {
//                loseFlag = true;
//            }
//
//
//            // timing
//            long usedTime = System.currentTimeMillis() - startTime;
//            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
//            if (milliSecondLeftToSleep > 0) {
//                sleeper.sleepFor(milliSecondLeftToSleep);
//            }
//        }
//    }
    public void run() {

        //this.running = true;
        this.runner.run(new CountdownAnimation(3, 3, sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * The method which is the execution of the animation itself.
     *
     * @param d - the draw surface we are using
     */


    @Override
    public void doOneFrame(DrawSurface d) {

        applyBonuses();
        this.sprites.drawAllOn(d);
        //gui.show(d);
        this.sprites.notifyAllTimePassed();
        if (currentBalls.getValue() == 0) {
            //totalScore.increase(currentScore.getValue());
            this.running = false;
        }
        if (currentBlocks.getValue() == 0) {
            //totalScore.increase(currentScore.getValue());
            this.running = false;
            this.gameRunning = false;
        }
        // if we won
//        if (winFlag && loop > 0) {
//            totalScore.setValue(currentScore.getValue());
//            running = false;
//            gameRunning = false;
//            return;
////            if (!gui.getDialogManager().showYesNoDialog("Game over message", "You won! Next level?")) {
////                System.exit(1);
////            }
////            this.running = false;
////            //gui.close();
////            totalScore.increase(currentScore.getValue());
////            return;
////            // if we lost
//        } else if (loseFlag && loop > 0) {
////            if (!gui.getDialogManager().showYesNoDialog("Game over message", "You lost! Try again?")) {
////                System.exit(1);
////            }
////            resetLevel();
////            this.initialize();
////            this.run();
//            lives.decrease(1);
//            if (lives.getValue() == 0) {
//                gameRunning = false;
//                running = false;
//                return;
//            }
//            restartLevel();
//            running = false;
//            return;
//        } else if (winFlag || loseFlag) {
//            loop++;
//        }
//
//        if (currentBlocks.getValue() <= 0) {
//            System.out.println("DEBUG: blocks:" + currentBlocks.getValue());
//            winFlag = true;
//        }
//

        if (keyboard.isPressed("p")) {
            runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }

    }

    public void doCycle() {
        spawnActors();
        running = true;
        run();
    }

    /**
     * checks if the animation should stop.
     *
     * @return - the condition.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * checks if the animation should stop.
     *
     * @return - the condition.
     */
    public boolean gameShouldStop() {
        return !this.gameRunning;
    }

    @Override
    public int numberOfBalls() {
        return numberOfBalls;
    }

    /**
     * @param balls the number of balls we set.
     */
    public void setNumberOfBalls(int balls) {
        numberOfBalls = balls;
        currentBalls.setValue(balls);
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return p1.getSPEED();
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    public void setBackground() {
        new Backdrop(BACKGROUND_FILE).addToGame(this);
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }


    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockToRemove;
    }

    public int getLives() {
        return lives.getValue();
    }

    public void addBonus(Bonus bonus) {
        activeBonuses.add(bonus);
    }

    public void removeBonus(Bonus bonus) {
        activeBonuses.remove(bonus);
    }

    public void applyBonuses() {
        for (Bonus b : new ArrayList<>(activeBonuses)) {
            if (b.getEndTime() <= System.currentTimeMillis()) {
                b.removeFromGame();
                continue;
            }
            b.applyBonus();
        }
    }
}

