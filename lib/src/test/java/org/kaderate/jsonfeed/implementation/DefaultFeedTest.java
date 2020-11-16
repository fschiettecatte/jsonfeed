/**
 * DefaultFeedTest.java
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
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.StringBuilder;
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
 * Default Feed tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.6.0
 */
public class DefaultFeedTest {


    private static final String TEST_STRING_1_0 = "{" +
            "\"version\": \"https://jsonfeed.org/version/1.0\"," +
            "\"title\": \"Feed Feed\"," +
            "\"home_page_url\": \"https://ham.org/\"," +
            "\"feed_url\": \"https://ham.org/feed.json\"," +
            "\"description\": \"Ham Feed Description\"," +
            "\"user_comment\": \"Ham Feed User Comment\"," +
            "\"next_url\": \"https://ham.org/feed.json?page=2\"," +
            "\"icon\": \"https://ham.org/icon.jpg\"," +
            "\"favicon\": \"https://ham.org/favicon.jpg\"," +
            "\"expired\": true," +
            "\"author\": { " +
                "\"name\": \"Dalek Caan\"," +
                "\"url\": \"https://ham.org/authorCaan.html\"," +
                "\"avatar\": \"https://ham.org/avatarCaan.html\"" +
            "}, " +
            "\"items\": [ "+
                "{" +
                    "\"id\": \"2\"," +
                    "\"content_text\": \"This is a second item.\"," +
                    "\"url\": \"https://example.org/second-item\"," +
                    "\"author\": { " +
                        "\"name\": \"Dalek Jast\"," +
                        "\"url\": \"https://ham.org/authorJast.html\"," +
                        "\"avatar\": \"https://ham.org/avatarJast.html\"" +
                    "}," +
                    "\"attachments\": [" +
                        "{" +
                            "\"url\": \"https://ham.org/doctorRevenge.m4v\"," +
                            "\"mime_type\": \"video/x-m4v\"," +
                            "\"title\": \"Doctor's Revenge\"," +
                            "\"size_in_bytes\": 300," +
                            "\"duration_in_seconds\": 400" +
                        "} " +
                    "] " +
                "}, " +
                "{ "+
                    "\"id\": \"1\", " +
                    "\"content_html\": \"<p>Hello, world!</p>\", " +
                    "\"url\": \"https://example.org/initial-post\"," +
                    "\"author\": { " +
                        "\"name\": \"Dalek Thay\"," +
                        "\"url\": \"https://ham.org/authorThay.html\"," +
                        "\"avatar\": \"https://ham.org/avatarThay.html\"" +
                    "}," +
                    "\"attachments\": [" +
                        "{" +
                            "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
                            "\"mime_type\": \"video/x-m4v\"," +
                            "\"title\": \"Dalek Invasion\"," +
                            "\"size_in_bytes\": 100," +
                            "\"duration_in_seconds\": 200" +
                        "} " +
                    "] " +
                "} " +
            "], " +
            "\"hubs\": [ "+
                "{" +
                    "\"type\": \"Tardis\"," +
                    "\"url\": \"https://ham.org/tardis.html\"" +
                "} " +
            "], " +
            "\"_blue_shed\": { " +
                "\"about\": \"https://blueshed-podcasts.com/json-feed-extension-docs\"," +
                "\"explicit\": false," +
                "\"copyright\": \"1948 by George Orwell\"," +
                "\"owner\": \"Big Brother and the Holding Company\"," +
                "\"subtitle\": \"All shouting, all the time. Double. Plus. Good.\"" +
            "} " +
        "}";



