//XXXXXXXXX
package game.engine.accessories;

import game.engine.actors.Block;
import game.ui.shapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Create eldritch blocks.
 */
public class CreateEldritchBlocks {
    /**
     * Create list of blocks in the necessary form for the eldritch level.
     * Adapted from parsing a binary text file into block form.
     * broken into 3 parts because checkstyle barks at me for having a long method.
     * pfffffft.
     *
     * @return the list
     */
    public static List<Block> create() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(15, 225), 15, 15));
        blocks.add(new Block(new Point(15, 240), 15, 15));
        blocks.add(new Block(new Point(15, 255), 15, 15));
        blocks.add(new Block(new Point(30, 225), 15, 15));
        blocks.add(new Block(new Point(30, 240), 15, 15));
        blocks.add(new Block(new Point(30, 255), 15, 15));
        blocks.add(new Block(new Point(45, 225), 15, 15));
        blocks.add(new Block(new Point(45, 240), 15, 15));
        blocks.add(new Block(new Point(45, 255), 15, 15));
        blocks.add(new Block(new Point(60, 225), 15, 15));
        blocks.add(new Block(new Point(60, 240), 15, 15));
        blocks.add(new Block(new Point(60, 255), 15, 15));
        blocks.add(new Block(new Point(75, 225), 15, 15));
        blocks.add(new Block(new Point(75, 240), 15, 15));
        blocks.add(new Block(new Point(75, 255), 15, 15));
        blocks.add(new Block(new Point(90, 225), 15, 15));
        blocks.add(new Block(new Point(90, 240), 15, 15));
        blocks.add(new Block(new Point(90, 255), 15, 15));
        blocks.add(new Block(new Point(105, 225), 15, 15));
        blocks.add(new Block(new Point(105, 240), 15, 15));
        blocks.add(new Block(new Point(105, 255), 15, 15));
        blocks.add(new Block(new Point(120, 225), 15, 15));
        blocks.add(new Block(new Point(120, 240), 15, 15));
        blocks.add(new Block(new Point(120, 255), 15, 15));
        blocks.add(new Block(new Point(135, 165), 15, 15));
        blocks.add(new Block(new Point(135, 180), 15, 15));
        blocks.add(new Block(new Point(135, 195), 15, 15));
        blocks.add(new Block(new Point(135, 210), 15, 15));
        blocks.add(new Block(new Point(135, 225), 15, 15));
        blocks.add(new Block(new Point(135, 240), 15, 15));
        blocks.add(new Block(new Point(135, 255), 15, 15));
        blocks.add(new Block(new Point(150, 135), 15, 15));
        blocks.add(new Block(new Point(150, 150), 15, 15));
        blocks.add(new Block(new Point(150, 165), 15, 15));
        blocks.add(new Block(new Point(150, 180), 15, 15));
        blocks.add(new Block(new Point(150, 195), 15, 15));
        blocks.add(new Block(new Point(150, 210), 15, 15));
        blocks.add(new Block(new Point(150, 225), 15, 15));
        blocks.add(new Block(new Point(150, 240), 15, 15));
        blocks.add(new Block(new Point(150, 255), 15, 15));
        blocks.add(new Block(new Point(165, 45), 15, 15));
        blocks.add(new Block(new Point(165, 60), 15, 15));
        blocks.add(new Block(new Point(165, 75), 15, 15));
        blocks.add(new Block(new Point(165, 90), 15, 15));
        blocks.add(new Block(new Point(165, 105), 15, 15));
        blocks.add(new Block(new Point(165, 120), 15, 15));
        blocks.add(new Block(new Point(165, 135), 15, 15));
        blocks.add(new Block(new Point(165, 150), 15, 15));
        blocks.add(new Block(new Point(165, 165), 15, 15));
        blocks.add(new Block(new Point(165, 180), 15, 15));
        blocks.add(new Block(new Point(165, 195), 15, 15));
        blocks.add(new Block(new Point(165, 210), 15, 15));
        blocks.add(new Block(new Point(165, 225), 15, 15));
        blocks.add(new Block(new Point(165, 240), 15, 15));
        blocks.add(new Block(new Point(165, 255), 15, 15));
        blocks.add(new Block(new Point(180, 45), 15, 15));
        blocks.add(new Block(new Point(180, 60), 15, 15));
        blocks.add(new Block(new Point(180, 75), 15, 15));
        blocks.add(new Block(new Point(180, 90), 15, 15));
        blocks.add(new Block(new Point(180, 105), 15, 15));
        blocks.add(new Block(new Point(180, 120), 15, 15));
        blocks.add(new Block(new Point(180, 135), 15, 15));
        blocks.add(new Block(new Point(180, 150), 15, 15));
        blocks.add(new Block(new Point(180, 165), 15, 15));
        blocks.add(new Block(new Point(180, 180), 15, 15));
        blocks.add(new Block(new Point(180, 195), 15, 15));
        blocks.add(new Block(new Point(180, 210), 15, 15));
        blocks.add(new Block(new Point(180, 225), 15, 15));
        blocks.add(new Block(new Point(180, 240), 15, 15));
        blocks.add(new Block(new Point(180, 255), 15, 15));
        blocks.add(new Block(new Point(195, 225), 15, 15));
        blocks.add(new Block(new Point(195, 240), 15, 15));
        blocks.add(new Block(new Point(195, 255), 15, 15));
        blocks.add(new Block(new Point(210, 225), 15, 15));
        blocks.add(new Block(new Point(210, 240), 15, 15));
        blocks.add(new Block(new Point(210, 255), 15, 15));
        blocks.add(new Block(new Point(225, 150), 15, 15));
        blocks.add(new Block(new Point(225, 165), 15, 15));
        blocks.add(new Block(new Point(225, 180), 15, 15));
        blocks.add(new Block(new Point(225, 195), 15, 15));
        blocks.add(new Block(new Point(225, 210), 15, 15));
        blocks.add(new Block(new Point(225, 225), 15, 15));
        blocks.add(new Block(new Point(225, 240), 15, 15));
        blocks.add(new Block(new Point(225, 255), 15, 15));
        blocks.add(new Block(new Point(240, 150), 15, 15));
        blocks.add(new Block(new Point(240, 165), 15, 15));
        blocks.add(new Block(new Point(240, 180), 15, 15));
        blocks.add(new Block(new Point(240, 195), 15, 15));
        blocks.add(new Block(new Point(240, 210), 15, 15));
        blocks.add(new Block(new Point(240, 225), 15, 15));
        blocks.add(new Block(new Point(240, 240), 15, 15));
        blocks.add(new Block(new Point(240, 255), 15, 15));
        blocks.add(new Block(new Point(255, 225), 15, 15));
        blocks.add(new Block(new Point(255, 240), 15, 15));
        blocks.add(new Block(new Point(255, 255), 15, 15));
        blocks.add(new Block(new Point(270, 225), 15, 15));
        blocks.add(new Block(new Point(270, 240), 15, 15));
        blocks.add(new Block(new Point(270, 255), 15, 15));
        blocks.add(new Block(new Point(270, 270), 15, 15));
        blocks.add(new Block(new Point(270, 285), 15, 15));
        blocks.add(new Block(new Point(270, 300), 15, 15));
        blocks.add(new Block(new Point(270, 315), 15, 15));
        blocks.add(new Block(new Point(270, 330), 15, 15));
        blocks.add(new Block(new Point(270, 345), 15, 15));
        blocks.add(new Block(new Point(270, 360), 15, 15));
        blocks.add(new Block(new Point(270, 375), 15, 15));
        blocks.add(new Block(new Point(270, 390), 15, 15));
        blocks.add(new Block(new Point(270, 405), 15, 15));
        blocks.add(new Block(new Point(285, 225), 15, 15));
        blocks.add(new Block(new Point(285, 240), 15, 15));
        blocks.add(new Block(new Point(285, 255), 15, 15));
        blocks.add(new Block(new Point(285, 270), 15, 15));
        blocks.add(new Block(new Point(285, 285), 15, 15));
        blocks.add(new Block(new Point(285, 300), 15, 15));
        blocks.add(new Block(new Point(285, 315), 15, 15));

        blocks.addAll(createPt1());
        blocks.addAll(createPt2());

        int r = 255;
        int i = 0;

        for (Block b : blocks) {
            Color color = new Color(r, 255, 20);
            b.setColor(color);
            i++;
            if (r > 0 && (i % 2 == 0)) {
                r -= 1;
            }
        }
        return blocks;
    }

    /**
     * Create list of blocks in the necessary form for the eldritch level.
     *
     * @return the list
     */
    private static List<Block> createPt2() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(555, 300), 15, 15));
        blocks.add(new Block(new Point(555, 315), 15, 15));
        blocks.add(new Block(new Point(570, 120), 15, 15));
        blocks.add(new Block(new Point(570, 285), 15, 15));
        blocks.add(new Block(new Point(570, 300), 15, 15));
        blocks.add(new Block(new Point(570, 315), 15, 15));
        blocks.add(new Block(new Point(585, 285), 15, 15));
        blocks.add(new Block(new Point(585, 300), 15, 15));
        blocks.add(new Block(new Point(585, 315), 15, 15));
        blocks.add(new Block(new Point(600, 285), 15, 15));
        blocks.add(new Block(new Point(600, 300), 15, 15));
        blocks.add(new Block(new Point(600, 315), 15, 15));
        blocks.add(new Block(new Point(600, 330), 15, 15));
        blocks.add(new Block(new Point(600, 345), 15, 15));
        blocks.add(new Block(new Point(600, 360), 15, 15));
        blocks.add(new Block(new Point(615, 285), 15, 15));
        blocks.add(new Block(new Point(615, 300), 15, 15));
        blocks.add(new Block(new Point(615, 315), 15, 15));
        blocks.add(new Block(new Point(615, 330), 15, 15));
        blocks.add(new Block(new Point(615, 345), 15, 15));
        blocks.add(new Block(new Point(615, 360), 15, 15));
        blocks.add(new Block(new Point(630, 285), 15, 15));
        blocks.add(new Block(new Point(630, 300), 15, 15));
        blocks.add(new Block(new Point(630, 315), 15, 15));
        blocks.add(new Block(new Point(645, 285), 15, 15));
        blocks.add(new Block(new Point(645, 300), 15, 15));
        blocks.add(new Block(new Point(645, 315), 15, 15));
        blocks.add(new Block(new Point(660, 195), 15, 15));
        blocks.add(new Block(new Point(660, 285), 15, 15));
        blocks.add(new Block(new Point(660, 300), 15, 15));
        blocks.add(new Block(new Point(660, 315), 15, 15));
        blocks.add(new Block(new Point(675, 195), 15, 15));
        blocks.add(new Block(new Point(675, 285), 15, 15));
        blocks.add(new Block(new Point(675, 300), 15, 15));
        blocks.add(new Block(new Point(675, 315), 15, 15));
        blocks.add(new Block(new Point(690, 90), 15, 15));
        blocks.add(new Block(new Point(690, 105), 15, 15));
        blocks.add(new Block(new Point(690, 120), 15, 15));
        blocks.add(new Block(new Point(690, 135), 15, 15));
        blocks.add(new Block(new Point(690, 150), 15, 15));
        blocks.add(new Block(new Point(690, 165), 15, 15));
        blocks.add(new Block(new Point(690, 180), 15, 15));
        blocks.add(new Block(new Point(690, 195), 15, 15));
        blocks.add(new Block(new Point(690, 210), 15, 15));
        blocks.add(new Block(new Point(690, 225), 15, 15));
        blocks.add(new Block(new Point(690, 240), 15, 15));
        blocks.add(new Block(new Point(690, 255), 15, 15));
        blocks.add(new Block(new Point(690, 270), 15, 15));
        blocks.add(new Block(new Point(690, 285), 15, 15));
        blocks.add(new Block(new Point(690, 300), 15, 15));
        blocks.add(new Block(new Point(690, 315), 15, 15));
        blocks.add(new Block(new Point(705, 90), 15, 15));
        blocks.add(new Block(new Point(705, 105), 15, 15));
        blocks.add(new Block(new Point(705, 120), 15, 15));
        blocks.add(new Block(new Point(705, 135), 15, 15));
        blocks.add(new Block(new Point(705, 150), 15, 15));
        blocks.add(new Block(new Point(705, 165), 15, 15));
        blocks.add(new Block(new Point(705, 180), 15, 15));
        blocks.add(new Block(new Point(705, 195), 15, 15));
        blocks.add(new Block(new Point(705, 210), 15, 15));
        blocks.add(new Block(new Point(705, 225), 15, 15));
        blocks.add(new Block(new Point(705, 240), 15, 15));
        blocks.add(new Block(new Point(705, 255), 15, 15));
        blocks.add(new Block(new Point(705, 270), 15, 15));
        blocks.add(new Block(new Point(705, 285), 15, 15));
        blocks.add(new Block(new Point(705, 300), 15, 15));
        blocks.add(new Block(new Point(705, 315), 15, 15));
        blocks.add(new Block(new Point(720, 285), 15, 15));
        blocks.add(new Block(new Point(720, 300), 15, 15));
        blocks.add(new Block(new Point(720, 315), 15, 15));
        blocks.add(new Block(new Point(735, 285), 15, 15));
        blocks.add(new Block(new Point(735, 300), 15, 15));
        blocks.add(new Block(new Point(735, 315), 15, 15));
        blocks.add(new Block(new Point(750, 285), 15, 15));
        blocks.add(new Block(new Point(750, 300), 15, 15));
        blocks.add(new Block(new Point(750, 315), 15, 15));
        blocks.add(new Block(new Point(765, 165), 15, 15));
        blocks.add(new Block(new Point(765, 180), 15, 15));
        blocks.add(new Block(new Point(765, 195), 15, 15));
        blocks.add(new Block(new Point(765, 210), 15, 15));
        blocks.add(new Block(new Point(765, 225), 15, 15));
        blocks.add(new Block(new Point(765, 240), 15, 15));
        blocks.add(new Block(new Point(765, 255), 15, 15));
        blocks.add(new Block(new Point(765, 270), 15, 15));
        blocks.add(new Block(new Point(765, 285), 15, 15));
        blocks.add(new Block(new Point(765, 300), 15, 15));
        blocks.add(new Block(new Point(765, 315), 15, 15));
        blocks.add(new Block(new Point(765, 330), 15, 15));
        blocks.add(new Block(new Point(765, 345), 15, 15));
        return blocks;
    }

    /**
     * Create list of blocks in the necessary form for the eldritch level.
     *
     * @return the list
     */
    private static List<Block> createPt1() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(360, 210), 15, 15));
        blocks.add(new Block(new Point(360, 225), 15, 15));
        blocks.add(new Block(new Point(360, 240), 15, 15));
        blocks.add(new Block(new Point(360, 255), 15, 15));
        blocks.add(new Block(new Point(360, 270), 15, 15));
        blocks.add(new Block(new Point(360, 285), 15, 15));
        blocks.add(new Block(new Point(360, 300), 15, 15));
        blocks.add(new Block(new Point(360, 315), 15, 15));
        blocks.add(new Block(new Point(375, 165), 15, 15));
        blocks.add(new Block(new Point(375, 180), 15, 15));
        blocks.add(new Block(new Point(375, 195), 15, 15));
        blocks.add(new Block(new Point(375, 210), 15, 15));
        blocks.add(new Block(new Point(375, 270), 15, 15));
        blocks.add(new Block(new Point(375, 285), 15, 15));
        blocks.add(new Block(new Point(375, 300), 15, 15));
        blocks.add(new Block(new Point(375, 315), 15, 15));
        blocks.add(new Block(new Point(390, 285), 15, 15));
        blocks.add(new Block(new Point(390, 300), 15, 15));
        blocks.add(new Block(new Point(390, 315), 15, 15));
        blocks.add(new Block(new Point(405, 285), 15, 15));
        blocks.add(new Block(new Point(405, 300), 15, 15));
        blocks.add(new Block(new Point(405, 315), 15, 15));
        blocks.add(new Block(new Point(420, 285), 15, 15));
        blocks.add(new Block(new Point(420, 300), 15, 15));
        blocks.add(new Block(new Point(420, 315), 15, 15));
        blocks.add(new Block(new Point(435, 285), 15, 15));
        blocks.add(new Block(new Point(435, 300), 15, 15));
        blocks.add(new Block(new Point(435, 315), 15, 15));
        blocks.add(new Block(new Point(450, 150), 15, 15));
        blocks.add(new Block(new Point(450, 165), 15, 15));
        blocks.add(new Block(new Point(450, 180), 15, 15));
        blocks.add(new Block(new Point(450, 195), 15, 15));
        blocks.add(new Block(new Point(450, 210), 15, 15));
        blocks.add(new Block(new Point(450, 225), 15, 15));
        blocks.add(new Block(new Point(450, 240), 15, 15));
        blocks.add(new Block(new Point(450, 255), 15, 15));
        blocks.add(new Block(new Point(450, 270), 15, 15));
        blocks.add(new Block(new Point(450, 285), 15, 15));
        blocks.add(new Block(new Point(450, 300), 15, 15));
        blocks.add(new Block(new Point(450, 315), 15, 15));
        blocks.add(new Block(new Point(465, 150), 15, 15));
        blocks.add(new Block(new Point(465, 165), 15, 15));
        blocks.add(new Block(new Point(465, 180), 15, 15));
        blocks.add(new Block(new Point(465, 195), 15, 15));
        blocks.add(new Block(new Point(465, 210), 15, 15));
        blocks.add(new Block(new Point(465, 225), 15, 15));
        blocks.add(new Block(new Point(465, 240), 15, 15));
        blocks.add(new Block(new Point(465, 255), 15, 15));
        blocks.add(new Block(new Point(465, 270), 15, 15));
        blocks.add(new Block(new Point(465, 285), 15, 15));
        blocks.add(new Block(new Point(465, 300), 15, 15));
        blocks.add(new Block(new Point(465, 315), 15, 15));
        blocks.add(new Block(new Point(480, 285), 15, 15));
        blocks.add(new Block(new Point(480, 300), 15, 15));
        blocks.add(new Block(new Point(480, 315), 15, 15));
        blocks.add(new Block(new Point(495, 285), 15, 15));
        blocks.add(new Block(new Point(495, 300), 15, 15));
        blocks.add(new Block(new Point(495, 315), 15, 15));
        blocks.add(new Block(new Point(510, 285), 15, 15));
        blocks.add(new Block(new Point(510, 300), 15, 15));
        blocks.add(new Block(new Point(510, 315), 15, 15));
        blocks.add(new Block(new Point(525, 45), 15, 15));
        blocks.add(new Block(new Point(525, 60), 15, 15));
        blocks.add(new Block(new Point(525, 75), 15, 15));
        blocks.add(new Block(new Point(525, 90), 15, 15));
        blocks.add(new Block(new Point(525, 105), 15, 15));
        blocks.add(new Block(new Point(525, 120), 15, 15));
        blocks.add(new Block(new Point(525, 135), 15, 15));
        blocks.add(new Block(new Point(525, 150), 15, 15));
        blocks.add(new Block(new Point(525, 165), 15, 15));
        blocks.add(new Block(new Point(525, 180), 15, 15));
        blocks.add(new Block(new Point(525, 195), 15, 15));
        blocks.add(new Block(new Point(525, 210), 15, 15));
        blocks.add(new Block(new Point(525, 225), 15, 15));
        blocks.add(new Block(new Point(525, 240), 15, 15));
        blocks.add(new Block(new Point(525, 255), 15, 15));
        blocks.add(new Block(new Point(525, 270), 15, 15));
        blocks.add(new Block(new Point(525, 285), 15, 15));
        blocks.add(new Block(new Point(525, 300), 15, 15));
        blocks.add(new Block(new Point(525, 315), 15, 15));
        blocks.add(new Block(new Point(540, 45), 15, 15));
        blocks.add(new Block(new Point(540, 60), 15, 15));
        blocks.add(new Block(new Point(540, 75), 15, 15));
        blocks.add(new Block(new Point(540, 90), 15, 15));
        blocks.add(new Block(new Point(540, 105), 15, 15));
        blocks.add(new Block(new Point(540, 120), 15, 15));
        blocks.add(new Block(new Point(540, 135), 15, 15));
        blocks.add(new Block(new Point(540, 150), 15, 15));
        blocks.add(new Block(new Point(540, 165), 15, 15));
        blocks.add(new Block(new Point(540, 180), 15, 15));
        blocks.add(new Block(new Point(540, 195), 15, 15));
        blocks.add(new Block(new Point(540, 210), 15, 15));
        blocks.add(new Block(new Point(540, 225), 15, 15));
        blocks.add(new Block(new Point(540, 240), 15, 15));
        blocks.add(new Block(new Point(540, 255), 15, 15));
        blocks.add(new Block(new Point(540, 270), 15, 15));
        blocks.add(new Block(new Point(540, 285), 15, 15));
        blocks.add(new Block(new Point(540, 300), 15, 15));
        blocks.add(new Block(new Point(540, 315), 15, 15));
        blocks.add(new Block(new Point(555, 120), 15, 15));
        blocks.add(new Block(new Point(555, 285), 15, 15));
        blocks.add(new Block(new Point(285, 330), 15, 15));
        blocks.add(new Block(new Point(285, 345), 15, 15));
        blocks.add(new Block(new Point(285, 360), 15, 15));
        blocks.add(new Block(new Point(285, 375), 15, 15));
        blocks.add(new Block(new Point(285, 390), 15, 15));
        blocks.add(new Block(new Point(285, 405), 15, 15));
        blocks.add(new Block(new Point(300, 285), 15, 15));
        blocks.add(new Block(new Point(300, 300), 15, 15));
        blocks.add(new Block(new Point(300, 315), 15, 15));
        blocks.add(new Block(new Point(315, 285), 15, 15));
        blocks.add(new Block(new Point(315, 300), 15, 15));
        blocks.add(new Block(new Point(315, 315), 15, 15));
        blocks.add(new Block(new Point(330, 285), 15, 15));
        blocks.add(new Block(new Point(330, 300), 15, 15));
        blocks.add(new Block(new Point(330, 315), 15, 15));
        blocks.add(new Block(new Point(345, 165), 15, 15));
        blocks.add(new Block(new Point(345, 210), 15, 15));
        blocks.add(new Block(new Point(345, 225), 15, 15));
        blocks.add(new Block(new Point(345, 240), 15, 15));
        blocks.add(new Block(new Point(345, 255), 15, 15));
        blocks.add(new Block(new Point(345, 270), 15, 15));
        blocks.add(new Block(new Point(345, 285), 15, 15));
        blocks.add(new Block(new Point(345, 300), 15, 15));
        blocks.add(new Block(new Point(345, 315), 15, 15));
        blocks.add(new Block(new Point(360, 165), 15, 15));
        blocks.add(new Block(new Point(360, 180), 15, 15));
        return blocks;
    }
}
