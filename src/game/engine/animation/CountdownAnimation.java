package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.engine.actors.sprites.SpriteCollection;

import java.awt.Color;

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfseconds;
    private final SpriteCollection gameScreen;
    private static int timeLeft;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfseconds = numOfSeconds;
        this.gameScreen = gameScreen;
        timeLeft = countFrom;
    }

    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(250, d.getHeight() / 2, String.valueOf(timeLeft), 60);
        Sleeper sleeper = new Sleeper();
        if (timeLeft < 3) {
            sleeper.sleepFor((long) numOfseconds * 300);
        }
        timeLeft--;
    }

    public boolean shouldStop() {
        if (timeLeft >= 0) {
            return false;
        }
        return true;
    }
}