package game.engine.actors.sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object Game.Engine.Actors.Sprites.SpriteCollection.
 * Hold a list of all sprites in our environment.
 * Supports adding new ones,drawing all of them and notifying all of them of the inevitable passage of time.
 */
public class SpriteCollection {

    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * @param s the sprite we are adding.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * @param s the sprite we are removing.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.spriteList);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * call timePassed() on all sprites.
     *
     * @param d - the surface we draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}
