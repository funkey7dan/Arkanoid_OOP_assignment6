package game.engine.actors;

import biuoop.DrawSurface;
import game.engine.accessories.SoundPlayer;
import game.engine.accessories.bonuses.Bonus;
import game.engine.accessories.bonuses.ChangePaddleWidth;
import game.engine.accessories.bonuses.DoubleBall;
import game.engine.accessories.bonuses.ExtraLife;
import game.engine.accessories.bonuses.LaserBall;
import game.engine.accessories.bonuses.ShotgunBall;
import game.engine.actors.collidables.Collidable;
import game.engine.actors.collidables.CollisionInfo;
import game.engine.actors.collidables.GameEnvironment;
import game.engine.actors.sprites.Sprite;
import game.engine.levels.GameLevel;
import game.ui.shapes.Line;
import game.ui.shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Gift.
 */
public class Gift implements Sprite {
    private final int r;
    private Color color;
    private Point center;
    private GameLevel gameLevel;
    private final Velocity v;
    private GameEnvironment ge;
    private Line trajectory;
    private Bonus bonus;

    /**
     * Constructor for the ball class.
     *
     * @param center - the center point of the balls center.
     * @param r      - the radius of the ball.
     */
    public Gift(Point center, int r) {
        this.center = center;
        this.color = Color.magenta;
        this.r = r;
        this.v = (Velocity.fromAngleAndSpeed(180, 2));
        setTrajectory(v);

    }

    @Override
    public void drawOn(DrawSurface surface) {
        //ball.drawOn(surface);
        // we check if the ball is inside a block, and we don't display it if so.
        for (Collidable c : ge.getCollidableList()) {
            if (c.getCollisionRectangle().isInside(this.center)) {
                return;
            }
        }
        surface.setColor(this.color);
        surface.fillOval((int) center.getX(), (int) center.getY(), r * 3, r * 2);
        surface.setColor(Color.MAGENTA);
        surface.drawOval((int) center.getX(), (int) center.getY(), (r * 3) + 1, (r * 2) + 1);
        if (this.color == Color.green || this.color == Color.pink) {
            surface.setColor(Color.black);
        } else {
            surface.setColor(Color.WHITE);
        }
        surface.drawText((int) center.getX() + 4, (int) center.getY() + 20, bonus.getName(), 11);

    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        CollisionInfo collision;
        //check to see if the ball has been ran over by the paddle, and if so we commence rescue operations.
        collision = ge.getClosestCollision(trajectory);
        for (Collidable c : ge.getCollidableList()) {
            if ((c.getCollisionRectangle().isInside(this.center)
                    ||
                    c.getCollisionRectangle().isInside(new Point(center.getX() + ((r * 3) / 2), center.getY() + r)))
                    &&
                    c.equals(gameLevel.getP1())) {
                gameLevel.addBonus(bonus);
                removeFromGame(gameLevel);
                moveOneStep();
                try {
                    SoundPlayer.playSound(SoundPlayer.Effects.bonus.ordinal());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        if (collision != null && collision.collisionObject().equals(gameLevel.getP1())) {
            gameLevel.addBonus(bonus);
            removeFromGame(gameLevel);
        }
        moveOneStep();
    }

    /**
     * Adds the object to the game.
     *
     * @param g - the game we want to add to.
     */
    public void addToGame(GameLevel g) {
        List<Bonus> bonuses = new ArrayList<>();
        bonuses.add(new DoubleBall(g));
        bonuses.add(new ShotgunBall(g));
        bonuses.add(new LaserBall(g));
        bonuses.add(new ExtraLife(g));
        bonuses.add(new ChangePaddleWidth(g));
        g.addSprite(this);
        this.gameLevel = g;
        this.ge = g.getEnvironment();
        Random rand1 = new Random();
        int i = rand1.nextInt(bonuses.size());
        bonus = bonuses.get(i);
        switch (i) {
            case 0:
            case 1:
                // ball modifications color
                this.color = Color.BLACK;
                break;
            case 2:
                // laser ball color
                this.color = Color.green;
                break;
            case 3:
                // lifes color
                this.color = Color.PINK;
                break;
            default:
                this.color = Color.gray;
        }
    }

    /**
     * Removes the object from the game.
     *
     * @param g - the game we want to remove the block from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
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
     * Move one step.
     */
    public void moveOneStep() {
        CollisionInfo collision;
        this.center = this.v.applyToPoint(this.center);
        // we return the ball to the bounds of the screen in case it left them.
        this.setTrajectory(this.v);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color1 the color
     */
    public void setColor(Color color1) {
        this.color = color1;
    }
}
