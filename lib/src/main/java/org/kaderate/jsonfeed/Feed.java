//
// Feed.java
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
package org.kaderate.jsonfeed;


// Import Java stuff
import java.net.URL;
import java.util.List;


// Import JSONFeed stuff
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.Version;


//
// Feed interface
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public interface Feed {


    //
    // Get the version
    //
    // @return  the version
    //
    public Version getVersion();


    //
    // Get the title
    //
    // @return  the title
    //
    public String getTitle();


    //
    // Get the home page URL
    //
    // @return  the home page URL
    //
    public URL getHomePageUrl();


    //
    // Get the feed URL
    //
    // @return  the feed URL
    //
    public URL getFeedUrl();


    //
    // Get the description
    //
    // @return  the description
    //
    public String getDescription();


    //
    // Get the user comment
    //
    // @return  the user comment
    //
    public String getUserComment();


    //
    // Get the next URL
    //
    // @return  the next URL
    //
    public URL getNextUrl();


    //
    // Get the icon (URL)
    //
    // @return  the icon (URL)
    //
    public URL getIcon();


    //
    // Get the favicon (URL)
    //
    // @return  the favicon (URL)
    //
    public URL getFavicon();


    //
    // Get the author
    //
    // @return  the author
    //
    public Author getAuthor();


    //
    // Get the author list (JSON feed 1.1 only)
    //
    // @return  the author list
    //
    public List<Author> getAuthorList();


    //
    // Get the language (JSON feed 1.1 only)
    //
    // @return  the language
    //
    public String getLanguage();


    //
    // Get the expired
    //
    // @return  the expired
    //
    public Boolean getExpired();


    //
    // Get the hub list
    //
    // @return  the hub list
    //
    public List<Hub> getHubList();


    //
    // Get the item list
    //
    // @return  the item list
    //
    public List<Item> getItemList();



    //
    // Check the validity of the feed object
    //
    // @return  true if the feed object is valid
    //
    public boolean isValid();


    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    public String toJSONString();


}
