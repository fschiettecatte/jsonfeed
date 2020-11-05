/**
 * VersionTest.java
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/* Import JSON stuff */
import org.json.JSONArray;
import org.json.JSONObject;


/* Import JUnit stuff */
import org.junit.Test;
import static org.junit.Assert.*;



/**
 * Version tests
 *
 * @author François Schiettecatte (fschiettecatte@gmail.com)
 * @version 0.1.0
 */
public class VersionTest {


    /**
     * Test 1
     */
    @Test
    public void test1() {

        String versionString = Version.VERSION_1_0.getVersionName();

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_0, version);

    }


    /**
     * Test 2
     */
    @Test
    public void test2() {

        String versionString = Version.VERSION_1_0.getVersionName().toUpperCase();

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_0, version);

    }


    /**
     * Test 3
     */
    @Test
    public void test3() {

        String versionString = Version.VERSION_1_1.getVersionName();

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_1, version);

    }


    /**
     * Test 4
     */
    @Test
    public void test4() {

        String versionString = Version.VERSION_1_1.getVersionName().toUpperCase();

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_1, version);

    }


    /**
     * Test 5
     */
    @Test
    public void test5() {

        String versionString = "https://jsonfeed.org/version/1";

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_0, version);

    }


    /**
     * Test 6
     */
    @Test
    public void test6() {

        String versionString = "HTTPS://JSONFEED.ORG/VERSION/1";

        Version version = Version.fromString(versionString);

        assertEquals(Version.VERSION_1_0, version);

    }


    /**
     * Test 7
     */
    @Test
    public void test7() {

        assertTrue(Version.VERSION_1_0.getVersionID() < Version.VERSION_1_1.getVersionID());
        assertTrue(Version.VERSION_1_1.getVersionID() > Version.VERSION_1_0.getVersionID());

    }


    /**
     * Test 8
     */
    @Test
    public void test8() {

        assertFalse(Version.VERSION_1_0.getVersionID() >= Version.VERSION_1_1.getVersionID());
        assertFalse(Version.VERSION_1_1.getVersionID() <= Version.VERSION_1_0.getVersionID());

    }


    /**
     * Test 9
     */
    @Test
    public void test9() {

        assertNotNull(Version.VERSION_1_0.toJSONString());
        assertNotNull(Version.VERSION_1_1.toJSONString());

    }


}
