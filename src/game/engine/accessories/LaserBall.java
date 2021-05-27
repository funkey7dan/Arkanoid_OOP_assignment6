package game.engine.accessories;

import game.engine.actors.Ball;
import game.engine.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class LaserBall extends Bonus {
    private int previousSpeed;
    //private Color previousColor;
    private ChangePaddleColor changePaddleColor;
    private String name;
    private List<Ball> balls = new ArrayList<>();


    public LaserBall(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.green);
        this.name = "laser";
    }

    @Override
    public void applyBonus() {
        changePaddleColor.applyBonus();
        //gameLevel.getP1().setColor(Color.blue);
        //previousSpeed = gameLevel.paddleSpeed();
        this.balls.addAll(this.gameLevel.getBalls());
        for (Ball b : balls) {
            b.setLaserFlag(true);
            b.setColor(Color.ORANGE);
        }

    }

    @Override
    public void removeFromGame() {
        //super.gameLevel.getP1().setSpeed(previousSpeed);
        changePaddleColor.removeFromGame();
        gameLevel.removeBonus(this);
        for (Ball b : balls) {
            b.setLaserFlag(false);
            b.setColor(Color.white);
        }

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
