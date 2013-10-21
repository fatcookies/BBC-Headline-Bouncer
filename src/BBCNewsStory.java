/**
 * User: fc
 * Date: 12/10/13
 */

import java.net.URL;


public class BBCNewsStory extends AppletHyperLink {


    private String description;
    private long published;
    private URL thumbnail;

    public BBCNewsStory(String title, String description, String link,
                        long published, String thumbnail) {
        super(title, link);
        try {
            this.thumbnail = new URL(thumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.description = description;
        this.published = published;


    }

    public String getDescription() {
        return description;
    }

    public long getPublished() {
        return published;
    }

    public URL getThumbnail() {
        return thumbnail;
    }
}
