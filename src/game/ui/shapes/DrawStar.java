package game.ui.shapes;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.geom.Ellipse2D;

/**
 * The type Draw star.
 */
public class DrawStar {
    private static final List<Ellipse2D.Double> OVALS = new ArrayList<>();

    /**
     * Instantiates a new Draw star.
     */
    public DrawStar() {
        for (int i = 1; i < 100; i += 1) {
            createStars();
        }

    }

    /**
     * Create stars.
     */
    public void createStars() {
        int width1 = ThreadLocalRandom.current().nextInt(5, 15);
        int height1 = width1 / 5;
        int x1 = ThreadLocalRandom.current().nextInt(1, 800);
        int y1 = ThreadLocalRandom.current().nextInt(1, 600);
        int x2 = x1 + width1 / 2;
        int y2 = y1 - width1 / 3;
        Ellipse2D.Double oval1 = new Ellipse2D.Double(x1, y1, width1, height1);
        Ellipse2D.Double oval2 = new Ellipse2D.Double(x2, y2, height1, width1);
        OVALS.add(oval1);
        OVALS.add(oval2);
    }

    /**
     * Draw the star on the surface d.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        Random rand = new Random();
        // DONT TRY TO ADD COLOR TO THE STARS THEY FLICKER
        for (Ellipse2D.Double o : OVALS) {
            int i = rand.nextInt(3);
            //d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            d.fillOval((int) o.x, (int) o.y, (int) o.width, (int) o.height);
        }

    }
}
