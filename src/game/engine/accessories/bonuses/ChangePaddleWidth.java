//XXXXXXXXX
package game.engine.accessories.bonuses;

import game.engine.levels.GameLevel;

/**
 * The bonus type Change paddle width.
 * Changes the paddles width temporarily.
 */
public class ChangePaddleWidth extends Bonus {
    private final double previousWidth;
    private final String name;

    /**
     * Instantiates a new Change paddle width.
     *
     * @param g the g
     */
    public ChangePaddleWidth(GameLevel g) {
        super(g);
        previousWidth = super.getGameLevel().getLevelInfo().paddleWidth();
        this.name = "Width";
    }

    @Override
    public void applyBonus() {
        super.applyBonus();
        super.getGameLevel().getP1().setPaddleWidth(previousWidth * 2);
    }

    @Override
    public void removeFromGame() {
        //super.gameLevel.getP1().setSpeed(previousSpeed);
        getGameLevel().getP1().setPaddleWidth(previousWidth);
        getGameLevel().removeBonus(this);
        super.removeFromGame();

    }

    @Override
    public String getName() {
        return name;
    }

}
