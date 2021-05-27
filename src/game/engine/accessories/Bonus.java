package game.engine.accessories;

import game.engine.levels.GameLevel;

public class Bonus {
    protected long startTime;
    protected long endTime;
    protected GameLevel gameLevel;

    public Bonus(GameLevel g) {
        this.startTime = System.currentTimeMillis();
        this.endTime = startTime + 6000;
        this.gameLevel = g;
    }

    public void applyBonus() {

    }

    public long getEndTime() {
        return endTime;
    }


    public void removeFromGame() {
        gameLevel.removeBonus(this);
    }

    public String getName() {
        return "GenericBonus";
    }
}
