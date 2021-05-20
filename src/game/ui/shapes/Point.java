package game.ui.shapes;//ID: ***REMOVED***

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * The class for the object game.ui.shapes.Point.
 * Has attributes of x and y coordinates.
 */
public class Point {

    // the coordinates of the point in 2d space
    private double x;
    private double y;

    /**
     * The constructor for point.
     *
     * @param x - the x coordinate.
     * @param y - the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * The distance between two points (x1,y1) and (x2,y2) is the square root of: ((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)).
     *
     * @param other - the point we compare to
     * @return returns the distance
     */
    public double distance(Point other) {
        return (Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y))));
    }

    /**
     * distance -- return the distance of this point to other line.
     *
     * @param other - the point we compare to
     * @return returns the distance
     */
    public double distance(Line other) {
        double a = other.end().getX() - other.start().getX();
        double b = other.end().getY() - other.end().getY();
        return ((Math.abs(((a) * (other.start().getY() - this.getY())) - (other.start().getX() - this.getX()) * b))
                / Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)));

    }

//    /**
//     * Equals -- return true is the points are equal, false otherwise.
//     *
//     * @param other - the point we compare to
//     * @return return true if the points are equal, false otherwise.
//     */
//    public boolean equals(game.ui.shapes.Point other) {
//        if (other == null) {
//            return false;
//        }
//        return (Utillity.aEqual(other.x, this.x)) && (Utillity.aEqual(other.y, this.y));
//    }

    //accessors
    //getters

    /**
     * @return x coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return y coordinate of the point.
     */
    public double getY() {
        return this.y;
    }

    //setters

    /**
     * @param newX - the x coordinate we set
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @param newY - the y coordinate we set
     */
    public void setY(double newY) {
        this.y = newY;
    }
}