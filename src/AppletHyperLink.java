import java.net.URL;

/**
 * User: fc
 * Date: 17/10/13
 */
public abstract class AppletHyperLink {

    protected String text;
    protected URL link;

    public AppletHyperLink(String text, String link) {
        this.text = text;
        try {
            this.link = new URL(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public URL getLink() {
        return link;
    }
}
