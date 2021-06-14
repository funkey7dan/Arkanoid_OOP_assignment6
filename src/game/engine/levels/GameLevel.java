//ID: ***REMOVED***

package game.engine.levels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.engine.accessories.Counter;
import game.engine.accessories.SoundPlayer;
import game.engine.accessories.bonuses.Bonus;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.actors.Frame;
import game.engine.actors.Paddle;
import game.engine.actors.Velocity;
import game.engine.actors.collidables.Collidable;
import game.engine.actors.collidables.GameEnvironment;
import game.engine.actors.sprites.Sprite;
import game.engine.actors.sprites.SpriteCollection;
import game.engine.animation.Animation;
import game.engine.animation.AnimationRunner;
import game.engine.animation.CountdownAnimation;
import game.engine.animation.GravityOnAnimation;
import game.engine.animation.KeyPressStoppableAnimation;
import game.engine.animation.PauseScreen;
import game.engine.levels.levelinfo.LevelInformation;
import game.engine.listeners.BallRemover;
import game.engine.listeners.BlockRemover;
import game.engine.listeners.GiftAdder;
import game.engine.listeners.ScoreTrackingListener;
import game.ui.indicators.LevelNameIndicator;
import game.ui.indicators.LivesIndicator;
import game.ui.indicators.ScoreIndicator;
import game.ui.shapes.Backdrop;
import game.ui.shapes.Point;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The type Game level.
 *
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com> A class for the object Game
 * Has attributes having a Sprite Collection, the relevant gui.
 * Holds the information about the size of the GUI, and the background image for the game. Supports adding all the
 * hittable objects to the game environment, and also the Sprites. Can initialize the game, and run it.
 */
public class GameLevel implements Animation, LevelInformation {
    private final Counter lives;
    // the collection of all our active elements of the game (aka sprites)
    private final SpriteCollection sprites;
    // the collection of all our collidibale elements of the game.
    private final GameEnvironment environment;
    private final GUI gui;
    private final AnimationRunner runner;
    private final long startTime;

    //LevelInformation
    private LevelInformation levelInformation;
    private int numberOfBalls = 0;
    private final List<Block> blocks = new ArrayList<>();
    private final List<Ball> balls = new ArrayList<>();
    private final List<Bonus> activeBonuses = new ArrayList<>();
    private Paddle p1;
    private String levelName = "GameLevel";
    private Sprite background;
    private int blockToRemove = 0;


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

    /**
     * The constant BALL_SPEED.
     */
// parameters for the balls
    public static final int BALL_SPEED = 7;
    private static final int BALL_ANGLE = 0;
    private static final int BALL_RADIUS = 5;
    private static final int BALL_START_X = PADDLE_START_X + (PADDLE_WIDTH / 2);
    private static final int BALL_START_Y = 550;


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
    private static int pressedPassed = 0;
    // per level score
    private final Counter currentScore = new Counter();
    // total game score
    private final Counter totalScore;


    //Flags
    private Boolean noDeathFlag = false;
    private final boolean winFlag = false;
    private final boolean loseFlag = false;
    private boolean running;
    private final KeyboardSensor keyboard;
    private boolean gameRunning;
    private final boolean bonusesFlag;
    private boolean gravityFlag;
    private boolean alreadyPressed = false;

