/**
 * DefaultFeed.java
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Feed;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.Version;
import org.kaderate.jsonfeed.implementation.DefaultAuthor;
import org.kaderate.jsonfeed.implementation.DefaultHub;
import org.kaderate.jsonfeed.implementation.DefaultItem;


/**
 * Default implementation for Feed
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.4.0
 */
public class DefaultFeed implements Feed {


    /**
     * Version, defaults to 1.0
     */
    private Version version = Version.VERSION_1_0;


    /**
     * Title
     */
    private String title = null;


    /**
     * Home page URL
     */
    private URL homePageUrl = null;


    /**
     * Feed URL
     */
    private URL feedUrl = null;


    /**
     * Description
     */
    private String description = null;


    /**
     * User comment
     */
    private String userComment = null;


    /**
     * Next URL
     */
    private URL nextUrl = null;


    /**
     * Icon (URL)
     */
    private URL icon = null;


    /**
     * Favicon (URL)
     */
    private URL favicon = null;


    /**
     * Author
     */
    private Author author = null;


    /**
     * Author list (JSON Feed 1.1 only)
     */
    private List<Author> authorList = new ArrayList<Author>();


    /**
     * Language (JSON Feed 1.1 only)
     */
    private String language = null;


    /**
     * Expired
     */
    private Boolean expired = null;


    /**
     * Hub list
     */
    private List<Hub> hubList = new ArrayList<Hub>();


    /**
     * Item list
     */
    private List<Item> itemList = new ArrayList<Item>();


    /**
     * Extensions JSON object
     */
    private JSONObject extensionsJsonObject = null;



    /**
     * Parse a JSON string and return the feed
     *
     * @param   jsonString  the feed as a JSON string
     *
     * @return  the feed object
     *
     * @exception   MalformedURLException
     *              If the home page URL is invalid
     *
     * @exception   MalformedURLException
     *              If the feed URL is invalid
     *
     * @exception   MalformedURLException
     *              If the next URL is invalid
     *
     * @exception   MalformedURLException
     *              If the icon (URL) is invalid
     *
     * @exception   MalformedURLException
     *              If the favicon (URL) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    public static Feed fromString(final String jsonString) throws MalformedURLException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(jsonString);

        /* Parse the JSON string */
        final Feed feed = new DefaultFeed(jsonObject);

