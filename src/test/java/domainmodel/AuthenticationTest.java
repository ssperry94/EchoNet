package domainmodel;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataPipe;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.Authentication;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

/**
 * @author Sam Perry
 */
public class AuthenticationTest {

    private Authentication authentication;
    private DataPipe dataPipe;
    private User testUser;
    
    @Before
    public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Initialize DataPipe and Authentication
        testUser = new User(6);
        testUser.setTable(new Table(Config.USER_TABLE));
        testUser.setFirstName("test");
        testUser.setLastName("test");
        testUser.setUsername("newuser");
        testUser.setBirthday("Test");
        testUser.setEmail("test");
        dataPipe = new DataPipe();
        authentication = new Authentication(6);
    }

    @Test
    public void testRegisterValidUser() throws Exception {
        // Act
        boolean result = authentication.Register("newuser", "securepassword", this.testUser);

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
        assertFalse("Registration should fail with null username", authentication.Register(null, "password", this.testUser));
        assertFalse("Registration should fail with null password", authentication.Register("username", null, this.testUser));
    }

    @Test
    public void testRegisterUserWithEmptyUsernameOrPassword() {
        // Act & Assert
        assertFalse("Registration should fail with empty username", authentication.Register("", "password", this.testUser));
        assertFalse("Registration should fail with empty password", authentication.Register("username", "", this.testUser));
    }

    @Test
    public void testRegisterDuplicateUsername() throws Exception {
        // Act
        assertTrue(authentication.Register("existinguser", "newpassword", this.testUser));
        boolean result = authentication.Register("existinguser", "newpassword", this.testUser);

        // Assert
        assertFalse("Registration should fail for duplicate username", result);
        User u = new User(6);
        u.setTable(new Table(Config.USER_TABLE));
        assertTrue(dataPipe.remove(u));
        assertTrue(dataPipe.remove(authentication));
    }
    
    
    @Test
    public void testLoginSuccessful() {
        // Act
        User result = authentication.login("test123", "123test");

        // Assert
        assertNotNull("Login should succeed for valid credentials", result);
    }

    @Test
    public void testLoginInvalidPassword() {
        // Act
        User result = authentication.login("test123", "wrongpassword");

        // Assert
        assertNull("Login should fail for invalid password", result);
    }

    @Test
    public void testLoginUsernameNotFound() {
        // Act
        User result = authentication.login("nonexistentuser", "password");

        // Assert
        assertNull("Login should fail if the username is not found", result);
    }

    @Test
    public void testLoginEmptyUsername() {
        // Act
        User result = authentication.login("", "password");

        // Assert
        assertNull("Login should fail for an empty username", result);
    }

    @Test
    public void testLoginEmptyPassword() {
        // Act
        User result = authentication.login("test123", "");

        // Assert
        assertNull("Login should fail for an empty password", result);
    }

    @Test
    public void testLoginNullUsernameAndPassword() {
        // Act
        User result = authentication.login(null, null);

        // Assert
        assertNull("Login should fail for null username and password", result);
    }

}
