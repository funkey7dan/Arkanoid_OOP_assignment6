package game.engine.accessories;

import game.engine.levels.GameLevel;

import java.awt.Color;

public class ExtraLife extends Bonus {
    private int previousSpeed;
    //private Color previousColor;
    private ChangePaddleColor changePaddleColor;
    private String name;


    public ExtraLife(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.RED);
        previousSpeed = gameLevel.getRunner().getFramesPerSecond();
        this.name = "Speed";
    }

    @Override
    public void applyBonus() {

        changePaddleColor.applyBonus();
        //gameLevel.getP1().setColor(Color.blue);
        //previousSpeed = gameLevel.paddleSpeed();
        super.gameLevel.getRunner().setFramesPerSecond(120);

    }

    @Override
    public void removeFromGame() {
        //super.gameLevel.getP1().setSpeed(previousSpeed);
        changePaddleColor.removeFromGame();
        super.gameLevel.getRunner().setFramesPerSecond(previousSpeed);
        gameLevel.removeBonus(this);
        super.removeFromGame();

    }

    @Override
    public long getEndTime() {
        return super.getEndTime();
    }

    @Override
    public String getName() {
        return name;
    }
}
