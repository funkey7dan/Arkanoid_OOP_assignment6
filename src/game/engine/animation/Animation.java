package game.engine.animation;

import biuoop.DrawSurface;

/**
 * Interface for running the animation loop.
 */
public interface Animation {

    /**
     * The method which is the execution of the animation itself.
     *
     * @param d - the draw surface we are using
     */
    void doOneFrame(DrawSurface d);

    /**
     * The stopping condition for the animation loop.
     *
     * @return - the condition.
     */
    boolean shouldStop();
}
