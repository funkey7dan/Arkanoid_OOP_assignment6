//XXXXXXXXX

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object Block.
 * Has attributes of the rectangle that holds its shape, the color of the block and the texture overlay.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rect;
    private Color color = Color.blue;
    private Image texture;
    private Image icon;
    private static final int BLOCK_WIDTH = 50;
    private ArrayList<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor for Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public Block(Point upperLeft, double width, double height) {
        this.rect = new Rectangle(upperLeft, width, height);
        setTexture();
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public Block(Rectangle rec) {
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

        notifyHit(hitter);
        return new Velocity(newDx, newDy);

    }

    @Override
    public void drawOn(DrawSurface surface) {

        surface.setColor(this.color);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
        surface.drawImage((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(), texture);
        if (icon != null) {
            surface.drawImage((int) this.rect.getUpperLeft().getX(),
                    (int) this.rect.getUpperLeft().getY(), icon);
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * Sets the color field.
     *
     * @param color1 - the color we set
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param g - the game we want to remove the block from.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * @return returns the type of object.
     */
    @Override
    public String getType() {
        return "Block";
    }

    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setTexture() {
        try {
            if (this.rect.getWidth() <= BLOCK_WIDTH) {
                this.texture = ImageIO.read(new File("textures/Shine2.png"));
            } else {
                this.texture = ImageIO.read(new File("textures/Shine_paddle2.png"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setSkullIcon() {
        try {
            this.icon = ImageIO.read(new File("textures/Skull.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setGiftIcon() {
        try {
            this.icon = ImageIO.read(new File("textures/Gift.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Notifies the hit listeners a hit with the hitter  occurred.
     *
     * @param hitter- the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
