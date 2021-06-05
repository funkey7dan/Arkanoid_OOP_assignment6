package game.engine.animation;

import biuoop.DrawSurface;
import game.engine.accessories.Counter;

import java.awt.Color;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {
    /**
     * The Total score.
     */
    private final Counter totalScore;
    private final boolean stop;

    /**
     * Instantiates a new Win screen.
     *
     * @param totalScore the total score
     */
    public WinScreen(Counter totalScore) {
        this.totalScore = totalScore;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(255, 215, 0));
        d.drawText(260, 150, "You won!", 65);
        d.drawText(285, 165 + 30, "Your score is:" + totalScore.getValue(), 32);
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

}
