import java.awt.*;

/**
 * User: fc
 * Date: 12/10/13
 */
public class Environment {

    private int x;
    private int y;
    private double gravity;
    private double bounce;
    private Color background;

    public Environment(int x,int y, double gravity, double bounce, Color background) {
        this.x = x;
        this.y = y;
        this.gravity = gravity;
        this.bounce = bounce;
        this.background = background;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getGravity() {
        return gravity;
    }

    public double getBounce() {
        return bounce;
    }

    public Color getBackground() {
        return background;
    }
}
