import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * User: fc
 * Date: 12/10/13
 */
public class Main extends Applet implements MouseListener {

    private static final int FRAME_PERIOD = (int) 1000 / 120;
    private static final int SIZE_X = 640;
    private static final int SIZE_Y = 480;

    private Image image;
    private Timer timer;
    private ArrayList<AppletText> texts;
    ArrayList<BBCNewsStory> newsStories;
    private Environment env;

    @Override
    public void start() {
        final Graphics g = this.getGraphics();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        paint(g);
                    }
                },
                0, FRAME_PERIOD
        );

    }


    @Override
    public void init() {
        env = new Environment(SIZE_X, SIZE_Y, 1, 0.5, Color.BLACK);
        this.setBackground(env.getBackground());
        image = createImage(SIZE_X, SIZE_Y);

        texts = new ArrayList<AppletText>();

        Thread t = new Thread() {
            public void run() {
                newsStories = new BBCNewsScraper().getStories();

                for (int i = 0; i < 10; i++) { // limiting to top 10 stories
                    texts.add(new AppletText(env, newsStories.get(i).getTitle(), 60 * i, 30 * i));
                }
            }
        };
        t.run();

        addMouseListener(this);

    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        g2d.clearRect(0, 0, SIZE_X, SIZE_Y);
        for (AppletText text : texts) {
            text.draw(g2d);
        }
        g.drawImage(image, 0, 0, null);

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        for (int i = 0; i < texts.size(); i++) {
            // if we are clicking on a link!
            if (texts.get(i).getBounds().contains(mouseEvent.getX(), mouseEvent.getY())) {
                this.getAppletContext().showDocument(newsStories.get(i).getLink(), "_blank");
                System.out.println("clicked on " + newsStories.get(i).getLink());
                return;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
