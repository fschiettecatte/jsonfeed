/**
 * Author.java
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
import java.net.URL;


/* Import JSON stuff */
import org.json.JSONObject;
import org.json.JSONString;


/**
 * Author interface
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public interface Author extends JSONString {


    /**
     * Get the name
     *
     * @return  the name, null if not specified
     */
    public String getName();


    /**
     * Set the name
     *
     * @param   name  the name
     *
     * @return  the author
     */
    public Author setName(String name);


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
     * @return  the author
     */
    public Author setUrl(URL url);


    /**
     * Get the avatar (URL)
     *
     * @return  the avatar URL, null if not specified
     */
    public URL getAvatar();


    /**
     * Set the avatar (URL)
     *
     * @param   avatar  the avatar URL
     *
     * @return  the author
     */
    public Author setAvatar(URL avatar);


    /**
     * Get author extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    public JSONObject getExtensionsJSONObject();


    /**
     * Set the author extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the author
     */
    public Author setExtensionsJSONObject(JSONObject extensionsJsonObject);


    /**
     * Check the validity of the author object
     *
     * @return  true if the author object is valid
     */
    public boolean isValid();


    /**
     * Return the JSON string representation for this object
     *
     * @return      the JSON string representation for this object
     */
    public String toJSONString();


}
