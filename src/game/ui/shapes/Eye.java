package game.ui.shapes;

import biuoop.DrawSurface;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;

import java.awt.Color;

/**
 * The type Eye.
 */
public class Eye implements Sprite {
    /**
     * The Middle.
     */
    private final Point middle;
    /**
     * The Iris point.
     */
    private Point irisPoint;
    /**
     * The Radius.
     */
    private final int radius;

    /**
     * The Game.
     */
    private GameLevel game;
    private final double eyeballDistance;

    /**
     * The Color.
     */
    private final Color color;


    /**
     * Instantiates a new Eye.
     *
     * @param iris   the iris
     * @param radius the radius
     * @param color  the color
     */
    public Eye(Point iris, int radius, Color color) {
        this.middle = iris;
        this.irisPoint = iris;
        this.radius = radius;
        this.eyeballDistance = radius - (radius * 0.4) - 5;
        this.color = color;
    }

    // a static variable to track the change in the size of the pupil.
    // limits the dilation.
    private static double i = 0.4;

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {

        trackPoint(game.getP1().getMiddle());
        //draw outline
        d.setColor(Color.black);
        //draw the white of the eye
        d.setColor(new Color(255, 255, 235));
        d.fillCircle((int) middle.getX(), (int) middle.getY(), radius);
        //draw eyeball
        d.fillCircle((int) middle.getX(), (int) middle.getY(), radius + 1);
        d.setColor(this.color);
        d.fillCircle((int) irisPoint.getX(), (int) irisPoint.getY(), (int) (radius * 0.4));
        d.setColor(this.color.darker());
        d.fillCircle((int) irisPoint.getX(), (int) irisPoint.getY(), (int) (radius * i));
        d.setColor(Color.black);
        d.fillCircle((int) irisPoint.getX(), (int) irisPoint.getY(), (int) ((radius * 0.4) * i));
        if (i <= 0.45) {
            i += 0.0002;
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        trackPoint(game.getP1().getMiddle());
    }

    /**
     * Track point.
     *
     * @param p the p
     */
    public void trackPoint(Point p) {
        Point origin = this.irisPoint;
        double theta = Math.atan2((p.getY() - origin.getY()),
                (p.getX() - origin.getX()));
        int x = (int) Math.round(Math.cos(theta) * eyeballDistance)
                + (int) middle.getX();
        int y = (int) Math.round(Math.sin(theta) * eyeballDistance)
                + (int) middle.getY();
        if (i >= 0.30 && (irisPoint.getX() != x || irisPoint.getY() != y)) {
            i -= 0.0008;
        }
        this.irisPoint = (new Point(x, y));

    }


    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.game = g;
    }
}
