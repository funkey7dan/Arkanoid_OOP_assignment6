package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.engine.accessories.Counter;

import java.awt.Color;

public class LoseScreen implements Animation {
    Counter totalScore;
    private boolean stop;

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
        d.drawText(280, 165 + 30, "Your score is:" + String.valueOf(totalScore.getValue()), 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
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
