//***REMOVED***
package game.engine.accessories.bonuses;

import game.engine.levels.GameLevel;

/**
 * A class for the Bonus object.
 * Represents a temporary effect changing the rules of the game.
 * Has a value of the starting and ending times, holds reference to the gameLevel it belongs to.
 * Has a default effect length in milliseconds.
 */
public class Bonus {
    private final long startTime;
    private final long endTime;
    private GameLevel gameLevel;
    private final long bonusLength = 10000;

    /**
     * Instantiates a new Bonus.
     *
     * @param g the game level
     */
    public Bonus(GameLevel g) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + bonusLength;
        this.setGameLevel(g);
    }

    /**
     * Apply bonus.
     */
    public void applyBonus() {

    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public long getEndTime() {
        return endTime;
    }


    /**
     * Remove from game.
     */
    public void removeFromGame() {
        getGameLevel().removeBonus(this);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return "GenericBonus";
    }

    /**
     * Gets game level.
     *
     * @return the game level
     */
    protected GameLevel getGameLevel() {
        return gameLevel;
    }

    /**
     * Sets game level.
     *
     * @param gameLevel1 the game level
     */
    protected void setGameLevel(GameLevel gameLevel1) {
        this.gameLevel = gameLevel1;
    }
}
