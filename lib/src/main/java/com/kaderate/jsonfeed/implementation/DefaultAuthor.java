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
package com.kaderate.jsonfeed.implementation;


/* Import Java stuff */
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JSONFeed stuff */
import com.kaderate.jsonfeed.Author;


/**
 * Default implementation for Author
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 1.0.0
 */
public class DefaultAuthor implements Author {


    /**
     * Name
     */
    private String name = null;


    /**
     * URI
     */
    private URI uri = null;


    /**
     * Avatar (URI)
     */
    private URI avatar = null;


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
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the avatar URI is invalid
     */
    protected static Author fromString(final String jsonString) throws URISyntaxException {

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
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the avatar URI is invalid
     */
    protected static List<Author> fromJsonArray(final JSONArray jsonArray) throws URISyntaxException {

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
     * @exception   URISyntaxException
     *              If the URI is invalid
     *
     * @exception   URISyntaxException
     *              If the avatar URI is invalid
     */
    protected DefaultAuthor(final JSONObject jsonObject) throws URISyntaxException {

        /* Get the name */
        this.setName(jsonObject.optString("name", null));

        /* Get the URI */
        if ( jsonObject.has("url") == true ) {
            this.setUri(new URI(jsonObject.getString("url")));
        }

        /* Get the avatar (URI) */
        if ( jsonObject.has("avatar") == true ) {
            this.setAvatar(new URI(jsonObject.getString("avatar")));
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
     * @param   uri     the URI
     * @param   avatar  the avatar (URI)
     */
    public DefaultAuthor(final String name, final URI uri, final URI avatar) {

        this.setName(name);
        this.setUri(uri);
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
     *
     * @return  the author
     */
    @Override
    public Author setName(String name) {

        this.name = name;
        return (this);

    }



    /**
     * Get the URI
     *
     * @return  the URI, null if not specified
     */
    @Override
    public URI getUri() {

        return (this.uri);

    }



    /**
     * Set the URI
     *
     * @param   uri  the URI
     *
     * @return  the author
     */
    @Override
    public Author setUri(URI uri) {

        this.uri = uri;
        return (this);

    }



    /**
     * Get the avatar (URI)
     *
     * @return  the avatar URI, null if not specified
     */
    @Override
    public URI getAvatar() {

        return (this.avatar);

    }



    /**
     * Set the avatar (URI)
     *
     * @param   avatar  the avatar URI
     *
     * @return  the author
     */
    @Override
    public Author setAvatar(URI avatar) {

        this.avatar = avatar;
        return (this);

    }



    /**
     * Get author extensions as a JSON object
     *
     * @return  the extensions JSON object
     */
    @Override
    public JSONObject getExtensionsJSONObject() {

        return (this.extensionsJsonObject);

    }



    /**
     * Set the author extensions JSON object
     *
     * @param   extensionsJsonObject  the extensions JSON object
     *
     * @return  the author
     */
    @Override
    public Author setExtensionsJSONObject(JSONObject extensionsJsonObject) {

        this.extensionsJsonObject = extensionsJsonObject;
        return (this);

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

        if ( this.getUri() != null ) {
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

        /* Add the URI */
        if ( this.getUri() != null ) {
            jsonObject.put("url", this.getUri().toString());
        }

        /* Add the avatar (URI) */
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
