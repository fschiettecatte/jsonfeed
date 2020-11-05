//
// Attachment.java
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
package org.kaderate.jsonfeed;


// Import Java stuff
import java.net.URL;


//
// Attachment interface
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public interface Attachment {


    //
    // Get the URL
    //
    // @return  the URL
    //
    public URL getUrl();


    //
    // Get the mime type
    //
    // @return  the mime type
    //
    public String getMimeType();


    //
    // Get the title
    //
    // @return  the title
    //
    public String getTitle();


    //
    // Get the size in bytes
    //
    // @return  the size in bytes
    //
    public Integer getSizeInBytes();


    //
    // Get the duration in seconds
    //
    // @return  the duration in seconds
    //
    public Integer getDurationInSeconds();


    //
    // Check the validity of the author object
    //
    // @return  true if the author object is valid
    //
    public boolean isValid();


    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    public String toJSONString();


}
