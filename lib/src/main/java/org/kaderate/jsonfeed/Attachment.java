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


/**
 * Attachment interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public interface Attachment {

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
     */
    public void setUrl(URL url);


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
     */
    public void setMimeType(String mimeType);


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
     * Get the size in bytes
     *
     * @return  the size in bytes, null if not specified
     */
    public Integer getSizeInBytes();


    /**
     * Set the size in bytes
     *
     * @param   sizeInBytes  the size in bytes
     */
    public void setSizeInBytes(Integer sizeInBytes);


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
     */
    public void setDurationInSeconds(Integer durationInSeconds);


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
