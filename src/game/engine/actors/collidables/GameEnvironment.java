//XXXXXXXXX
package game.engine.actors.collidables;

import game.ui.shapes.Line;
import game.ui.shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object Game.Engine.Actors.Collidables.GameEnvironment.
 * Has an array of all the collidable objects in the game.
 * Supporting adding new collidable objects, and queries about existing.
 */
public class GameEnvironment {

    //List of all obstacles in our environment
    private final List<Collidable> collidableList = new ArrayList<>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c the collidable object we add
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Removes given collidable from the environment.
     *
     * @param c the collidable object we remove
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }


    /**
     * If this object will not collide with any of the hittable objects.
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory line of movement of the object
     * @return the Game.Engine.Actors.Collidables.CollisionInfo about the closest obstacle to the object
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestObject;
        Point closestPoint;
        double minDistance = trajectory.length();
        CollisionInfo closestInfo = null;
        List<Collidable> collidables = new ArrayList<>(this.collidableList);


        //check if any collidable are in the way
        for (Collidable c : collidables) {

            // check the point of collision
            closestPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            // we check if the object is closer than the minimum distance
            if ((closestPoint != null) && (closestPoint.distance(trajectory.start()) < minDistance)) {
                minDistance = closestPoint.distance(trajectory.start());
                closestObject = c;
                closestInfo = new CollisionInfo(closestPoint, closestObject);
            }
        }

        return closestInfo;
    }

    /**
     * @return the List of all the collidable objects.
     */
    public List<Collidable> getCollidableList() {
        return collidableList;
    }

    public void clear() {
        collidableList.clear();
    }
}
