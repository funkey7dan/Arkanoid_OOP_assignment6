package game.engine.accessories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The type Text to blocks 2.
 */
public class TextToBlocks2 {


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("src/game/engine/accessories/spaceinvader.txt"));
            for (String line : allLines) {
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        System.out.print(c);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
