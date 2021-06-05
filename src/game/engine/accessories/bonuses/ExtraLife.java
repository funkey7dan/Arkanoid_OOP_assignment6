//XXXXXXXXX
package game.engine.accessories.bonuses;

import game.engine.levels.GameLevel;


/**
 * The bonus type Extra life.
 * Adds an extra live to the player.
 */
public class ExtraLife extends Bonus {
    private final String name;


    /**
     * Instantiates a new Extra life.
     *
     * @param g the g
     */
    public ExtraLife(GameLevel g) {
        super(g);
        this.name = "+1 life";
    }

    @Override
    public void applyBonus() {
        super.getGameLevel().incrementLives();
        removeFromGame();
    }

    @Override
    public void removeFromGame() {
        getGameLevel().removeBonus(this);
        super.removeFromGame();

    }

    @Override
    public String getName() {
        return name;
    }
}
