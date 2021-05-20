//XXXXXXXXX
package game.engine.listeners;

/**
 * Interface for classes that notifies object they are hit.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the hit listener we add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the hit listener we remove.
     */
    void removeHitListener(HitListener hl);
}