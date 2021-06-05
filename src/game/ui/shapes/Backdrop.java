package game.ui.shapes;

import biuoop.DrawSurface;
import game.engine.levels.GameLevel;
import game.engine.actors.sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.util.Objects;

/**
 * The type Backdrop.
 */
@Deprecated
public class Backdrop implements Sprite {
    private Image image;
    private File file;

    /**
     * Instantiates a new Backdrop.
     *
     * @param file the file
     */
    public Backdrop(File file) {
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Background.jpg")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for drawing the object on a given surface.
     * (draw the sprite to the screen)
     *
     * @param d - the surface we will draw on
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, image);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Sets the background image field.
     */
    public void setBackground() {
        try {
            image = ImageIO.read(file);
            image.setAccelerationPriority(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
