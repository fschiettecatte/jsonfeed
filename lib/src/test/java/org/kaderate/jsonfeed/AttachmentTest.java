//
// AttachmentTest.java
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
import org.kaderate.jsonfeed.implementation.DefaultAttachment;



//
// Attachment tests
//
// @author François Schiettecatte (fschiettecatte@gmail.com)
// @version 0.1.0
//
public class AttachmentTest {


    private static final String TEST_STRING_1_X = "{" +
            "\"url\": \"https://ham.org/dalekInvasion.m4v\"," +
            "\"mime_type\": \"video/x-m4v\"," +
            "\"title\": \"Dalek Invasion\"," +
            "\"size_in_bytes\": 100," +
            "\"duration_in_seconds\": 200" +
        "}";



    //
    // Test valid 1
    //
    @Test
    public void testValid1() throws MalformedURLException {

        Attachment attachment = DefaultAttachment.fromString(AttachmentTest.TEST_STRING_1_X);

        assertNotNull(attachment);
        assertTrue(attachment.isValid());

        assertEquals(attachment.getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachment.getMimeType(), "video/x-m4v");
        assertEquals(attachment.getTitle(), "Dalek Invasion");
        assertEquals(attachment.getSizeInBytes().intValue(), 100);
        assertEquals(attachment.getDurationInSeconds().intValue(), 200);

        assertNotNull(attachment.toJSONString());

    }


    //
    // Test valid 2
    //
    @Test
    public void testValid2() throws MalformedURLException {

        JSONObject jsonObject1 = new JSONObject()
                .put("url", "https://ham.org/dalekInvasion.m4v")
                .put("mime_type", "video/x-m4v")
                .put("title", "Dalek Invasion")
                .put("size_in_bytes", 100)
                .put("duration_in_seconds", 200);

        JSONObject jsonObject2 = new JSONObject()
                .put("url", "https://ham.org/doctorRevenge.m4v")
                .put("mime_type", "video/x-m4v")
                .put("title", "Doctor's Revenge")
                .put("size_in_bytes", 300)
                .put("duration_in_seconds", 400);

        JSONArray jsonArray = new JSONArray(new Object[] {jsonObject1, jsonObject2});

        List<Attachment> attachmentList = DefaultAttachment.fromJsonArray(jsonArray);

        assertNotNull(attachmentList);
        assertTrue(attachmentList.size() == 2);
        assertTrue(attachmentList.get(0).isValid());
        assertTrue(attachmentList.get(1).isValid());

        assertEquals(attachmentList.get(0).getUrl().toString(), "https://ham.org/dalekInvasion.m4v");
        assertEquals(attachmentList.get(0).getMimeType(), "video/x-m4v");
        assertEquals(attachmentList.get(0).getTitle(), "Dalek Invasion");
        assertEquals(attachmentList.get(0).getSizeInBytes().intValue(), 100);
        assertEquals(attachmentList.get(0).getDurationInSeconds().intValue(), 200);
        assertNotNull(attachmentList.get(0).toJSONString());

        assertEquals(attachmentList.get(1).getUrl().toString(), "https://ham.org/doctorRevenge.m4v");
        assertEquals(attachmentList.get(1).getMimeType(), "video/x-m4v");
        assertEquals(attachmentList.get(1).getTitle(), "Doctor's Revenge");
        assertEquals(attachmentList.get(1).getSizeInBytes().intValue(), 300);
        assertEquals(attachmentList.get(1).getDurationInSeconds().intValue(), 400);
        assertNotNull(attachmentList.get(1).toJSONString());

        assertNotNull(jsonArray.toString());

    }


}
