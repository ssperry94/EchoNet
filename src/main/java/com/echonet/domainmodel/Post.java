//author - Joshua Johnson
package com.echonet.domainmodel;




import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;




import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;




/**
* represents social media post in the domain model.
* post which contains the variables content, timestamp, userID, and gives users option to
* give imagePath (allows user to upload image)
*/
public class Post extends Domain {
  // Variables
  private String content; // context(text) of the post
  private Timestamp timestamp; // time the post was created
  private int postID; // unique ID for the post
  private String imagePath; // path to an image file associated with the post




  /**
   * constructs a post with the specified userID.
   *
   * @param userID The ID of the user creating the post.
   *
   * @throws SQLException                If error occurs within database
   * @throws ClassNotFoundException      If the Table class can't be found.
   * @throws DataBaseNotFoundException   If the database can't be found.
   */
  public Post(final int userID) throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
      super(userID);
      this.table = new Table(Config.POSTS_TABLE);
  }




  /**
   * Constructs a Post with all fields.
   *
   * @param userID     ID of user that is creating post
   * @param postID     unique ID for the post
   * @param content    The content(or text) of the post
   * @param timestamp  The time the post was created
   * @param imagePath  The path to an image associated with the post
   *
   * @throws SQLException                 If error occurs within database
   * @throws ClassNotFoundException      If the Table class can't be found.
   * @throws DataBaseNotFoundException   If the database can't be found.
   */
  public Post(final int userID, final int postID, final String content, final Timestamp timestamp, final String imagePath)
          throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
      super(userID);
      this.table = new Table(Config.POSTS_TABLE);
      this.postID = postID;
      this.content = content;
      this.timestamp = timestamp;
      this.imagePath = imagePath;
  }




  /**
   * returns the postID.
   *
   * @return postID.
   */
  public int getPostID() {
      return this.postID;
  }




  /**
   * returns post content
   *
   * @return content
   */
  public String getContent() {
      return this.content;
  }




  /**
   * returns the time the post was created
   *
   * @return timestamp
   */
  public Timestamp getTimestampObject() {
      return this.timestamp;
  }




  /**
   * returns the timestamp as a string
   *
   * @return either the timestamp as a string, or null if it is not set.
   */
  public String getTimestampString() {
      return this.timestamp != null ? this.timestamp.toString() : null;
  }




  /**
   * returns image associated with post
   *
   * @return image path.
   */
  public String getImagePath() {
      return this.imagePath;
  }




  /**
   * sets unique postID
   *
   * @param postID
   */
  public void setPostID(final int postID) {
      this.postID = postID;
  }




  /**
   * sets post content
   *
   * @param content new post content
   */
  public void setContent(final String content) {
      this.content = content;
  }




  /**
   * sets timestamp for post
   */
  public void setTimestamp() {
      Date currentDate = new Date();
      this.timestamp = new Timestamp(currentDate.getTime());
  }




  /**
   * generates a random postID
   */
  public void setAutomaticPostID() {
      Random randomIdGenerator = new Random();
      this.postID = randomIdGenerator.nextInt(999);
  }




  /**
   * sets the image path for the post
   *
   * @param imagePath
   */
  public void setImagePath(final String imagePath) {
      this.imagePath = imagePath;
  }




  /**
   * generates a map of the post's fields for database storage
   *
   * @return a map containing the post's data
   */
  @Override
  public Map<Integer, Object> createMapForBackEnd() {
      Map<Integer, Object> dataMap = new HashMap<>();
      dataMap.put(0, this.getID());
      dataMap.put(1, this.postID);
      dataMap.put(2, this.content);
      dataMap.put(3, this.getTimestampString());
      dataMap.put(4, this.imagePath);
      return dataMap;
  }
}
