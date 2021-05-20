package game.engine.actors;

import game.engine.actors.collidables.Collidable;
import game.engine.actors.sprites.Sprite;
import game.Game;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import java.awt.Color;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Paddle.
 * Has attributes of the rectangle that holds its shape, the color of the block and the texture overlay.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block block;
    private Rectangle rect;
    private Point middle;
    private final Color color = new Color(200, 15, 25);
    private static final int SPEED = 7;
    private static int guiHeight;
    private static int guiWidth;
    private static final int FONT_SIZE = 12;


    /**
     * Constructor for game.engine.actors.Paddle.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public Paddle(Point upperLeft, double width, double height) {
        this.block = new Block(upperLeft, width, height);
        this.rect = block.getCollisionRectangle();
        this.middle = rect.getTopSide().middle();
    }

    /**
     * Alternative constructor.
     *
     * @param block - the block we use to build the paddle
     */
    public Paddle(Block block) {
        this.block = block;
        this.rect = block.getCollisionRectangle();
        this.middle = rect.getTopSide().middle();

    }

    /**
     * Moves the paddle to the left by creating a new block in the next space,
     * according to the movement speed.
     */
    public void moveLeft() {
        this.block = new Block(new Point(rect.getUpperLeft().getX() - SPEED, rect.getUpperLeft().getY()),
                rect.getWidth(), rect.getHeight());
        this.rect = block.getCollisionRectangle();
        this.middle = rect.getTopSide().middle();
    }

    /**
     * Moves the paddle to the right by creating a new block in the next space,
     * according to the movement speed.
     */
    public void moveRight() {
        this.block = new Block(new Point(rect.getUpperLeft().getX() + SPEED, rect.getUpperLeft().getY()),
                rect.getWidth(), rect.getHeight());
        this.rect = block.getCollisionRectangle();
        this.middle = rect.getTopSide().middle();
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)
                && (this.rect.getUpperLeft().getX() > 0)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                && ((this.rect.getUpperRight().getX()) + SPEED <= guiWidth)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {

        this.block.setColor(this.color);
        this.block.drawOn(surface);
        surface.drawText((int) rect.getUpperLeft().getX() + 25, (int) rect.getUpperLeft().getY() + 15,
                "Player 1", FONT_SIZE);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        if (this.rect.getTopSide().isPointOnSegment(collisionPoint)) {
            switch (this.rect.getTopSide().whatPart(collisionPoint)) {
                case 0:
                    return Velocity.fromAngleAndSpeed(300, SPEED);
                case 1:
                    return Velocity.fromAngleAndSpeed(330, SPEED);
                case 2:
                    return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                case 3:
                    return Velocity.fromAngleAndSpeed(30, SPEED);
                case 4:
                    return Velocity.fromAngleAndSpeed(60, SPEED);
                default:
                    break;
            } // check if the hit is on a vertical plane.
        } else if (getCollisionRectangle().getRightSide().isPointOnSegment(collisionPoint)
                || getCollisionRectangle().getLeftSide().isPointOnSegment(collisionPoint)) {

            //change the direction of the balls movement to the opposite
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (getCollisionRectangle().getBottomSide().isPointOnSegment(collisionPoint)) {
            //change the direction of the balls movement to the opposite
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    @Override
    public void addToGame(Game g) {
        setKeyboard(g.getGui().getKeyboardSensor());
        g.addSprite(this);
        g.addCollidable(this);
        guiHeight = Game.getGuiHeight();
        guiWidth = Game.getGuiWidth();
    }

    /**
     * Adds a key press sensor to the paddle.
     *
     * @param keyboard1 the keyboard we want to set.
     */
    public void setKeyboard(biuoop.KeyboardSensor keyboard1) {
        this.keyboard = keyboard1;
    }

}