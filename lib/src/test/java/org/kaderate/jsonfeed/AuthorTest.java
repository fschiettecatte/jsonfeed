//
// AuthorTest.java
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


// Import JSON stuff
import org.json.JSONArray;
import org.json.JSONObject;


// Import JUnit stuff
import org.junit.Test;
import static org.junit.Assert.*;

// Import JSONFeed stuff
import org.kaderate.jsonfeed.implementation.DefaultAuthor;



//
// Author tests
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public class AuthorTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"name\": \"Dalek Caan\"," +
            "\"url\": \"https://ham.org/authorCaan.html\"," +
            "\"avatar\": \"https://ham.org/avatarCaan.html\"" +
        "}";



    //
    // Test valid 1
    //
    @Test
    public void testValid1() throws MalformedURLException {

        Author author = DefaultAuthor.fromString(AuthorTest.TEST_STRING_1_X);

        assertNotNull(author);
        assertTrue(author.isValid());

        assertEquals(author.getName(), "Dalek Caan");
        assertEquals(author.getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(author.getAvatar().toString(), "https://ham.org/avatarCaan.html");

        assertNotNull(author.toJSONString());

    }


    //
    // Test valid 2
    //
    @Test
    public void testValid2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("name", "Dalek Caan")
                .put("url", "https://ham.org/authorCaan.html")
                .put("avatar", "https://ham.org/avatarCaan.html");

        JSONObject jsonObject2 = new JSONObject()
                .put("name", "Dalek Jast")
                .put("url", "https://ham.org/authorJast.html")
                .put("avatar", "https://ham.org/avatarJast.html");

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Author> authorList = DefaultAuthor.fromJsonArray(jsonArray);

        assertNotNull(authorList);
        assertTrue(authorList.size() == 2);
        assertTrue(authorList.get(0).isValid());
        assertTrue(authorList.get(1).isValid());

        assertEquals(authorList.get(0).getName(), "Dalek Caan");
        assertEquals(authorList.get(0).getUrl().toString(), "https://ham.org/authorCaan.html");
        assertEquals(authorList.get(0).getAvatar().toString(), "https://ham.org/avatarCaan.html");
        assertNotNull(authorList.get(0).toJSONString());

        assertEquals(authorList.get(1).getName(), "Dalek Jast");
        assertEquals(authorList.get(1).getUrl().toString(), "https://ham.org/authorJast.html");
        assertEquals(authorList.get(1).getAvatar().toString(), "https://ham.org/avatarJast.html");
        assertNotNull(authorList.get(1).toJSONString());

        assertNotNull(jsonArray.toString());

    }


}
