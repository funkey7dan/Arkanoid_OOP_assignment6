package game.engine.actors;//XXXXXXXXX

import game.engine.actors.collidables.Collidable;
import game.engine.actors.sprites.Sprite;
import game.Game;
import biuoop.DrawSurface;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Frame.
 * Has attributes of the rectangle that holds its shape, the color of it's block.
 */
public class Frame implements Collidable, Sprite {
    private Rectangle rect;
    private Color color = Color.blue;

    /**
     * Constructor for game.engine.actors.Frame. creates a rectangle for the frame.
     *
     * @param upperLeft - the left top corner of the frame
     * @param width     - the width of the frame
     * @param height    - the height of the frame
     */
    public Frame(Point upperLeft, double width, double height) {
        this.rect = new Rectangle(upperLeft, width, height);
    }

    /**
     * Alternative constructor.
     *
     * @param rec - the rectangle we use to build the block
     */
    public Frame(Rectangle rec) {
        this.rect = rec;
    }

    @Override
    public Rectangle getCollisionRectangle() {

        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double newDx = currentVelocity.getDx(), newDy = currentVelocity.getDy();

        // check if the hit is on a vertical plane
        if (rect.getTopSide().isPointOnSegment(collisionPoint)
                || rect.getBottomSide().isPointOnSegment(collisionPoint)) {

            //change the direction of the balls movement to the opposite
            newDy = -newDy;
        }
        // check if the hit is on a horizontal plane
        if (rect.getRightSide().isPointOnSegment(collisionPoint)
                || rect.getLeftSide().isPointOnSegment(collisionPoint)) {

            //change the direction of the balls movement to the opposite
            newDx = -newDx;
        }

        return new Velocity(newDx, newDy);

    }

    @Override
    public void drawOn(DrawSurface surface) {

    }

    @Override
    public void timePassed() {

    }

    /**
     * Sets the color.
     *
     * @param color1 - the color we set.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @return returns the type of object.
     */
    @Override
    public String getType() {
        return "game.engine.actors.Frame";
    }
}
