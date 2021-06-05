//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.actors.Ball;
import game.engine.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The bonus type Laser ball.
 * Makes the ball pass through the blocks like butter, without bouncing off, only destroying (like a sci-fi laser).
 */
public class LaserBall extends Bonus {
    private final ChangePaddleColor changePaddleColor;
    private final String name;
    private final List<Ball> balls = new ArrayList<>();


    /**
     * Instantiates a new Laser ball.
     *
     * @param g the g
     */
    public LaserBall(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.green);
        this.name = "laserBall";
    }

    @Override
    public void applyBonus() {
        changePaddleColor.applyBonus();
        this.balls.addAll(this.getGameLevel().getBalls());
        for (Ball b : balls) {
            b.setLaserFlag(true);
            b.setColor(Color.ORANGE);
        }

    }

    @Override
    public void removeFromGame() {
        //super.gameLevel.getP1().setSpeed(previousSpeed);
        changePaddleColor.removeFromGame();
        getGameLevel().removeBonus(this);
        for (Ball b : balls) {
            b.setLaserFlag(false);
            b.setColor(Color.white);
        }

    }

    @Override
    public String getName() {
        return name;
    }

}
