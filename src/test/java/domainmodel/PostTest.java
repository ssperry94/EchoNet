package domainmodel;

import java.sql.SQLException;
import java.util.Map;

import javax.swing.JLabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.domainmodel.Post;
import com.echonet.exceptions.DataBaseNotFoundException;

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

    @Test
    public void testPostBackendFunctionality() throws ClassNotFoundException, SQLException, DataBaseNotFoundException{
        DataPipe writeToPost = new DataPipe();
        boolean success;
        Post post = new Post(1,101, "applesauce");
        // success = writeToPost.write(post);
        // assertTrue(success);

        Map<String, Object> dataMap = writeToPost.read(post);
        // assertEquals("Map should contain correct userID", 1, (int) dataMap.get("user_id"));
        // assertEquals("Map should contain correct postID", 101, (int) dataMap.get("postID"));
        // assertEquals("Map should contain correct content", "applesause", (String) dataMap.get("contents"));
        //once you check user ID you can write read delte with this
        success = writeToPost.remove(post);
        assertTrue(success);


    }
    
}
