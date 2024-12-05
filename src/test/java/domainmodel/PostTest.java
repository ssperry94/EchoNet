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


public class PostTest {


   @Test //ensures Post constructor initializes correctly
   public void testPostConstructorWithUserId() throws Exception {
       // set up testing environment
       int userID = 1;


       // carry out the testing
       Post post = new Post(userID);


       // determine if action passed correct result - in this case it should give the correct userID
       assertEquals("User ID should match", userID, post.getID());
       assertNotNull("Table should not be null", post.getTable());
   }


   @Test //ensures construct initializes all correct fields (uiserId, postId, content, timestamp, imagePath)
   public void testPostConstructorWithDetails() throws Exception {
       // Arrange
       int userID = 1;
       int postID = 101;
       String content = "This is a test post.";
       Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");
       String imagePath = "/images/test.png";


       // Act
       Post post = new Post(userID, postID, content, timestamp, imagePath);


       // Assert
       assertEquals("User ID should match", userID, post.getID());
       assertEquals("Post ID should match", postID, post.getPostID());
       assertEquals("Content should match", content, post.getContent());
       assertEquals("Timestamp should match", timestamp, post.getTimestampObject());
       assertEquals("Image path should match", imagePath, post.getImagePath());
   }


   @Test  //should verify that setters are correctly updated
   public void testSetters() throws Exception {
       // set up
       int userID = 1;
       Post post = new Post(userID);
       int newPostID = 202;
       String newContent = "Updated post content";
       String newImagePath = "/images/updated.png";


       // deploy test
       post.setPostID(newPostID);
       post.setContent(newContent);
       post.setImagePath(newImagePath);


       // verify if test passed or failed
       assertEquals("Post ID should be updated", newPostID, post.getPostID());
       assertEquals("Content should be updated", newContent, post.getContent());
       assertEquals("Image path should be updated", newImagePath, post.getImagePath());
   }


   @Test //ensures backend map is correctly set up with all correct fields
   public void testCreateMapForBackEnd() throws Exception {
       //
       int userID = 1;
       int postID = 101;
       String content = "Test content";
       Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");
       String imagePath = "/images/test.png";


       Post post = new Post(userID, postID, content, timestamp, imagePath);


       // Act
       Map<Integer, Object> dataMap = post.createMapForBackEnd();


       // Assert
       assertEquals("Data map should contain user ID", userID, dataMap.get(0));
       assertEquals("Data map should contain post ID", postID, dataMap.get(1));
       assertEquals("Data map should contain content", content, dataMap.get(2));
       assertEquals("Data map should contain timestamp", timestamp.toString(), dataMap.get(3));
       assertEquals("Data map should contain image path", imagePath, dataMap.get(4));
   }


   @Test //ensures timestamp is set properly with an appropriate range
   public void testSetTimestamp() throws Exception {
       // Arrange
       long tolerance = 5000; // Allowable time difference in milliseconds
       long currentTime = System.currentTimeMillis();
       Post post = new Post(1);


       // Act
       post.setTimestamp();
       Timestamp actualTimestamp = post.getTimestampObject();


       // Assert
       assertTrue("Timestamp should be within the tolerance range",
               Math.abs(currentTime - actualTimestamp.getTime()) <= tolerance);
   }


   @Test //tests that setAutomaticPostId generatives valid postID in allowed range
   public void testAutomaticPostID() throws Exception {
       Post post = new Post(1);
       post.setAutomaticPostID();


       int postID = post.getPostID();
       if (postID < 0 || postID > 999) {
           fail("Post ID is out of the valid range.");
       }
   }


   @Test //tests that datapipe can correctly read write and remove data from database for post
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

