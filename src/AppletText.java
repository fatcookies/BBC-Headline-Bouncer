import java.awt.*;

/**
 * User: fc
 * Date: 12/10/13
 */
public class AppletText {

    private String text;
    private Color colour;
    private Font font = null;
    private double x, y;
    private double dx, dy;

    private Environment env;
    private Graphics2D graphics;

    public static final Color[] COLOURS = {Color.BLUE, Color.RED, Color.ORANGE,
            Color.MAGENTA, Color.GREEN, Color.WHITE,
            Color.CYAN, Color.PINK, Color.YELLOW};

    public AppletText(Environment e, String text, double x, double y) {
        this(e, text, Color.WHITE, x, y);
    }

    public AppletText(Environment e, String text, Color colour, double x, double y) {
        this.text = text;
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.dx = e.getBounce() * Math.random();
        this.dy = e.getGravity();
        this.env = e;
    }

    public void setFont(Font f) {
        this.font = f;
    }

    private double calculateBounceValue() {
        return env.getBounce() * Math.random() * (Math.random() > 0.5 ? 1 : -1);
    }

    public void draw(Graphics2D g) {
        graphics = g;

        g.setColor(colour);
        if (font != null) {
            g.setFont(font);
        }

        if (calculateBounce(g)) { // if text bounces, select a random colour from list
            colour = COLOURS[(int) (Math.random() * COLOURS.length)];
            g.setColor(colour);
        }

        x += dx;
        y += dy;
        g.drawString(text, (int) x, (int) y);
    }

    public Rectangle getBounds() {
        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
        int stringLength = metrics.stringWidth(text);
        int stringHeight = metrics.getHeight();

        return new Rectangle((int) x, (int) y, stringLength, stringHeight);
    }


    private boolean calculateBounce(Graphics2D g) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int stringLength = metrics.stringWidth(text);
        int stringHeight = metrics.getHeight();

        if (x < 0) {
            x = 0.1;
            dx *= -1;
            dy = calculateBounceValue();
            return true;
        }

        if (y < 0) {
            y = 0.1;
            dy *= -1;
            dx = calculateBounceValue();
            return true;
        }

        if (x > env.getX() - stringLength) {
            x = env.getX() - 0.1 - stringLength;
            dx *= -1;
            dy = calculateBounceValue();
            return true;
        }

        if (y > env.getY() - stringHeight) {
            y = env.getY() - 0.1 - stringHeight;
            dy *= -1;
            dx = calculateBounceValue();
            return true;
        }

        return false;
    }
}
