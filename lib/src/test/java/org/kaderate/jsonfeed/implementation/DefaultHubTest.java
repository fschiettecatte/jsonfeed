/**
 * DefaultHubTest.java
 *
 * @author Francois Schiettecatte
 * @version 1.0
 *
 * Change History:
 *    - Nov 3, 2020 - File was created
 *
 * TBD:
 *    -
 *
 *
 */


/* Package location */
package org.kaderate.jsonfeed.implementation;


/* Import Java stuff */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.HashMap;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JUnit stuff */
import org.junit.Test;
import static org.junit.Assert.*;

/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Attachment;
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Feed;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.Version;
import org.kaderate.jsonfeed.implementation.DefaultAttachment;
import org.kaderate.jsonfeed.implementation.DefaultAuthor;
import org.kaderate.jsonfeed.implementation.DefaultFeed;
import org.kaderate.jsonfeed.implementation.DefaultHub;
import org.kaderate.jsonfeed.implementation.DefaultItem;



/**
 * Default Hub tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.4.0
 */
public class DefaultHubTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"type\": \"Tardis\"," +
            "\"url\": \"https://ham.org/tardis.html\"," +
            "\"_blue_shed\": { " +
                "\"about\": \"https://blueshed-podcasts.com/json-feed-extension-docs\"," +
                "\"explicit\": false," +
                "\"copyright\": \"1948 by George Orwell\"," +
                "\"owner\": \"Big Brother and the Holding Company\"," +
                "\"subtitle\": \"All shouting, all the time. Double. Plus. Good.\"" +
            "} " +
        "}";



    /**
     * Test 1
     */
    @Test
    public void test1() throws MalformedURLException {

        Hub hub = DefaultHub.fromString(DefaultHubTest.TEST_STRING_1_X);

        assertNotNull(hub);
        assertTrue(hub.isValid());

        assertEquals(hub.getType(), "Tardis");
        assertEquals(hub.getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(hub.getExtensionsJSONObject());
        assertNotNull(hub.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)hub.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)hub.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)hub.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)hub.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)hub.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(hub.toJSONString());

    }


    /**
     * Test 2
     */
    @Test
    public void test2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("type", "Tardis")
                .put("url", "https://ham.org/tardis.html");

        JSONObject jsonObject2 = new JSONObject()
                .put("type", "Timeship")
                .put("url", "https://ham.org/timeship.html");

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Hub> hubList = DefaultHub.fromJsonArray(jsonArray);

        assertNotNull(hubList);
        assertTrue(hubList.size() == 2);
        assertTrue(hubList.get(0).isValid());
        assertTrue(hubList.get(1).isValid());

        assertEquals(hubList.get(0).getType(), "Tardis");
        assertEquals(hubList.get(0).getUrl().toString(), "https://ham.org/tardis.html");

        assertEquals(hubList.get(1).getType(), "Timeship");
        assertEquals(hubList.get(1).getUrl().toString(), "https://ham.org/timeship.html");

    }


    /**
     * Test 3
     */
    @Test
    public void test3() throws MalformedURLException {

        Hub hub = new DefaultHub("Tardis", new URL("https://ham.org/tardis.html"));

        assertTrue(hub.isValid());

        assertEquals(hub.getType(), "Tardis");
        assertEquals(hub.getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(hub.toJSONString());

    }


    /**
     * Test 4
     */
    @Test
    public void test4() throws MalformedURLException {

        Hub hub = new DefaultHub();
        hub.setType("Tardis");
        hub.setUrl(new URL("https://ham.org/tardis.html"));

        JSONObject jsonObject = new JSONObject()
                .put("about", "https://blueshed-podcasts.com/json-feed-extension-docs")
                .put("explicit", false)
                .put("copyright", "1948 by George Orwell")
                .put("owner", "Big Brother and the Holding Company")
                .put("subtitle", "All shouting, all the time. Double. Plus. Good.");
        hub.setExtensionsJSONObject(new JSONObject().put("_blue_shed", jsonObject));

        assertTrue(hub.isValid());

        assertEquals(hub.getType(), "Tardis");
        assertEquals(hub.getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(hub.getExtensionsJSONObject());
        assertNotNull(hub.getExtensionsJSONObject().getJSONObject("_blue_shed"));
        assertEquals(hub.getExtensionsJSONObject().getJSONObject("_blue_shed").get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(hub.getExtensionsJSONObject().getJSONObject("_blue_shed").get("explicit"), false);
        assertEquals(hub.getExtensionsJSONObject().getJSONObject("_blue_shed").get("copyright"), "1948 by George Orwell");
        assertEquals(hub.getExtensionsJSONObject().getJSONObject("_blue_shed").get("owner"), "Big Brother and the Holding Company");
        assertEquals(hub.getExtensionsJSONObject().getJSONObject("_blue_shed").get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(hub.toJSONString());

    }


    /**
     * Test 5
     */
    @Test
    public void test5() throws MalformedURLException {

        Hub hub = new DefaultHub();
        assertFalse(hub.isValid());

        hub = new DefaultHub();
        hub.setType("Tardis");
        assertFalse(hub.isValid());

        hub = new DefaultHub();
        hub.setUrl(new URL("https://ham.org/tardis.html"));
        assertFalse(hub.isValid());

    }


}
