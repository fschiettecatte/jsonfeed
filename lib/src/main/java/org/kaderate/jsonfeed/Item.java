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
package org.kaderate.jsonfeed;


/* Import Java stuff */
import java.net.URL;
import java.time.Instant;
import java.util.List;


/* Import JSON stuff */
import org.json.JSONObject;
import org.json.JSONString;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Attachment;
import org.kaderate.jsonfeed.Author;


/**
 * Item interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.6.0
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
     * Get the URL
     *
     * @return  the URL, null if not specified
     */
    public URL getUrl();


    /**
     * Set the URL
     *
     * @param   url  the URL
     *
     * @return  the item
     */
    public Item setUrl(URL url);


    /**
     * Get the external URL
     *
     * @return  the external URL, null if not specified
     */
    public URL getExternalUrl();


    /**
     * Set the external RL
     *
     * @param   externalUrl the external URL
     *
     * @return  the item
     */
    public Item setExternalUrl(URL externalUrl);


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
     * Get the image (URL)
     *
     * @return  the image URL, null if not specified
     */
    public URL getImage();


    /**
     * Set the image (URL)
     *
     * @param   image  the image URL
     *
     * @return  the item
     */
    public Item setImage(URL image);


    /**
     * Get the banner image (URL)
     *
     * @return  the banner image URL, null if not specified
     */
    public URL getBannerImage();


    /**
     * Set the banner image (URL)
     *
     * @param   bannerImage  the banner image URL
     *
     * @return  the item
     */
    public Item setBannerImage(URL bannerImage);


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
