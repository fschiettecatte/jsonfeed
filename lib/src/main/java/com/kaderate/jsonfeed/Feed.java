/**
 * Feed.java
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
package com.kaderate.jsonfeed;


/* Import Java stuff */
import java.net.URI;
import java.util.List;


/* Import JSON stuff */
import org.json.JSONObject;
import org.json.JSONString;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Author;
import com.kaderate.jsonfeed.Hub;
import com.kaderate.jsonfeed.Item;
import com.kaderate.jsonfeed.Version;


/**
 * Feed interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public interface Feed extends JSONString {


    /**
     * Get the version
     *
     * @return  the version, null if not specified
     */
    public Version getVersion();


    /**
     * Get the title
     *
     * @return  the title, null if not specified
     */
    public String getTitle();


    /**
     * Set the title
     *
     * @param   title  the title
     *
     * @return  the feed
     */
    public Feed setTitle(String title);


    /**
     * Get the home page URI
     *
     * @return  the home page URI, null if not specified
     */
    public URI getHomePageUri();


    /**
     * Set the home page URI
     *
     * @param   homePageUri the home page URI
     *
     * @return  the feed
     */
    public Feed setHomePageUri(URI homePageUri);


    /**
     * Get the feed URI
     *
     * @return  the feed URI, null if not specified
     */
    public URI getFeedUri();


    /**
     * Set the feed URI
     *
     * @param   feedUri the feed URI
     *
     * @return  the feed
     */
    public Feed setFeedUri(URI feedUri);


    /**
     * Get the description
     *
     * @return  the description, null if not specified
     */
    public String getDescription();


    /**
     * Set the description
     *
     * @param   description  the description
     *
     * @return  the feed
     */
    public Feed setDescription(String description);


    /**
     * Get the user comment
     *
     * @return  the user comment, null if not specified
     */
    public String getUserComment();


    /**
     * Set the user comment
     *
     * @param   userComment  the user comment
     *
     * @return  the feed
     */
    public Feed setUserComment(String userComment);


    /**
     * Get the next URI
     *
     * @return  the next URI, null if not specified
     */
    public URI getNextUri();


    /**
     * Set the next URI
     *
     * @param   nextUri the next URI
     *
     * @return  the feed
     */
    public Feed setNextUri(URI nextUri);


    /**
     * Get the icon (URI)
     *
     * @return  the icon URI, null if not specified
     */
    public URI getIcon();


    /**
     * Set the icon (URI)
     *
     * @param   icon the icon URI
     *
     * @return  the feed
     */
    public Feed setIcon(URI icon);


    /**
     * Get the favicon (URI)
     *
     * @return  the favicon URI, null if not specified
     */
    public URI getFavicon();


    /**
     * Set the favicon (URI)
     *
     * @param   favicon the favicon URI
     *
     * @return  the feed
     */
    public Feed setFavicon(URI favicon);


    /**
     * Get the author
     *
     * @return  the author, null if not specified
     */
    public Author getAuthor();


    /**
     * Set the author
     *
     * @param   author  the author
     *
     * @return  the feed
     */
    public Feed setAuthor(Author author);


    /**
     * Get the author list (JSON Feed 1.1 only)
     *
     * @return  the author list, empty list if there are no authors
     */
    public List<Author> getAuthorList();


    /**
     * Set the author list (JSON Feed 1.1 only)
     *
     * @param   authorList  the author list
     *
     * @return  the feed
     */
    public Feed setAuthorList(List<Author> authorList);


    /**
     * Get the language (JSON Feed 1.1 only)
     *
     * @return  the language, null if not specified
     */
    public String getLanguage();


    /**
     * Set the language
     *
     * @param   language  the language
     *
     * @return  the feed
     */
    public Feed setLanguage(String language);


    /**
     * Get the expired
     *
     * @return  the expired, null if not specified
     */
    public Boolean getExpired();


    /**
     * Set the expired
     *
     * @param   expired  the expired
     *
     * @return  the feed
     */
    public Feed setExpired(Boolean expired);


    /**
     * Get the hub list
     *
     * @return  the hub list, empty list if there are no hubs
     */
    public List<Hub> getHubList();


    /**
     * Set the hub list
     *
     * @param   hubList  the hub list
     *
     * @return  the feed
     */
    public Feed setHubList(List<Hub> hubList);


    /**
     * Get the item list
     *
     * @return  the item list, empty list if there are no hubs
     */
    public List<Item> getItemList();


    /**
     * Set the item list
     *
     * @param   itemList  the item list
     *
     * @return  the feed
     */
    public Feed setItemList(List<Item> itemList);


    /**
     * Get feed extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    public JSONObject getExtensionsJSONObject();


    /**
     * Set the feed extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the feed
     */
    public Feed setExtensionsJSONObject(JSONObject extensionsJsonObject);


    /**
     * Check the validity of the feed object
     *
     * @return  true if the feed object is valid
     */
    public boolean isValid();


    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    public String toJSONString();


}
