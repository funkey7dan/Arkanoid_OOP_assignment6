// XXXXXXXXX
package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.Game;

/**
 * A ball remover is in charge of removing balls from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor for the BallRemover.
     *
     * @param game           - the game we listen to and remove balls from.
     * @param remainingBalls - amount of remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}