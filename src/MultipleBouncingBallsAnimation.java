import biuoop.DrawSurface;
import biuoop.GUI;
import java.util.Random;

/**
 * @author Naveh Marchoom
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Create an animation of several bouncing balls.
     * @param args an array containing the sizes of the balls.
     */
    public static void main(String[] args) {
        int[] sizes = createIntegerArray(args);
        GUI gui = new GUI("Multliple Bouncing Balls Animation", 400, 300);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        Ball[] balls = new Ball[sizes.length];
        int x, y;

        // Create a list of balls in random places on the board, with random colors:
        for (int i = 0; i < balls.length; i++) {
            // Generate the ball in the given size in a random place and color:
            x = rand.nextInt(400 - sizes[i]); // Generate a x axis between 0 and (400 - the ball size).
            y = rand.nextInt(300 - sizes[i]); // Generate a y axis between 0 and (300 - the ball size).
            balls[i] = new Ball(x, y, sizes[i], randomColor());
            balls[i].setTopBoundaries(400, 300);

            // Generate a random velocity considering the ball size:
            double angle, speed;
            angle = (double) rand.nextInt(180) + 1; // Generate a random angle between 1 and 180.
            speed = generateSpeed(sizes[i]);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
        }

        // Move the balls:
        while (true) {
            DrawSurface board = gui.getDrawSurface();
            // Move all of the balls, and draw them on the DrawSurface:
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(board);
            }
            gui.show(board);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Create an integer array out of a String array.
     * @param args the string array.
     * @return the new integer array.
     */
    public static int[] createIntegerArray(String[] args) {
        int[] nums = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }
        return nums;
    }

    /**
     * Generate a random color.
     * @return a random color.
     */
    public static java.awt.Color randomColor() {
        Random rand = new Random();
        return new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    /**
     * Generate a ball speed considering his size.
     * @param size the ball's size.
     * @return the generated speed.
     */
    public static double generateSpeed(int size) {
        int speedPenalty;
        if (size >= 50) {
            speedPenalty = 17;
        } else {
            speedPenalty = size / 3;
        }
        return 20 - speedPenalty;
    }
}