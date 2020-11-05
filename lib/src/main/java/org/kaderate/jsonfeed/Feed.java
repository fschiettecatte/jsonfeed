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
package org.kaderate.jsonfeed;


/* Import Java stuff */
import java.net.URL;
import java.util.List;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.Version;


/**
 * Feed interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public interface Feed {


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
     */
    public void setTitle(String title);


    /**
     * Get the home page URL
     *
     * @return  the home page URL, null if not specified
     */
    public URL getHomePageUrl();


    /**
     * Set the home page URL
     *
     * @param   homePageUrl the home page URL
     */
    public void setHomePageUrl(URL homePageUrl);


    /**
     * Get the feed URL
     *
     * @return  the feed URL, null if not specified
     */
    public URL getFeedUrl();


    /**
     * Set the feed URL
     *
     * @param   feedUrl the feed URL
     */
    public void setFeedUrl(URL feedUrl);


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
     */
    public void setDescription(String description);


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
     */
    public void setUserComment(String userComment);


    /**
     * Get the next URL
     *
     * @return  the next URL, null if not specified
     */
    public URL getNextUrl();


    /**
     * Set the next URL
     *
     * @param   nextUrl the next URL
     */
    public void setNextUrl(URL nextUrl);


    /**
     * Get the icon (URL)
     *
     * @return  the icon URL, null if not specified
     */
    public URL getIcon();


    /**
     * Set the icon (URL)
     *
     * @param   icon the icon URL
     */
    public void setIcon(URL icon);


    /**
     * Get the favicon (URL)
     *
     * @return  the favicon URL, null if not specified
     */
    public URL getFavicon();


    /**
     * Set the favicon (URL)
     *
     * @param   favicon the favicon URL
     */
    public void setFavicon(URL favicon);


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
     */
    public void setAuthor(Author author);


    /**
     * Get the author list (JSON feed 1.1 only)
     *
     * @return  the author list, empty list if there are no authors
     */
    public List<Author> getAuthorList();


    /**
     * Set the author list (JSON feed 1.1 only)
     *
     * @param   authorList  the author list
     */
    public void setAuthorList(List<Author> authorList);


    /**
     * Get the language (JSON feed 1.1 only)
     *
     * @return  the language, null if not specified
     */
    public String getLanguage();


    /**
     * Set the language
     *
     * @param   language  the language
     */
    public void setLanguage(String language);


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
     */
    public void setExpired(Boolean expired);


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
     */
    public void setHubList(List<Hub> hubList);


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
     */
    public void setItemList(List<Item> itemList);



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
