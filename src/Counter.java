public class Counter {
    private int count = 0;

    /**
     * add number to current count.
     *
     * @param number - the number we add
     */
    void increase(int number) {
        count += number;
    }

    /**
     * substract number from current count.
     *
     * @param number - the number we subtract
     */
    void decrease(int number) {
        count -= number;
    }


    /**
     * @return current count.
     */
    int getValue() {
        return count;
    }
}