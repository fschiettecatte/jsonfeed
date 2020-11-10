/**
 * DefaultAttachmentTest.java
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
 * Default Attachment tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.5.0
 */
public class DefaultAttachmentTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
            "\"mime_type\": \"video/x-m4v\"," +
            "\"title\": \"Dalek Invasion\"," +
            "\"size_in_bytes\": 100," +
            "\"duration_in_seconds\": 200," +
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

        Attachment attachment = DefaultAttachment.fromString(DefaultAttachmentTest.TEST_STRING_1_X);

        assertNotNull(attachment);
        assertTrue(attachment.isValid());

        assertEquals(attachment.getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachment.getMimeType(), "video/x-m4v");
        assertEquals(attachment.getTitle(), "Dalek Invasion");
        assertEquals(attachment.getSizeInBytes().intValue(), 100);
        assertEquals(attachment.getDurationInSeconds().intValue(), 200);

        assertNotNull(attachment.getExtensionsJSONObject());
        assertNotNull(attachment.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)attachment.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)attachment.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)attachment.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)attachment.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)attachment.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(attachment.toJSONString());

    }


    /**
     * Test 2
     */
    @Test
    public void test2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("url", "https://ham.org/dalekInvasion.m4v")
                .put("mime_type", "video/x-m4v")
                .put("title", "Dalek Invasion")
                .put("size_in_bytes", 100)
                .put("duration_in_seconds", 200);

        JSONObject jsonObject2 = new JSONObject()
                .put("url", "https://ham.org/doctorRevenge.m4v")
                .put("mime_type", "video/x-m4v")
                .put("title", "Doctor's Revenge")
                .put("size_in_bytes", 300)
                .put("duration_in_seconds", 400);

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Attachment> attachmentList = DefaultAttachment.fromJsonArray(jsonArray);

        assertNotNull(attachmentList);
        assertTrue(attachmentList.size() == 2);
        assertTrue(attachmentList.get(0).isValid());
        assertTrue(attachmentList.get(1).isValid());

        assertEquals(attachmentList.get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachmentList.get(0).getMimeType(), "video/x-m4v");
        assertEquals(attachmentList.get(0).getTitle(), "Dalek Invasion");
        assertEquals(attachmentList.get(0).getSizeInBytes().intValue(), 100);
        assertEquals(attachmentList.get(0).getDurationInSeconds().intValue(), 200);

        assertEquals(attachmentList.get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(attachmentList.get(1).getMimeType(), "video/x-m4v");
        assertEquals(attachmentList.get(1).getTitle(), "Doctor's Revenge");
        assertEquals(attachmentList.get(1).getSizeInBytes().intValue(), 300);
        assertEquals(attachmentList.get(1).getDurationInSeconds().intValue(), 400);

    }


    /**
     * Test 3
     */
    @Test
    public void test3() throws MalformedURLException {

        Attachment attachment = new DefaultAttachment(new URL("https://ham.org/dalekInvasion.m4v"), "video/x-m4v");

        assertTrue(attachment.isValid());

        assertEquals(attachment.getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachment.getMimeType(), "video/x-m4v");

        assertNotNull(attachment.toJSONString());

    }


    /**
     * Test 4
     */
    @Test
    public void test4() throws MalformedURLException {

        Attachment attachment = new DefaultAttachment()
                .setUrl(new URL("https://ham.org/dalekInvasion.m4v"))
                .setMimeType("video/x-m4v")
                .setSizeInBytes(300)
                .setDurationInSeconds(400);

        JSONObject jsonObject = new JSONObject()
                .put("about", "https://blueshed-podcasts.com/json-feed-extension-docs")
                .put("explicit", false)
                .put("copyright", "1948 by George Orwell")
                .put("owner", "Big Brother and the Holding Company")
                .put("subtitle", "All shouting, all the time. Double. Plus. Good.");
        attachment.setExtensionsJSONObject(new JSONObject().put("_blue_shed", jsonObject));

        assertTrue(attachment.isValid());

        assertEquals(attachment.getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachment.getMimeType(), "video/x-m4v");
        assertEquals(attachment.getSizeInBytes().intValue(), 300);
        assertEquals(attachment.getDurationInSeconds().intValue(), 400);

        assertNotNull(attachment.getExtensionsJSONObject());
        assertNotNull(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed"));
        assertEquals(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed").get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed").get("explicit"), false);
        assertEquals(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed").get("copyright"), "1948 by George Orwell");
        assertEquals(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed").get("owner"), "Big Brother and the Holding Company");
        assertEquals(attachment.getExtensionsJSONObject().getJSONObject("_blue_shed").get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(attachment.toJSONString());

    }


    /**
     * Test 5
     */
    @Test
    public void test5() throws MalformedURLException {

        Attachment attachment = new DefaultAttachment();
        assertFalse(attachment.isValid());

        attachment = new DefaultAttachment();
        attachment.setUrl(new URL("https://ham.org/dalekInvasion.m4v"));
        assertFalse(attachment.isValid());

        attachment = new DefaultAttachment();
        attachment.setMimeType("video/x-m4v");
        assertFalse(attachment.isValid());

    }


}
