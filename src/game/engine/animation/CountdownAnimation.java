//***REMOVED***
package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.engine.actors.sprites.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private final double numOfseconds;
    private final SpriteCollection gameScreen;
    private static int timeLeft;
    private final int countFrom;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfseconds = numOfSeconds;
        this.gameScreen = gameScreen;
        timeLeft = countFrom;
        this.countFrom = countFrom;
    }

    /**
     * Run a single frame of the animation.
     *
     * @param d - the draw surface we are using
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(250, d.getHeight() / 2, String.valueOf(timeLeft), 60);
        Sleeper sleeper = new Sleeper();
        if (timeLeft < countFrom) {
            sleeper.sleepFor(670);
        }
        timeLeft--;
    }

    /**
     * @return whether the animation should stop - if the time left is zero, we stop.
     */
    public boolean shouldStop() {
        return timeLeft < 0;
    }

    /**
     * Stops this animation.
     */
    public void stopThis() {
    }
}