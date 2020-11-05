//
// DefaultFeed.java
//
// @author Francois Schiettecatte
// @version 1.0
//
// Change History:
//    - Nov 3, 2020 - File was created
//
// TBD:
//    -
//
//
//


// Package location
package org.kaderate.jsonfeed.implementation;


// Import Java stuff
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// Import JSON stuff
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;


// Import JSONFeed stuff
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Feed;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.Version;
import org.kaderate.jsonfeed.implementation.DefaultAuthor;
import org.kaderate.jsonfeed.implementation.DefaultHub;
import org.kaderate.jsonfeed.implementation.DefaultItem;


//
// Default implementation for Feed
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public class DefaultFeed implements Feed, JSONString {


    //
    // Version
    //
    private Version version = null;


    //
    // Title
    //
    private String title = null;


    //
    // Home page URL
    //
    private URL homePageUrl = null;


    //
    // Feed URL
    //
    private URL feedUrl = null;


    //
    // Description
    //
    private String description = null;


    //
    // User comment
    //
    private String userComment = null;


    //
    // Next URL
    //
    private URL nextUrl = null;


    //
    // Icon (URL)
    //
    private URL icon = null;


    //
    // Favicon (URL)
    //
    private URL favicon = null;


    //
    // Author
    //
    private Author author = null;


    //
    // Author list (JSON feed 1.1 only)
    //
    private List<Author> authorList = new ArrayList<Author>();


    //
    // Language (JSON feed 1.1 only)
    //
    private String language = null;


    //
    // Expired
    //
    private Boolean expired = null;


    //
    // Hub list
    //
    private List<Hub> hubList = new ArrayList<Hub>();


    //
    // Item list
    //
    private List<Item> itemList = new ArrayList<Item>();



    //
    // Parse a JSON string and return the feed
    //
    // @param   jsonString  the feed as a JSON string
    //
    // @return  the feed object
    //
    // @exception   MalformedURLException
    //              If the home page URL is invalid
    //
    // @exception   MalformedURLException
    //              If the feed URL is invalid
    //
    // @exception   MalformedURLException
    //              If the next URL is invalid
    //
    // @exception   MalformedURLException
    //              If the icon (URL) is invalid
    //
    // @exception   MalformedURLException
    //              If the favicon (URL) is invalid
    //
    public static Feed fromString(final String jsonString) throws MalformedURLException {

        // Parse the JSON string to a JSON object
        final JSONObject jsonObject = new JSONObject(jsonString);

        // Parse the JSON string
        final Feed feed = new DefaultFeed(jsonObject);

        // Return the feed
        return (feed);

    }


    //
    // Constructor
    //
    // @param   jsonObject  the feed as a JSON object
    //
    // @return  the feed object
    //
    // @exception   MalformedURLException
    //              If the home page URL is invalid
    //
    // @exception   MalformedURLException
    //              If the feed URL is invalid
    //
    // @exception   MalformedURLException
    //              If the next URL is invalid
    //
    // @exception   MalformedURLException
    //              If the icon (URL) is invalid
    //
    // @exception   MalformedURLException
    //              If the favicon (URL) is invalid
    //
    protected DefaultFeed(final JSONObject jsonObject) throws MalformedURLException {

        // Get the version
        this.version = Version.fromString(jsonObject.optString("version"));

        // Get the title
        this.title = jsonObject.optString("title");

        // Get the home page URL
        if ( jsonObject.has("home_page_url") == true ) {
            this.homePageUrl = new URL(jsonObject.getString("home_page_url"));
        }

        // Get the feed URL
        if ( jsonObject.has("feed_url") == true ) {
            this.feedUrl = new URL(jsonObject.getString("feed_url"));
        }

        // Get the description
        this.description = jsonObject.optString("description");

        // Get the user comment
        this.userComment = jsonObject.optString("user_comment");

        // Get the next URL
        if ( jsonObject.has("next_url") == true ) {
            this.nextUrl = new URL(jsonObject.getString("next_url"));
        }

        // Get the icon (URL)
        if ( jsonObject.has("icon") == true ) {
            this.icon = new URL(jsonObject.getString("icon"));
        }

        // Get the favicon (URL)
        if ( jsonObject.has("favicon") == true ) {
            this.favicon = new URL(jsonObject.getString("favicon"));
        }

        // Get the language
        this.language = jsonObject.optString("language");

        // Get the expired
        if ( jsonObject.has("expired") == true ) {
            this.expired = jsonObject.getBoolean("expired");
        }

        // Get the author
        if ( jsonObject.has("author") == true ) {
            this.author = new DefaultAuthor(jsonObject.getJSONObject("author"));
        }

        // Get the authors
        if ( jsonObject.has("authors") == true ) {
            this.authorList = DefaultAuthor.fromJsonArray(jsonObject.getJSONArray("authors"));
        }

        // Get the hubs
        if ( jsonObject.has("hubs") == true ) {
            this.hubList = DefaultHub.fromJsonArray(jsonObject.getJSONArray("hubs"));
        }

        // Get the items
        if ( jsonObject.has("items") == true ) {
            this.itemList = DefaultItem.fromJsonArray(jsonObject.getJSONArray("items"));
        }

    }



    //
    // Get the version
    //
    // @return  the version
    //
    @Override
    public Version getVersion() {

        return (this.version);

    }



    //
    // Get the title
    //
    // @return  the title
    //
    @Override
    public String getTitle() {

        return (this.title);

    }



    //
    // Get the home page URL
    //
    // @return  the home page URL
    //
    @Override
    public URL getHomePageUrl() {

        return (this.homePageUrl);

    }



    //
    // Get the feed URL
    //
    // @return  the feed URL
    //
    @Override
    public URL getFeedUrl() {

        return (this.feedUrl);

    }



    //
    // Get the description
    //
    // @return  the description
    //
    @Override
    public String getDescription() {

        return (this.description);

    }



    //
    // Get the user comment
    //
    // @return  the user comment
    //
    @Override
    public String getUserComment() {

        return (this.userComment);

    }



    //
    // Get the next URL
    //
    // @return  the next URL
    //
    @Override
    public URL getNextUrl() {

        return (this.nextUrl);

    }



    //
    // Get the icon (URL)
    //
    // @return  the icon (URL)
    //
    @Override
    public URL getIcon() {

        return (this.icon);

    }



    //
    // Get the favicon (URL)
    //
    // @return  the favicon (URL)
    //
    @Override
    public URL getFavicon() {

        return (this.favicon);

    }



    //
    // Get the author
    //
    // @return  the author
    //
    @Override
    public Author getAuthor() {

        return (this.author);

    }



    //
    // Get the author list (JSON feed 1.1 only)
    //
    // @return  the author list
    //
    @Override
    public List<Author> getAuthorList() {

        return (this.authorList);

    }



    //
    // Get the language (JSON feed 1.1 only)
    //
    // @return  the language
    //
    @Override
    public String getLanguage() {

        return (this.language);

    }



    //
    // Get the expired
    //
    // @return  the expired
    //
    @Override
    public Boolean getExpired() {

        return (this.expired);

    }



    //
    // Get the hub list
    //
    // @return  the hub list
    //
    @Override
    public List<Hub> getHubList() {

        return (this.hubList);

    }



    //
    // Get the item list
    //
    // @return  the item list
    //
    @Override
    public List<Item> getItemList() {

        return (this.itemList);

    }



    //
    // Check the validity of the feed object
    //
    // @return  true if the feed object is valid
    //
    @Override
    public boolean isValid() {

        // Check the feed fields
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



    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    @Override
    public String toJSONString() {

        // Create the JSON object
        final JSONObject jsonObject = new JSONObject();

        // Add the version
        jsonObject.put("version", this.getVersion());

        // Add the title
        jsonObject.put("title", this.getTitle());

        // Add the home page URL
        if ( this.getHomePageUrl() != null ) {
            jsonObject.put("home_page_url", this.getHomePageUrl().toString());
        }

        // Add the feed URL
        if ( this.getFeedUrl() != null ) {
            jsonObject.put("feed_url", this.getFeedUrl().toString());
        }

        // Add the description
        if ( this.getDescription() != null ) {
            jsonObject.put("description", this.getDescription());
        }

        // Add the user comment
        if ( this.getUserComment() != null ) {
            jsonObject.put("user_comment", this.getUserComment());
        }

        // Add the next URL
        if ( this.getFeedUrl() != null ) {
            jsonObject.put("next_url", this.getFeedUrl().toString());
        }

        // Add the icon (URL)
        if ( this.getIcon() != null ) {
            jsonObject.put("icon", this.getIcon().toString());
        }

        // Add the favicon (URL)
        if ( this.getFavicon() != null ) {
            jsonObject.put("favicon", this.getFavicon().toString());
        }

        // Add the author
        if ( this.getAuthor() != null ) {
            jsonObject.put("author", this.getAuthor());
        }

        // Add the authors
        if ( this.getAuthorList().size() > 0 ) {
            jsonObject.put("authors", this.getAuthorList());
        }

        // Add the language
        if ( this.getLanguage() != null ) {
            jsonObject.put("language", this.getLanguage());
        }

        // Add the expired
        if ( this.getExpired() != null ) {
            jsonObject.put("expired", this.getExpired());
        }

        // Add the hubs
        if ( this.getHubList().size() > 0 ) {
            jsonObject.put("hubs", this.getHubList());
        }

        // Add the items
        if ( this.getItemList().size() > 0 ) {
            jsonObject.put("items", this.getItemList());
        }

        // Get the JSON string
        final String jsonString = jsonObject.toString();

        // Return the JSON string
        return (jsonString);

    }



    //
    // Upgrade this feed to the stated version
    //
    // @param   toVersion       to version
    //
    // @return  true if the feed was upgraded, false if not
    //
    public boolean upgrade(final Version toVersion) {

        // We can only upgrade the feed
        if ( toVersion.getVersionID() <= this.getVersion().getVersionID() ) {
            return (false);
        }


        // Upgrade from version 1.0 to version 1.1
        if ( (this.getVersion() == Version.VERSION_1_0) && (toVersion == Version.VERSION_1_1) ) {

            // Upgrade the feed author
            if ( this.getAuthor() != null ) {
                this.authorList.add(this.getAuthor());
                this.author = null;
            }

            // Upgrade every item
            for ( final Item item : this.getItemList() ) {
                ((DefaultItem)item).upgrade(this.getVersion(), toVersion);
            }

            // Upgrade the version number
            this.version = toVersion;
        }


        // Feed was upgraded
        return (true);

    }


}
