//XXXXXXXXX
package game.engine.actors;

import game.engine.actors.collidables.Collidable;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;
import biuoop.DrawSurface;
import game.engine.listeners.HitListener;
import game.engine.listeners.HitNotifier;
import game.ui.shapes.Point;
import game.ui.shapes.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Block.
 * Has attributes of the rectangle that holds its shape, the color of the block and the texture overlay.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rect;
    private Color color = Color.blue;
    private BufferedImage bfTexture;
    private Image texture;
    private Image icon;
    private static final int BLOCK_WIDTH = 50;
    private final ArrayList<HitListener> hitListeners = new ArrayList<>();

    /**
     * Constructor for game.engine.actors.Block.
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
        setTexture();
    }

    /**
     * Constructor for creating a block from a block.
     *
     * @param block the block we copy from.
     */
    public Block(Block block) {
        this.rect = block.rect;
        this.color = block.color;
        setTexture();
    }


    @Override
    public Rectangle getCollisionRectangle() {

        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        double newDx = currentVelocity.getDx(), newDy = currentVelocity.getDy();

        if (!hitter.isLaserFlag()) {
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
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes the object from the game.
     *
     * @param g - the game we want to remove the block from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
        g.blocks().remove(this);
    }


    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setTexture() {
        try {
            this.texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Shine2.png")));

        } catch (
                Exception e) {
            try {
                this.texture = ImageIO.read(new File("textures/Shine2.png"));
            } catch (Exception e1) {
                e1.printStackTrace();
                e.printStackTrace();
            }
        }
        this.texture = texture.getScaledInstance((int) this.rect.getWidth(), (int) this.rect.getHeight(),
                Image.SCALE_DEFAULT);
        texture.setAccelerationPriority(1);
    }

    /**
     * Sets the texture field.
     * We add the path to the image we use for the texture.
     */
    public void setSkullIcon() {
        try {
            this.icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Skull.png")));
            try {
                this.icon = ImageIO.read(new File("textures/Skull.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.icon = icon.getScaledInstance((int) this.rect.getWidth(), (int) this.rect.getHeight(),
                    Image.SCALE_DEFAULT);
            icon.setAccelerationPriority((float) 0.1);

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
            this.icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Gift.png")));


        } catch (Exception e) {
            try {
                this.icon = ImageIO.read(new File("textures/Gift.png"));
            } catch (Exception e1) {
                e1.printStackTrace();
                e.printStackTrace();
            }

        }
        this.icon = icon.getScaledInstance((int) this.rect.getWidth(), (int) this.rect.getHeight(),
                Image.SCALE_DEFAULT);
        icon.setAccelerationPriority((float) 0.1);
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
     * Remove all the hit listeners.
     */
    public void removeALLHitListener() {
        hitListeners.clear();
    }


    /**
     * Notifies the hit listeners a hit with the hitter  occurred.
     *
     * @param hitter - the ball that hit this block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Creates a new "Gift" object, in the bottom middle of the block, falling down.
     *
     * @return a reference to the spawned gift.
     */
    public Gift spawnGift() {
        double centerX = this.getCollisionRectangle().getBottomSide().middle().getX() - this.rect.getWidth() / 2;
        double centerY = this.getCollisionRectangle().getBottomSide().middle().getY();
        return new Gift(new Point(centerX, centerY), 15);
    }

}
