package game.engine.accessories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class textToBlocks2 {



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
