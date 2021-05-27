//XXXXXXXXX
package game.ui.gameinfo;

import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A class for the Lives Indicator which shows the player his score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * Constructor.
     *
     * @param gameLevel - the game the indicator belongs to.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        addToGame(gameLevel);
    }

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {


        int score = gameLevel.getCurrentScore().getValue()
                +
                gameLevel.getTotalScore().getValue();
        String s = String.valueOf(score);
        //d.drawText(355, 30, s, 16);
        d.setColor(Color.black);
        d.drawText(338, 31, "Score: " + s, 20);
        d.setColor(Color.white);
        d.drawText(340, 30, "Score: " + s, 20);
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
