package game.ui.shapes;//XXXXXXXXX

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.ui.shapes.Rectangle.
 * Has attributes of upperLeft, radius, width , height.
 * Supports getters for each of the attributes.
 */
public class Rectangle {

    // Corner points
    private final Point upperLeft;
    private final Point upperRight;
    private final Point lowerLeft;
    private final Point lowerRight;
    private final Line rightSide;
    private final Line leftSide;
    private final Line topSide;
    private final Line bottomSide;
    private final double width;
    private final double height;


    /**
     * Constructor for the game.ui.shapes.Rectangle class.
     *
     * @param upperLeft - the location of the left upper corner of the rectangle
     * @param width     - width of the rectangle
     * @param height    - height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
        this.width = width;
        this.height = height;
        this.rightSide = new Line(upperRight, lowerRight);
        this.leftSide = new Line(upperLeft, lowerLeft);
        this.topSide = new Line(upperLeft, upperRight);
        this.bottomSide = new Line(lowerLeft, lowerRight);
    }


    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line - the line we check the intersection with
     * @return array of the intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        if (line.isIntersecting(rightSide)) {
            list.add(line.intersectionWith(rightSide));
        }
        if (line.isIntersecting(leftSide)) {
            list.add(line.intersectionWith(leftSide));
        }
        if (line.isIntersecting(topSide)) {
            list.add(line.intersectionWith(topSide));
        }
        if (line.isIntersecting(bottomSide)) {
            list.add(line.intersectionWith(bottomSide));
        }
        return list;
    }


    /**
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return Returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return Returns the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return Returns the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return Returns the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * @return Returns the top line of the rectangle.
     */
    public Line getTopSide() {
        return this.topSide;
    }

    /**
     * @return Returns the bottom Side of the rectangle.
     */
    public Line getBottomSide() {
        return this.bottomSide;
    }

    /**
     * @return Returns the right Side of the rectangle.
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * @return Returns the left Side of the rectangle.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * check if the given point is inside the rectangle.
     *
     * @param check - the point we check
     * @return true or false
     */
    public Boolean isInside(Point check) {
        if ((check.getX() > this.lowerLeft.getX())
                && (check.getX() < this.lowerRight.getX())
                && (check.getY() > this.upperRight.getY())
                && (check.getY() < this.lowerRight.getY())) {
            return true;
        }
        return false;
    }


}

