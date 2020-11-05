//
// Item.java
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
import java.time.Instant;
import java.util.List;


// Import JSONFeed stuff
import org.kaderate.jsonfeed.Attachment;
import org.kaderate.jsonfeed.Author;


//
// Item interface
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public interface Item {


    //
    // Get the ID
    //
    // @return  the ID
    //
    public String getID();


    //
    // Get the URL
    //
    // @return  the URL
    //
    public URL getUrl();


    //
    // Get the external URL
    //
    // @return  the external URL
    //
    public URL getExternalUrl();


    //
    // Get the title
    //
    // @return  the title
    //
    public String getTitle();


    //
    // Get the content text
    //
    // @return  the content text
    //
    public String getContentText();


    //
    // Get the content HTML
    //
    // @return  the content HTML
    //
    public String getContentHtml();


    //
    // Get the summary
    //
    // @return  the summary
    //
    public String getSummary();


    //
    // Get the image (URL)
    //
    // @return  the image (URL)
    //
    public URL getImage();


    //
    // Get the banner image (URL)
    //
    // @return  the banner image (URL)
    //
    public URL getBannerImage();


    //
    // Get the date published
    //
    // @return  the date published
    //
    public Instant getDatePublished();


    //
    // Get the date modified
    //
    // @return  the date modified
    //
    public Instant getDateModified();


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
    // Get the tag list
    //
    // @return  the tag list
    //
    public List<String> getTagList();


    //
    // Get the language (JSON feed 1.1 only)
    //
    // @return  the language
    //
    public String getLanguage();


    //
    // Get the attachment list
    //
    // @return  the attachment list
    //
    public List<Attachment> getAttachmentList();



    //
    // Check the validity of the item object
    //
    // @return  true if the item object is valid
    //
    public boolean isValid();


    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    public String toJSONString();


}
