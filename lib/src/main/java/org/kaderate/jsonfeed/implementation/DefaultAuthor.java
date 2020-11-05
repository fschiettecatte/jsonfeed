//
// DefaultAuthor.java
//
// @author Francois Schiettecatte
// @version 1.0
//
// Change History:
//    - Nov 3, 2020 - File was created
//
// TBD:
//    -
//
//
//


// Package location
package org.kaderate.jsonfeed.implementation;


// Import Java stuff
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


// Import JSON stuff
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;


// Import JSONFeed stuff
import org.kaderate.jsonfeed.Author;


//
// Default implementation for Author
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public class DefaultAuthor implements Author, JSONString {


    //
    // Name
    //
    private String name = null;


    //
    // URL
    //
    private URL url = null;


    //
    // Avatar (URL)
    //
    private URL avatar = null;



    //
    // Parse a JSON string and return the author
    //
    // @param   jsonString  the author as a JSON string
    //
    // @return  the author object
    //
    // @exception   MalformedURLException
    //              If the URL is invalid
    //
    // @exception   MalformedURLException
    //              If the avatar URL is invalid
    //
    public static Author fromString(final String jsonString) throws MalformedURLException {

        // Parse the JSON string to a JSON object
        final JSONObject jsonObject = new JSONObject(jsonString);

        // Parse the JSON string
        final Author author = new DefaultAuthor(jsonObject);

        // Return the author
        return (author);

    }



    //
    // Process the JSON array and return the author object list
    //
    // @param   jsonArray  the JSON array
    //
    // @return  the author object list
    //
    // @exception   MalformedURLException
    //              If the URL is invalid
    //
    // @exception   MalformedURLException
    //              If the avatar URL is invalid
    //
    public static List<Author> fromJsonArray(final JSONArray jsonArray) throws MalformedURLException {

        // Create the author list
        final List<Author> authorList = new ArrayList<Author>();

        // Process the JSON array
        for ( final Object object : jsonArray ) {
            authorList.add(new DefaultAuthor((JSONObject)object));
        }

        // Return the author list
        return (authorList);

    }



    //
    // Constructor
    //
    // @param   jsonObject  the author as a JSON object
    //
    // @return  the author object
    //
    // @exception   MalformedURLException
    //              If the URL is invalid
    //
    // @exception   MalformedURLException
    //              If the avatar URL is invalid
    //
    protected DefaultAuthor(final JSONObject jsonObject) throws MalformedURLException {

        // Get the name
        this.name = jsonObject.optString("name");

        // Get the URL
        if ( jsonObject.has("url") == true ) {
            this.url = new URL(jsonObject.getString("url"));
        }

        // Get the avatar (URL)
        if ( jsonObject.has("avatar") == true ) {
            this.avatar = new URL(jsonObject.getString("avatar"));
        }

    }



    //
    // Get the name
    //
    // @return  the name
    //
    @Override
    public String getName() {

        return (this.name);

    }



    //
    // Get the URL
    //
    // @return  the URL
    //
    @Override
    public URL getUrl() {

        return (this.url);

    }



    //
    // Get the avatar
    //
    // @return  the avatar
    //
    @Override
    public URL getAvatar() {

        return (this.avatar);

    }



    //
    // Check the validity of the author object
    //
    // @return  true if the author object is valid
    //
    @Override
    public boolean isValid() {

        // Check the author fields
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



    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    @Override
    public String toJSONString() {

        // Create the JSON object
        final JSONObject jsonObject = new JSONObject();

        // Add the name
        if ( this.getName() != null ) {
            jsonObject.put("name", this.getName());
        }

        // Add the url
        if ( this.getUrl() != null ) {
            jsonObject.put("url", this.getUrl().toString());
        }

        // Add the avatar (URL)
        if ( this.getAvatar() != null ) {
            jsonObject.put("avatar", this.getAvatar().toString());
        }

        // Get the JSON string
        final String jsonString = jsonObject.toString();

        // Return the JSON string
        return (jsonString);

    }


}
