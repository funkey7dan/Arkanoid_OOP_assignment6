//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.levels.GameLevel;

import java.awt.Color;

/**
 * The bonus type Change game speed.
 * Sets the games speed to x2 it's speed temporarily.
 */
public class ChangeGameSpeed extends Bonus {
    private final int previousSpeed;
    private final ChangePaddleColor changePaddleColor;
    private final String name;


    /**
     * Instantiates a new Change game speed.
     *
     * @param g the g
     */
    public ChangeGameSpeed(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.RED);
        previousSpeed = getGameLevel().getRunner().getFramesPerSecond();
        this.name = "x2 Speed";
    }

    @Override
    public void applyBonus() {

        changePaddleColor.applyBonus();
        super.getGameLevel().getRunner().setFramesPerSecond(120);

    }

    @Override
    public void removeFromGame() {
        //super.getGameLevel().getP1().setSpeed(previousSpeed);
        changePaddleColor.removeFromGame();
        super.getGameLevel().getRunner().setFramesPerSecond(previousSpeed);
        getGameLevel().removeBonus(this);
        super.removeFromGame();

    }

    @Override
    public String getName() {
        return name;
    }
}
