/**
 * HubTest.java
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
package org.kaderate.jsonfeed;


/* Import Java stuff */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JUnit stuff */
import org.junit.Test;
import static org.junit.Assert.*;

/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.implementation.DefaultHub;



/**
 * Hub tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class HubTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"type\": \"Tardis\"," +
            "\"url\": \"https://ham.org/tardis.html\"" +
        "}";



    /**
     * Test 1
     */
    @Test
    public void test1() throws MalformedURLException {

        Hub hub = DefaultHub.fromString(HubTest.TEST_STRING_1_X);

        assertNotNull(hub);
        assertTrue(hub.isValid());

        assertEquals(hub.getType(), "Tardis");
        assertEquals(hub.getUrl().toString(), "https://ham.org/tardis.html");

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
        assertNotNull(hubList.get(0).toJSONString());

        assertEquals(hubList.get(1).getType(), "Timeship");
        assertEquals(hubList.get(1).getUrl().toString(), "https://ham.org/timeship.html");
        assertNotNull(hubList.get(1).toJSONString());

        assertNotNull(jsonArray.toString());

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

        assertTrue(hub.isValid());

        assertEquals(hub.getType(), "Tardis");
        assertEquals(hub.getUrl().toString(), "https://ham.org/tardis.html");

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
