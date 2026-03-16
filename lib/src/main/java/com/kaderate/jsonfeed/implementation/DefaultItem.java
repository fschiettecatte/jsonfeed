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
package com.kaderate.jsonfeed.implementation;


/* Import Java stuff */
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Attachment;
import com.kaderate.jsonfeed.Author;
import com.kaderate.jsonfeed.Feed;
import com.kaderate.jsonfeed.Hub;
import com.kaderate.jsonfeed.Item;
import com.kaderate.jsonfeed.implementation.DefaultAttachment;
import com.kaderate.jsonfeed.implementation.DefaultAuthor;
import com.kaderate.jsonfeed.implementation.DefaultHub;
import com.kaderate.jsonfeed.implementation.DefaultItem;
import com.kaderate.jsonfeed.Version;


/**
 * Default implementation for Item
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public class DefaultItem implements Item {


    /**
     * ID
     */
    private String id = null;


    /**
     * URI
     */
    private URI uri = null;


    /**
     * External URI
     */
    private URI externalUri = null;


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
     * Next URI
     */
    private URI nextUri = null;


    /**
     * Image (URI)
     */
    private URI image = null;


    /**
     * Banner image (URI)
     */
    private URI bannerImage = null;


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
     * Author list (JSON Feed 1.1 only)
     */
    private List<Author> authorList = new ArrayList<Author>();


    /**
     * Tag list
     */
    private List<String> tagList = new ArrayList<String>();


    /**
     * Language (JSON Feed 1.1 only)
     */
    private String language = null;


    /**
     * Attachment list
     */
    private List<Attachment> attachmentList = new ArrayList<Attachment>();


    /**
     * Extensions JSON object
     */
    private JSONObject extensionsJsonObject = new JSONObject();



    /**
     * Parse a JSON string and return the item
     *
     * @param   jsonString  the item as a JSON string
     *
     * @return  the item object
     *
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the external URI is invalid
     *
     * @exception   URISyntaxException
     *              If the image (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the banner image (URI) is invalid
     */
    protected static Item fromString(final String jsonString) throws URISyntaxException {

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
     * @param   jsonArray   the JSON array
     *
     * @return  the item object list
     *
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the external URI is invalid
     *
     * @exception   URISyntaxException
     *              If the image (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the banner image (URI) is invalid
     */
    protected static List<Item> fromJsonArray(final JSONArray jsonArray) throws URISyntaxException {

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
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the external URI is invalid
     *
     * @exception   URISyntaxException
     *              If the image (URI) is invalid
     *
     * @exception   URISyntaxException
     *              If the banner image (URI) is invalid
     */
    protected DefaultItem(final JSONObject jsonObject) throws URISyntaxException {

        /* Get the ID */
        this.setID(jsonObject.optString("id", null));

        /* Get the URI */
        if ( jsonObject.has("url") == true ) {
            this.setUri(new URI(jsonObject.getString("url")));
        }

        /* Get the external URI */
        if ( jsonObject.has("external_url") == true ) {
            this.setExternalUri(new URI(jsonObject.getString("external_url")));
        }

        /* Get the title */
        this.setTitle(jsonObject.optString("title", null));

        /* Get the content text */
        this.setContentText(jsonObject.optString("content_text", null));

        /* Get the content HTML */
        this.setContentHtml(jsonObject.optString("content_html", null));

        /* Get the summary */
        this.setSummary(jsonObject.optString("summary", null));

        /* Get the image (URI) */
        if ( jsonObject.has("image") == true ) {
            this.setImage(new URI(jsonObject.getString("image")));
        }

        /* Get the banner image (URI) */
        if ( jsonObject.has("banner_image") == true ) {
            this.setBannerImage(new URI(jsonObject.getString("banner_image")));
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
        if ( jsonObject.has("language") == true ) {
            this.setLanguage(jsonObject.getString("language"));
        }

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


        /* Get the extensions */
        for ( final Map.Entry<String, Object> entry : jsonObject.toMap().entrySet() ) {
            if ( entry.getKey().startsWith("_") == true ) {
                this.extensionsJsonObject.put(entry.getKey(), entry.getValue());
            }
        }

    }



    /**
     * Constructor
     *
     * @param   id          the ID
     */
    public DefaultItem(final String id) {

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
     *
     * @return  the item
     */
    @Override
    public Item setID(String id) {

        this.id = id;
        return (this);

    }



    /**
     * Get the URI
     *
     * @return  the URI, null if not specified
     */
    @Override
    public URI getUri() {

        return (this.uri);

    }



    /**
     * Set the URI
     *
     * @param   uri  the URI
     *
     * @return  the item
     */
    @Override
    public Item setUri(URI uri) {

        this.uri = uri;
        return (this);

    }



    /**
     * Get the external URI
     *
     * @return  the external URI, null if not specified
     */
    @Override
    public URI getExternalUri() {

        return (this.externalUri);

    }



    /**
     * Set the external URI
     *
     * @param   externalUri     the external URI
     *
     * @return  the item
     */
    @Override
    public Item setExternalUri(URI externalUri) {

        this.externalUri = externalUri;
        return (this);

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
     * @return  the item
     */
    @Override
    public Item setTitle(String title) {

        this.title = title;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setContentText(String contentText) {

        this.contentText = contentText;
        return (this);

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
     * @param   contentHtml  the content HTML
     *
     * @return  the item
     */
    @Override
    public Item setContentHtml(String contentHtml) {

        this.contentHtml = contentHtml;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setSummary(String summary) {

        this.summary = summary;
        return (this);

    }



    /**
     * Get the image (URI)
     *
     * @return  the image URI, null if not specified
     */
    @Override
    public URI getImage() {

        return (this.image);

    }



    /**
     * Set the image (URI)
     *
     * @param   image     the image URI
     *
     * @return  the item
     */
    @Override
    public Item setImage(URI image) {

        this.image = image;
        return (this);

    }



    /**
     * Get the banner image (URI)
     *
     * @return  the banner image URI, null if not specified
     */
    @Override
    public URI getBannerImage() {

        return (this.bannerImage);

    }



    /**
     * Set the banner image (URI)
     *
     * @param   bannerImage     the banner image URI
     *
     * @return  the item
     */
    @Override
    public Item setBannerImage(URI bannerImage) {

        this.bannerImage = bannerImage;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setDatePublished(Instant datePublished) {

        this.datePublished = datePublished;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setDateModified(Instant dateModified) {

        this.dateModified = dateModified;
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
     * @return  the item
     */
    @Override
    public Item setAuthor(Author author) {

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
     * @return  the item
     */
    @Override
    public Item setAuthorList(List<Author> authorList) {

        this.authorList = authorList;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setTagList(List<String> tagList) {

        this.tagList = tagList;
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
     * @return  the item
     */
    @Override
    public Item setLanguage(String language) {

        this.language = language;
        return (this);

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
     *
     * @return  the item
     */
    @Override
    public Item setAttachmentList(List<Attachment> attachmentList) {

        this.attachmentList = attachmentList;
        return (this);

    }



    /**
     * Get item extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        return (this.extensionsJsonObject);

    }



    /**
     * Set the item extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the item
     */
    @Override
    public Item setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;
        return (this);

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
     * Upgrade this item to the passed version
     *
     * @param   toVersion       to version
     *
     * @return  true if the item was upgraded
     */
    public boolean upgrade(final Version toVersion) {

        /* Upgrade flag */
        boolean itemUpgraded = false;

        /* Upgrade to version 1.1 */
        if ( toVersion == Version.VERSION_1_1 ) {

            /* Upgrade the item author */
            if ( this.getAuthor() != null ) {
                this.authorList.add(this.getAuthor());
                this.setAuthor(null);
                itemUpgraded = true;
            }
        }

        return (itemUpgraded);

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

        /* Add the URI */
        if ( this.getUri() != null ) {
            jsonObject.put("url", this.getUri().toString());
        }

        /* Add the external URI */
        if ( this.getExternalUri() != null ) {
            jsonObject.put("external", this.getExternalUri().toString());
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

        /* Add the image (URI) */
        if ( this.getImage() != null ) {
            jsonObject.put("image", this.getImage().toString());
        }

        /* Add the banner image (URI) */
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

        /* Add the authors */
        if ( (this.getAuthorList() != null) && (this.getAuthorList().size() > 0) ) {
            jsonObject.put("authors", this.getAuthorList());
        }
        /* Add the author */
        else if ( this.getAuthor() != null ) {
            jsonObject.put("author", this.getAuthor());
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
