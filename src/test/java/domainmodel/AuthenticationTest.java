package domainmodel;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;

import com.echonet.domainmodel.Authentication;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class AuthenticationTest {

    private Authentication authentication;
    private DataPipe dataPipe;
    
    @Before
    public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Initialize DataPipe and Authentication
        dataPipe = new DataPipe();
        authentication = new Authentication(6);
    }

    @Test
    public void testRegisterValidUser() throws Exception {
        // Act
        boolean result = authentication.Register("newuser", "securepassword");

        // Assert
        assertTrue("Registration should succeed for a valid new user", result);

        // Verify in database
        User verifyUser = new User(6);
        verifyUser.setTable(new Table(Config.USER_TABLE));
        Map<String, Object> data = dataPipe.read(verifyUser);
        assertNotNull("User should exist in the database after registration", data);
        assertEquals("Username should match", "newuser", data.get("username"));
        assertTrue(dataPipe.remove(verifyUser));
        assertTrue(dataPipe.remove(authentication));
    }

    @Test
    public void testRegisterUserWithNullUsernameOrPassword() {
        // Act & Assert
        assertFalse("Registration should fail with null username", authentication.Register(null, "password"));
        assertFalse("Registration should fail with null password", authentication.Register("username", null));
    }

    @Test
    public void testRegisterUserWithEmptyUsernameOrPassword() {
        // Act & Assert
        assertFalse("Registration should fail with empty username", authentication.Register("", "password"));
        assertFalse("Registration should fail with empty password", authentication.Register("username", ""));
    }

    @Test
    public void testRegisterDuplicateUsername() throws Exception {
        // Act
        assertTrue(authentication.Register("existinguser", "newpassword"));
        boolean result = authentication.Register("existinguser", "newpassword");

        // Assert
        assertFalse("Registration should fail for duplicate username", result);
        User u = new User(6);
        u.setTable(new Table(Config.USER_TABLE));
        assertTrue(dataPipe.remove(u));
        assertTrue(dataPipe.remove(authentication));
    }

}
