import biuoop.GUI;
import biuoop.DrawSurface;


public class BallTest1 {
   public static void main(String[] args) {
	      GUI gui = new GUI("title",200,200);
	      biuoop.Sleeper sleeper = new biuoop.Sleeper();
	      Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
	      ball.setVelocity(2, 2);
	      while (true) {
	    	 DrawSurface d = gui.getDrawSurface();
	         ball.moveOneStep();
	         ball.drawOn(d);
	         gui.show(d);
	         sleeper.sleepFor(50);  // wait for 50 milliseconds.
	      }
   }
}