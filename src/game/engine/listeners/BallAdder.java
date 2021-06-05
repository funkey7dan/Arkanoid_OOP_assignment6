package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.levels.GameLevel;

/**
 * A ball adder is in charge of balls from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallAdder implements HitListener {
    private final Counter remainingBalls;

    /**
     * Constructor for the ball adder class.
     *
     * @param gameLevel           - tge game to which we add the ball.
     * @param remainingBalls - the number of remaining balls in the game.
     */
    public BallAdder(GameLevel gameLevel, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.spawnBall();
        remainingBalls.increase(1);
    }
}