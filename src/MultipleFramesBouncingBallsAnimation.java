import biuoop.DrawSurface;
import biuoop.GUI;
import java.util.Random;

/**
 * @author Naveh Marchoom
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * Create an animation of multiple jumping balls. Half of  them in the big gray rectangle,
     * and the other half in the yellow small rectangle.
     * @param args The sizes of the balls.
     */
    public static void main(String[] args) {
        int[] sizes = createIntegerArray(args);
        GUI gui = new GUI("Multliple Bouncing Balls Animation", 600, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();
        int x, y, j = 0;

        Line grayRec = new Line(50, 50, 450, 450);
        Line yellowRec = new Line(450, 450, 150, 150);
        Ball[] grayBalls = new Ball[sizes.length / 2];
        Ball[] yellowBalls = new Ball[sizes.length / 2];

        // Create the list of the balls on the gray rectangle:
        for (int i = 0; i < grayBalls.length; i++) {

            // Generate the ball in the given size in a random place and color:
            x = rand.nextInt(450 - sizes[j]) + 50; // Generate a x axis between 0 and (400 - the ball size).
            y = rand.nextInt(450 - sizes[j]) + 50; // Generate a y axis between 0 and (300 - the ball size).
            grayBalls[i] = new Ball(x, y, sizes[j], randomColor(java.awt.Color.GRAY));
            // Setting the boundaries:
            grayBalls[i].setTopBoundaries(500, 500);
            grayBalls[i].setBottomBoundaries(50, 50);

            // Generate a random velocity considering the ball size:
            double angle, speed;
            angle = (double) rand.nextInt(180) + 1; // Generate a random angle between 1 and 180.
            speed = generateSpeed(sizes[j]);
            grayBalls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            j++;
        }

        // Create the list of the balls in the yellow rectangle:
        for (int i = 0; i < grayBalls.length; i++) {

            // Generate the ball in the given size in a random place and color:
            x = rand.nextInt(150 - sizes[j]) + 450; // Generate a x axis between 0 and (400 - the ball size).
            y = rand.nextInt(150 - sizes[j]) + 450; // Generate a y axis between 0 and (300 - the ball size).
            yellowBalls[i] = new Ball(x, y, sizes[j], randomColor(java.awt.Color.YELLOW));
            // Setting the boundaries:
            yellowBalls[i].setTopBoundaries(600, 600);
            yellowBalls[i].setBottomBoundaries(450, 450);

            // Generate a random velocity considering the ball size:
            double angle, speed;
            angle = (double) rand.nextInt(180) + 1; // Generate a random angle between 1 and 180.
            speed = generateSpeed(sizes[j]);
            yellowBalls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            j++;
        }

        // Move the balls:
        while (true) {
            DrawSurface board = gui.getDrawSurface();
            // Draw the rectangles:
            drawRectangle(board, grayRec, java.awt.Color.GRAY);
            drawRectangle(board, yellowRec, java.awt.Color.YELLOW);

            // Move all of the balls, and draw them on the DrawSurface:
            for (int i = 0; i < grayBalls.length; i++) {
                grayBalls[i].moveOneStep();
                grayBalls[i].drawOn(board);
                yellowBalls[i].moveOneStep();
                yellowBalls[i].drawOn(board);
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
     * Generate a random color. Won't return the frame's color.
     * @param frameColor the color of the surrounding frame.
     * @return a random color.
     */
    public static java.awt.Color randomColor(java.awt.Color frameColor) {
        Random rand = new Random();
        java.awt.Color randomColor;
        do {
            randomColor = new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        } while (randomColor.equals(frameColor));
        return randomColor;
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
        return 25 - speedPenalty;
    }

    /**
     * Draw a rectangle using a line.
     * @param board the DrawSurface.
     * @param l the line.
     * @param color the rectangle color.
     */
    public static void drawRectangle(DrawSurface board, Line l, java.awt.Color color) {
        board.setColor(color);
        board.fillRectangle((int) l.start().getX(), (int) l.start().getY(),
                (int) l.end().getX(), (int) l.end().getY());
    }
}

