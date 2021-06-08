package game.ui.backgrounds;

import biuoop.DrawSurface;
import game.ui.shapes.DrawStar;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * The type Invader background.
 */
public class InvaderBackground extends Background {
    private DrawStar ds;

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public InvaderBackground(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     * @param color     - the color of the block
     */
    public InvaderBackground(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
        this.ds = new DrawStar();
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public InvaderBackground(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.setColor(Color.black);
        super.drawOn(d);
        d.setColor(Color.yellow.brighter());
        ds.drawOn(d);
    }

    @Override
    public void setColor(Color color1) {
        super.setColor(color1);
    }
}
