//XXXXXXXXX
package game.engine.actors;

import game.engine.actors.collidables.Collidable;
import game.engine.actors.collidables.CollisionInfo;
import game.engine.actors.collidables.GameEnvironment;
import game.engine.actors.sprites.Sprite;
import game.Game;
import biuoop.DrawSurface;
import game.ui.shapes.Line;
import game.ui.shapes.Point;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.util.Random;
import java.util.Comparator;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Ball.
 * Has attributes of center, radius, color , velocity and the limits of the frame it's located in.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Ball implements Sprite {
    private Point center;

    // size\radius of the ball
    private final int r;
    private java.awt.Color color;
    private Velocity v;
    private Line trajectory;

    // the constraints of the frame the ball can move in
    private int guiHeight;
    private int guiWidth;
    private int frameX = 0;
    private int frameY = 0;

    //the game environment
    private GameEnvironment ge;

    // the game to which the ball belongs.
    private Game game;

    // the image we use for the ball texture
    private Image texture;

    /**
     * Constructor for the ball class.
     *
     * @param center - the center point of the balls center.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.r = r;
        this.center = center;
        this.color = color;
    }

    /**
     * Constructor for the ball class.
     *
     * @param centerX - the x coordinate of the balls center.
     * @param centerY - the y coordinate of the balls center.
     * @param r       - the radius of the ball.
     * @param color   - the color of the ball.
     */
    public Ball(double centerX, double centerY, int r, java.awt.Color color) {
        this.r = r;
        this.center = new Point(centerX, centerY);
        this.color = color;
    }

    /**
     * Alternative Constructor for the ball class.
     *
     * @param ball the ball we use as a template
     */
    public Ball(Ball ball) {
        this.r = ball.r;
        this.center = new Point(ball.getX(), ball.getY());
        this.color = ball.color;
    }

    /**
     * A class to give us the option to compare balls by size.
     */
    static class SortBySize implements Comparator<Ball> {

        /**
         * Used for sorting in ascending order of size.
         *
         * @param first  - the first ball we compare
         * @param second - the second ball we compare
         * @return we return the difference in the size of the balls.
         */
        @Override
        public int compare(Ball first, Ball second) {
            return first.getSize() - second.getSize();
        }
    }

    // accessors

    /**
     * @return the x - coordinate of the center.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y - coordinate of the center.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the radius of the ball (size jf you will).
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the height of the GUI screen(vertical upper bound of the frame).
     */
    public int getGuiHeight() {
        return this.guiHeight;
    }

    /**
     * @return the width of the GUI screen(horizontal upper bound of the frame).
     */
    public int getGuiWidth() {
        return this.guiWidth;
    }

    /**
     * @return the horizontal bottom bound of the frame.
     */
    public int getFrameX() {
        return this.frameX;
    }

    /**
     * @return the vertical bottom bound of the frame.
     */
    public int getFrameY() {
        return this.frameY;
    }

    /**
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @return center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the game environment.
     */
    public GameEnvironment getGe() {
        return ge;
    }

    /**
     * @return the trajectory of the ball.
     */
    public Line getTrajectory() {
        return trajectory;
    }

    // setters

    /**
     * @param height - the Height of the gui window we want to set.
     * @param width  - the width of the gui window we want to set.
     */
    public void setGuiSize(int width, int height) {
        this.guiHeight = height;
        this.guiWidth = width;
    }

    /**
     * @param x the x-coordinate of the leftmost bottom corner of the frame.
     * @param y the y-coordinate of the leftmost bottom corner of the frame.
     */
    public void setFrameLimit(int x, int y) {
        frameX = x;
        frameY = y;
    }

    /**
     * @param x the x-coordinate we set
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * @param y the y-coordinate we set
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * @param newColor - The color we want to set.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Sets the velocity using a given velocity.
     *
     * @param setTo the velocity we set to the ball.
     */
    public void setVelocity(Velocity setTo) {
        this.v = setTo;
        setTrajectory(v);
    }

    /**
     * Sets the velocity using a x and y elements of the velocity.
     *
     * @param dx - the horizontal speed.
     * @param dy - the vertical speed.
     */
    public void setVelocity(double dx, double dy) {

        this.v = new Velocity(dx, dy);
        setTrajectory(v);
    }

    /**
     * @param v1 - the velocity we use to set the trajectory
     */
    public void setTrajectory(Velocity v1) {
        double x = center.getX(), y = center.getY();

        // we get the point that the ball will arrive to at current velocity.
        x += v1.getDx();
        y += v1.getDy();
        x = Math.round(x);
        y = Math.round(y);
        this.trajectory = new Line(this.center, new Point(x, y));
    }

    /**
     * Sets the game environment to the ball.
     *
     * @param ge1 - the game environment
     */
    public void setGameEnvironment(GameEnvironment ge1) {
        this.ge = ge1;
    }

    @Override
    public void drawOn(DrawSurface surface) {

        // we check if the ball is inside a block, and we don't display it if so.
        for (Collidable c : ge.getCollidableList()) {
            if (c.getCollisionRectangle().isInside(this.center)) {
                return;
            }
        }
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.drawImage((int) this.getCenter().getX(),
                (int) this.getCenter().getY(), texture);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moves the ball one step according to it's velocity.
     * If the ball is about to collide with an object, move it according to the direction it came from.
     * Works by changing the location of the ball via it's center.
     */
    public void moveOneStep() {

        // create a Random generator
        Random rand = new Random();
        CollisionInfo collision;

        // randomize each color
        int red = rand.nextInt(255);
        int green = rand.nextInt(255);
        int blue = rand.nextInt(255);

        // mixes the random values for each color to get a new shade
        Color randColor = new Color(red, green, blue);

        // find the closest collision to the object on the current trajectory
        collision = this.ge.getClosestCollision(this.trajectory);

        //check to see if the ball has been ran over by the paddle, and if so we commence rescue operations.
        for (Collidable c : ge.getCollidableList()) {
            if (c.getCollisionRectangle().isInside(this.center)) {

                // we move the ball to the height of paddle
                this.center.setY(c.getCollisionRectangle().getUpperRight().getY());
                return;

            }
        }
        String direction;
        if (collision != null) {
            direction = checkCollisionDirection(collision);

            // if there will be a collision, we move the ball almost to the point of collision.
            this.center = this.getVelocity().applyLessToPoint2(collision.collisionPoint(), direction);
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }

        // we return the ball to the bounds of the screen in case it left them.
        returnToBounds();
        this.setTrajectory(this.v);
    }

    /**
     * Receives a collision info as an input, and tells us what direction the hit came from.
     *
     * @param collision - the collision we want to find the direction of.
     * @return - a string containing the direction of the hit.
     */
    public String checkCollisionDirection(CollisionInfo collision) {
        String direction = "";
        boolean isOnTopSide = collision.collisionObject().getCollisionRectangle().
                getTopSide().isPointOnSegment(collision.collisionPoint());
        boolean isOnBotSide = collision.collisionObject().getCollisionRectangle().
                getBottomSide().isPointOnSegment(collision.collisionPoint());
        boolean isOnRightSide = collision.collisionObject().getCollisionRectangle().
                getRightSide().isPointOnSegment(collision.collisionPoint());
        boolean isOnLeftSide = collision.collisionObject().getCollisionRectangle().
                getLeftSide().isPointOnSegment(collision.collisionPoint());
        boolean vChangeFlag = false;

        // if the collision is with the top of the rectangle
        if (isOnTopSide) {
            if (!vChangeFlag) {
                this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
                vChangeFlag = true;
            }
            direction += "top";
        }

        // if the ball came from below
        if (isOnBotSide) {
            if (!vChangeFlag) {
                this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
                vChangeFlag = true;
            }
            direction += "bottom";

        }
        // if the ball collided with the left side
        if (isOnLeftSide) {
            if (!vChangeFlag) {
                this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
                vChangeFlag = true;
            }
            direction += "left";
        }

        // if the ball came from the right
        if (isOnRightSide) {
            if (!vChangeFlag) {
                this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
                vChangeFlag = true;
            }
            direction += "right";
        }
        return direction;
    }


    /**
     * Returns the ball to the frame that was set for it.
     * Works by changing the location of the ball via it's center.
     */
    public void returnToBounds() {
        double diffX, diffY;

        if (this.center.getX() > this.getGuiWidth()) {
            diffX = this.center.getX() - this.getGuiWidth();
            this.center.setX(this.center.getX() - diffX);
        }
        if (this.center.getX() < this.getFrameX()) {
            diffX = this.getFrameX() - this.center.getX();
            this.center.setX(this.center.getX() + diffX);
        }
        if (this.center.getY() > this.getGuiHeight()) {
            diffY = this.center.getY() - this.getGuiHeight();
            this.center.setY(this.center.getY() - diffY);
        }
        if (this.center.getY() < this.getFrameY()) {
            diffY = this.getFrameY() - this.center.getY();
            this.center.setY(this.center.getY() + diffY);
        }
    }


    /**
     * We correct the point that's the balls middle, if the ball is spawned out of bounds.
     */
    @Deprecated
    void correctBounds() {

        // check the difference between provided coordinates, and the gui window size, accounting for ball size
        double xDiffMax = guiWidth - (center.getX() + r), yDiffMax = guiHeight - (center.getY() + r);

        // if the ball is spawned outside of the GUI, gently nudge it to the center
        if (xDiffMax <= 0) {
            center.setX(frameX + ((guiWidth - frameX) / 2));
        }
        if (yDiffMax <= 0) {
            center.setY(frameX + ((guiHeight - frameY) / 2));
        }

        // we check if the ball is outside of the frame
        double xDiffMin = center.getX() - r, yDiffMin = center.getY() - r;
        if (xDiffMin <= frameX) {
            center.setX(frameX + ((guiWidth - frameX) / 2));
        }

        if (yDiffMin <= frameY) {
            center.setY(frameX + ((guiHeight - frameY) / 2));
        }
    }

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        this.setGuiSize(Game.getGuiWidth(), Game.getGuiHeight());
        this.setGameEnvironment(g.getEnvironment());
        this.game = g;
    }

    /**
     * Removes the object from the game.
     *
     * @param g - the game we want to remove the block from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setTexture() {
        try {
            this.texture = ImageIO.read(new File("textures/ball.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new ball at the location of this ball, that moves in the opposite direction.
     */
    public void spawnBall() {
        Ball child = new Ball(this);
        child.setVelocity(-this.getVelocity().getDx(), -this.getVelocity().getDy());
        child.addToGame(game);
    }
}



