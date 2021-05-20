//ID: ***REMOVED***
package game.engine.actors;

import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

/**
 * /**
 * * @author Daniel Bronfman
 * * A class for the object KillBlock - a special block that removed a ball upon being hit.
 */
public class KillBlock extends Block {
    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public KillBlock(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public KillBlock(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.setSkullIcon();
        super.drawOn(surface);

    }
}
