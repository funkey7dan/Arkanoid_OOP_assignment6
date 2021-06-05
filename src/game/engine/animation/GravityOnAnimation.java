package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.engine.actors.sprites.SpriteCollection;

import java.awt.Color;

/**
 * The type Gravity on animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class GravityOnAnimation implements Animation {
    private final double numOfseconds;
    private final SpriteCollection gameScreen;
    private static int timeLeft;

    /**
     * Instantiates a new Gravity on animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public GravityOnAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfseconds = numOfSeconds;
        this.gameScreen = gameScreen;
        timeLeft = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW);
        d.fillRectangle(206, 138, 385, 245);
        d.setColor(Color.black);
        d.drawText(246, 200, "ARTIFICIAL\n", 60);
        d.drawText(246, 260,
                "GRAVITY\n", 60);
        d.drawText(346, 320, "ON!", 60);
        Sleeper sleeper = new Sleeper();
        if (timeLeft < 3) {
            sleeper.sleepFor((long) numOfseconds * 300);
        }
        timeLeft--;
    }

    @Override
    public boolean shouldStop() {
        return timeLeft < 0;
    }
}