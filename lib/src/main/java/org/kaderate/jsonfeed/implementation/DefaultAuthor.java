/**
 * DefaultAuthor.java
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
import org.kaderate.jsonfeed.Author;


/**
 * Default implementation for Author
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.4.0
 */
public class DefaultAuthor implements Author {


    /**
     * Name
     */
    private String name = null;


    /**
     * URL
     */
    private URL url = null;


    /**
     * Avatar (URL)
     */
    private URL avatar = null;


    /**
     * Extensions JSON object
     */
    private JSONObject extensionsJsonObject = new JSONObject();



    /**
     * Parse a JSON string and return the author
     *
     * @param   jsonString  the author as a JSON string
     *
     * @return  the author object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the avatar URL is invalid
     */
    protected static Author fromString(final String jsonString) throws MalformedURLException {

        /* Parse the JSON string to a JSON object */
        final JSONObject jsonObject = new JSONObject(jsonString);

        /* Parse the JSON string */
        final Author author = new DefaultAuthor(jsonObject);

        /* Return the author */
        return (author);

    }



    /**
     * Process the JSON array and return the author object list
     *
     * @param   jsonArray  the JSON array
     *
     * @return  the author object list
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the avatar URL is invalid
     */
    protected static List<Author> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

        /* Create the author list */
        final List<Author> authorList = new ArrayList<Author>();

        /* Process the JSON array */
        for ( final Object object : jsonArray ) {
            authorList.add(new DefaultAuthor((JSONObject)object));
        }

        /* Return the author list */
        return (authorList);

    }



    /**
     * Constructor
     *
     * @param   jsonObject  the author as a JSON object
     *
     * @exception   MalformedURLException
     *              If the URL is invalid
     *
     * @exception   MalformedURLException
     *              If the avatar URL is invalid
     */
    protected DefaultAuthor(final JSONObject jsonObject) throws MalformedURLException {

        /* Get the name */
        this.setName(jsonObject.optString("name", null));

        /* Get the URL */
        if ( jsonObject.has("url") == true ) {
            this.setUrl(new URL(jsonObject.getString("url")));
        }

        /* Get the avatar (URL) */
        if ( jsonObject.has("avatar") == true ) {
            this.setAvatar(new URL(jsonObject.getString("avatar")));
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
     * @param   name    the name
     * @param   url     the URL
     * @param   avatar  the avatar (URL)
     */
    public DefaultAuthor(final String name, final URL url, final URL avatar) {

        /* Set the name, url and avatar */
        this.setName(name);
        this.setUrl(url);
        this.setAvatar(avatar);

    }



    /**
     * Constructor
     */
    public DefaultAuthor() {

    }



    /**
     * Get the name
     *
     * @return  the name, null if not specified
     */
    @Override
    public String getName() {

        return (this.name);

    }



    /**
     * Set the name
     *
     * @param   name  the name
     */
    @Override
    public void setName(String name) {

       this.name = name;

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
     * Get the avatar (URL)
     *
     * @return  the avatar URL, null if not specified
     */
    @Override
    public URL getAvatar() {

        return (this.avatar);

    }



    /**
     * Set the avatar (URL)
     *
     * @param   avatar  the avatar URL
     */
    @Override
    public void setAvatar(URL avatar) {

       this.avatar = avatar;

    }



    /**
     * Get author extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        /* Return the extensions JSON object */
        return (this.extensionsJsonObject);

    }



    /**
     * Set the author extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     */
    @Override
    public void setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;

    }



    /**
     * Check the validity of the author object
     *
     * @return  true if the author object is valid
     */
    @Override
    public boolean isValid() {

        /* Check the author fields */
        if ( this.getName() != null ) {
            return (true);
        }

        if ( this.getUrl() != null ) {
            return (true);
        }

        if ( this.getAvatar() != null ) {
            return (true);
        }

        return (false);

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

        /* Add the name */
        if ( this.getName() != null ) {
            jsonObject.put("name", this.getName());
        }

        /* Add the url */
        if ( this.getUrl() != null ) {
            jsonObject.put("url", this.getUrl().toString());
        }

        /* Add the avatar (URL) */
        if ( this.getAvatar() != null ) {
            jsonObject.put("avatar", this.getAvatar().toString());
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
