package datahandling;

import java.util.Map;

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
        User testUser2 = new User(2); 
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
            assertEquals("User ID should match", 2, dataMap.get("user_id"));
            assertEquals("First name should match", "Jane", dataMap.get("first_name"));
            assertEquals("Last name should match", "Doe", dataMap.get("last_name"));
            assertEquals("Username should match", "jdoe456", dataMap.get("username"));
            assertEquals("Birthday should match", "2/2/2000", dataMap.get("birthday"));
            assertEquals("Email should match", "jdoe@email.com", dataMap.get("email"));

        }
    }
}

