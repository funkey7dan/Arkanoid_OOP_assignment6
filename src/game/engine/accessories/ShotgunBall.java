package game.engine.accessories;

import game.engine.actors.Ball;
import game.engine.levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ShotgunBall extends Bonus {
    private int previousSpeed;
    //private Color previousColor;
    private ChangePaddleColor changePaddleColor;
    private String name;
    private List<Ball> balls = new ArrayList<>();


    public ShotgunBall(GameLevel g) {
        super(g);
        this.changePaddleColor = new ChangePaddleColor(g, Color.green);
        this.name = "Shotgun";
    }

    @Override
    public void applyBonus() {

        //gameLevel.getP1().setColor(Color.blue);
        //previousSpeed = gameLevel.paddleSpeed();
        this.balls.addAll(this.gameLevel.getBalls());
        for (Ball b : this.balls) {
            b.spewBalls();
        }
        removeFromGame();

    }

    @Override
    public void removeFromGame() {
        changePaddleColor.removeFromGame();
        gameLevel.removeBonus(this);

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
