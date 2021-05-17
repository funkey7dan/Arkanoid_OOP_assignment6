//XXXXXXXXX

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object CollisionInfo.
 * Has attributes of the rectangle that holds the collision point, and the collision object.
 * Supports getting the collision point and the collision object.
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param cp     - the collision point.
     * @param object - the object we hit.
     */
    public CollisionInfo(Point cp, Collidable object) {
        this.collisionPoint = cp;
        this.collisionPoint.setX(Math.round(cp.getX()));
        this.collisionPoint.setY(Math.round(cp.getY()));
        this.collisionObject = object;

    }


    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {

        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}