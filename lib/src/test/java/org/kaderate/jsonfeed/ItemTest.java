/**
 * ItemTest.java
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
import org.kaderate.jsonfeed.Version;
import org.kaderate.jsonfeed.implementation.DefaultItem;



/**
 * Item tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class ItemTest {


    private static final String TEST_STRING_1_0 = "{" +
            "\"id\": \"1\"," +
            "\"url\": \"https://ham.org/item1.html\"," +
            "\"external_url\": \"https://external.org/item1.html\"," +
            "\"title\": \"First Item\"," +
            "\"content_text\": \"This is the first item text.\"," +
            "\"content_html\": \"This is the <b>first item</b> HTML.\"," +
            "\"summary\": \"First item summary.\"," +
            "\"image\": \"https://ham.org/image1.png\"," +
            "\"banner_image\": \"https://ham.org/banner1.png\"," +
            "\"date_published\": \"2010-02-07T14:04:00-05:00\"," +
            "\"date_modified\": \"2010-02-09T14:04:00-05:00\"," +
            "\"author\": { " +
                "\"name\": \"Dalek Jast\"," +
                "\"url\": \"https://ham.org/authorJast.html\"," +
                "\"avatar\": \"https://ham.org/avatarJast.html\"" +
            "}," +
            "\"tags\": [ \"Tag one\", \"Tag two\" ]," +
            "\"attachments\": [" +
                "{" +
                    "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
                    "\"mime_type\": \"video/x-m4v\"," +
                    "\"title\": \"Dalek Invasion\"," +
                    "\"size_in_bytes\": 100," +
                    "\"duration_in_seconds\": 200" +
                "}," +
                "{" +
                    "\"url\": \"https://ham.org/doctorRevenge.m4v\"," +
                    "\"mime_type\": \"video/x-m4v\"," +
                    "\"title\": \"Doctor's Revenge\"," +
                    "\"size_in_bytes\": 300," +
                    "\"duration_in_seconds\": 400" +
                "} " +
            "]" +
        "}";



    private static final String TEST_STRING_1_1 = "{" +
            "\"id\": \"2\"," +
            "\"url\": \"https://ham.org/item2.html\"," +
            "\"external_url\": \"https://external.org/item2.html\"," +
            "\"title\": \"Second Item\"," +
            "\"content_text\": \"This is the second item text.\"," +
            "\"content_html\": \"This is the <b>second item</b> HTML.\"," +
            "\"summary\": \"Second item summary.\"," +
            "\"image\": \"https://ham.org/image2.png\"," +
            "\"banner_image\": \"https://ham.org/banner2.png\"," +
            "\"date_published\": \"2010-02-07T14:04:00-05:00\"," +
            "\"date_modified\": \"2010-02-09T14:04:00-05:00\"," +
            "\"language\": \"en-US\"," +
            "\"authors\": [" +
                "{" +
                    "\"name\": \"Dalek Jast\"," +
                    "\"url\": \"https://ham.org/authorJast.html\"," +
                    "\"avatar\": \"https://ham.org/avatarJast.html\"" +
                "}," +
                "{" +
                    "\"name\": \"Dalek Sec\"," +
                    "\"url\": \"https://ham.org/authorSec.html\"," +
                    "\"avatar\": \"https://ham.org/avatarSec.html\"" +
                "} " +
            "]," +
            "\"tags\": [ \"Tag three\", \"Tag four\" ]," +
            "\"attachments\": [" +
                "{" +
                    "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
                    "\"mime_type\": \"video/x-m4v\"," +
                    "\"title\": \"Dalek Invasion\"," +
                    "\"size_in_bytes\": 100," +
                    "\"duration_in_seconds\": 200" +
                "}," +
                "{" +
                    "\"url\": \"https://ham.org/doctorRevenge.m4v\"," +
                    "\"mime_type\": \"video/x-m4v\"," +
                    "\"title\": \"Doctor's Revenge\"," +
                    "\"size_in_bytes\": 300," +
                    "\"duration_in_seconds\": 400" +
                "} " +
            "]" +
        "}";



    /**
     * Test valid 1
     */
    @Test
    public void testValid1() throws MalformedURLException {

        Item item = DefaultItem.fromString(ItemTest.TEST_STRING_1_0);

        assertNotNull(item);
        assertTrue(item.isValid());

        assertEquals(item.getID(), "1");
        assertEquals(item.getUrl().toString(), "https://ham.org/item1.html");
        assertEquals(item.getExternalUrl().toString(), "https://external.org/item1.html");
        assertEquals(item.getTitle(), "First Item");
        assertEquals(item.getContentText(), "This is the first item text.");
        assertEquals(item.getContentHtml(), "This is the <b>first item</b> HTML.");
        assertEquals(item.getSummary(), "First item summary.");
        assertEquals(item.getImage().toString(), "https://ham.org/image1.png");
        assertEquals(item.getBannerImage().toString(), "https://ham.org/banner1.png");
        assertEquals(item.getDatePublished().toString(), "2010-02-07T19:04:00Z");
        assertEquals(item.getDateModified().toString(), "2010-02-09T19:04:00Z");

        assertNotNull(item.getAuthor());
        assertEquals(item.getAuthor().getName(), "Dalek Jast");
        assertEquals(item.getAuthor().getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthor().getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertNotNull(item.getTagList());
        assertTrue(item.getTagList().size() == 2);
        assertEquals(item.getTagList().get(0), "Tag one");
        assertEquals(item.getTagList().get(1), "Tag two");

        assertNotNull(item.getAttachmentList());
        assertTrue(item.getAttachmentList().size() == 2);
        assertTrue(item.getAttachmentList().get(0).isValid());
        assertTrue(item.getAttachmentList().get(1).isValid());

        assertEquals(item.getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(item.getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(item.getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(item.getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertEquals(item.getAttachmentList().get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(item.getAttachmentList().get(1).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(1).getTitle(), "Doctor's Revenge");
        assertEquals(item.getAttachmentList().get(1).getSizeInBytes().intValue(), 300);
        assertEquals(item.getAttachmentList().get(1).getDurationInSeconds().intValue(), 400);

        assertNotNull(item.toJSONString());

    }


    /**
     * Test valid 2
     */
    @Test
    public void testValid2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("id", "1")
                .put("url", "https://ham.org/item1.html")
                .put("external_url", "https://external.org/item1.html")
                .put("title", "First Item")
                .put("content_text", "This is the first item text.")
                .put("content_html", "This is the <b>first item</b> HTML.")
                .put("summary", "First item summary.")
                .put("image", "https://ham.org/image1.png")
                .put("banner_image", "https://ham.org/banner1.png")
                .put("date_published", "2010-02-07T14:04:00-05:00")
                .put("date_modified", "2010-02-09T14:04:00-05:00");

        JSONObject jsonObject2 = new JSONObject()
                .put("id", "2")
                .put("url", "https://ham.org/item2.html")
                .put("external_url", "https://external.org/item2.html")
                .put("title", "Second Item")
                .put("content_text", "This is the second item text.")
                .put("content_html", "This is the <b>second item</b> HTML.")
                .put("summary", "Second item summary.")
                .put("image", "https://ham.org/image2.png")
                .put("banner_image", "https://ham.org/banner2.png")
                .put("date_published", "2010-02-11T14:04:00-05:00")
                .put("date_modified", "2010-02-13T14:04:00-05:00");

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Item> itemList = DefaultItem.fromJsonArray(jsonArray);

        assertNotNull(itemList);
        assertTrue(itemList.size() == 2);
        assertTrue(itemList.get(0).isValid());
        assertTrue(itemList.get(1).isValid());

        assertEquals(itemList.get(0).getID(), "1");
        assertEquals(itemList.get(0).getUrl().toString(), "https://ham.org/item1.html");
        assertEquals(itemList.get(0).getExternalUrl().toString(), "https://external.org/item1.html");
        assertEquals(itemList.get(0).getTitle(), "First Item");
        assertEquals(itemList.get(0).getContentText(), "This is the first item text.");
        assertEquals(itemList.get(0).getContentHtml(), "This is the <b>first item</b> HTML.");
        assertEquals(itemList.get(0).getSummary(), "First item summary.");
        assertEquals(itemList.get(0).getImage().toString(), "https://ham.org/image1.png");
        assertEquals(itemList.get(0).getBannerImage().toString(), "https://ham.org/banner1.png");
        assertEquals(itemList.get(0).getDatePublished().toString(), "2010-02-07T19:04:00Z");
        assertEquals(itemList.get(0).getDateModified().toString(), "2010-02-09T19:04:00Z");
        assertNotNull(itemList.get(0).toJSONString());

        assertEquals(itemList.get(1).getID(), "2");
        assertEquals(itemList.get(1).getUrl().toString(), "https://ham.org/item2.html");
        assertEquals(itemList.get(1).getExternalUrl().toString(), "https://external.org/item2.html");
        assertEquals(itemList.get(1).getTitle(), "Second Item");
        assertEquals(itemList.get(1).getContentText(), "This is the second item text.");
        assertEquals(itemList.get(1).getContentHtml(), "This is the <b>second item</b> HTML.");
        assertEquals(itemList.get(1).getSummary(), "Second item summary.");
        assertEquals(itemList.get(1).getImage().toString(), "https://ham.org/image2.png");
        assertEquals(itemList.get(1).getBannerImage().toString(), "https://ham.org/banner2.png");
        assertEquals(itemList.get(1).getDatePublished().toString(), "2010-02-11T19:04:00Z");
        assertEquals(itemList.get(1).getDateModified().toString(), "2010-02-13T19:04:00Z");
        assertNotNull(itemList.get(1).toJSONString());

        assertNotNull(jsonArray.toString());

    }


    /**
     * Test valid 3
     */
    @Test
    public void testValid3() throws MalformedURLException {

        Item item = DefaultItem.fromString(ItemTest.TEST_STRING_1_1);

        assertNotNull(item);
        assertTrue(item.isValid());

        assertEquals(item.getID(), "2");
        assertEquals(item.getUrl().toString(), "https://ham.org/item2.html");
        assertEquals(item.getExternalUrl().toString(), "https://external.org/item2.html");
        assertEquals(item.getTitle(), "Second Item");
        assertEquals(item.getContentText(), "This is the second item text.");
        assertEquals(item.getContentHtml(), "This is the <b>second item</b> HTML.");
        assertEquals(item.getSummary(), "Second item summary.");
        assertEquals(item.getImage().toString(), "https://ham.org/image2.png");
        assertEquals(item.getBannerImage().toString(), "https://ham.org/banner2.png");
        assertEquals(item.getDatePublished().toString(), "2010-02-07T19:04:00Z");
        assertEquals(item.getDateModified().toString(), "2010-02-09T19:04:00Z");
        assertEquals(item.getLanguage(), "en-US");

        assertNotNull(item.getAuthorList());
        assertEquals(item.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(item.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertEquals(item.getAuthorList().get(1).getName(), "Dalek Sec");
        assertEquals(item.getAuthorList().get(1).getUrl().toString(), "https://ham.org/authorSec.html");
        assertEquals(item.getAuthorList().get(1).getAvatar().toString(), "https://ham.org/avatarSec.html");

        assertNotNull(item.getTagList());
        assertTrue(item.getTagList().size() == 2);
        assertEquals(item.getTagList().get(0), "Tag three");
        assertEquals(item.getTagList().get(1), "Tag four");

        assertNotNull(item.getAttachmentList());
        assertTrue(item.getAttachmentList().size() == 2);
        assertTrue(item.getAttachmentList().get(0).isValid());
        assertTrue(item.getAttachmentList().get(1).isValid());

        assertEquals(item.getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(item.getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(item.getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(item.getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertEquals(item.getAttachmentList().get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(item.getAttachmentList().get(1).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(1).getTitle(), "Doctor's Revenge");
        assertEquals(item.getAttachmentList().get(1).getSizeInBytes().intValue(), 300);
        assertEquals(item.getAttachmentList().get(1).getDurationInSeconds().intValue(), 400);

        assertNotNull(item.toJSONString());

    }


    /**
     * Test valid 4
     */
    @Test
    public void testValid4() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("id", "1")
                .put("url", "https://ham.org/item1.html")
                .put("external_url", "https://external.org/item1.html")
                .put("title", "First Item")
                .put("content_text", "This is the first item text.")
                .put("content_html", "This is the <b>first item</b> HTML.")
                .put("summary", "First item summary.")
                .put("image", "https://ham.org/image1.png")
                .put("banner_image", "https://ham.org/banner1.png")
                .put("date_published", "2010-02-07T14:04:00-05:00")
                .put("date_modified", "2010-02-09T14:04:00-05:00")
                .put("language", "en-US");

        JSONObject jsonObject2 = new JSONObject()
                .put("id", "2")
                .put("url", "https://ham.org/item2.html")
                .put("external_url", "https://external.org/item2.html")
                .put("title", "Second Item")
                .put("content_text", "This is the second item text.")
                .put("content_html", "This is the <b>second item</b> HTML.")
                .put("summary", "Second item summary.")
                .put("image", "https://ham.org/image2.png")
                .put("banner_image", "https://ham.org/banner2.png")
                .put("date_published", "2010-02-11T14:04:00-05:00")
                .put("date_modified", "2010-02-13T14:04:00-05:00")
                .put("language", "en-US");

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Item> itemList = DefaultItem.fromJsonArray(jsonArray);

        assertNotNull(itemList);
        assertTrue(itemList.size() == 2);
        assertTrue(itemList.get(0).isValid());
        assertTrue(itemList.get(1).isValid());

        assertEquals(itemList.get(0).getID(), "1");
        assertEquals(itemList.get(0).getUrl().toString(), "https://ham.org/item1.html");
        assertEquals(itemList.get(0).getExternalUrl().toString(), "https://external.org/item1.html");
        assertEquals(itemList.get(0).getTitle(), "First Item");
        assertEquals(itemList.get(0).getContentText(), "This is the first item text.");
        assertEquals(itemList.get(0).getContentHtml(), "This is the <b>first item</b> HTML.");
        assertEquals(itemList.get(0).getSummary(), "First item summary.");
        assertEquals(itemList.get(0).getImage().toString(), "https://ham.org/image1.png");
        assertEquals(itemList.get(0).getBannerImage().toString(), "https://ham.org/banner1.png");
        assertEquals(itemList.get(0).getDatePublished().toString(), "2010-02-07T19:04:00Z");
        assertEquals(itemList.get(0).getDateModified().toString(), "2010-02-09T19:04:00Z");
        assertEquals(itemList.get(0).getLanguage(), "en-US");
        assertNotNull(itemList.get(0).toJSONString());

        assertEquals(itemList.get(1).getID(), "2");
        assertEquals(itemList.get(1).getUrl().toString(), "https://ham.org/item2.html");
        assertEquals(itemList.get(1).getExternalUrl().toString(), "https://external.org/item2.html");
        assertEquals(itemList.get(1).getTitle(), "Second Item");
        assertEquals(itemList.get(1).getContentText(), "This is the second item text.");
        assertEquals(itemList.get(1).getContentHtml(), "This is the <b>second item</b> HTML.");
        assertEquals(itemList.get(1).getSummary(), "Second item summary.");
        assertEquals(itemList.get(1).getImage().toString(), "https://ham.org/image2.png");
        assertEquals(itemList.get(1).getBannerImage().toString(), "https://ham.org/banner2.png");
        assertEquals(itemList.get(1).getDatePublished().toString(), "2010-02-11T19:04:00Z");
        assertEquals(itemList.get(1).getDateModified().toString(), "2010-02-13T19:04:00Z");
        assertEquals(itemList.get(1).getLanguage(), "en-US");
        assertNotNull(itemList.get(1).toJSONString());

        assertNotNull(jsonArray.toString());

    }


    /**
     * Test valid 5
     */
    @Test
    public void testValid5() throws MalformedURLException {

        Item item = DefaultItem.fromString(ItemTest.TEST_STRING_1_0);

        assertNotNull(item);
        assertTrue(item.isValid());

        assertNotNull(item.getAuthor());
        assertEquals(item.getAuthor().getName(), "Dalek Jast");
        assertEquals(item.getAuthor().getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthor().getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertTrue(((DefaultItem)item).upgrade(Version.VERSION_1_0, Version.VERSION_LATEST));
        assertTrue(item.isValid());

        assertNotNull(item.getAuthorList());
        assertEquals(item.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(item.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertNull(item.getAuthor());


    }


}