    private static final String TEST_STRING_1_1 = "{" +
            "\"version\": \"https://jsonfeed.org/version/1.1\"," +
            "\"title\": \"Feed Feed\"," +
            "\"home_page_url\": \"https://ham.org/\"," +
            "\"feed_url\": \"https://ham.org/feed.json\"," +
            "\"description\": \"Ham Feed Description\"," +
            "\"user_comment\": \"Ham Feed User Comment\"," +
            "\"next_url\": \"https://ham.org/feed.json?page=2\"," +
            "\"icon\": \"https://ham.org/icon.jpg\"," +
            "\"favicon\": \"https://ham.org/favicon.jpg\"," +
            "\"language\": \"en-US\"," +
            "\"expired\": true," +
            "\"authors\": [ " +
                "{" +
                    "\"name\": \"Dalek Caan\"," +
                    "\"url\": \"https://ham.org/authorCaan.html\"," +
                    "\"avatar\": \"https://ham.org/avatarCaan.html\"" +
                "}, " +
                "{" +
                    "\"name\": \"Dalek Jast\"," +
                    "\"url\": \"https://ham.org/authorJast.html\"," +
                    "\"avatar\": \"https://ham.org/avatarJast.html\"" +
                "} " +
            "], " +
            "\"items\": [ "+
                "{" +
                    "\"id\": \"2\"," +
                    "\"content_text\": \"This is a second item.\"," +
                    "\"url\": \"https://example.org/second-item\"," +
                    "\"language\": \"en-US\"," +
                    "\"authors\": [" +
                        "{" +
                            "\"name\": \"Dalek Sec\"," +
                            "\"url\": \"https://ham.org/authorSec.html\"," +
                            "\"avatar\": \"https://ham.org/avatarSec.html\"" +
                        "} " +
                    "], " +
                    "\"attachments\": [" +
                        "{" +
                            "\"url\": \"https://ham.org/doctorRevenge.m4v\"," +
                            "\"mime_type\": \"video/x-m4v\"," +
                            "\"title\": \"Doctor's Revenge\"," +
                            "\"size_in_bytes\": 300," +
                            "\"duration_in_seconds\": 400" +
                        "} " +
                    "] " +
                "}, " +
                "{ "+
                    "\"id\": \"1\", " +
                    "\"content_html\": \"<p>Hello, world!</p>\", " +
                    "\"url\": \"https://example.org/initial-post\"," +
                    "\"language\": \"en-US\"," +
                    "\"authors\": [" +
                        "{" +
                            "\"name\": \"Dalek Thay\"," +
                            "\"url\": \"https://ham.org/authorThay.html\"," +
                            "\"avatar\": \"https://ham.org/avatarThay.html\"" +
                        "} " +
                    "], " +
                    "\"attachments\": [" +
                        "{" +
                            "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
                            "\"mime_type\": \"video/x-m4v\"," +
                            "\"title\": \"Dalek Invasion\"," +
                            "\"size_in_bytes\": 100," +
                            "\"duration_in_seconds\": 200" +
                        "} " +
                    "] " +
                "} " +
            "], " +
            "\"hubs\": [ "+
                "{" +
                    "\"type\": \"Tardis\"," +
                    "\"url\": \"https://ham.org/tardis.html\"" +
                "} " +
            "], " +
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

        Feed feed = DefaultFeed.fromString(DefaultFeedTest.TEST_STRING_1_0);

        assertNotNull(feed);
        assertTrue(feed.isValid());

        assertEquals(feed.getVersion(), Version.VERSION_1_0);
        assertEquals(feed.getTitle(), "Feed Feed");
        assertEquals(feed.getHomePageUrl().toString(), "https://ham.org/");
        assertEquals(feed.getFeedUrl().toString(), "https://ham.org/feed.json");
        assertEquals(feed.getDescription(), "Ham Feed Description");
        assertEquals(feed.getUserComment(), "Ham Feed User Comment");
        assertEquals(feed.getIcon().toString(), "https://ham.org/icon.jpg");
        assertEquals(feed.getFavicon().toString(), "https://ham.org/favicon.jpg");
        assertEquals(feed.getExpired(), true);

