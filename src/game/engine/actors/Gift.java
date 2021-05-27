package game.engine.actors;

import biuoop.DrawSurface;
import game.engine.accessories.Bonus;
import game.engine.accessories.ChangeGameSpeed;
import game.engine.accessories.DoubleBall;
import game.engine.accessories.LaserBall;
import game.engine.accessories.ShotgunBall;
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

public class Gift implements Sprite {
    private final int r;
    private Color color;
    Point center;
    GameLevel gameLevel;
    Velocity v;
    GameEnvironment ge;
    private Line trajectory;
    private Bonus bonus;

    /**
     * Constructor for the ball class.
     *
     * @param center - the center point of the balls center.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Gift(Point center, int r, Color color) {
        this.center = center;
        this.color = Color.magenta;
        this.r = r;
        this.v = (Velocity.fromAngleAndSpeed(180, 2));
        setTrajectory(v);

    }

    public void drawOn(DrawSurface surface) {
        //ball.drawOn(surface);
        // we check if the ball is inside a block, and we don't display it if so.
        surface.setColor(this.color);
        surface.fillOval((int) center.getX(), (int) center.getY(), r * 3, r);
        surface.setColor(Color.WHITE);
        surface.drawText((int) center.getX() - 5, (int) center.getY() + 10, bonus.getName(), 10);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        CollisionInfo collision;
        //check to see if the ball has been ran over by the paddle, and if so we commence rescue operations.
        collision = ge.getClosestCollision(trajectory);
        for (Collidable c : ge.getCollidableList()) {
            if (c.getCollisionRectangle().isInside(this.center) &&
                    c.equals(gameLevel.getP1())) {
                gameLevel.addBonus(bonus);
                removeFromGame(gameLevel);
                moveOneStep();
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
        bonuses.add(new ChangeGameSpeed(g));
        g.addSprite(this);
        this.gameLevel = g;
        this.ge = g.getEnvironment();
        Random rand1 = new Random();
        int i = rand1.nextInt(3);
        bonus = bonuses.get(i);
        switch (i) {
            case 0:
            case 1:
                this.color = Color.BLACK;
                break;
            case 2:
                this.color = Color.green;
                break;
            case 3:
                this.color = Color.RED;
        }
        //bonus = bonuses.get(3); TODO check before submit
        //g.setNumberOfBalls(g.numberOfBalls() + 1);
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

    public void moveOneStep() {
        CollisionInfo collision;
        // find the closest collision to the object on the current trajectory
        collision = this.ge.getClosestCollision(this.trajectory);
        this.center = this.v.applyToPoint(this.center);
        // we return the ball to the bounds of the screen in case it left them.
        this.setTrajectory(this.v);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
