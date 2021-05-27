package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.engine.accessories.Counter;
import game.ui.shapes.Point;

import java.awt.Color;

public class WinScreen implements Animation {
    Counter totalScore;
    private boolean stop;

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
        d.drawText(285, 165 + 30, "Your score is:" + String.valueOf(totalScore.getValue()), 32);
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
