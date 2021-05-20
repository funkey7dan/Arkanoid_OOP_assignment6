package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.Game;

// a game.engine.listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
    }
}