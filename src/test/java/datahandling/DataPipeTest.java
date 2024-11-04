package datahandling;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.User;

public class DataPipeTest {

    private DataPipe dataPipe;
    private User testUser;

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
        ResultSet rs = dataPipe.read(testUser, true);

        // Assert
        assertNotNull("ResultSet should not be null", rs);
        try {
            assertTrue("ResultSet should contain at least one row", rs.next());
            // Verify all fields match expected values
            assertEquals("User ID should match", 1, rs.getInt("user_id"));
            assertEquals("First name should match", "John", rs.getString("first_name"));
            assertEquals("Last name should match", "Smith", rs.getString("last_name"));
            assertEquals("Username should match", "jsmith123", rs.getString("username"));
            assertEquals("Birthday should match", "1/1/1999", rs.getString("birthday"));
            assertEquals("Email should match", "jsmith@email.com", rs.getString("email"));
        } catch (SQLException e) {
            fail("SQLException occurred during ResultSet processing: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWriteMethod() throws Exception{      
        Table t = new Table("user_test", true);   
        User testUser = new User(2); 
        testUser.setFirstName("Jane");
        testUser.setLastName("Doe");
        testUser.setUsername("jdoe456");
        testUser.setBirthday("2/2/2000");
        testUser.setEmail("jdoe@email.com");
        testUser.setTable(t);
        

        // Act: Attempt to write the user data to the database
        boolean writeSuccess = dataPipe.write(testUser, true);

        // Assert: Verify the write was successful and data was inserted
        assertTrue("Write operation should return true", writeSuccess);

        // Verify insertion by querying data back
        ResultSet rs = dataPipe.read(testUser, true);
        if(rs == null) {
            fail("Couldn't find data.");
        }
        else {
            try {
                assertTrue("ResultSet should contain at least one row", rs.next());
                assertEquals("User ID should match", 2, rs.getInt("user_id"));
                assertEquals("First name should match", "Jane", rs.getString("first_name"));
                assertEquals("Last name should match", "Doe", rs.getString("last_name"));
                assertEquals("Username should match", "jdoe456", rs.getString("username"));
                assertEquals("Birthday should match", "2/2/2000", rs.getString("birthday"));
                assertEquals("Email should match", "jdoe@email.com", rs.getString("email"));
            } catch (SQLException e) {
                fail("SQLException occured.");
            }

        }
    }
}

