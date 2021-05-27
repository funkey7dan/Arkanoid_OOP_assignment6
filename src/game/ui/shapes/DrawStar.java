package game.ui.shapes;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.geom.Ellipse2D;

public class DrawStar {
    private static List<Ellipse2D.Double> ovals = new ArrayList<>();

    public DrawStar() {
        for (int i = 1; i < 100; i += 1) {
            createStars();
        }

    }

    public void createStars() {
        int width1 = ThreadLocalRandom.current().nextInt(5, 15);
        int height1 = width1 / 5;
        //int width1 = 15;
        //int height1 = 3;
        int x1 = ThreadLocalRandom.current().nextInt(1, 800) + 30;
        int y1 = ThreadLocalRandom.current().nextInt(1, 600) + 30;
        //int x1 = 100;
        //int y1 = 100;
        int width2 = height1;
        int height2 = width1;
        int x2 = x1 + width1 / 2;
        int y2 = y1 - height2 / 3;
        Ellipse2D.Double oval1 = new Ellipse2D.Double(x1, y1, width1, height1);
        Ellipse2D.Double oval2 = new Ellipse2D.Double(x2, y2, width2, height2);
        ovals.add(oval1);
        ovals.add(oval2);
    }

    public void drawOn(DrawSurface d) {
        Random rand = new Random();
        // DONT TRY TO ADD COLOR TO THE STARS THEY FLICKER
        for (Ellipse2D.Double o : ovals) {
            int i = rand.nextInt(3);
            //d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            d.fillOval((int) o.x, (int) o.y, (int) o.width, (int) o.height);
        }

    }
}
