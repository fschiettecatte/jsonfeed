/**
 * Item.java
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
import java.time.Instant;
import java.util.List;


/* Import JSON stuff */
import org.json.JSONObject;
import org.json.JSONString;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Attachment;
import com.kaderate.jsonfeed.Author;


/**
 * Item interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public interface Item extends JSONString {


    /**
     * Get the ID
     *
     * @return  the ID, null if not specified
     */
    public String getID();


    /**
     * Set the ID
     *
     * @param   id  the ID
     *
     * @return  the item
     */
    public Item setID(String id);


    /**
     * Get the URI
     *
     * @return  the URI, null if not specified
     */
    public URI getUri();


    /**
     * Set the URI
     *
     * @param   uri  the URI
     *
     * @return  the item
     */
    public Item setUri(URI uri);


    /**
     * Get the external URI
     *
     * @return  the external URI, null if not specified
     */
    public URI getExternalUri();


    /**
     * Set the external RL
     *
     * @param   externalUri the external URI
     *
     * @return  the item
     */
    public Item setExternalUri(URI externalUri);


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
     * @return  the item
     */
    public Item setTitle(String title);


    /**
     * Get the content text
     *
     * @return  the content text, null if not specified
     */
    public String getContentText();


    /**
     * Set the content text
     *
     * @param   contentText  the content text
     *
     * @return  the item
     */
    public Item setContentText(String contentText);


    /**
     * Get the content HTML
     *
     * @return  the content HTML, null if not specified
     */
    public String getContentHtml();


    /**
     * Set the content HTML
     *
     * @param   contentHtml  the content HTML
     *
     * @return  the item
     */
    public Item setContentHtml(String contentHtml);


    /**
     * Get the summary
     *
     * @return  the summary, null if not specified
     */
    public String getSummary();


    /**
     * Set the summary
     *
     * @param   summary  the summary
     *
     * @return  the item
     */
    public Item setSummary(String summary);


    /**
     * Get the image (URI)
     *
     * @return  the image URI, null if not specified
     */
    public URI getImage();


    /**
     * Set the image (URI)
     *
     * @param   image  the image URI
     *
     * @return  the item
     */
    public Item setImage(URI image);


    /**
     * Get the banner image (URI)
     *
     * @return  the banner image URI, null if not specified
     */
    public URI getBannerImage();


    /**
     * Set the banner image (URI)
     *
     * @param   bannerImage  the banner image URI
     *
     * @return  the item
     */
    public Item setBannerImage(URI bannerImage);


    /**
     * Get the date published
     *
     * @return  the date published, null if not specified
     */
    public Instant getDatePublished();


    /**
     * Set the date published
     *
     * @param   datePublished  the date published
     *
     * @return  the item
     */
    public Item setDatePublished(Instant datePublished);


    /**
     * Get the date modified
     *
     * @return  the date modified, null if not specified
     */
    public Instant getDateModified();


    /**
     * Set the date modified
     *
     * @param   dateModified  the date modified
     *
     * @return  the item
     */
    public Item setDateModified(Instant dateModified);


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
     * @return  the item
     */
    public Item setAuthor(Author author);


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
     * @return  the item
     */
    public Item setAuthorList(List<Author> authorList);


    /**
     * Get the tag list
     *
     * @return  the tag list, empty list if there are no tags
     */
    public List<String> getTagList();


    /**
     * Set the tag list
     *
     * @param   tagList  the tag list
     *
     * @return  the item
     */
    public Item setTagList(List<String> tagList);


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
     * @return  the item
     */
    public Item setLanguage(String language);


    /**
     * Get the attachment list
     *
     * @return  the attachment list, empty list if there are no attachments
     */
    public List<Attachment> getAttachmentList();


    /**
     * Set the attachment list
     *
     * @param   attachmentList  the attachment list
     *
     * @return  the item
     */
    public Item setAttachmentList(List<Attachment> attachmentList);


    /**
     * Get item extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    public JSONObject getExtensionsJSONObject();


    /**
     * Set the item extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the item
     */
    public Item setExtensionsJSONObject(JSONObject extensionsJsonObject);


    /**
     * Check the validity of the item object
     *
     * @return  true if the item object is valid
     */
    public boolean isValid();


    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    public String toJSONString();


}
