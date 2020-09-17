import biuoop.DrawSurface;

/**
 * @author Naveh Marchoom
 */
public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity movement = new Velocity(0, 0);
    private int xMin = 0, xMax, yMin = 0, yMax;

    /**
     * Construct a new ball using an existing point, the ball's radius, and the ball's color.
     * @param center The position of the ball.
     * @param r The size of the ball.
     * @param color The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        // For in case parts of the ball are outside the bounds:
        if (center.getX() < r) {
            center = new Point(r, center.getY());
        }
        if (center.getY() < r) {
            center = new Point(center.getX(), r);
        }
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Construct a new ball using a point parameters, the ball's radius, and the ball's color.
     * @param x The position of the ball on the 'x' axis.
     * @param y The position of the ball on the 'y' axis.
     * @param r The size of the ball.
     * @param color The color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        // For in case parts of the ball are outside the bounds:
        if (x < r) {
            x = r;
        }
        if (y < r) {
            y = r;
        }
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Get the position of the ball on the 'x' axis.
     * @return the 'x' value of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the position of the ball on the 'y' axis.
     * @return the 'y' value of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the size of the ball.
     * @return the value of the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get the color of the ball.
     * @return the color value of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on a given surface.
     * @param surface the surface the ball should be drawn at.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Set the ball's velocity using an existing Velocity object.
     * @param v the ball's new velocity.
     */
    public void setVelocity(Velocity v) {
        this.movement = v;
    }

    /**
     * Set the ball's velocity using dx and dy values.
     * @param dx the ball's new 'x' value's velocity.
     * @param dy the ball's new 'y' value's velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.movement = new Velocity(dx, dy);
    }

    /**
     * Get the ball's velocity.
     * @return the ball's Velocity value.
     */
    public Velocity getVelocity() {
        return movement;
    }

    /**
     * Move the ball one step forward using the ball's velocity values.
     */
    public void moveOneStep() {
        // Swapping the dx values or dy values in case out of bounds:
        if (this.isXOutOfBounds()) {
            this.getVelocity().swapXDirection();
        }
        if (this.isYOutOfBounds()) {
            this.getVelocity().swapYDirection();
        }
        // Move the ball one step forward:
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Check if the ball is out of the horizontal boundaries.
     * @return True if the ball is out of boundaries. Else, returns false.
     */
    private boolean isXOutOfBounds() {

        // In case the ball reached the right border of the board with positive 'dx' velocity:
        if ((this.getVelocity().getDx() > 0)
                && (this.getX() + this.getVelocity().getDx() > (this.xMax - this.getSize()))) {
            return true;
        }

        // In case the ball reached the left border of the board with negative 'dx' velocity:
        if ((this.getVelocity().getDx() < 0)
                && (this.getX() + this.getVelocity().getDx() <= (this.xMin + this.getSize()))) {
            return true;
        }

        // In case the ball inside the both of the vertical borders of the board:
        return false;
    }

    /**
     * Check if the ball is out of the vertical boundaries.
     * @return True if the ball is out of boundaries. Else, returns false.
     */
    private boolean isYOutOfBounds() {
        // In case the ball reached the bottom border of the board with positive 'dy' velocity:
        if ((this.getVelocity().getDy() >= 0)
                && (this.getY() + this.getVelocity().getDy() >= (this.yMax - this.getSize()))) {
            return true;
        }

        // In case the ball reached the top border of the board with negative 'dy' velocity:
        if ((this.getVelocity().getDy() < 0)
                && (this.getY() + this.getVelocity().getDy() <= (this.yMin + this.getSize()))) {
            return true;
        }
        // In case the ball is inside both the horizontal borders of the board:
        return false;
    }

    /**
     * Set the ball boundaries using values.
     * @param x the DrawSurface width.
     * @param y the DrawSurface height.
     */
    public void setTopBoundaries(int x, int y) {
        this.xMax = x;
        this.yMax = y;
    }

    /**
     * Set the ball bottom Boundaries.
     * @param x the bottom horizontal boundary of the ball.
     * @param y the bottom vertical boundary of the ball
     */
    public void setBottomBoundaries(int x, int y) {
        this.xMin = x;
        this.yMin = y;
    }
}