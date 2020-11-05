/**
 * DefaultHub.java
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
import org.kaderate.jsonfeed.Hub;


/**
 * Default implementation for Hub
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class DefaultHub implements Hub, JSONString {


    /**
     * Type
     */
    private String type = null;


    /**
     * URL
     */
    private URL url = null;



    /**
     * Parse a JSON string and return the hub
     *
     * @param   jsonString  the hub as a JSON string
     *
     * @return  the hub object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    public static Hub fromString(final String jsonString) throws MalformedURLException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(jsonString);

        /* Parse the JSON string */
        final Hub hub = new DefaultHub(jsonObject);

        /* Return the hub */
        return (hub);

    }



    /**
     * Process the JSON array and return the hub object list
     *
     * @param   jsonArray  the JSON array
     *
     * @return  the hub object list
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    public static List<Hub> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

        /* Create the hub list */
        final List<Hub> hubList = new ArrayList<Hub>();

        /* Process the JSON array */
        for ( final Object object : jsonArray ) {
            hubList.add(new DefaultHub((JSONObject)object));
        }

        /* Return the hub list */
        return (hubList);

    }



    /**
     * Constructor
     *
     * @param   jsonObject  the hub as a JSON object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     */
    public DefaultHub(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the type */
        this.setType(jsonObject.optString("type"));

        /* Get the URL */
        if ( jsonObject.has("url") == true ) {
            this.setUrl(new URL(jsonObject.getString("url")));
        }

    }



    /**
     * Constructor
     *
     * @param   type  the type
     * @param   url   the URL
     */
    public DefaultHub(final String type, final URL url) {

        /* Set the type and URL */
        this.setType(type);
        this.setUrl(url);

    }



    /**
     * Constructor
     */
    public DefaultHub() {

    }



    /**
     * Get the type
     *
     * @return  the type, null if not specified
     */
    @Override
    public String getType() {

        return (this.type);

    }



    /**
     * Set the type
     *
     * @param   type  the type
     */
    @Override
    public void setType(String type) {

       this.type = type;

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
     * Check the validity of the hub object
     *
     * @return  true if the hub object is valid
     */
    @Override
    public boolean isValid() {

        /* Check the hub fields */
        if ( this.getType() == null ) {
            return (false);
        }

        if ( this.getUrl() == null ) {
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

        /* Add the type */
        jsonObject.put("type", this.getType());

        /* Add the URL */
        jsonObject.put("url", this.getUrl().toString());

        /* Get the JSON string */
        final String jsonString = jsonObject.toString();

        /* Return the JSON string */
        return (jsonString);

    }


}
