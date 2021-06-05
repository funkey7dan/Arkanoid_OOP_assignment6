//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.actors.Ball;
import game.engine.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The bonus type Double ball.
 * Creates a spray of smaller balls from the current ball. they shoot out radially.
 */
public class ShotgunBall extends Bonus {
    private final ChangePaddleColor changePaddleColor;
    private final String name;
    private final List<Ball> balls = new ArrayList<>();


    /**
     * Instantiates a new Shotgun ball.
     *
     * @param g the g
     */
    public ShotgunBall(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.green);
        this.name = "Shotgun";
    }

    @Override
    public void applyBonus() {
        this.balls.addAll(super.getGameLevel().getBalls());
        for (Ball b : this.balls) {
            b.spewBalls();
        }
        removeFromGame();

    }

    @Override
    public void removeFromGame() {
        changePaddleColor.removeFromGame();
        super.getGameLevel().removeBonus(this);

    }

    @Override
    public String getName() {
        return name;
    }

}
