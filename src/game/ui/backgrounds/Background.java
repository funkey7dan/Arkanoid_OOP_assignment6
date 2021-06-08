//XXXXXXXXX
package game.ui.backgrounds;

import biuoop.DrawSurface;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * The class for the object Background.
 * It's the sprite that represents the background in a game level.
 * Serves as template for the other backgrounds.
 */
public class Background implements Sprite {

    private final Rectangle rect;
    private Color color = Color.black;

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public Background(Point upperLeft, double width, double height) {
        this.rect = new Rectangle(upperLeft, width, height);
    }

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     * @param color     - the color of the block
     */
    public Background(Point upperLeft, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public Background(Rectangle rec) {
        this.rect = rec;
    }


    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(15, 625, "Press p to pause, press m to mute.", 10);
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
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

    /**
     * Sets the color field.
     *
     * @param color1 - the color we set
     */
    public void setColor(Color color1) {
        this.color = color1;
    }
}
