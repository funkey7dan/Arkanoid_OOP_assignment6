//XXXXXXXXX
package game.engine.listeners;

import game.engine.accessories.Counter;
import game.engine.accessories.SoundPlayer;
import game.engine.actors.Ball;
import game.engine.actors.Block;
import game.engine.levels.GameLevel;


/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel       - the game we listen to and remove blocks from.
     * @param remainingBlocks - the blocks we will remove.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeALLHitListener();
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
        SoundPlayer.playSound(SoundPlayer.Effects.impact.ordinal());

    }
}