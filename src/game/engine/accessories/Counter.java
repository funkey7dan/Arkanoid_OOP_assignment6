package game.engine.accessories;

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
     * substract number from current count.
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