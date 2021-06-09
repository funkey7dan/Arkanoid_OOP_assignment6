package game.engine.animation;

import biuoop.DrawSurface;
import game.engine.accessories.Counter;

import java.awt.Color;

/**
 * The type Lose screen.
 */
public class LoseScreen implements Animation {
    /**
     * The Total score.
     */
    private final Counter totalScore;
    private boolean stop;

    /**
     * Instantiates a new Lose screen.
     *
     * @param totalScore the total score
     */
    public LoseScreen(Counter totalScore) {
        this.totalScore = totalScore;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(0, 0, 170));
        d.drawText(230, 150, "Game Over", 65);
        d.drawText(280, 165 + 30, "Your score is:" + (totalScore.getValue()), 32);
    }

    /**
     * The stopping condition for the animation loop.
     *
     * @return - the condition.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Stops this animation.
     */
    public void stopThis() {
        this.stop = true;
    }

}
