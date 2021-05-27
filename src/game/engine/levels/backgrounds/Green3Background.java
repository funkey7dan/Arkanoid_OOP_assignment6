package game.engine.levels.backgrounds;

import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

public class Green3Background extends Background {
    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public Green3Background(Point upperLeft, double width, double height) {
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
    public Green3Background(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public Green3Background(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.setColor(new Color(0, 150, 0));
        super.drawOn(d);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(65, 400, 100, 200);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(98, 350, 35, 50);
        d.setColor(Color.DARK_GRAY.brighter());
        d.fillRectangle(111, 150, 10, 200);
        d.setColor(Color.DARK_GRAY.brighter());
        d.setColor(new Color(216, 172, 102));
        d.fillCircle(116, 150, 15);
        d.setColor(new Color(246, 77, 54));
        d.fillCircle(116, 150, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(116, 150, 5);
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(68 + (j * 20), 380 + (i * 35), 15, 20);
            }
        }

//        d.setColor(new Color(250, 250, 150));
//        d.fillCircle(200, 140, 50);
//        d.setColor(new Color(235, 200, 100));
//        d.fillCircle(200, 140, 40);
//        d.setColor(new Color(250, 250, 0));
//        d.fillCircle(200, 140, 30);
//        for (int i = 0; i < 800; i+=10) {
//            d.drawLine(200, 140, i, 250);
//        }
//        d.setColor(new Color(250, 250, 250));
//        d.fillCircle(200, 140, 5);


    }
}
