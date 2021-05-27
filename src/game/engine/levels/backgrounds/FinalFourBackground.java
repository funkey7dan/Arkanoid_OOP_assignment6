package game.engine.levels.backgrounds;

import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

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
        for (int i = 1; i < 10; i++) {
            d.drawLine(130 + i * 7, 435, 110 + i * 8,600);
        }
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
