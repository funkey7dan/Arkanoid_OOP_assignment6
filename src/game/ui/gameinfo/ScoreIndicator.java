package game.ui.gameinfo;

import game.engine.actors.sprites.Sprite;
import game.Game;
import biuoop.DrawSurface;

import java.awt.Color;

public class ScoreIndicator implements Sprite {
    private Game game;

    public ScoreIndicator(Game game) {
        this.game = game;
        addToGame(game);
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

        int score = game.getCurrentScore().getValue();
        String s = String.valueOf(score);
        //d.drawText(355, 30, s, 16);
        d.drawText(700, 30, "Score: " +s, 16);
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