    /**
     * Constructor based on level info.
     *
     * @param information     - the level information we will use.
     * @param keyboardSensor  the keyboard sensor used.
     * @param animationRunner the animation runner used.
     * @param totalScore      total score counter
     * @param gui             the gui we use
     * @param lives           the counter for the lives.
     */
    public GameLevel(LevelInformation information, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter totalScore, GUI gui, Counter lives) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.setLevelInformation(information);
        this.setBackground(getLevelInformation().getBackground());
        this.setLevelName(information.levelName());
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.totalScore = totalScore;
        this.gui = gui;
        this.lives = lives;
        this.gameRunning = true;
        this.bonusesFlag = true;
        this.gravityFlag = false;
        getCurrentScore().setValue(0);
        startTime = System.currentTimeMillis();


    }


    /**
     * Gets level name.
     *
     * @return the name of the level.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Gets p 1.
     *
     * @return the p 1
     */
    public Paddle getP1() {
        return p1;
    }

    /**
     * Gets runner.
     *
     * @return the runner
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
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


    /**
     * Create frame.
     */
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
        BallRemover ballRemover = new BallRemover(this, getCurrentBalls());
        if (!noDeathFlag) {
            bottom.addHitListener(ballRemover);
        }
    }

    /**
     * Spawn actors.
     */
    public void spawnActors() {

        sprites.removeSprite(p1);
        balls.clear();
        environment.removeCollidable(p1);
        p1 = new Paddle(new Block(new Point((GUI_WIDTH - getLevelInformation().paddleWidth()) / 2, PADDLE_START_Y),
                getLevelInformation().paddleWidth(), PADDLE_HEIGHT));
        p1.setSpeed(getLevelInformation().paddleSpeed());
        p1.addToGame(this);
        generateBalls(getLevelInformation().numberOfBalls(), getLevelInformation().initialBallVelocities());
        if (gravityFlag) {
            this.p1.setSpeed(7);
        }
    }

    /**
     * Generate balls.
     *
     * @param numberOfBalls1 the number of balls
     * @param ballVelocity   the ball velocity
     */
    public void generateBalls(int numberOfBalls1, List<Velocity> ballVelocity) {

        for (int i = 0; i < numberOfBalls1; i++) {
            Ball ball = new Ball(p1.getMiddle().getX(),
                    p1.getMiddle().getY() - BALL_RADIUS, BALL_RADIUS,
                    Color.WHITE);
            Velocity v = ballVelocity.get(i);
            if (gravityFlag && v.getSpeed() != 7) {

                v.setSpeed(7);
            }
            ball.setVelocity(v);
            ball.addToGame(this);
            getCurrentBalls().increase(1);

        }
    }

    /**
     * Generate blocks.
     */
    public void generateBlocks() {

        // create a Random generator
        Random rand1 = new Random();

        //Create listeners
        BlockRemover blockRemover = new BlockRemover(this, getCurrentBlocks());
        GiftAdder giftAdder = new GiftAdder(this, getCurrentBalls());

        ScoreTrackingListener scoreListener = new ScoreTrackingListener(getCurrentScore());
        for (Block block : getLevelInformation().blocks()) {

            if (rand1.nextInt(50) == 1 && levelInformation.blocks().size() > 10 && bonusesFlag) {
                block.setGiftIcon();
                block.addHitListener(giftAdder);
            }
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            blocks.add(block);
        }
        getCurrentBlocks().setValue(getLevelInformation().numberOfBlocksToRemove());
        //currentBlocks.setValue(2);

    }


    /**
     * Initialize.
     */
    public void initialize() {

        //create the blocks that comprise the frame of the game.
        runner.setFramesPerSecond(60);
        activeBonuses.clear();
        createFrame();
        getBackground().addToGame(this);

        //creates the blocks
        generateBlocks();

        // Listeners
        LivesIndicator livesIndicator = new LivesIndicator(this);
        livesIndicator.addToGame(this);
        LevelNameIndicator lvlName = new LevelNameIndicator(this);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this);
    }


    /**
     * Gets environment.
     *
     * @return returns the game environment
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * Gets gui.
     *
     * @return the gui which we use.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets gui height.
     *
     * @return the GUI height.
     */
    public static int getGuiHeight() {
        return GUI_HEIGHT;
    }

    /**
     * Gets gui width.
     *
     * @return the GUI width.
     */
    public static int getGuiWidth() {
        return GUI_WIDTH;
    }


    /**
     * Gets current blocks.
     *
     * @return number of current blocks.
     */
    public Counter getCurrentBlocks() {
        return currentBlocks;
    }

    /**
     * Gets current balls.
     *
     * @return number of current balls.
     */
    public Counter getCurrentBalls() {
        return currentBalls;
    }

    /**
     * Gets current score.
     *
     * @return current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Gets total score.
     *
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
    public void run() {

        this.runner.run(new CountdownAnimation(3, 3, sprites));

        // use our runner to run the current animation -- which is one turn of
        // the game.
        try {
            SoundPlayer.loopTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.runner.run(this);

    }

    /**
     * The method which is the execution of the animation itself.
     *
     * @param d - the draw surface we are using
     */


    @Override
    public void doOneFrame(DrawSurface d) {

        //System.out.println("DEBUG " + (System.currentTimeMillis() - this.startTime));
        if (!gravityFlag && ((System.currentTimeMillis() - this.startTime) >= 10000) && paddleSpeed() == 3) {
            gravityFlag = true;
            try {
                SoundPlayer.playSound(SoundPlayer.Effects.gravity.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.runner.run(new GravityOnAnimation(3, 3, sprites));
            this.p1.setSpeed(7);

            for (Ball ball : balls) {
                Velocity v = ball.getVelocity();
                v.setSpeed(7);
                ball.setVelocity(v);
            }
        }
        applyBonuses();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (getCurrentBalls().getValue() == 0) {
            this.activeBonuses.clear();
            this.running = false;
        }
        if (getCurrentBlocks().getValue() == 0) {
            this.running = false;
            this.gameRunning = false;
        }
        if (keyboard.isPressed("p") || keyboard.isPressed("P")) {
            try {
                SoundPlayer.playSound(SoundPlayer.Effects.paused.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
            SoundPlayer.pauseTheme();
            runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }
        // counts the cycles from the keyPress
        pressedPassed++;
        if (!keyboard.isPressed("m")) {
            alreadyPressed = false;
        }
        if (keyboard.isPressed("m") && !alreadyPressed) {
            alreadyPressed = true;
            SoundPlayer.muteOnOff();
        }


    }

    /**
     * Do cycle.
     */
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
     * Stops this animation.
     */
    @Override
    public void stopThis() {
        running = false;
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
     * Sets number of balls.
     *
     * @param balls1 the number of balls we set.
     */
    public void setNumberOfBalls(int balls1) {
        numberOfBalls = balls1;
        getCurrentBalls().setValue(balls1);
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
        return getLevelName();
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    /**
     * Sets background.
     */
    @Deprecated
    // Not needed anymore.
    public void setBackground() {
        new Backdrop(BACKGROUND_FILE).addToGame(this);
    }

    @Override
    public List<Block> blocks() {
        return blocks;
    }


    /**
     * Add ball.
     *
     * @param ball the ball
     */
    public void addBall(Ball ball) {
        balls.add(ball);
    }

    /**
     * Remove ball.
     *
     * @param ball the ball
     */
    public void removeBall(Ball ball) {
        balls.remove(ball);
    }

    @Override
    public int numberOfBlocksToRemove() {
        return getBlockToRemove();
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives.getValue();
    }

    /**
     * Gets level info.
     *
     * @return the level info
     */
    public LevelInformation getLevelInfo() {
        return this.getLevelInformation();
    }

    /**
     * Increment lives.
     */
    public void incrementLives() {
        lives.increase(1);
    }

    /**
     * Add bonus.
     *
     * @param bonus the bonus
     */
    public void addBonus(Bonus bonus) {
        activeBonuses.add(bonus);
    }

    /**
     * Remove bonus.
     *
     * @param bonus the bonus
     */
    public void removeBonus(Bonus bonus) {
        activeBonuses.remove(bonus);
    }

    /**
     * Apply bonuses.
     */
    public void applyBonuses() {
        for (Bonus b : new ArrayList<>(activeBonuses)) {
            if (b.getEndTime() <= System.currentTimeMillis()) {
                b.removeFromGame();
                continue;
            }
            b.applyBonus();
        }
    }

    /**
     * Gets level information.
     *
     * @return the level information
     */
    protected LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * Sets level information.
     *
     * @param levelInformation1 the level information
     */
    protected void setLevelInformation(LevelInformation levelInformation1) {
        this.levelInformation = levelInformation1;
    }

    /**
     * Sets level name.
     *
     * @param levelName1 the level name
     */
    protected void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }

    /**
     * Sets background.
     *
     * @param background1 the background
     */
    protected void setBackground(Sprite background1) {
        this.background = background1;
    }

    /**
     * Gets block to remove.
     *
     * @return the block to remove
     */
    protected int getBlockToRemove() {
        return blockToRemove;
    }

    /**
     * Sets block to remove.
     *
     * @param blockToRemove1 the block to remove
     */
    protected void setBlockToRemove(int blockToRemove1) {
        this.blockToRemove = blockToRemove1;
    }
}

