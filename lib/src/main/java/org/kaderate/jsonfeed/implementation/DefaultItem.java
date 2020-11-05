/**
 * DefaultItem.java
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
import java.time.Instant;
import java.time.OffsetDateTime;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Attachment;
import org.kaderate.jsonfeed.Author;
import org.kaderate.jsonfeed.Feed;
import org.kaderate.jsonfeed.Hub;
import org.kaderate.jsonfeed.Item;
import org.kaderate.jsonfeed.implementation.DefaultAttachment;
import org.kaderate.jsonfeed.implementation.DefaultAuthor;
import org.kaderate.jsonfeed.implementation.DefaultHub;
import org.kaderate.jsonfeed.implementation.DefaultItem;
import org.kaderate.jsonfeed.Version;


/**
 * Default implementation for Item
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class DefaultItem implements Item, JSONString {


    /**
     * ID
     */
    private String id = null;


    /**
     * URL
     */
    private URL url = null;


    /**
     * External URL
     */
    private URL externalUrl = null;


    /**
     * Title
     */
    private String title = null;


    /**
     * Content text
     */
    private String contentText = null;


    /**
     * Content HTML
     */
    private String contentHtml = null;


    /**
     * Summary
     */
    private String summary = null;


    /**
     * Next URL
     */
    private URL nextUrl = null;


    /**
     * Image (URL)
     */
    private URL image = null;


    /**
     * Banner image (URL)
     */
    private URL bannerImage = null;


    /**
     * Date published
     */
    private Instant datePublished = null;


    /**
     * Date modified
     */
    private Instant dateModified = null;


    /**
     * Author
     */
    private Author author = null;


    /**
     * Author list (JSON feed 1.1 only)
     */
    private List<Author> authorList = new ArrayList<Author>();


    /**
     * ag list
     */
    private List<String> tagList = new ArrayList<String>();


    /**
     * Language (JSON feed 1.1 only)
     */
    private String language = null;


    /**
     * Attachment list
     */
    private List<Attachment> attachmentList = new ArrayList<Attachment>();



    /**
     * Parse a JSON string and return the item
     *
     * @param   jsonString  the feed as a JSON string
     *
     * @return  the item object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the external URL is invalid
     *
     * @exception   MalformedURLException
     *              If the image (URL) is invalid
     *
     * @exception   MalformedURLException
     *              If the banner image (URL) is invalid
     */
    public static Item fromString(final String jsonString) throws MalformedURLException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(jsonString);

        /* Parse the JSON string */
        final Item item = new DefaultItem(jsonObject);

        /* Return the item */
        return (item);

    }



    /**
     * Process the JSON array and return the item object list
     *
     * @param   jsonArray  the JSON array
     *
     * @return  the item object list
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the external URL is invalid
     *
     * @exception   MalformedURLException
     *              If the image (URL) is invalid
     *
     * @exception   MalformedURLException
     *              If the banner image (URL) is invalid
     */
    public static List<Item> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

        /* Create the item list */
        final List<Item> itemList = new ArrayList<Item>();

        /* Process the JSON array */
        for ( final Object object : jsonArray ) {
            itemList.add(new DefaultItem((JSONObject)object));
        }

        /* Return the item list */
        return (itemList);

    }


    /**
     * Constructor
     *
     * @param   jsonObject  the item as a JSON object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the external URL is invalid
     *
     * @exception   MalformedURLException
     *              If the image (URL) is invalid
     *
     * @exception   MalformedURLException
     *              If the banner image (URL) is invalid
     */
    protected DefaultItem(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the ID */
        this.setID(jsonObject.optString("id"));

        /* Get the URL */
        if ( jsonObject.has("url") == true ) {
            this.setUrl(new URL(jsonObject.getString("url")));
        }

        /* Get the external URL */
        if ( jsonObject.has("external_url") == true ) {
            this.setExternalUrl(new URL(jsonObject.getString("external_url")));
        }

        /* Get the title */
        this.setTitle(jsonObject.optString("title"));

        /* Get the content text */
        this.setContentText(jsonObject.optString("content_text"));

        /* Get the content HTML */
        this.setContentHtml(jsonObject.optString("content_html"));

        /* Get the summary */
        this.setSummary(jsonObject.optString("summary"));

        /* Get the image (URL) */
        if ( jsonObject.has("image") == true ) {
            this.setImage(new URL(jsonObject.getString("image")));
        }

        /* Get the banner image (URL) */
        if ( jsonObject.has("banner_image") == true ) {
            this.setBannerImage(new URL(jsonObject.getString("banner_image")));
        }

        /* Get the date published */
        if ( jsonObject.has("date_published") == true ) {
            this.setDatePublished(OffsetDateTime.parse(jsonObject.getString("date_published")).toInstant());
        }

        /* Get the date modified */
        if ( jsonObject.has("date_modified") == true ) {
            this.setDateModified(OffsetDateTime.parse(jsonObject.getString("date_modified")).toInstant());
        }

        /* Get the language */
        this.setLanguage(jsonObject.optString("language"));

        /* Get the author */
        if ( jsonObject.has("author") == true ) {
            this.setAuthor(new DefaultAuthor(jsonObject.getJSONObject("author")));
        }

        /* Get the authors */
        if ( jsonObject.has("authors") == true ) {
            this.setAuthorList(DefaultAuthor.fromJsonArray(jsonObject.getJSONArray("authors")));
        }

        /* Get the tags */
        if ( jsonObject.has("tags") == true ) {
            final List<String> tagList = new ArrayList<String>();
            for ( Object object : jsonObject.getJSONArray("tags") ) {
                tagList.add((String)object);
            }
            this.setTagList(tagList);
        }

        /* Get the attachments */
        if ( jsonObject.has("attachments") == true ) {
            this.setAttachmentList(DefaultAttachment.fromJsonArray(jsonObject.getJSONArray("attachments")));
        }

    }



    /**
     * Constructor
     *
     * @param   id  the ID
     */
    public DefaultItem(final String id) {

        /* Set the ID */
        this.setID(id);

    }



    /**
     * Constructor
     */
    public DefaultItem() {

    }



    /**
     * Get the ID
     *
     * @return  the ID, null if not specified
     */
    @Override
    public String getID() {

        return (this.id);

    }



    /**
     * Set the ID
     *
     * @param   id  the ID
     */
    @Override
    public void setID(String id) {

       this.id = id;

    }



    /**
     * Get the URL
     *
     * @return  the URL, null if not specified
     */
    @Override
    public URL getUrl() {

        return (this.url);

    }



    /**
     * Set the URL
     *
     * @param   url  the URL
     */
    @Override
    public void setUrl(URL url) {

       this.url = url;

    }



    /**
     * Get the external URL
     *
     * @return  the external URL, null if not specified
     */
    @Override
    public URL getExternalUrl() {

        return (this.externalUrl);

    }



    /**
     * Set the external URL
     *
     * @param   externalUrl     the external URL
     */
    @Override
    public void setExternalUrl(URL externalUrl) {

       this.externalUrl = externalUrl;

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
     * Get the content text
     *
     * @return  the content text, null if not specified
     */
    @Override
    public String getContentText() {

        return (this.contentText);

    }



    /**
     * Set the content text
     *
     * @param   contentText  the content text
     */
    @Override
    public void setContentText(String contentText) {

       this.contentText = contentText;

    }



    /**
     * Get the content HTML
     *
     * @return  the content HTML, null if not specified
     */
    @Override
    public String getContentHtml() {

        return (this.contentHtml);

    }



    /**
     * Set the content HTML
     *
     * @param   contentText  the content HTML
     */
    @Override
    public void setContentHtml(String contentHtml) {

       this.contentHtml = contentHtml;

    }



    /**
     * Get the summary
     *
     * @return  the summary, null if not specified
     */
    @Override
    public String getSummary() {

        return (this.summary);

    }



    /**
     * Set the summary
     *
     * @param   summary  the summary
     */
    @Override
    public void setSummary(String summary) {

       this.summary = summary;

    }



    /**
     * Get the image (URL)
     *
     * @return  the image URL, null if not specified
     */
    @Override
    public URL getImage() {

        return (this.image);

    }



    /**
     * Set the image (URL)
     *
     * @param   image     the image URL
     */
    @Override
    public void setImage(URL image) {

       this.image = image;

    }



    /**
     * Get the banner image (URL)
     *
     * @return  the banner image URL, null if not specified
     */
    @Override
    public URL getBannerImage() {

        return (this.bannerImage);

    }



    /**
     * Set the banner image (URL)
     *
     * @param   bannerImage     the banner image URL
     */
    @Override
    public void setBannerImage(URL bannerImage) {

       this.bannerImage = bannerImage;

    }



    /**
     * Get the date published
     *
     * @return  the date published, null if not specified
     */
    @Override
    public Instant getDatePublished() {

        return (this.datePublished);

    }



    /**
     * Set the date published
     *
     * @param   datePublished     the date published
     */
    @Override
    public void setDatePublished(Instant datePublished) {

       this.datePublished = datePublished;

    }



    /**
     * Get the date modified
     *
     * @return  the date modified, null if not specified
     */
    @Override
    public Instant getDateModified() {

        return (this.dateModified);

    }



    /**
     * Set the date modified
     *
     * @param   dateModified     the date modified
     */
    @Override
    public void setDateModified(Instant dateModified) {

       this.dateModified = dateModified;

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
     * Get the author list (JSON feed 1.1 only)
     *
     * @return  the author list, empty list if there are no authors
     */
    @Override
    public List<Author> getAuthorList() {

        return (this.authorList);

    }



    /**
     * Set the author list (JSON feed 1.1 only)
     *
     * @param   authorList  the author list
     */
    @Override
    public void setAuthorList(List<Author> authorList) {

       this.authorList = authorList;

    }



    /**
     * Get the tag list
     *
     * @return  the tag list, empty list if there are no tags
     */
    @Override
    public List<String> getTagList() {

        return (this.tagList);

    }



    /**
     * Set the tag list
     *
     * @param   tagList     the tag list
     */
    @Override
    public void setTagList(List<String> tagList) {

       this.tagList = tagList;

    }



    /**
     * Get the language (JSON feed 1.1 only)
     *
     * @return  the language, null if not specified
     */
    @Override
    public String getLanguage() {

        return (this.language);

    }



    /**
     * Set the language (JSON feed 1.1 only)
     *
     * @param   language  the language
     */
    @Override
    public void setLanguage(String language) {

       this.language = language;

    }



    /**
     * Get the attachment list
     *
     * @return  the attachment list, empty list if there are no attachments
     */
    @Override
    public List<Attachment> getAttachmentList() {

        return (this.attachmentList);

    }



    /**
     * Set the attachment list
     *
     * @param   attachmentList  the attachment list
     */
    @Override
    public void setAttachmentList(List<Attachment> attachmentList) {

       this.attachmentList = attachmentList;

    }



    /**
     * Check the validity of the item object
     *
     * @return  true if the item object is valid
     */
    @Override
    public boolean isValid() {

        /* Check the item fields */
        if ( this.getID() != null ) {
            return (true);
        }

        return (false);

    }



    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    @Override
    public String toJSONString() {

        /* Create the JSON object */
        final JSONObject jsonObject = new JSONObject();

        /* Add the ID */
        jsonObject.put("id", this.getID());

        /* Add the url */
        if ( this.getUrl() != null ) {
            jsonObject.put("url", this.getUrl().toString());
        }

        /* Add the external url */
        if ( this.getExternalUrl() != null ) {
            jsonObject.put("external", this.getExternalUrl().toString());
        }

        /* Add the title */
        if ( this.getTitle() != null ) {
            jsonObject.put("type", this.getTitle());
        }

        /* Add the content text */
        if ( this.getContentText() != null ) {
            jsonObject.put("content_text", this.getContentText());
        }

        /* Add the content HTML */
        if ( this.getContentHtml() != null ) {
            jsonObject.put("content_html", this.getContentHtml());
        }

        /* Add the summary */
        if ( this.getSummary() != null ) {
            jsonObject.put("summary", this.getSummary());
        }

        /* Add the image (URL) */
        if ( this.getImage() != null ) {
            jsonObject.put("image", this.getImage().toString());
        }

        /* Add the banner image (URL) */
        if ( this.getBannerImage() != null ) {
            jsonObject.put("banner_image", this.getBannerImage().toString());
        }

        /* Add the date published */
        if ( this.getDatePublished() != null ) {
            jsonObject.put("date_published", this.getDatePublished().toString());
        }

        /* Add the date modified */
        if ( this.getDateModified() != null ) {
            jsonObject.put("date_modified", this.getDateModified().toString());
        }

        /* Add the author */
        if ( this.getAuthor() != null ) {
            jsonObject.put("author", this.getAuthor());
        }

        /* Add the authors */
        if ( (this.getAuthorList() != null) && (this.getAuthorList().size() > 0) ) {
            jsonObject.put("authors", this.getAuthorList());
        }

        /* Add the tags */
        if ( (this.getTagList() != null) && (this.getTagList().size() > 0) ) {
            jsonObject.put("tags", this.getTagList());
        }

        /* Add the language */
        if ( this.getLanguage() != null ) {
            jsonObject.put("language", this.getLanguage());
        }

        /* Add the attachments */
        if ( (this.getAttachmentList() != null) && (this.getAttachmentList().size() > 0) ) {
            jsonObject.put("attachments", this.getAttachmentList());
        }

        /* Get the JSON string */
        final String jsonString = jsonObject.toString();

        /* Return the JSON string */
        return (jsonString);

    }



    /**
     * Upgrade this item to the passed version
     *
     * @param   fromVersion     from version
     * @param   toVersion       to version
     *
     * @return  true if the item was upgraded, false if not
     */
    public boolean upgrade(final Version fromVersion, final Version toVersion) {

        /* We can only upgrade the item */
        if ( toVersion.getVersionID() <= fromVersion.getVersionID() ) {
            return (false);
        }


        /* Upgrade from version 1.0 to version 1.1 */
        if ( (fromVersion == Version.VERSION_1_0) && (toVersion == Version.VERSION_1_1) ) {

            /* Upgrade the item author */
            if ( this.getAuthor() != null ) {
                this.authorList.add(this.getAuthor());
                this.author = null;
            }
        }


        /* Item was upgraded */
        return (true);

    }


}
