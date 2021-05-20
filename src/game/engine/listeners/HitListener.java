package game.engine.listeners;

import game.engine.actors.Ball;
import game.engine.actors.Block;

public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the game.engine.actors.Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}