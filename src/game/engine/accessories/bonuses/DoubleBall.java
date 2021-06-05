//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.actors.Ball;
import game.engine.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The bonus type Double ball.
 * Creates an additional ball from every existing ball.
 */
public class DoubleBall extends Bonus {
    private final ChangePaddleColor changePaddleColor;
    private final String name;
    private final List<Ball> balls = new ArrayList<>();


    /**
     * Instantiates a new Double ball.
     *
     * @param g the g
     */
    public DoubleBall(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.green);
        this.name = "Double";
    }

    @Override
    public void applyBonus() {
        this.balls.addAll(this.getGameLevel().getBalls());
        for (Ball b : balls) {
            b.spawnBall();
        }
        removeFromGame();

    }

    @Override
    public void removeFromGame() {
        changePaddleColor.removeFromGame();
        getGameLevel().removeBonus(this);

    }

    @Override
    public String getName() {
        return name;
    }

}
