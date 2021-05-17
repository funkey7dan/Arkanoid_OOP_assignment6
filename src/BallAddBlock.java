import biuoop.DrawSurface;

public class BallAddBlock extends Block {
    /**
     * Constructor for Block.
     *
     * @param upperLeft - the left top corner of the block
     * @param width     - the width of the block
     * @param height    - the height of the block
     */
    public BallAddBlock(Point upperLeft, double width, double height) {
        super(upperLeft, width, height);
    }

    /**
     * Alternative Constructor, using an existing rectangle.
     *
     * @param rec - the rectangle we use to build the block
     */
    public BallAddBlock(Rectangle rec) {
        super(rec);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        super.setGiftIcon();
        super.drawOn(surface);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        return super.hit(hitter, collisionPoint, currentVelocity);
    }
}
