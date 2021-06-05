//XXXXXXXXX
package game.ui.indicators;

import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A class for the Lives Indicator which shows the player the number of balls he has left on the screen.
 */
public class LivesIndicator implements Sprite {
    private final GameLevel gameLevel;

    /**
     * Constructor.
     *
     * @param gameLevel - the game the lives are in.
     */
    public LivesIndicator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {


        int lives = gameLevel.getLives();
        String s = String.valueOf(lives);
        //d.drawText(55, 30, s, 16);
        d.setColor(Color.black);
        d.drawText(8, 31, "Lives: " + s, 20);
        d.setColor(Color.white);
        d.drawText(10, 30, "Lives: " + s, 20);

    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
