//XXXXXXXXX
package game;

/**
 * Our main driver class for the game.
 *
 * @author Daniel Bronfman
 * @Email: <daniel.bronfman2010@gmail.com>
 * A class for the object game.engine.actors.Ball.
 * Has attributes of center, radius, color , velocity and the limits of the frame it's located in.
 * Supports moving one step or moving one step with a change of color after bouncing off the bounds.
 */
public class Ass5Game {

    /**
     * The main function for Ass3game, creates a game object,initializes it and runs.
     *
     * @param args - the arguments we get from the console.
     */
    public static void main(String[] args) {

        Game game = new Game();
        if (args.length != 0 && args[0].equals("noDie")) {
            game.setNoDeathFlag();
        }
        game.initialize();
        game.run();
    }
}
