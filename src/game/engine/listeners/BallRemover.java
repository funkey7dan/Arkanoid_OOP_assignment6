// ID:***REMOVED***
package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.levels.GameLevel;

/**
 * A ball remover is in charge of removing balls from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Constructor for the BallRemover.
     *
     * @param gameLevel           - the game we listen to and remove balls from.
     * @param remainingBalls - amount of remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        //remainingBalls.decrease(1);
    }
}