        /* Return the feed */
        return (feed);

    }


    /**
     * Constructor
     *
     * @param   jsonObject  the feed as a JSON object
     *
     * @exception   MalformedURLException
     *              If the home page URL is invalid
     *
     * @exception   MalformedURLException
     *              If the feed URL is invalid
     *
     * @exception   MalformedURLException
     *              If the next URL is invalid
     *
     * @exception   MalformedURLException
     *              If the icon (URL) is invalid
     *
     * @exception   MalformedURLException
     *              If the favicon (URL) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    protected DefaultFeed(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the version, required */
        if ( jsonObject.has("version") == true ) {
            this.version = Version.fromString(jsonObject.getString("version"));
            if ( this.version == null ) {
                throw new IllegalArgumentException("Invalid version value");
            }
        }
        else {
            throw new IllegalArgumentException("Missing version");
        }

        /* Get the title */
        this.setTitle(jsonObject.optString("title", null));

        /* Get the home page URL */
        if ( jsonObject.has("home_page_url") == true ) {
            this.setHomePageUrl(new URL(jsonObject.getString("home_page_url")));
        }

        /* Get the feed URL */
        if ( jsonObject.has("feed_url") == true ) {
            this.setFeedUrl(new URL(jsonObject.getString("feed_url")));
        }

        /* Get the description */
        this.setDescription(jsonObject.optString("description", null));

        /* Get the user comment */
        this.setUserComment(jsonObject.optString("user_comment", null));

        /* Get the next URL */
        if ( jsonObject.has("next_url") == true ) {
            this.setNextUrl(new URL(jsonObject.getString("next_url")));
        }

        /* Get the icon (URL) */
        if ( jsonObject.has("icon") == true ) {
            this.setIcon(new URL(jsonObject.getString("icon")));
        }

        /* Get the favicon (URL) */
        if ( jsonObject.has("favicon") == true ) {
            this.setFavicon(new URL(jsonObject.getString("favicon")));
        }

        /* Get the language */
        if ( jsonObject.has("language") == true ) {
            this.setLanguage(jsonObject.getString("language"));
        }

        /* Get the expired */
        if ( jsonObject.has("expired") == true ) {
            this.setExpired(jsonObject.getBoolean("expired"));
        }

        /* Get the author */
        if ( jsonObject.has("author") == true ) {
            this.setAuthor(new DefaultAuthor(jsonObject.getJSONObject("author")));
        }

        /* Get the authors */
        if ( jsonObject.has("authors") == true ) {
            this.setAuthorList(DefaultAuthor.fromJsonArray(jsonObject.getJSONArray("authors")));
        }

        /* Get the hubs */
        if ( jsonObject.has("hubs") == true ) {
            this.setHubList(DefaultHub.fromJsonArray(jsonObject.getJSONArray("hubs")));
        }

        /* Get the items */
        if ( jsonObject.has("items") == true ) {
            this.setItemList(DefaultItem.fromJsonArray(jsonObject.getJSONArray("items")));
        }


        /* Get the extensions */
        final JSONObject extensionsJsonObject = new JSONObject();
        for ( final Map.Entry<String, Object> entry : jsonObject.toMap().entrySet() ) {
            if ( entry.getKey().startsWith("_") == true ) {
                extensionsJsonObject.put(entry.getKey(), entry.getValue());
            }
        }
        if ( extensionsJsonObject.isEmpty() == false ) {
            this.extensionsJsonObject = extensionsJsonObject;
        }


        /* Normalize the feed */
        this.normalize();

    }



    /**
     * Constructor
     *
     * @param   title       the title
     * @param   itemList    the item list
     */
    public DefaultFeed(final String title, final List<Item> itemList) {

        /* Set the title and item list */
        this.setTitle(title);
        this.setItemList(itemList);

    }



    /**
     * Constructor
     */
    public DefaultFeed() {

    }



    /**
     * Get the version
     *
     * @return  the version, null if not specified
     */
    @Override
    public Version getVersion() {

        return (this.version);

    }



    /**
     * Get the title
     *
     * @return  the title, null if not specified
     */
    @Override
    public String getTitle() {

        return (this.title);

    }



    /**
     * Set the title
     *
     * @param   title  the title
     */
    @Override
    public void setTitle(String title) {

       this.title = title;

    }



    /**
     * Get the home page URL
     *
     * @return  the home page URL, null if not specified
     */
    @Override
    public URL getHomePageUrl() {

        return (this.homePageUrl);

    }



    /**
     * Set the home page URL
     *
     * @param   homePageUrl     the home page URL
     */
    @Override
    public void setHomePageUrl(URL homePageUrl) {

       this.homePageUrl = homePageUrl;

    }



    /**
     * Get the feed URL
     *
     * @return  the feed URL, null if not specified
     */
    @Override
    public URL getFeedUrl() {

        return (this.feedUrl);

    }



    /**
     * Set the feed URL
     *
     * @param   feedUrl     the feed URL
     */
    @Override
    public void setFeedUrl(URL feedUrl) {

       this.feedUrl = feedUrl;

    }



    /**
     * Get the description
     *
     * @return  the description, null if not specified
     */
    @Override
    public String getDescription() {

        return (this.description);

    }



    /**
     * Set the description
     *
     * @param   description  the description
     */
    @Override
    public void setDescription(String description) {

       this.description = description;

    }



    /**
     * Get the user comment
     *
     * @return  the user comment, null if not specified
     */
    @Override
    public String getUserComment() {

        return (this.userComment);

    }



    /**
     * Set the user comment
     *
     * @param   userComment  the user comment
     */
    @Override
    public void setUserComment(String userComment) {

       this.userComment = userComment;

    }



    /**
     * Get the next URL
     *
     * @return  the next URL, null if not specified
     */
    @Override
    public URL getNextUrl() {

        return (this.nextUrl);

    }



    /**
     * Set the next URL
     *
     * @param   nextUrl     the next URL
     */
    @Override
    public void setNextUrl(URL nextUrl) {

       this.nextUrl = nextUrl;

    }



    /**
     * Get the icon (URL)
     *
     * @return  the icon URL, null if not specified
     */
    @Override
    public URL getIcon() {

        return (this.icon);

    }



    /**
     * Set the icon (URL)
     *
     * @param   icon     the icon URL
     */
    @Override
    public void setIcon(URL icon) {

       this.icon = icon;

    }



    /**
     * Get the favicon (URL)
     *
     * @return  the favicon URL, null if not specified
     */
    @Override
    public URL getFavicon() {

        return (this.favicon);

    }



    /**
     * Set the favicon (URL)
     *
     * @param   favicon     the favicon URL
     */
    @Override
    public void setFavicon(URL favicon) {

       this.favicon = favicon;

    }



    /**
     * Get the author
     *
     * @return  the author, null if not specified
     */
    @Override
    public Author getAuthor() {

        return (this.author);

    }



    /**
     * Set the author
     *
     * @param   author     the author
     */
    @Override
    public void setAuthor(Author author) {

        this.author = author;

    }



    /**
     * Get the author list (JSON Feed 1.1 only)
     *
     * @return  the author list, empty list if there are no authors
     */
    @Override
    public List<Author> getAuthorList() {

        return (this.authorList);

    }



    /**
     * Set the author list (JSON Feed 1.1 only)
     *
     * @param   authorList  the author list
     */
    @Override
    public void setAuthorList(List<Author> authorList) {

        this.authorList = authorList;

    }



    /**
     * Get the language (JSON Feed 1.1 only)
     *
     * @return  the language, null if not specified
     */
    @Override
    public String getLanguage() {

        return (this.language);

    }



    /**
     * Set the language (JSON Feed 1.1 only)
     *
     * @param   language  the language
     */
    @Override
    public void setLanguage(String language) {

        this.language = language;

    }



    /**
     * Get the expired
     *
     * @return  the expired, null if not specified
     */
    @Override
    public Boolean getExpired() {

        return (this.expired);

    }



    /**
     * Set the expired
     *
     * @param   expired     the expired
     */
    @Override
    public void setExpired(Boolean expired) {

        this.expired = expired;

    }



    /**
     * Get the hub list
     *
     * @return  the hub list, empty list if there are no hubs
     */
    @Override
    public List<Hub> getHubList() {

        return (this.hubList);

    }



    /**
     * Set the hub list
     *
     * @param   hubList  the hub list
     */
    @Override
    public void setHubList(List<Hub> hubList) {

        this.hubList = hubList;

    }



    /**
     * Get the item list
     *
     * @return  the item list, empty list if there are no items
     */
    @Override
    public List<Item> getItemList() {

        return (this.itemList);

    }



    /**
     * Set the item list
     *
     * @param   itemList  the item list
     */
    @Override
    public void setItemList(List<Item> itemList) {

        /* Check the item version */
        if ( itemList != null ) {
            for ( final Item item : itemList ) {
                ((DefaultItem)item).upgrade(this.getVersion());
            }
        }

        this.itemList = itemList;

    }



    /**
     * Get feed extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        /* Return the extensions JSON object */
        return (this.extensionsJsonObject);

    }



    /**
     * Set the feed extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     */
    @Override
    public void setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;

    }



    /**
     * Check the validity of the feed object
     *
     * @return  true if the feed object is valid
     */
    @Override
    public boolean isValid() {

        /* Check the feed fields */
        if ( this.getVersion() == null ) {
            return (false);
        }

        if ( this.getTitle() == null ) {
            return (false);
        }

        if ( this.getItemList() == null ) {
            return (false);
        }

        if ( this.getItemList().isEmpty() == true ) {
            return (false);
        }

        return (true);

    }



    /**
     * Upgrade this feed to the passed version
     *
     * @param   toVersion       to version
     *
     * @return  true if the feed was upgraded
     */
    public boolean upgrade(final Version toVersion) {

        /* Upgrade flag */
        boolean feedUpgraded = false;

        /* Upgrade to version 1.1 */
        if ( toVersion == Version.VERSION_1_1 ) {

            /* Upgrade the feed author */
            if ( this.getAuthor() != null ) {
                this.authorList.add(this.getAuthor());
                this.setAuthor(null);
                feedUpgraded = true;
            }

            /* Upgrade every item */
            for ( final Item item : this.getItemList() ) {
                feedUpgraded |= ((DefaultItem)item).upgrade(toVersion);
            }

            /* Update the version number */
            if ( this.getVersion() != toVersion ) {
                this.version = toVersion;
                feedUpgraded = true;
            }
        }

        /* Feed was not upgraded */
        return (feedUpgraded);

    }



    /**
     * Normalize this feed if needed
     *
     * @return  true if the feed was normalized
     */
    private boolean normalize() {

        /* Detected versions */
        boolean detectedVersion_1_0 = false;
        boolean detectedVersion_1_1 = false;

        /* Check language */
        if ( this.getLanguage() != null ) {
            detectedVersion_1_1 = true;
        }

        /* Check author list */
        if ( (this.getAuthorList() != null) && (this.getAuthorList().size() > 0) ) {
            detectedVersion_1_1 = true;
        }

        /* Check author */
        if ( this.getAuthor() != null ) {
            detectedVersion_1_0 = true;
        }

        /* Check item language / author list / author */
        for ( final Item item : this.getItemList() ) {
            if ( item.getLanguage() != null ) {
                detectedVersion_1_1 = true;
            }
            if ( (item.getAuthorList() != null) && (item.getAuthorList().size() > 0) ) {
                detectedVersion_1_1 = true;
            }
            if ( this.getAuthor() != null ) {
                detectedVersion_1_0 = true;
            }
        }


        /* Both version 1.0 and 1.1 elements were detected, upgrade to 1.1 */
        if ( (detectedVersion_1_0 == true) && (detectedVersion_1_1 == true) ) {
            return (this.upgrade(Version.VERSION_1_1));
        }

        /* Version 1.1 elements were detected and the feed version is 1.0, upgrade to 1.1 */
        else if ( (detectedVersion_1_1 == true) && (this.getVersion() == Version.VERSION_1_0) ) {
            return (this.upgrade(Version.VERSION_1_1));
        }

        /* Version 1.0 elements were detected and the feed version is 1.1, upgrade to 1.1 */
        else if ( (detectedVersion_1_0 == true) && (this.getVersion() == Version.VERSION_1_1) ) {
            return (this.upgrade(Version.VERSION_1_1));
        }


        /* Feed was not normalized */
        return (false);

    }



    /**
     * Return the JSON string representation for this object
     *
     * The feed will be upgaded from version 1.0 to 1.1 if needed
     *
     * @return      the JSON string representation for this object
     */
    @Override
    public String toJSONString() {

        /* Normalize the feed */
        this.normalize();


        /* Create the JSON object */
        final JSONObject jsonObject = new JSONObject();

        /* Add the version */
        jsonObject.put("version", this.getVersion());

        /* Add the title */
        jsonObject.put("title", this.getTitle());

        /* Add the home page URL */
        if ( this.getHomePageUrl() != null ) {
            jsonObject.put("home_page_url", this.getHomePageUrl().toString());
        }

        /* Add the feed URL */
        if ( this.getFeedUrl() != null ) {
            jsonObject.put("feed_url", this.getFeedUrl().toString());
        }

        /* Add the description */
        if ( this.getDescription() != null ) {
            jsonObject.put("description", this.getDescription());
        }

        /* Add the user comment */
        if ( this.getUserComment() != null ) {
            jsonObject.put("user_comment", this.getUserComment());
        }

        /* Add the next URL */
        if ( this.getFeedUrl() != null ) {
            jsonObject.put("next_url", this.getFeedUrl().toString());
        }

        /* Add the icon (URL) */
        if ( this.getIcon() != null ) {
            jsonObject.put("icon", this.getIcon().toString());
        }

        /* Add the favicon (URL) */
        if ( this.getFavicon() != null ) {
            jsonObject.put("favicon", this.getFavicon().toString());
        }

        /* Add the authors */
        if ( (this.getAuthorList() != null) && (this.getAuthorList().size() > 0) ) {
            jsonObject.put("authors", this.getAuthorList());
        }
        /* Add the author */
        else if ( this.getAuthor() != null ) {
            jsonObject.put("author", this.getAuthor());
        }

        /* Add the language */
        if ( this.getLanguage() != null ) {
            jsonObject.put("language", this.getLanguage());
        }

        /* Add the expired */
        if ( this.getExpired() != null ) {
            jsonObject.put("expired", this.getExpired());
        }

        /* Add the hubs */
        if ( (this.getHubList() != null) && (this.getHubList().size() > 0) ) {
            jsonObject.put("hubs", this.getHubList());
        }

        /* Add the items */
        if ( (this.getItemList() != null) && (this.getItemList().size() > 0) ) {
            jsonObject.put("items", this.getItemList());
        }

        /* Add the extensions */
        if ( this.extensionsJsonObject != null ) {
            for ( final Map.Entry<String, Object> entry : this.extensionsJsonObject.toMap().entrySet() ) {
                if ( entry.getKey().startsWith("_") == true ) {
                    jsonObject.put(entry.getKey(), entry.getValue());
                }
            }
        }

        /* Get the JSON string */
        final String jsonString = jsonObject.toString();

        /* Return the JSON string */
        return (jsonString);

    }


}
