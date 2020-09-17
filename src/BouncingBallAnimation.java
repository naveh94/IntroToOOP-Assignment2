import biuoop.DrawSurface;
import biuoop.GUI;
import java.util.Random;
/**
 * @author Naveh Marchoom
 */
public class BouncingBallAnimation {
    /**
     * Create an animation of a bouncing ball with random values.
     * @param args ignored
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Bouncing Ball Animation", 400, 300);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Random rand = new Random();

        // Generate random values for the ball:
        int x, y, r;
        r = rand.nextInt(50) + 10; // Generate a radius between 10 and 59.
        x = rand.nextInt(400 - r); // Generate a x axis between 0 and (400 - the ball size).
        y = rand.nextInt(300 - r); // Generate a y axis between 0 and (300 - the ball size).
        // Generate a random color:
        java.awt.Color color = new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
        Ball ball = new Ball(x, y, r, color);
        ball.setTopBoundaries(400, 300);

        // Generate random angle and speed:
        double angle, speed;
        angle = (double) rand.nextInt(180) + 1; // Generate a random angle between 1 and 180.
        speed = (double) rand.nextInt(20) + 1; // Generate a random speed between 1 and 20.
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        ball.setVelocity(v);

        // Start moving the ball:
        while (true) {
            DrawSurface board = gui.getDrawSurface();
            ball.moveOneStep();
            ball.drawOn(board);
            gui.show(board);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}