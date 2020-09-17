import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * @author Naveh Marchoom
 */
public class AbstractArtDrawing {

    /**
     * Generate a random line parameters.
     * @return a new random line.
     */
    private Line generateRandomLine() {
        Random randGenerator = new Random();
        int x1 = randGenerator.nextInt(400) + 1; // get integer in range 1-400
        int y1 = randGenerator.nextInt(300) + 1; // get integer in range 1-300
        int x2 = randGenerator.nextInt(400) + 1; // get integer in range 1-400
        int y2 = randGenerator.nextInt(300) + 1; // get integer in range 1-300
        Line randomLine = new Line(x1, y1, x2, y2);
        return randomLine;
    }

    /**
     * Draw a black line on the board.
     * @param board the DrawSurface it should be drawn on.
     * @param line the line that should be drawn.
     */
    private void drawLine(DrawSurface board, Line line) {
        board.setColor(Color.BLACK);
        board.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());
    }

    /**
     * Mark the middle of a line in blue dots.
     * @param board the DrawSurface it should be drawn on.
     * @param line the line that should be marked.
     */
    private void markMiddle(DrawSurface board, Line line) {
        board.setColor(Color.BLUE);
        board.fillCircle((int) line.middle().getX(), (int) line.middle().getY(), 3);
    }

    /**
     * Mark the line intersections in red dots.
     * @param board the DrawSurface it should be drawn on.
     * @param lines the lines it check intersection with.
     * @param curr the current line checked.
     */
    private void markIntersections(DrawSurface board, Line[] lines, int curr) {
        board.setColor(Color.RED);
        Point inter;
        for (int i = 0; i < curr; i++) {
            if (lines[curr].isIntersecting(lines[i])) {
                inter = lines[curr].intersectionWith(lines[i]);
                board.fillCircle((int) inter.getX(), (int) inter.getY(), 3);
            }
        }
    }

    /**
     * Generated random lines and draw them on the DrawSurface, marking their centers and intersections.
     */
    public void drawRandomLines() {
        GUI gui = new GUI("Abstract Art", 400, 300);
        DrawSurface board = gui.getDrawSurface();
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = generateRandomLine();
            drawLine(board, lines[i]); // Draw the line in black.
            markMiddle(board, lines[i]); // Mark the middle in blue.
            markIntersections(board, lines, i); // Mark intersections in red.
        }
        gui.show(board);
    }

    /**
     * Make an abstract art picture using random lines.
     * @param args ignored.
     */
    public static void main(String[] args) {
        AbstractArtDrawing assingment = new AbstractArtDrawing();
        assingment.drawRandomLines();
    }
}
