import biuoop.DrawSurface;


/**
 * The interface for moving objects.
 * <p>
 * Supports drawing the object on a given surface, time-passed actions and adding to the game.
 */
public interface Sprite {


    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    void addToGame(Game g);
}

