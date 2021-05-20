package game.engine.listeners;

import game.engine.actors.Ball;
import game.engine.actors.Block;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A game.engine.actors.Block was hit.");
    }
}