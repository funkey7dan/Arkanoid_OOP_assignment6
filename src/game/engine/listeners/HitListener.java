//XXXXXXXXX
package game.engine.listeners;

import game.engine.actors.Ball;
import game.engine.actors.Block;

/**
 * Interface for classes that listen to occurences of objects being hit.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the object that's being hit.
     * @param hitter   - the hitting object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}