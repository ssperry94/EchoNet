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
}

