/**
 *
 * @author dell
 *
 */
public class Point {

    private double x, y;

    /**
     * An object consisting of x and y coordinates on the horizonal field.
     * @param x - the point's x parameter.
     * @param y - the point's y parameter.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculated the distance between this point and the other point.
     * @param other - the other point.
     * @return The distance between the points.
     */
    public double distance(Point other) {
        double x1 = this.x, x2 = other.getX(), y1 = this.y, y2 = other.getY();
        double distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return distance;
    }

    /**
     * Check if the two points are equal to each other.
     * @param other - the other point
     * @return True if both points have same coordinates, else returns false.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * Get the x coordinate of the point.
     * @return The x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the point.
     * @return The y coordinate.
     */
    public double getY() {
        return this.y;
    }
}
