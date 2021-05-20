package game.ui.shapes;//ID: ***REMOVED***

import java.util.List;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.ui.shapes.Line.
 * Has attributes of start, end, color and getLength.
 * Supports checking intersection with other lines, finding the intersection point , checking if a point is on the line,
 * checking whether the line has the same getLength as other line, checking if 2 lines are the same.
 */
public class Line {
    private final Point start;
    private final Point end;
    private double length;

    // constructors

    /**
     * Constructor for the class game.ui.shapes.Line using two points.
     *
     * @param start - the starting point of the line.
     * @param end   - the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

        // get the distance between the starting and ending points to get the getLength of the line.
        this.length = this.start.distance(this.end);
    }

    /**
     * Constructor for the class game.ui.shapes.Line using two sets of coordinates.
     *
     * @param x1 - the x-coordinate of the starting point.
     * @param y1 - the y-coordinate of the starting point.
     * @param x2 - the x-coordinate of the ending point.
     * @param y2 - the x-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

        // get the distance between the starting and ending points to get the getLength of the line.
        this.length = this.start.distance(this.end);
    }

    // Accessors
    // Getters

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.length;
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((this.end.getX() + this.start.getX()) / 2,
                (this.end.getY() + this.start.getY()) / 2);
    }

    /**
     * @return the starting point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks whether the given point is on the line.
     *
     * @param point - the point we are searching
     * @return true of the point on the segment, flase otherwise
     */
    public boolean isPointOnSegment(Point point) {

        // we check if the x coordinate is bigger than or equal to the smallest x value of the line
        return point.getX() >= Math.min(this.start.getX(), this.end.getX())
                &&
                // we check if the x coordinate is smaller than or equal to the biggest x value of the line
                point.getX() <= Math.max(this.start.getX(), this.end.getX())
                &&
                // we check if the y coordinate is bigger than or equal to the smallest y value of the line
                (point.getY() >= Math.min(this.start.getY(), this.end.getY())
                        // we check if the y coordinate is smaller than or equal to the smallest y value of the line
                        && point.getY() <= Math.max(this.start.getY(), this.end.getY()));
    }


    /**
     * check in what part of the line we are.
     *
     * @param point - the point we check.
     * @return what part of the line the point is in.
     */
    public int whatPart(Point point) {

        // we choose to how many parts we want to split the line
        int part = 5;
        return ((int) (point.getX() - this.start.getX()) / ((int) length / part));
    }

    /**
     * @param other - the line we are checking for intersection with our line.
     * @return true if the line intersects, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other - the line we are checking for intersection with our line.
     * @return intersection point if the lines intersect,null otherwise.
     */
    public Point intersectionWith(Line other) {

        // get the coordinates of all the lines
        Double x1 = this.start.getX(),
                y1 = this.start.getY(),
                x2 = this.end.getX(),
                y2 = this.end.getY(),
                x3 = other.start.getX(),
                y3 = other.start.getY(),
                x4 = other.end.getX(),
                y4 = other.end.getY(),

                //slope of this line
                m1 = (y2 - y1) / (x2 - x1),

                // linear equation of this line
                b1 = y1 - m1 * x1,

                //slope of the other line
                m2 = (y4 - y3) / (x4 - x3),

                // linear equation of the other line
                b2 = y3 - m2 * x3;


        // we find the x-coordinate of the intersection and use it in this lines equation to find the y.
        Double pX = -(b1 - b2) / (m1 - m2),
                pY = b1 + m1 * pX;
        if (b1.equals(b2)) {
            pY = b1;
            if (this.start == other.end) {
                pX = this.start.getX();
            } else if (other.start == this.end) {
                pX = this.end.getX();
            }
        }

        //check if the slope of one of the lines is undefined, and adjust equation accordingly
        if (m1.isInfinite()) {
            b1 = x1;
            pX = b1;
            pY = b2 + m2 * pX;
        }
        if (m2.isInfinite()) {
            b2 = x3;
            pX = b2;
            pY = b1 + m1 * pX;
        }

        // if the two lines are the same line they don't have a single intersection so we return null
        if (this.equals(other) || pY.isInfinite()) {
            return null;
        }

        // we create the intersection point using the coordinates we found.
        if (pX.isNaN()) {
            pX = 0.0;
        }
        if (pY.isNaN()) {
            pY = 0.0;
        }
        Point intersection = new Point(Math.round(pX), Math.round(pY));

        // if the intersection is on both segments, we return the point.
        if (this.isPointOnSegment(intersection) && other.isPointOnSegment(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if this line and another line are the same length.
     *
     * @param other - the line we are comparing with
     * @return true if the lines are of the same length, false otherwise.
     */
    public boolean sameLength(Line other) {
        return this.length() == other.length();
    }

    /**
     * Checks if this line and another line are the same line.
     *
     * @param other - the line we are comparing with
     * @return true if the lines are of the same line, flase otherwise.
     */
    public boolean equals(Line other) {
        double epsilon = Math.pow(10, -6);

        return ((Math.abs(this.start.distance(other.start)) <= epsilon)
                || ((Math.abs(this.start.distance(other.end)) <= epsilon)
                && (Math.abs(this.end.distance(other.end)) <= epsilon)
                || ((Math.abs(this.end.distance(other.start)) <= epsilon))));
    }


    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect - the rectangle we are checking.
     * @return the point of intersection, or null if there is none.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // we generate a list of intersection points with the rectangle
        List<Point> list = rect.intersectionPoints(this);

        // we check if the intersections exist
        if (list.isEmpty()) {
            return null;
        }

        Point closest = this.end;

        // we set the minimal distance to be at first the length of the line
        double minDistance = this.length;
        for (Point point : list) {

            // we check if the distance from the start of the line to the point is smaller than the minimal
            if (this.start.distance(point) < minDistance) {
                minDistance = this.start.distance(point);
                closest.setX(Math.round(point.getX()));
                closest.setY(Math.round(point.getY()));
            }
        }
        return closest;
    }
}