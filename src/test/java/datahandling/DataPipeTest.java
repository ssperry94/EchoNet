package datahandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.User;
import com.echonet.utilities.Config;

public class DataPipeTest {

    private DataPipe dataPipe;
    private User testUser;

    public static void deleteUserById(int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            // Load the database driver
            Class.forName(Config.DATABASE_DRIVER);

            // Establish the database connection
            connection = DriverManager.getConnection(Config.TEST_DATABASE_INIT);

            // Define the SQL delete statement
            String deleteSql = "DELETE FROM user_test WHERE user_id = ?";

            // Prepare and execute the delete statement
            stmt = connection.prepareStatement(deleteSql);
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User with ID " + userId + " deleted successfully.");
            } else {
                System.out.println("No user found with ID " + userId);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL exception: " + e.getMessage());
        } finally {
            // Clean up resources
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Before
    public void setUp() throws Exception {
        dataPipe = new DataPipe();
        testUser = new User(1);
        Table t = new Table("user_test", true);
        testUser.setTable(t);
    }

    @Test
    public void testRead_WithTestDatabase() {
        // Act
        Map<String, Object> dataMap = dataPipe.read(testUser, true);

        // Assert
        assertNotNull("Data map should not be null", dataMap);
        assertEquals("User ID should match", 1, dataMap.get("user_id"));
        assertEquals("First name should match", "John", dataMap.get("first_name"));
        assertEquals("Last name should match", "Smith", dataMap.get("last_name"));
        assertEquals("Username should match", "jsmith123", dataMap.get("username"));
        assertEquals("Birthday should match", "1/1/1999", dataMap.get("birthday"));
        assertEquals("Email should match", "jsmith@email.com", dataMap.get("email"));
    }

    @Test
    public void testWriteMethod() throws Exception{      
        Table t = new Table("user_test", true);   
        User testUser2 = new User(66); 
        testUser2.setFirstName("Jane");
        testUser2.setLastName("Doe");
        testUser2.setUsername("jdoe456");
        testUser2.setBirthday("2/2/2000");
        testUser2.setEmail("jdoe@email.com");
        testUser2.setTable(t);
        

        // Act: Attempt to write the user data to the database
        boolean writeSuccess = dataPipe.write(testUser2, true);

        // Assert: Verify the write was successful and data was inserted
        assertTrue("Write operation should return true", writeSuccess);

        //Verify insertion by querying data back
        Map <String, Object> dataMap = dataPipe.read(testUser2, true);
        if(dataMap == null) {
            fail("Couldn't find data.");
        }
        else {
            assertEquals("User ID should match", 66, dataMap.get("user_id"));
            assertEquals("First name should match", "Jane", dataMap.get("first_name"));
            assertEquals("Last name should match", "Doe", dataMap.get("last_name"));
            assertEquals("Username should match", "jdoe456", dataMap.get("username"));
            assertEquals("Birthday should match", "2/2/2000", dataMap.get("birthday"));
            assertEquals("Email should match", "jdoe@email.com", dataMap.get("email"));

            deleteUserById(66);

        }
    }

    @Test
    public void testRemove() throws Exception{
        User testUser3 = new User(99);
        testUser3.setFirstName("Mark");
        testUser3.setLastName("Brown");
        testUser3.setUsername("mbrown123");
        testUser3.setBirthday("3/3/1999");
        testUser3.setEmail("mbrown@email.com");
        testUser3.setTable(new Table("user_test", true));

        assertTrue(dataPipe.write(testUser3, true));
        assertTrue(dataPipe.remove(testUser3, true));
        Map <String, Object> testMap = dataPipe.read(testUser3, true);
        assertNull(testMap);

    }
}

