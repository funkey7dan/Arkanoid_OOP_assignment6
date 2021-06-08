package game.ui.backgrounds;

import biuoop.DrawSurface;
import game.engine.levels.GameLevel;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * The type Direct hit background.
 */
public class DirectHitBackground extends Background {

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public DirectHitBackground(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * Constructor for block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     * @param color     - the color of the block
     */
    public DirectHitBackground(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public DirectHitBackground(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.setColor(Color.black);
        super.drawOn(d);
        d.setColor(Color.BLUE);
        d.drawLine(260, 165, 540, 165);
        d.drawLine(400, 300, 400, 20);
        d.drawCircle(400, 165, 120);
        d.drawCircle(400, 165, 80);
        d.drawCircle(400, 165, 50);
    }

    @Override
    public void setColor(Color color1) {
        super.setColor(color1);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
    }
}
