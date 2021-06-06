package game.engine.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 */
public class KeyPressStoppableAnimation implements Animation {

    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        isAlreadyPressed = false;
    }

    /**
     * The method which is the execution of the animation itself.
     *
     * @param d - the draw surface we are using
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.sensor.isPressed(key)) {
            this.isAlreadyPressed = true;
        }
        if (isAlreadyPressed) {
            return;
        }
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
