package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * A class for the object that's responsible for running the animation loop,
 * and projecting it to the user.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * @param framesPerSecond - our target FPS.
     * @param gui             - the gui we will run the animation on.
     * @param sleeper         - sleeper agent.
     */
    public AnimationRunner(int framesPerSecond, GUI gui, Sleeper sleeper) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = sleeper;
    }

    /**
     * Runs an animation.
     *
     * @param animation the animation we run.
     */
    public void run(Animation animation) {

        while (!animation.shouldStop()) {
            int millisecondsPerFrame = 1000 / framesPerSecond;
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            // timings
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }

    public void setFramesPerSecond(int fps) {
        this.framesPerSecond = fps;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

}
