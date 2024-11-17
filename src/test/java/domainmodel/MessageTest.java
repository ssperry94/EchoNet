package domainmodel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.domainmodel.Message;

public class MessageTest {

    @Test
    public void testMessageConstructorWithUserId() throws Exception {
        // Arrange
        int userId = 1;

        // Act
        Message message = new Message(userId);

        // Assert
        assertEquals("User ID should match", userId, message.getID());
        assertNotNull("Table should not be null", message.getTable());
    }

    @Test
    public void testMessageConstructorWithDetails() throws Exception {
        // Arrange
        int userId = 1;
        int messageId = 101;
        String contents = "Hello, world!";
        Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");

        // Act
        Message message = new Message(userId, messageId, contents, timestamp);

        // Assert
        assertEquals("User ID should match", userId, message.getID());
        assertEquals("Message ID should match", messageId, message.getMessageID());
        assertEquals("Contents should match", contents, message.getContents());
        assertEquals("Timestamp should match", timestamp, message.getTimeStampObject());
        assertEquals("Timestamp string should match", timestamp.toString(), message.getTimeStampString());
    }

    @Test
    public void testSetters() throws Exception {
        // Arrange
        int userId = 1;
        Message message = new Message(userId);
        int newMessageId = 202;
        String newContents = "Updated message contents";

        // Act
        message.setMessageID(newMessageId);
        message.setContents(newContents);

        // Assert
        assertEquals("Message ID should be updated", newMessageId, message.getMessageID());
        assertEquals("Contents should be updated", newContents, message.getContents());
    }

    @Test
    public void testCreateMapForBackEnd() throws Exception {
        // Arrange
        int userId = 1;
        int messageId = 101;
        String contents = "Test message";
        Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");

        Message message = new Message(userId, messageId, contents, timestamp);

        // Act
        Map<Integer, Object> dataMap = message.createMapForBackEnd();

        // Assert
        assertEquals("Data map should contain user ID", userId, dataMap.get(0));
        assertEquals("Data map should contain message ID", messageId, dataMap.get(1));
        assertEquals("Data map should contain contents", contents, dataMap.get(2));
        assertEquals("Data map should contain timestamp as a string: ", timestamp.toString(), dataMap.get(3));
    }

    @Test
    public void testDataPipeWithMessage() throws Exception {
        int userId = 1;
        int messageId = 101;
        String contents = "Test message";
        Timestamp timestamp = Timestamp.valueOf("2023-01-01 12:00:00");

        Message message = new Message(userId, messageId, contents, timestamp);

        DataPipe dataPipe = new DataPipe();

        boolean writeSuccess = dataPipe.write(message);
        assertTrue(writeSuccess);
        Map <String, Object> dataMap = dataPipe.read(message);
        assertNotNull(dataMap);
        assertEquals("Data map should contain user ID", userId, dataMap.get("user_id"));
        assertEquals("Data map should contain message ID", messageId, dataMap.get("messageID"));
        assertEquals("Data map should contain timestamp as string", timestamp.toString(), dataMap.get("timestamp"));
        assertEquals("Data map should contain contents", contents, dataMap.get("contents"));

        boolean removeSuccess = dataPipe.remove(message);
        assertTrue(removeSuccess);
    }

    @Test
    public void testSetTimestamp() throws Exception {
       // Arrange
       long tolerance = 5000; // Allowable time difference in milliseconds
       long currentTime = new Date().getTime();
       Message message = new Message(1);

       // Act
       message.setTimeStamp(); 
       Timestamp actualStamp = message.getTimeStampObject();

       // Assert
       assertTrue("Timestamp should be within the tolerance range",
               Math.abs(currentTime - actualStamp.getTime()) <= tolerance);
    }   
}
