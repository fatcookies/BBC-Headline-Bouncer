import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * User: fc
 * Date: 12/10/13
 */
public class BBCNewsScraper {

    public static final String BBC_LINK = "http://api.bbcnews.appengine.co.uk/stories/headlines";


    public ArrayList<BBCNewsStory> getStories() {
        String json = "";
        try {
            json = new URLGrabber(BBC_LINK).getContents();
        } catch (Exception e) {
            e.printStackTrace();
            ArrayList<BBCNewsStory> stories = new ArrayList<BBCNewsStory>() {{
                try {
                    add(new BBCNewsStory("Error loading news", "", "http://www.google.com", 0, "http://www.google.com"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }};
            return stories;
        }

        return jsonToStories(json);
    }

    private static ArrayList<BBCNewsStory> jsonToStories(String json) {
        ArrayList<BBCNewsStory> stories = new ArrayList<BBCNewsStory>();
        JSONObject json_stories = new JSONObject(json);
        JSONArray json_data = json_stories.getJSONArray("stories");

        for (int i = 0; i < json_data.length(); i++) {
            JSONObject json_story = json_data.getJSONObject(i);
            try {
                BBCNewsStory story = new BBCNewsStory(json_story.getString("title"),
                        json_story.getString("description"), json_story.getString("link"),
                        json_story.getLong("published"), json_story.getString("thumbnail"));
                stories.add(story);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stories;
    }


    class URLGrabber {
        URL link;

        public String getContents() throws Exception {
            String contents = "";

            URLConnection conn = link.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn
                    .getInputStream()));
            String current;
            while ((current = in.readLine()) != null) {
                contents += current;

            }
            in.close();
            return contents;
        }

        public URLGrabber(String url) throws Exception {
            this.link = new URL(url);
        }
    }
}
