package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private final boolean stop;

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        //d.setColor(Color.RED);
        //d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);

        d.setColor(Color.black);
        d.drawText(128, 155, "Game Paused", 85);
        d.drawText(233, 203, "press space to continue", 32);
        d.fillRectangle(345,252,35,150);
        d.fillRectangle(405,252,35,150);
        d.setColor(Color.white);
        d.drawText(130, 150, "Game Paused", 85);
        d.drawText(235, 200, "press space to continue", 32);
        d.fillRectangle(348,250,35,150);
        d.fillRectangle(408,250,35,150);

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