        assertNotNull(feed.getAuthor());
        assertTrue(feed.getAuthor().isValid());
        assertEquals(feed.getAuthor().getName(), "Dalek Caan");
        assertEquals(feed.getAuthor().getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(feed.getAuthor().getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 2);

        assertTrue(feed.getItemList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getID(), "2");
        assertEquals(feed.getItemList().get(0).getContentText(), "This is a second item.");
        assertEquals(feed.getItemList().get(0).getUrl().toString(), "https://example.org/second-item");
        assertNotNull(feed.getItemList().get(0).getAuthor());
        assertTrue(feed.getItemList().get(0).getAuthor().isValid());
        assertEquals(feed.getItemList().get(0).getAuthor().getName(), "Dalek Jast");
        assertEquals(feed.getItemList().get(0).getAuthor().getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(feed.getItemList().get(0).getAuthor().getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertNotNull(feed.getItemList().get(0).getAttachmentList());
        assertTrue(feed.getItemList().get(0).getAttachmentList().size() == 1);
        assertTrue(feed.getItemList().get(0).getAttachmentList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getTitle(), "Doctor's Revenge");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getSizeInBytes().intValue(), 300);
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getDurationInSeconds().intValue(), 400);

        assertTrue(feed.getItemList().get(1).isValid());
        assertEquals(feed.getItemList().get(1).getID(), "1");
        assertEquals(feed.getItemList().get(1).getContentHtml(), "<p>Hello, world!</p>");
        assertEquals(feed.getItemList().get(1).getUrl().toString(), "https://example.org/initial-post");
        assertNotNull(feed.getItemList().get(1).getAuthor());
        assertTrue(feed.getItemList().get(1).getAuthor().isValid());
        assertEquals(feed.getItemList().get(1).getAuthor().getName(), "Dalek Thay");
        assertEquals(feed.getItemList().get(1).getAuthor().getUrl().toString(), "https://ham.org/authorThay.html");
        assertEquals(feed.getItemList().get(1).getAuthor().getAvatar().toString(), "https://ham.org/avatarThay.html");
        assertNotNull(feed.getItemList().get(1).getAttachmentList());
        assertTrue(feed.getItemList().get(1).getAttachmentList().size() == 1);
        assertTrue(feed.getItemList().get(1).getAttachmentList().get(0).isValid());
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertNotNull(feed.getHubList());
        assertTrue(feed.getHubList().size() == 1);
        assertTrue(feed.getHubList().get(0).isValid());
        assertEquals(feed.getHubList().get(0).getType(), "Tardis");
        assertEquals(feed.getHubList().get(0).getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(feed.getExtensionsJSONObject());
        assertNotNull(feed.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(feed.toJSONString());

    }


    /**
     * Test 2
     */
    @Test
    public void test2() throws MalformedURLException {

        Feed feed = DefaultFeed.fromString(DefaultFeedTest.TEST_STRING_1_1);

        assertNotNull(feed);
        assertTrue(feed.isValid());

        assertEquals(feed.getVersion(), Version.VERSION_1_1);
        assertEquals(feed.getTitle(), "Feed Feed");
        assertEquals(feed.getHomePageUrl().toString(), "https://ham.org/");
        assertEquals(feed.getFeedUrl().toString(), "https://ham.org/feed.json");
        assertEquals(feed.getDescription(), "Ham Feed Description");
        assertEquals(feed.getUserComment(), "Ham Feed User Comment");
        assertEquals(feed.getIcon().toString(), "https://ham.org/icon.jpg");
        assertEquals(feed.getFavicon().toString(), "https://ham.org/favicon.jpg");
        assertEquals(feed.getLanguage(), "en-US");
        assertEquals(feed.getExpired(), true);

        assertNotNull(feed.getAuthorList());
        assertTrue(feed.getAuthorList().get(0).isValid());
        assertEquals(feed.getAuthorList().get(0).getName(), "Dalek Caan");
        assertEquals(feed.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(feed.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarCaan.html");
        assertEquals(feed.getAuthorList().get(1).getName(), "Dalek Jast");
        assertEquals(feed.getAuthorList().get(1).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(feed.getAuthorList().get(1).getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 2);

        assertTrue(feed.getItemList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getID(), "2");
        assertEquals(feed.getItemList().get(0).getContentText(), "This is a second item.");
        assertEquals(feed.getItemList().get(0).getUrl().toString(), "https://example.org/second-item");
        assertEquals(feed.getItemList().get(0).getLanguage(), "en-US");
        assertNull(feed.getItemList().get(0).getAuthor());
        assertNotNull(feed.getItemList().get(0).getAuthorList());
        assertTrue(feed.getItemList().get(0).getAuthorList().size() == 1);
        assertTrue(feed.getItemList().get(0).getAuthorList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getName(), "Dalek Sec");
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorSec.html");
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarSec.html");
        assertNotNull(feed.getItemList().get(0).getAttachmentList());
        assertTrue(feed.getItemList().get(0).getAttachmentList().size() == 1);
        assertTrue(feed.getItemList().get(0).getAttachmentList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getTitle(), "Doctor's Revenge");
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getSizeInBytes().intValue(), 300);
        assertEquals(feed.getItemList().get(0).getAttachmentList().get(0).getDurationInSeconds().intValue(), 400);

        assertTrue(feed.getItemList().get(1).isValid());
        assertEquals(feed.getItemList().get(1).getID(), "1");
        assertEquals(feed.getItemList().get(1).getContentHtml(), "<p>Hello, world!</p>");
        assertEquals(feed.getItemList().get(1).getUrl().toString(), "https://example.org/initial-post");
        assertEquals(feed.getItemList().get(1).getLanguage(), "en-US");
        assertNull(feed.getItemList().get(1).getAuthor());
        assertNotNull(feed.getItemList().get(1).getAuthorList());
        assertTrue(feed.getItemList().get(1).getAuthorList().size() == 1);
        assertTrue(feed.getItemList().get(1).getAuthorList().get(0).isValid());
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getName(), "Dalek Thay");
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorThay.html");
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarThay.html");
        assertNotNull(feed.getItemList().get(1).getAttachmentList());
        assertTrue(feed.getItemList().get(1).getAttachmentList().size() == 1);
        assertTrue(feed.getItemList().get(1).getAttachmentList().get(0).isValid());
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getMimeType(), "video/x-m4v");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getTitle(), "Dalek Invasion");
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getSizeInBytes().intValue(), 100);
        assertEquals(feed.getItemList().get(1).getAttachmentList().get(0).getDurationInSeconds().intValue(), 200);

        assertNotNull(feed.getHubList());
        assertTrue(feed.getHubList().size() == 1);
        assertTrue(feed.getHubList().get(0).isValid());
        assertEquals(feed.getHubList().get(0).getType(), "Tardis");
        assertEquals(feed.getHubList().get(0).getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(feed.getExtensionsJSONObject());
        assertNotNull(feed.getExtensionsJSONObject().get("_blue_shed"));
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("explicit"), false);
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("copyright"), "1948 by George Orwell");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("owner"), "Big Brother and the Holding Company");
        assertEquals(((HashMap)feed.getExtensionsJSONObject().get("_blue_shed")).get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(feed.toJSONString());

    }


    /**
     * Test 3
     */
    @Test
    public void test3() throws MalformedURLException {

        Feed feed = DefaultFeed.fromString(DefaultFeedTest.TEST_STRING_1_0);

        assertNotNull(feed);
        assertTrue(feed.isValid());

        assertEquals(feed.getVersion(), Version.VERSION_1_0);

        assertNotNull(feed.getAuthor());
        assertTrue(feed.getAuthor().isValid());
        assertEquals(feed.getAuthor().getName(), "Dalek Caan");
        assertEquals(feed.getAuthor().getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(feed.getAuthor().getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 2);

        assertTrue(feed.getItemList().get(0).isValid());
        assertNotNull(feed.getItemList().get(0).getAuthor());
        assertTrue(feed.getItemList().get(0).getAuthor().isValid());
        assertEquals(feed.getItemList().get(0).getAuthor().getName(), "Dalek Jast");
        assertEquals(feed.getItemList().get(0).getAuthor().getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(feed.getItemList().get(0).getAuthor().getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertTrue(feed.getItemList().get(1).isValid());
        assertNotNull(feed.getItemList().get(1).getAuthor());
        assertTrue(feed.getItemList().get(1).getAuthor().isValid());
        assertEquals(feed.getItemList().get(1).getAuthor().getName(), "Dalek Thay");
        assertEquals(feed.getItemList().get(1).getAuthor().getUrl().toString(), "https://ham.org/authorThay.html");
        assertEquals(feed.getItemList().get(1).getAuthor().getAvatar().toString(), "https://ham.org/avatarThay.html");


        assertTrue(((DefaultFeed)feed).upgrade(Version.VERSION_1_1));
        assertTrue(feed.isValid());


        assertEquals(feed.getVersion(), Version.VERSION_1_1);

        assertNotNull(feed.getAuthorList());
        assertTrue(feed.getAuthorList().get(0).isValid());
        assertEquals(feed.getAuthorList().get(0).getName(), "Dalek Caan");
        assertEquals(feed.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(feed.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 2);

        assertTrue(feed.getItemList().get(0).isValid());
        assertNull(feed.getItemList().get(0).getAuthor());
        assertNotNull(feed.getItemList().get(0).getAuthorList());
        assertTrue(feed.getItemList().get(0).getAuthorList().size() == 1);
        assertTrue(feed.getItemList().get(0).getAuthorList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(feed.getItemList().get(0).getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");

        assertTrue(feed.getItemList().get(1).isValid());
        assertNull(feed.getItemList().get(1).getAuthor());
        assertNotNull(feed.getItemList().get(1).getAuthorList());
        assertTrue(feed.getItemList().get(1).getAuthorList().size() == 1);
        assertTrue(feed.getItemList().get(1).getAuthorList().get(0).isValid());
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getName(), "Dalek Thay");
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorThay.html");
        assertEquals(feed.getItemList().get(1).getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarThay.html");

    }


    /**
     * Test 4
     */
//     @Test
//     public void test4() throws MalformedURLException, IOException {
//
//         /* Create a list of known JSON feeds, */
//         /* lifted from https://jsonfeed.org/version/1.1 */
//         List<URL> feedUrlList = new ArrayList<URL>();
//         feedUrlList.add(new URL("https://shapeof.com/feed.json"));
//         feedUrlList.add(new URL("https://flyingmeat.com/blog/feed.json"));
//         feedUrlList.add(new URL("https://daringfireball.net/feeds/json"));
//         feedUrlList.add(new URL("https://hypercritical.co/feeds/main.json"));
//         feedUrlList.add(new URL("https://inessential.com/feed.json"));
//         feedUrlList.add(new URL("https://jsonfeed.org/feed.json"));
//
//
//         /* Crawl the feed URLs */
//         for ( URL feedUrl : feedUrlList ) {
//
//             Feed feed = DefaultFeed.fromUrl(feedUrl);
//
//             assertNotNull(feed);
//             assertTrue(feed.isValid());
//
//             assertNotNull(feed.toJSONString());
//
//         }
//
//     }


    /**
     * Test 5
     */
    @Test
    public void test5() throws MalformedURLException {

        List<Item> itemList = new ArrayList<Item>();
        Item item = new DefaultItem("1");
        itemList.add(item);

        Feed feed = new DefaultFeed("Feed Feed", itemList);

        assertTrue(item.isValid());

        assertEquals(feed.getTitle(), "Feed Feed");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 1);
        assertTrue(feed.getItemList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getID(), "1");

        assertNotNull(feed.toJSONString());

    }


    /**
     * Test 6
     */
    @Test
    public void test6() throws MalformedURLException {

        List<Item> itemList = new ArrayList<Item>();
        Item item = new DefaultItem("1");
        itemList.add(item);

        Feed feed = new DefaultFeed("Feed Feed", itemList)
                .setHomePageUrl(new URL("https://ham.org/"))
                .setFeedUrl(new URL("https://ham.org/feed.json"))
                .setDescription("Ham Feed Description")
                .setUserComment("Ham Feed User Comment")
                .setNextUrl(new URL("https://ham.org/feed.json?page=2"))
                .setIcon(new URL("https://ham.org/icon.jpg"))
                .setFavicon(new URL("https://ham.org/favicon.jpg"))
                .setLanguage("en-US")
                .setExpired(false);

        List<Author> authorList = new ArrayList<Author>();
        authorList.add(new DefaultAuthor("Dalek Jast", new URL("https://ham.org/authorJast.html"), new URL("https://ham.org/avatarJast.html")));
        authorList.add(new DefaultAuthor("Dalek Sec", new URL("https://ham.org/authorSec.html"), new URL("https://ham.org/avatarSec.html")));
        feed.setAuthorList(authorList);

        List<Hub> hubList = new ArrayList<Hub>();
        hubList.add(new DefaultHub("Tardis", new URL("https://ham.org/tardis.html")));
        feed.setHubList(hubList);

        JSONObject jsonObject = new JSONObject()
                .put("about", "https://blueshed-podcasts.com/json-feed-extension-docs")
                .put("explicit", false)
                .put("copyright", "1948 by George Orwell")
                .put("owner", "Big Brother and the Holding Company")
                .put("subtitle", "All shouting, all the time. Double. Plus. Good.");
        feed.setExtensionsJSONObject(new JSONObject().put("_blue_shed", jsonObject));

        assertTrue(feed.isValid());

        assertEquals(feed.getTitle(), "Feed Feed");
        assertEquals(feed.getHomePageUrl().toString(), "https://ham.org/");
        assertEquals(feed.getFeedUrl().toString(), "https://ham.org/feed.json");
        assertEquals(feed.getDescription(), "Ham Feed Description");
        assertEquals(feed.getUserComment(), "Ham Feed User Comment");
        assertEquals(feed.getNextUrl().toString(), "https://ham.org/feed.json?page=2");
        assertEquals(feed.getIcon().toString(), "https://ham.org/icon.jpg");
        assertEquals(feed.getFavicon().toString(), "https://ham.org/favicon.jpg");
        assertEquals(feed.getLanguage(), "en-US");
        assertEquals(feed.getExpired(), false);

        assertNotNull(feed.getAuthorList());
        assertTrue(feed.getAuthorList().size() == 2);
        assertTrue(feed.getAuthorList().get(0).isValid());
        assertEquals(feed.getAuthorList().get(0).getName(), "Dalek Jast");
        assertEquals(feed.getAuthorList().get(0).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(feed.getAuthorList().get(0).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertTrue(feed.getAuthorList().get(1).isValid());
        assertEquals(feed.getAuthorList().get(1).getName(), "Dalek Sec");
        assertEquals(feed.getAuthorList().get(1).getUrl().toString(), "https://ham.org/authorSec.html");
        assertEquals(feed.getAuthorList().get(1).getAvatar().toString(), "https://ham.org/avatarSec.html");

        assertNotNull(feed.getItemList());
        assertTrue(feed.getItemList().size() == 1);
        assertTrue(feed.getItemList().get(0).isValid());
        assertEquals(feed.getItemList().get(0).getID(), "1");

        assertNotNull(feed.getHubList());
        assertTrue(feed.getHubList().size() == 1);
        assertTrue(feed.getHubList().get(0).isValid());
        assertEquals(feed.getHubList().get(0).getType(), "Tardis");
        assertEquals(feed.getHubList().get(0).getUrl().toString(), "https://ham.org/tardis.html");

        assertNotNull(feed.getExtensionsJSONObject());
        assertNotNull(feed.getExtensionsJSONObject().getJSONObject("_blue_shed"));
        assertEquals(feed.getExtensionsJSONObject().getJSONObject("_blue_shed").get("about"), "https://blueshed-podcasts.com/json-feed-extension-docs");
        assertEquals(feed.getExtensionsJSONObject().getJSONObject("_blue_shed").get("explicit"), false);
        assertEquals(feed.getExtensionsJSONObject().getJSONObject("_blue_shed").get("copyright"), "1948 by George Orwell");
        assertEquals(feed.getExtensionsJSONObject().getJSONObject("_blue_shed").get("owner"), "Big Brother and the Holding Company");
        assertEquals(feed.getExtensionsJSONObject().getJSONObject("_blue_shed").get("subtitle"), "All shouting, all the time. Double. Plus. Good.");

        assertNotNull(feed.toJSONString());

        assertEquals(feed.getVersion(), Version.VERSION_1_1);

    }


}
