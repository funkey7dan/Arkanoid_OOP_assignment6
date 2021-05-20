//ID: ***REMOVED***
package game.engine.actors;

import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

/**
 * /**
 * * @author Daniel Bronfman
 * * A class for the object AddBallBlock - a special block that spawns a new ball upon being hit.
 */
public class AddBallBlock extends Block {

    /**
     * Constructor for AddBallBlock.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public AddBallBlock(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public AddBallBlock(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.setGiftIcon();
        super.drawOn(surface);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        return super.hit(hitter, collisionPoint, currentVelocity);
    }
}
