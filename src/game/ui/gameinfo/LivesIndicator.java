//XXXXXXXXX
package game.ui.gameinfo;

import game.engine.actors.sprites.Sprite;
import game.Game;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A class for the Lives Indicator which shows the player the number of balls he has left on the screen.
 */
public class LivesIndicator implements Sprite {
    private final Game game;

    /**
     * Constructor.
     *
     * @param game - the game the lives are in.
     */
    public LivesIndicator(Game game) {
        this.game = game;
    }

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);

        int lives = game.getCurrentBalls().getValue();
        String s = String.valueOf(lives);
        //d.drawText(55, 30, s, 16);
        d.drawText(10, 30, "Lives: " + s, 16);

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
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
