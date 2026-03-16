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
package com.kaderate.jsonfeed.implementation;


/* Import Java stuff */
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.StringBuilder;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Author;
import com.kaderate.jsonfeed.Feed;
import com.kaderate.jsonfeed.Hub;
import com.kaderate.jsonfeed.Item;
import com.kaderate.jsonfeed.Version;
import com.kaderate.jsonfeed.implementation.DefaultAuthor;
import com.kaderate.jsonfeed.implementation.DefaultHub;
import com.kaderate.jsonfeed.implementation.DefaultItem;


/**
 * Default implementation for Feed
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
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
     * Home page URI
     */
    private URI homePageUri = null;


    /**
     * Feed URI
     */
    private URI feedUri = null;


    /**
     * Description
     */
    private String description = null;


    /**
     * User comment
     */
    private String userComment = null;


    /**
     * Next URI
     */
    private URI nextUri = null;


    /**
     * Icon (URI)
     */
    private URI icon = null;


    /**
     * Favicon (URI)
     */
    private URI favicon = null;


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
    private JSONObject extensionsJsonObject = new JSONObject();



    /**
     * Fetch a feed URI and return the feed
     *
     * @param   feedUri  the feed URI
     *
     * @return  the feed object
     *
     * @exception   MalformedURLException
     *              If the feed URI could not be converted to a URL
     *
     * @exception   IOException
     *              If the feed URI could not be read
     *
     * @exception   URISyntaxException
     *              If the home page URI is invalid
     *
     * @exception   URISyntaxException
     *              If the feed URI is invalid
     *
     * @exception   URISyntaxException
     *              If the next URI is invalid
     *
     * @exception   URISyntaxException
     *              If the icon (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the favicon (URI) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    public static Feed fromUri(final URI feedUri) throws MalformedURLException, URISyntaxException, IOException {

        /* Fetch a URI  */
        try ( final InputStream feedStream = feedUri.toURL().openStream() )  {

            /* Create the feed reader  */
            final Reader feedReader = new InputStreamReader(feedStream);

            /* Parse the returned JSON string */
            final Feed feed = DefaultFeed.fromReader(feedReader);

            /* Return the feed */
            return (feed);

        }

    }


    /**
     * Read the feed reader and return the feed
     *
     * @param   feedReader  the JSON string reader
     *
     * @return  the feed object
     *
     * @exception   IOException
     *              If the feed reader could not be read
     *
     * @exception   URISyntaxException
     *              If the home page URI is invalid
     *
     * @exception   URISyntaxException
     *              If the feed URI is invalid
     *
     * @exception   URISyntaxException
     *              If the next URI is invalid
     *
     * @exception   URISyntaxException
     *              If the icon (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the favicon (URI) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    public static Feed fromReader(final Reader feedReader) throws URISyntaxException, IOException {

        /* Variables to read the JSON string */
        final StringBuilder feedStringBuilder = new StringBuilder();
        final char[] feedReaderBuffer = new char[4096];
        int charactersRead = 0;

        /* Read the feed into the feed string builder */
        while ( (charactersRead = feedReader.read(feedReaderBuffer)) >= 0 ) {
            feedStringBuilder.append(feedReaderBuffer, 0, charactersRead);
        }

        /* Parse the feed string  */
        final Feed feed = DefaultFeed.fromString(feedStringBuilder.toString());

        /* Return the feed */
        return (feed);

    }


    /**
     * Parse a JSON feed string and return the feed
     *
     * @param   feedString  the JSON feed string
     *
     * @return  the feed object
     *
     * @exception   URISyntaxException
     *              If the home page URI is invalid
     *
     * @exception   URISyntaxException
     *              If the feed URI is invalid
     *
     * @exception   URISyntaxException
     *              If the next URI is invalid
     *
     * @exception   URISyntaxException
     *              If the icon (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the favicon (URI) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    public static Feed fromString(final String feedString) throws URISyntaxException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(feedString);

        /* Create a new feed from the JSON object */
        final Feed feed = new DefaultFeed(jsonObject);

        /* Return the feed */
        return (feed);

    }


    /**
     * Constructor
     *
     * @param   jsonObject  the feed as a JSON object
     *
     * @exception   URISyntaxException
     *              If the home page URI is invalid
     *
     * @exception   URISyntaxException
     *              If the feed URI is invalid
     *
     * @exception   URISyntaxException
     *              If the next URI is invalid
     *
     * @exception   URISyntaxException
     *              If the icon (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the favicon (URI) is invalid
     *
     * @exception   IllegalArgumentException
     *              If the version is missing or invalid
     */
    protected DefaultFeed(final JSONObject jsonObject) throws URISyntaxException {

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

        /* Get the home page URI */
        if ( jsonObject.has("home_page_url") == true ) {
            this.setHomePageUri(new URI(jsonObject.getString("home_page_url")));
        }

        /* Get the feed URI */
        if ( jsonObject.has("feed_url") == true ) {
            this.setFeedUri(new URI(jsonObject.getString("feed_url")));
        }

        /* Get the description */
        this.setDescription(jsonObject.optString("description", null));

        /* Get the user comment */
        this.setUserComment(jsonObject.optString("user_comment", null));

        /* Get the next URI */
        if ( jsonObject.has("next_url") == true ) {
            this.setNextUri(new URI(jsonObject.getString("next_url")));
        }

        /* Get the icon (URI) */
        if ( jsonObject.has("icon") == true ) {
            this.setIcon(new URI(jsonObject.getString("icon")));
        }

        /* Get the favicon (URI) */
        if ( jsonObject.has("favicon") == true ) {
            this.setFavicon(new URI(jsonObject.getString("favicon")));
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
        for ( final Map.Entry<String, Object> entry : jsonObject.toMap().entrySet() ) {
            if ( entry.getKey().startsWith("_") == true ) {
                this.extensionsJsonObject.put(entry.getKey(), entry.getValue());
            }
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
     *
     * @return  the feed
     */
    @Override
    public Feed setTitle(String title) {

        this.title = title;
        return (this);

    }



    /**
     * Get the home page URI
     *
     * @return  the home page URI, null if not specified
     */
    @Override
    public URI getHomePageUri() {

        return (this.homePageUri);

    }



    /**
     * Set the home page URI
     *
     * @param   homePageUri     the home page URI
     *
     * @return  the feed
     */
    @Override
    public Feed setHomePageUri(URI homePageUri) {

        this.homePageUri = homePageUri;
        return (this);

    }



    /**
     * Get the feed URI
     *
     * @return  the feed URI, null if not specified
     */
    @Override
    public URI getFeedUri() {

        return (this.feedUri);

    }



    /**
     * Set the feed URI
     *
     * @param   feedUri     the feed URI
     *
     * @return  the feed
     */
    @Override
    public Feed setFeedUri(URI feedUri) {

        this.feedUri = feedUri;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setDescription(String description) {

        this.description = description;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setUserComment(String userComment) {

        this.userComment = userComment;
        return (this);

    }



    /**
     * Get the next URI
     *
     * @return  the next URI, null if not specified
     */
    @Override
    public URI getNextUri() {

        return (this.nextUri);

    }



    /**
     * Set the next URI
     *
     * @param   nextUri     the next URI
     *
     * @return  the feed
     */
    @Override
    public Feed setNextUri(URI nextUri) {

       this.nextUri = nextUri;
       return (this);

    }



    /**
     * Get the icon (URI)
     *
     * @return  the icon URI, null if not specified
     */
    @Override
    public URI getIcon() {

        return (this.icon);

    }



    /**
     * Set the icon (URI)
     *
     * @param   icon     the icon URI
     *
     * @return  the feed
     */
    @Override
    public Feed setIcon(URI icon) {

        this.icon = icon;
        return (this);

    }



    /**
     * Get the favicon (URI)
     *
     * @return  the favicon URI, null if not specified
     */
    @Override
    public URI getFavicon() {

        return (this.favicon);

    }



    /**
     * Set the favicon (URI)
     *
     * @param   favicon     the favicon URI
     *
     * @return  the feed
     */
    @Override
    public Feed setFavicon(URI favicon) {

        this.favicon = favicon;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setAuthor(Author author) {

        this.author = author;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setAuthorList(List<Author> authorList) {

        this.authorList = authorList;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setLanguage(String language) {

        this.language = language;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setExpired(Boolean expired) {

        this.expired = expired;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setHubList(List<Hub> hubList) {

        this.hubList = hubList;
        return (this);

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
     *
     * @return  the feed
     */
    @Override
    public Feed setItemList(List<Item> itemList) {

        /* Upgrade every item */
        if ( itemList != null ) {
            for ( final Item item : itemList ) {
                ((DefaultItem)item).upgrade(this.getVersion());
            }
        }

        this.itemList = itemList;
        return (this);

    }



    /**
     * Get feed extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        return (this.extensionsJsonObject);

    }



    /**
     * Set the feed extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the feed
     */
    @Override
    public Feed setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;
        return (this);

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

        /* Add the home page URI */
        if ( this.getHomePageUri() != null ) {
            jsonObject.put("home_page_url", this.getHomePageUri().toString());
        }

        /* Add the feed URI */
        if ( this.getFeedUri() != null ) {
            jsonObject.put("feed_url", this.getFeedUri().toString());
        }

        /* Add the description */
        if ( this.getDescription() != null ) {
            jsonObject.put("description", this.getDescription());
        }

        /* Add the user comment */
        if ( this.getUserComment() != null ) {
            jsonObject.put("user_comment", this.getUserComment());
        }

        /* Add the next URI */
        if ( this.getFeedUri() != null ) {
            jsonObject.put("next_url", this.getFeedUri().toString());
        }

        /* Add the icon (URI) */
        if ( this.getIcon() != null ) {
            jsonObject.put("icon", this.getIcon().toString());
        }

        /* Add the favicon (URI) */
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
        if ( this.getExtensionsJSONObject() != null ) {
            for ( final Map.Entry<String, Object> entry : this.getExtensionsJSONObject().toMap().entrySet() ) {
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
