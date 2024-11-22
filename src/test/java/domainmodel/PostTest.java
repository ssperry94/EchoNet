package domainmodel;

import static org.junit.Assert.*;
import org.junit.Test;

import com.echonet.domainmodel.Post;

import org.junit.Before;

import javax.swing.*;
import java.util.Map;

public class PostTest {

    private Post post;

    @Before
    public void setUp() throws Exception {
        int userID = 1;
        int postID = 101;
        String content = "This is a test post.";
        post = new Post(userID, postID, content);
    }

    @Test
    public void testPostInitialization() {
        assertNotNull("Post object should not be null", post);
        assertEquals("UserID should match", 1, post.getID());
        assertEquals("PostID should match", 101, post.getPostID());
        assertEquals("Content should match", "This is a test post.", post.content);
        assertNotNull("Timestamp should not be null", post.timestamp);
    }

    @Test
    public void testGetPostLabel() {
        JLabel postLabel = post.getPostLabel();
        assertNotNull("Post label should not be null", postLabel);
        assertTrue("Post label should contain content",
                postLabel.getText().contains("This is a test post."));
        assertTrue("Post label should contain timestamp", 
                postLabel.getText().contains(post.timestamp));
    }

    @Test
    public void testCreateMapForBackEnd() {
        Map<Integer, Object> dataMap = post.createMapForBackEnd();
        assertNotNull("Data map should not be null", dataMap);
        assertEquals("Map should contain correct userID", 1, dataMap.get(0));
        assertEquals("Map should contain correct postID", 101, dataMap.get(1));
        assertEquals("Map should contain correct content", "This is a test post.", dataMap.get(2));
        assertEquals("Map should contain correct timestamp", post.timestamp, dataMap.get(3));
    }
}
