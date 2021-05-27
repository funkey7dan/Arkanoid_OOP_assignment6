package game.engine.accessories;

import game.engine.levels.GameLevel;

import java.awt.Color;

public class ChangePaddleColor extends Bonus {
    private int previousSpeed;
    private Color previousColor;
    private long msPassedCounter;
    private Color color;

    public ChangePaddleColor(GameLevel g, Color color) {
        super(g);
        this.color = color;
        previousColor = gameLevel.getP1().getColor();
        msPassedCounter = 0;
    }

    @Override
    public void applyBonus() {
        super.applyBonus();
        gameLevel.getP1().setColor(this.color);


        //previousSpeed = gameLevel.paddleSpeed();
        //super.gameLevel.getP1().setSpeed(gameLevel.paddleSpeed() * 2);

    }

    @Override
    public void removeFromGame() {
        //super.gameLevel.getP1().setSpeed(previousSpeed);
        gameLevel.getP1().setColor(previousColor);
        gameLevel.removeBonus(this);
        super.removeFromGame();

    }

    @Override
    public long getEndTime() {
        return super.getEndTime();
    }

}
