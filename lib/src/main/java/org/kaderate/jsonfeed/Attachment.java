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
     * Get the mime type
     *
     * @return  the mime type, null if not specified
     */
    public String getMimeType();


    /**
     * Get the title
     *
     * @return  the title, null if not specified
     */
    public String getTitle();


    /**
     * Get the size in bytes
     *
     * @return  the size in bytes, null if not specified
     */
    public Integer getSizeInBytes();


    /**
     * Get the duration in seconds
     *
     * @return  the duration in seconds, null if not specified
     */
    public Integer getDurationInSeconds();


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
