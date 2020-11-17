/**
 * DefaultAuthorTest.java
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
 * Default Author tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public class DefaultAuthorTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"name\": \"Dalek Caan\"," +
            "\"url\": \"https://ham.org/authorCaan.html\"," +
            "\"avatar\": \"https://ham.org/avatarCaan.html\"," +
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

        Author author = DefaultAuthor.fromString(DefaultAuthorTest.TEST_STRING_1_X);

        assertNotNull(author);
        assertTrue(author.isValid());

        assertEquals(author.getName(), "Dalek Caan");
        assertEquals(author.getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(author.getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(author.getExtensionsJSONObject());
        assertNotNull(author.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)author.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)author.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)author.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)author.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)author.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(author.toJSONString());

    }


    /**
     * Test 2
     */
    @Test
    public void test2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("name", "Dalek Caan")
                .put("url", "https://ham.org/authorCaan.html")
                .put("avatar", "https://ham.org/avatarCaan.html");

        JSONObject jsonObject2 = new JSONObject()
                .put("name", "Dalek Jast")
                .put("url", "https://ham.org/authorJast.html")
                .put("avatar", "https://ham.org/avatarJast.html");

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Author> authorList = DefaultAuthor.fromJsonArray(jsonArray);

        assertNotNull(authorList);
        assertTrue(authorList.size() == 2);
        assertTrue(authorList.get(0).isValid());
        assertTrue(authorList.get(1).isValid());

        assertEquals(authorList.get(0).getName(), "Dalek Caan");
        assertEquals(authorList.get(0).getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(authorList.get(0).getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertEquals(authorList.get(1).getName(), "Dalek Jast");
        assertEquals(authorList.get(1).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(authorList.get(1).getAvatar().toString(), "https://ham.org/avatarJast.html");

    }


    /**
     * Test 3
     */
    @Test
    public void test3() throws MalformedURLException {

        Author author = new DefaultAuthor("Dalek Caan", new URL("https://ham.org/authorCaan.html"), new URL("https://ham.org/avatarCaan.html"));

        assertTrue(author.isValid());

        assertEquals(author.getName(), "Dalek Caan");
        assertEquals(author.getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(author.getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(author.toJSONString());

    }


    /**
     * Test 4
     */
    @Test
    public void test4() throws MalformedURLException {

        Author author = new DefaultAuthor()
                .setName("Dalek Caan")
                .setUrl(new URL("https://ham.org/authorCaan.html"))
                .setAvatar(new URL("https://ham.org/avatarCaan.html"));

        JSONObject jsonObject = new JSONObject()
                .put("about", "https://blueshed-podcasts.com/json-feed-extension-docs")
                .put("explicit", false)
                .put("copyright", "1948 by George Orwell")
                .put("owner", "Big Brother and the Holding Company")
                .put("subtitle", "All shouting, all the time. Double. Plus. Good.");
        author.setExtensionsJSONObject(new JSONObject().put("_blue_shed", jsonObject));

        assertTrue(author.isValid());

        assertEquals(author.getName(), "Dalek Caan");
        assertEquals(author.getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(author.getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(author.getExtensionsJSONObject());
        assertNotNull(author.getExtensionsJSONObject().getJSONObject("_blue_shed"));
        assertEquals(author.getExtensionsJSONObject().getJSONObject("_blue_shed").get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(author.getExtensionsJSONObject().getJSONObject("_blue_shed").get("explicit"), false);
        assertEquals(author.getExtensionsJSONObject().getJSONObject("_blue_shed").get("copyright"), "1948 by George Orwell");
        assertEquals(author.getExtensionsJSONObject().getJSONObject("_blue_shed").get("owner"), "Big Brother and the Holding Company");
        assertEquals(author.getExtensionsJSONObject().getJSONObject("_blue_shed").get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(author.toJSONString());

    }


    /**
     * Test 5
     */
    @Test
    public void test5() {

        Author author = new DefaultAuthor();
        assertFalse(author.isValid());

    }


}
