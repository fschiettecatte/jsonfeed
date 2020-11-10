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
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import org.kaderate.jsonfeed.Hub;


/**
 * Default implementation for Hub
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.5.0
 */
public class DefaultHub implements Hub {


    /**
     * Type
     */
    private String type = null;


    /**
     * URL
     */
    private URL url = null;


    /**
     * Extensions JSON object
     */
    private JSONObject extensionsJsonObject = new JSONObject();



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
    protected static Hub fromString(final String jsonString) throws MalformedURLException {

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
    protected static List<Hub> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

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
    protected DefaultHub(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the type */
        this.setType(jsonObject.optString("type", null));

        /* Get the URL */
        if ( jsonObject.has("url") == true ) {
            this.setUrl(new URL(jsonObject.getString("url")));
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
     * @param   type  the type
     * @param   url   the URL
     */
    public DefaultHub(final String type, final URL url) {

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
     *
     * @return  the hub
     */
    @Override
    public Hub setType(String type) {

        this.type = type;
        return (this);

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
     * @return  the hub
     */
    @Override
    public Hub setUrl(URL url) {

        this.url = url;
        return (this);

    }



    /**
     * Get hub extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        return (this.extensionsJsonObject);

    }



    /**
     * Set the hub extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the hub
     */
    @Override
    public Hub setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;
        return (this);

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
