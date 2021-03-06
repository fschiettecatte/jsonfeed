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
package com.kaderate.jsonfeed.implementation;


/* Import Java stuff */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Attachment;


/**
 * Default implementation for Attachment
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public class DefaultAttachment implements Attachment {


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
     * Extensions JSON object
     */
    private JSONObject extensionsJsonObject = new JSONObject();



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
    protected static Attachment fromString(final String jsonString) throws MalformedURLException {

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
    protected static List<Attachment> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

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
            this.setUrl(new URL(jsonObject.getString("url")));
        }

        /* Get the mime type */
        this.setMimeType(jsonObject.optString("mime_type", null));

        /* Get the title */
        this.setTitle(jsonObject.optString("title", null));

        /* Get the size in bytes */
        if ( jsonObject.has("size_in_bytes") == true ) {
            this.setSizeInBytes(jsonObject.getInt("size_in_bytes"));
        }

        /* Get the duration in seconds */
        if ( jsonObject.has("duration_in_seconds") == true ) {
            this.setDurationInSeconds(jsonObject.optInt("duration_in_seconds"));
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
     * @param   url         the URL
     * @param   mimeType    the mime type
     */
    public DefaultAttachment(final URL url, final String mimeType) {

        this.setUrl(url);
        this.setMimeType(mimeType);

    }



    /**
     * Constructor
     */
    public DefaultAttachment() {

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
     *
     * @return  the attachment
     */
    @Override
    public Attachment setUrl(URL url) {

        this.url = url;
        return (this);

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
     * Set the mime type
     *
     * @param   mimeType  the mime type
     *
     * @return  the attachment
     */
    @Override
    public Attachment setMimeType(String mimeType) {

        this.mimeType = mimeType;
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
     * @return  the attachment
     */
    @Override
    public Attachment setTitle(String title) {

        this.title = title;
        return (this);

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
     * Set the size in bytes
     *
     * @param   sizeInBytes  the size in bytes
     *
     * @return  the attachment
     */
    @Override
    public Attachment setSizeInBytes(Integer sizeInBytes) {

        this.sizeInBytes = sizeInBytes;
        return (this);

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
     * Set the duration in seconds
     *
     * @param   durationInSeconds  the duration in seconds
     *
     * @return  the attachment
     */
    @Override
    public Attachment setDurationInSeconds(Integer durationInSeconds) {

        this.durationInSeconds = durationInSeconds;
        return (this);

    }



    /**
     * Get attachment extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        return (this.extensionsJsonObject);

    }



    /**
     * Set the attachment extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the attachment
     */
    @Override
    public Attachment setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;
        return (this);

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
