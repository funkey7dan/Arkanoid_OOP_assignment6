package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;

    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
    }

    /**
     * The method which is the execution of the animation itself.
     *
     * @param d - the draw surface we are using
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

    }

    /**
     * The stopping condition for the animation loop.
     *
     * @return - the condition.
     */
    @Override
    public boolean shouldStop() {

        if (this.sensor.isPressed(key)) {
            return true;
        }
        return animation.shouldStop();
    }
}
