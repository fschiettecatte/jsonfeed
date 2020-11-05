/**
 * DefaultAttachment.java
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


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Attachment;


/**
 * Default implementation for Attachment
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class DefaultAttachment implements Attachment, JSONString {


    /**
     * URL
     */
    private URL url = null;


    /**
     * Mime type
     */
    private String mimeType = null;


    /**
     * Title
     */
    private String title = null;


    /**
     * Size in bytes
     */
    private Integer sizeInBytes = null;


    /**
     * Duration in seconds
     */
    private Integer durationInSeconds = null;



    /**
     * Parse a JSON string and return the attachment
     *
     * @param   jsonString  the author as a JSON string
     *
     * @return  the author object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    public static Attachment fromString(final String jsonString) throws MalformedURLException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(jsonString);

        /* Parse the JSON string */
        final Attachment attachment = new DefaultAttachment(jsonObject);

        /* Return the attachment */
        return (attachment);

    }



    /**
     * Process the JSON array and return the attachment object list
     *
     * @param   jsonArray  the JSON array
     *
     * @return  the attachment object list
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    public static List<Attachment> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

        /* Create the attachment list */
        final List<Attachment> attachmentList = new ArrayList<Attachment>();

        /* Process the JSON array */
        for ( final Object object : jsonArray ) {
            attachmentList.add(new DefaultAttachment((JSONObject)object));
        }

        /* Return the attachment list */
        return (attachmentList);

    }



    /**
     * Constructor
     *
     * @param   jsonObject  the attachment as a JSON object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    protected DefaultAttachment(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the URL */
        if ( jsonObject.has("url") == true ) {
            this.url = new URL(jsonObject.getString("url"));
        }

        /* Get the mime type */
        this.mimeType = jsonObject.optString("mime_type");

        /* Get the title */
        this.title = jsonObject.optString("title");

        /* Get the size in bytes */
        if ( jsonObject.has("size_in_bytes") == true ) {
            this.sizeInBytes = jsonObject.getInt("size_in_bytes");
        }

        /* Get the duration in seconds */
        if ( jsonObject.has("duration_in_seconds") == true ) {
            this.durationInSeconds = jsonObject.getInt("duration_in_seconds");
        }

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
     * Get the mime type
     *
     * @return  the mime type, null if not specified
     */
    @Override
    public String getMimeType() {

        return (this.mimeType);

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
     * Get the size in bytes
     *
     * @return  the size in bytes, null if not specified
     */
    @Override
    public Integer getSizeInBytes() {

        return (this.sizeInBytes);

    }



    /**
     * Get the duration in seconds
     *
     * @return  the duration in seconds, null if not specified
     */
    @Override
    public Integer getDurationInSeconds() {

        return (this.durationInSeconds);

    }



    /**
     * Check the validity of the attachment object
     *
     * @return  true if the attachment object is valid
     */
    @Override
    public boolean isValid() {

        /* Check the attachment fields */
        if ( this.getUrl() == null ) {
            return (false);
        }

        if ( this.getMimeType() == null ) {
            return (false);
        }

        return (true);

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

        /* Add the url */
        jsonObject.put("url", this.getUrl().toString());

        /* Add the mime type */
        jsonObject.put("type", this.getMimeType());

        /* Add the title */
        if ( this.getTitle() != null ) {
            jsonObject.put("type", this.getTitle());
        }

        /* Add the size in bytes */
        if ( this.getSizeInBytes() != null ) {
            jsonObject.put("size_in_bytes", this.getSizeInBytes());
        }

        /* Add the duration in seconds */
        if ( this.getDurationInSeconds() != null ) {
            jsonObject.put("duration_in_seconds", this.getDurationInSeconds());
        }

        /* Get the JSON string */
        final String jsonString = jsonObject.toString();

        /* Return the JSON string */
        return (jsonString);

    }


}
