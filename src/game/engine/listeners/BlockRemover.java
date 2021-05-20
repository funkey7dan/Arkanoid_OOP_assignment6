//XXXXXXXXX
package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.Game;


/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game          - the game we listen to and remove blocks from.
     * @param removedBlocks - the blocks we will remove.
     */
    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}