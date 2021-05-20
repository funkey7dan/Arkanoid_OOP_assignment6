package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.Game;

// a ball remover is in charge of removing balls from the game, as well as keeping count
// of the number of blocks that remain.
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}