package game.engine.levels.backgrounds;

import biuoop.DrawSurface;
import game.engine.levels.GameLevel;
import game.ui.shapes.DrawStar;
import game.ui.shapes.Eye;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Eldritch background.
 */
public class EldrichBackground extends Background {
    private final DrawStar ds = new DrawStar();
    private final List<Eye> eyes = new ArrayList<>();

    /**
     * Constructor for game.engine.actors.Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public EldrichBackground(Point upperLeft, double width, double height) {
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
    public EldrichBackground(Point upperLeft, double width, double height, Color color) {
        super(upperLeft, width, height, color);
        eyes.add(new Eye(new Point(146, 300), 74, new Color(200, 41, 27)));
        eyes.add(new Eye(new Point(72, 96), 46, new Color(250, 121, 0)));
        eyes.add(new Eye(new Point(600, 186), 44, new Color(41, 200, 27)));
        eyes.add(new Eye(new Point(400, 393), 108, new Color(27, 150, 195)));
        eyes.add(new Eye(new Point(735, 115), 35, new Color(200, 41, 27)));
        eyes.add(new Eye(new Point(737, 450), 23, new Color(250, 121, 0)));
        eyes.add(new Eye(new Point(357, 79), 54, new Color(27, 150, 195)));
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public EldrichBackground(Rectangle rec) {
        super(rec);
    }

    @Override
    public void timePassed() {
        super.timePassed();

        for (Eye e : eyes) {
            e.timePassed();
        }

    }

    @Override
    public void drawOn(DrawSurface d) {

        super.setColor(new Color(115, 16, 105).darker());
        super.drawOn(d);
        d.setColor(Color.BLUE);
        for (Eye e : eyes) {
            e.drawOn(d);
        }

    }

    @Override
    public void setColor(Color color1) {
        super.setColor(color1);
    }

    @Override
    public void addToGame(GameLevel g) {
        super.addToGame(g);
        for (Eye e : eyes) {
            e.addToGame(g);
        }
    }
}
