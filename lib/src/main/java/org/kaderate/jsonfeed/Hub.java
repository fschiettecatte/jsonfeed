/**
 * Hub.java
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
 * Hub interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.4.0
 */
public interface Hub extends JSONString {


    /**
     * Get the type
     *
     * @return  the type, null if not specified
     */
    public String getType();


    /**
     * Set the type
     *
     * @param   type  the type
     */
    public void setType(String type);


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
     * Get hub extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    public JSONObject getExtensionsJSONObject();


    /**
     * Set the hub extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     */
    public void setExtensionsJSONObject(JSONObject extensionsJsonObject);


    /**
     * Check the validity of the hub object
     *
     * @return  true if the hub object is valid
     */
    public boolean isValid();


    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    public String toJSONString();


}
