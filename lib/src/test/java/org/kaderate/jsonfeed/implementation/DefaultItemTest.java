/**
 * DefaultItemTest.java
 *
 * @item Francois Schiettecatte
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
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
 * Default Item tests
 *
 * @item François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.5.0
 */
public class DefaultItemTest {


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
            "]," +
            "\"_blue_shed\": { " +
                "\"about\": \"https://blueshed-podcasts.com/json-feed-extension-docs\"," +
                "\"explicit\": false," +
                "\"copyright\": \"1948 by George Orwell\"," +
                "\"owner\": \"Big Brother and the Holding Company\"," +
                "\"subtitle\": \"All shouting, all the time. Double. Plus. Good.\"" +
            "} " +
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
            "]," +
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

        Item item = DefaultItem.fromString(DefaultItemTest.TEST_STRING_1_0);

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
        assertTrue(item.getAuthor().isValid());
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
        assertEquals(item.getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(item.getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(item.getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(item.getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertTrue(item.getAttachmentList().get(1).isValid());
        assertEquals(item.getAttachmentList().get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(item.getAttachmentList().get(1).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(1).getTitle(), "Doctor's Revenge");
        assertEquals(item.getAttachmentList().get(1).getSizeInBytes().intValue(), 300);
        assertEquals(item.getAttachmentList().get(1).getDurationInSeconds().intValue(), 400);

        assertNotNull(item.getExtensionsJSONObject());
        assertNotNull(item.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(item.toJSONString());

    }


    /**
     * Test 2
     */
    @Test
    public void test2() throws MalformedURLException {

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

        assertTrue(itemList.get(1).isValid());
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

    }


    /**
     * Test 3
     */
    @Test
    public void test3() throws MalformedURLException {

        Item item = DefaultItem.fromString(DefaultItemTest.TEST_STRING_1_1);

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
        assertTrue(item.getAuthorList().get(0).isValid());
        assertEquals(item.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(item.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertTrue(item.getAuthorList().get(1).isValid());
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
        assertEquals(item.getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(item.getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(item.getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(item.getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertTrue(item.getAttachmentList().get(1).isValid());
        assertEquals(item.getAttachmentList().get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(item.getAttachmentList().get(1).getMimeType(), "video/x-m4v");
        assertEquals(item.getAttachmentList().get(1).getTitle(), "Doctor's Revenge");
        assertEquals(item.getAttachmentList().get(1).getSizeInBytes().intValue(), 300);
        assertEquals(item.getAttachmentList().get(1).getDurationInSeconds().intValue(), 400);

        assertNotNull(item.getExtensionsJSONObject());
        assertNotNull(item.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)item.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(item.toJSONString());

    }


    /**
     * Test 4
     */
    @Test
    public void test4() throws MalformedURLException {

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

        assertTrue(itemList.get(1).isValid());
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

    }


    /**
     * Test 5
     */
    @Test
    public void test5() throws MalformedURLException {

        Item item = DefaultItem.fromString(DefaultItemTest.TEST_STRING_1_0);

        assertNotNull(item);
        assertTrue(item.isValid());

        assertNotNull(item.getAuthor());
        assertEquals(item.getAuthor().getName(), "Dalek Jast");
        assertEquals(item.getAuthor().getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthor().getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertTrue(((DefaultItem)item).upgrade(Version.VERSION_1_1));
        assertTrue(item.isValid());

        assertNotNull(item.getAuthorList());
        assertEquals(item.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(item.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertNull(item.getAuthor());


    }


    /**
     * Test 6
     */
    @Test
    public void test6() throws MalformedURLException {

        Item item = new DefaultItem("1");

        assertTrue(item.isValid());

        assertEquals(item.getID(), "1");

        assertNotNull(item.toJSONString());

    }


    /**
     * Test 7
     */
    @Test
    public void test7() throws MalformedURLException {

        Item item = new DefaultItem("1")
                .setUrl(new URL("https://ham.org/item1.html"))
                .setExternalUrl(new URL("https://external.org/item1.html"))
                .setTitle("First Item")
                .setContentText("This is the first item text.")
                .setContentHtml("This is the <b>first item</b> HTML.")
                .setSummary("First item summary.")
                .setImage(new URL("https://ham.org/image1.png"))
                .setBannerImage(new URL("https://ham.org/banner1.png"))
                .setDatePublished(OffsetDateTime.parse("2010-02-07T14:04:00-05:00").toInstant())
                .setDateModified(OffsetDateTime.parse("2010-02-13T14:04:00-05:00").toInstant())
                .setLanguage("en-US");

        List<Author> authorList = new ArrayList<Author>();
        authorList.add(new DefaultAuthor("Dalek Jast", new URL("https://ham.org/authorJast.html"), new URL("https://ham.org/avatarJast.html")));
        authorList.add(new DefaultAuthor("Dalek Sec", new URL("https://ham.org/authorSec.html"), new URL("https://ham.org/avatarSec.html")));
        item.setAuthorList(authorList);

        List<String> tagList = new ArrayList<String>();
        tagList.add("Tag One");
        tagList.add("Tag Two");
        item.setTagList(tagList);

        List<Attachment> attachmentList = new ArrayList<Attachment>();
        attachmentList.add(new DefaultAttachment(new URL("https://ham.org/dalekInvasion.m4v"), "video/x-m4v"));
        attachmentList.add(new DefaultAttachment(new URL("https://ham.org/doctorRevenge.m4v"), "video/x-m4v"));
        item.setAttachmentList(attachmentList);

        JSONObject jsonObject = new JSONObject()
                .put("about", "https://blueshed-podcasts.com/json-feed-extension-docs")
                .put("explicit", false)
                .put("copyright", "1948 by George Orwell")
                .put("owner", "Big Brother and the Holding Company")
                .put("subtitle", "All shouting, all the time. Double. Plus. Good.");
        item.setExtensionsJSONObject(new JSONObject().put("_blue_shed", jsonObject));

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
        assertEquals(item.getDateModified().toString(), "2010-02-13T19:04:00Z");
        assertEquals(item.getLanguage(), "en-US");

        assertNotNull(item.getAuthorList());
        assertTrue(item.getAuthorList().size() == 2);
        assertTrue(item.getAuthorList().get(0).isValid());
        assertEquals(item.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(item.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(item.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertTrue(item.getAuthorList().get(1).isValid());
        assertEquals(item.getAuthorList().get(1).getName(), "Dalek Sec");
        assertEquals(item.getAuthorList().get(1).getUrl().toString(), "https://ham.org/authorSec.html");
        assertEquals(item.getAuthorList().get(1).getAvatar().toString(), "https://ham.org/avatarSec.html");

        assertNotNull(item.getTagList());
        assertTrue(item.getTagList().size() == 2);
        assertNotNull(item.getTagList().get(0), "Tag One");
        assertNotNull(item.getTagList().get(1), "Tag Two");

        assertNotNull(item.getAttachmentList());
        assertTrue(item.getAttachmentList().size() == 2);
        assertTrue(item.getAttachmentList().get(0).isValid());
        assertEquals(item.getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(item.getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertTrue(item.getAttachmentList().get(1).isValid());
        assertEquals(item.getAttachmentList().get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(item.getAttachmentList().get(1).getMimeType(), "video/x-m4v");

        assertNotNull(item.getExtensionsJSONObject());
        assertNotNull(item.getExtensionsJSONObject().getJSONObject("_blue_shed"));
        assertEquals(item.getExtensionsJSONObject().getJSONObject("_blue_shed").get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(item.getExtensionsJSONObject().getJSONObject("_blue_shed").get("explicit"), false);
        assertEquals(item.getExtensionsJSONObject().getJSONObject("_blue_shed").get("copyright"), "1948 by George Orwell");
        assertEquals(item.getExtensionsJSONObject().getJSONObject("_blue_shed").get("owner"), "Big Brother and the Holding Company");
        assertEquals(item.getExtensionsJSONObject().getJSONObject("_blue_shed").get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(item.toJSONString());

    }


    /**
     * Test 8
     */
    @Test
    public void test8() throws MalformedURLException {

        Item item = new DefaultItem();
        assertFalse(item.isValid());

    }


}
