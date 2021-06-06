package game.ui.indicators;

import biuoop.DrawSurface;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;

import java.awt.Color;

/**
 * The type Level name indicator.
 */
public class LevelNameIndicator implements Sprite {

    /**
     * The Game level.
     */
    private final GameLevel gameLevel;

    /**
     * Instantiates a new Level name indicator.
     *
     * @param g the g
     */
    public LevelNameIndicator(GameLevel g) {
        this.gameLevel = g;
        addToGame(g);
    }

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        String s = gameLevel.getLevelName();
        d.setColor(Color.black);
        d.drawText(690 - s.length() * 12, 31, "Level name: " + s, 20);
        d.setColor(Color.white);
        d.drawText(692 - s.length() * 12, 30, "Level name: " + s, 20);

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
