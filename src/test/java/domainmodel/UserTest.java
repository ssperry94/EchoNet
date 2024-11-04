package domainmodel;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.Table;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User(); // Using the default constructor for testing
    }

    @Test
    public void testSetAndGetFirstName() {
        // Arrange
        String firstName = "John";

        // Act
        user.setFirstName(firstName);

        // Assert
        assertEquals(firstName, user.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        // Arrange
        String lastName = "Doe";

        // Act
        user.setLastName(lastName);

        // Assert
        assertEquals(lastName, user.getLastName());
    }

    @Test
    public void testSetAndGetUsername() {
        // Arrange
        String username = "johndoe";

        // Act
        user.setUsername(username);

        // Assert
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSetAndGetBirthday() {
        // Arrange
        String birthday = "1990-01-01";

        // Act
        user.setBirthday(birthday);

        // Assert
        assertEquals(birthday, user.getBirthday());
    }

    @Test
    public void testSetAndGetEmail() {
        // Arrange
        String email = "johndoe@example.com";

        // Act
        user.setEmail(email);

        // Assert
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetID() {
        // Arrange
        int id = 12345;

        // Act
        user.setID(id);

        // Assert
        assertEquals(id, user.getID());
    }

    @Test
    public void testSetAndGetTable() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        Table t = new Table("user_test", true);
        user.setTable(t);

        assertEquals(t, user.getTable());
    }
}
