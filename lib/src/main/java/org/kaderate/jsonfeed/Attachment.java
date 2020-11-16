/**
 * Attachment.java
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


/* Import JSON stuff */
import org.json.JSONObject;
import org.json.JSONString;


/**
 * Attachment interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.6.0
 */
public interface Attachment extends JSONString {

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
     * @return  the attachment
     */
    public Attachment setUrl(URL url);


    /**
     * Get the mime type
     *
     * @return  the mime type, null if not specified
     */
    public String getMimeType();


    /**
     * Set the mime type
     *
     * @param   mimeType  the mime type
     *
     * @return  the attachment
     */
    public Attachment setMimeType(String mimeType);


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
     * @return  the attachment
     */
    public Attachment setTitle(String title);


    /**
     * Get the size in bytes
     *
     * @return  the size in bytes, null if not specified
     */
    public Integer getSizeInBytes();


    /**
     * Set the size in bytes
     *
     * @param   sizeInBytes  the size in bytes
     *
     * @return  the attachment
     */
    public Attachment setSizeInBytes(Integer sizeInBytes);


    /**
     * Get the duration in seconds
     *
     * @return  the duration in seconds, null if not specified
     */
    public Integer getDurationInSeconds();


    /**
     * Set the duration in seconds
     *
     * @param   durationInSeconds  the duration in seconds
     *
     * @return  the attachment
     */
    public Attachment setDurationInSeconds(Integer durationInSeconds);


    /**
     * Get attachment extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    public JSONObject getExtensionsJSONObject();


    /**
     * Set the attachment extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the attachment
     */
    public Attachment setExtensionsJSONObject(JSONObject extensionsJsonObject);


    /**
     * Check the validity of the attachment object
     *
     * @return  true if the attachment object is valid
     */
    public boolean isValid();


    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    public String toJSONString();


}
