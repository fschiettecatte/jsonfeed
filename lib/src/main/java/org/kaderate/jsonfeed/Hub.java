//
// Hub.java
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
// Hub interface
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public interface Hub {


    //
    // Get the type
    //
    // @return  the type
    //
    public String getType();


    //
    // Get the URL
    //
    // @return  the URL
    //
    public URL getUrl();


    //
    // Check the validity of the hub object
    //
    // @return  true if the hub object is valid
    //
    public boolean isValid();


    //
    // Return the JSON string representation for this object
    //
    // @return      the JSON string representation for this object
    //
    public String toJSONString();


}
