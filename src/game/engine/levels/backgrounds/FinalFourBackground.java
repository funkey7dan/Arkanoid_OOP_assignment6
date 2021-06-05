package game.engine.levels.backgrounds;

import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * The type Final four background.
 */
public class FinalFourBackground extends Background {
    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public FinalFourBackground(Point upperLeft, double width, double height) {
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
    public FinalFourBackground(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public FinalFourBackground(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.setColor(new Color(0, 50, 100));
        super.drawOn(d);
        d.setColor(Color.white);
        d.setColor(new Color(100, 204, 250));

        //rain1
        for (int i = 1; i < 10; i++) {
            d.drawLine(130 + i * 7, 435, 110 + i * 8, 600);
        }

        // rain2
        for (int i = 1; i < 15; i++) {
            d.drawLine(135 + 480 + i * 6, 435 - 150, 110 + 550 + i * 8, 600);
        }
        d.setColor(new Color(0, 50, 100));
        for (int i = 1; i < 800; i += 5) {
            d.drawLine(0, 435 - 150 + i, 800, 435 - 150 + i);
            d.drawLine(0, 435 - 150 + i + 1, 800, 435 - 150 + i + 1);
        }

        // cloud1
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(130, 435, 23);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(135, 450, 21);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(150, 430, 25);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(180, 430, 30);
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(170, 450, 20);

        // cloud2
        d.setColor(new Color(170, 170, 170));
        d.fillCircle(130 + 500, 435 - 150, 23);
        d.setColor(new Color(170, 170, 170));

        d.fillCircle(135 + 500, 450 - 150, 21);
        d.setColor(new Color(187, 187, 187));
        d.fillCircle(150 + 500, 430 - 150, 25);
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(180 + 500, 430 - 150, 30);

        d.setColor(new Color(204, 204, 204));
        d.fillCircle(170 + 500, 450 - 150, 20);
    }
}
