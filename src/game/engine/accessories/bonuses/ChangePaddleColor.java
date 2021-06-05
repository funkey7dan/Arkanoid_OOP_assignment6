//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.levels.GameLevel;

import java.awt.Color;

/**
 * The bonus type Change paddle color.
 * Changes the paddles color temporarily.
 */
public class ChangePaddleColor extends Bonus {
    private final Color previousColor;
    private final Color color;

    /**
     * Instantiates a new Change paddle color.
     *
     * @param g     the g
     * @param color the color
     */
    public ChangePaddleColor(GameLevel g, Color color) {
        super(g);
        this.color = color;
        previousColor = new Color(200, 15, 25);
    }

    @Override
    public void applyBonus() {
        super.applyBonus();
        super.getGameLevel().getP1().setColor(this.color);
    }

    @Override
    public void removeFromGame() {
        super.getGameLevel().getP1().setColor(previousColor);
        super.getGameLevel().removeBonus(this);
        super.removeFromGame();

    }

}
