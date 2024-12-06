package domainmodel;


import java.sql.Timestamp;
import java.util.Map;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


import org.junit.Test;


import com.echonet.datahandling.DataPipe;
import com.echonet.domainmodel.Post;


/**
* unit tests for {@link Post} class
* ensures Post runs as expected
*/
public class PostTest {


   /**
    * tests the constructor of Post with a user ID to ensure it initializes correctly
    *
    * @throws Exception if error occurs
    */
   @Test
   public void testPostConstructorWithUserId() throws Exception {
       // set up testing environment
       int userID = 1;


       // carry out the testing
       Post post = new Post(userID);


       // determine if action passed correct result - in this case it should give the correct userID
       assertEquals("User ID should match", userID, post.getID());
       assertNotNull("Table should not be null", post.getTable());
   }


   /**
    * Tests the constructor of Post with detailed parameters to ensure it initializes all fields correctly.
    *
    * @throws Exception If any exception occurs during the test.
    */
   @Test
   public void testPostConstructorWithDetails() throws Exception {
       // set up testing environment
       int userID = 1;
       int postID = 101;
       String content = "This is a test post.";
       Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");
       String imagePath = "/images/test.png";


       // carry out the test
       Post post = new Post(userID, postID, content, timestamp, imagePath);


       // verify if test passed or failed
       assertEquals("User ID should match", userID, post.getID());
       assertEquals("Post ID should match", postID, post.getPostID());
       assertEquals("Content should match", content, post.getContent());
       assertEquals("Timestamp should match", timestamp, post.getTimestampObject());
       assertEquals("Image path should match", imagePath, post.getImagePath());
   }


   /**
    * tests the setter methods of Post to ensure they correctly update fields
    *
    * @throws Exception
    */
   @Test
   public void testSetters() throws Exception {
       // set up
       int userID = 1;
       Post post = new Post(userID);
       int newPostID = 202;
       String newContent = "Updated post content";
       String newImagePath = "/images/updated.png";


       // carry out the test
       post.setPostID(newPostID);
       post.setContent(newContent);
       post.setImagePath(newImagePath);


       // verify if test passed or failed
       assertEquals("Post ID should be updated", newPostID, post.getPostID());
       assertEquals("Content should be updated", newContent, post.getContent());
       assertEquals("Image path should be updated", newImagePath, post.getImagePath());
   }


   /**
    * tests backend map to ensure all required fields are filled.
    *
    * @throws Exception if error occurs
    */
   @Test
   public void testCreateMapForBackEnd() throws Exception {
       // set up
       int userID = 1;
       int postID = 101;
       String content = "Test content";
       Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");
       String imagePath = "/images/test.png";


       Post post = new Post(userID, postID, content, timestamp, imagePath);


       //carry out the test
       Map<Integer, Object> dataMap = post.createMapForBackEnd();


       // verify if test passed or failed
       assertEquals("Data map should contain user ID", userID, dataMap.get(0));
       assertEquals("Data map should contain post ID", postID, dataMap.get(1));
       assertEquals("Data map should contain content", content, dataMap.get(2));
       assertEquals("Data map should contain timestamp", timestamp.toString(), dataMap.get(3));
       assertEquals("Data map should contain image path", imagePath, dataMap.get(4));
   }


   /**
    * tests timestamp and ensures it is made within valid range.
    *
    * @throws Exception
    */
   @Test
   public void testSetTimestamp() throws Exception {
       // set up
       long tolerance = 5000; // Allowable time difference in milliseconds
       long currentTime = System.currentTimeMillis();
       Post post = new Post(1);


       // carry out
       post.setTimestamp();
       Timestamp actualTimestamp = post.getTimestampObject();


       // verify if test passed or failed
       assertTrue("Timestamp should be within the tolerance range",
               Math.abs(currentTime - actualTimestamp.getTime()) <= tolerance);
   }


   /**
    * tests that the Post ID generator creates a valid Post ID
    *
    * @throws Exception
    */
   @Test
   public void testAutomaticPostID() throws Exception {
       Post post = new Post(1);
       post.setAutomaticPostID();


       int postID = post.getPostID();
       if (postID < 0 || postID > 999) {
           fail("Post ID is out of the valid range.");
       }
   }


   /**
    * tests functionality of the DataPipe class for reading, writing, and removing Post objects from the database.
    *
    * @throws Exception if error occurs
    */
   @Test
   public void testDataPipeWithPost() throws Exception {
       int userID = 1;
       int postID = 102;
       String content = "This is a test post.";
       Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");
       String imagePath = "/images/test.png";


       Post post = new Post(userID, postID, content, timestamp, imagePath);


       DataPipe dataPipe = new DataPipe();


       // writes post to database
       boolean writeSuccess = dataPipe.write(post);
       assertTrue("Post should be written successfully", writeSuccess);


       // reads the post from database
       Map<String, Object> dataMap = dataPipe.read(post, "postID", postID);
       assertNotNull("Data map should not be null", dataMap);
       assertEquals("User ID should match", userID, dataMap.get("user_id"));
       assertEquals("Post ID should match", postID, dataMap.get("postID"));
       assertEquals("Content should match", content, dataMap.get("content"));
       assertEquals("Image path should match", imagePath, dataMap.get("image_path"));


       // Remove the post
       boolean removeSuccess = dataPipe.remove(post, "postID", postID);
       assertTrue("Post should be removed successfully", removeSuccess);
   }
}
