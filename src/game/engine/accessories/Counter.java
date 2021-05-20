// ID: ***REMOVED***
package game.engine.accessories;

/**
 * A class for the counter object which is used to count occurrences.
 * Supports increasing and decreasing the count, and returning the current value.
 */
public class Counter {
    private int count = 0;

    /**
     * add number to current count.
     *
     * @param number - the number we add
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the number we subtract
     */
    public void decrease(int number) {
        count -= number;
    }


    /**
     * @return current count.
     */
    public int getValue() {
        return count;
    }
}