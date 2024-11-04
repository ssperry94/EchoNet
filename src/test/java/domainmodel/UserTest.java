package domainmodel;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.Table;
import com.echonet.domainmodel.User;
import com.echonet.exceptions.DataBaseNotFoundException;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User(12345); // Using the default constructor for testing
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

    @Test
    public void testCreateMapForBackEnd() {

        //set up new user class
        user.setID(12345);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setBirthday("1990-01-01");
        user.setEmail("johndoe@example.com");
        // Act
        Map<Integer, Object> dataMap = user.createMapForBackEnd();

        // Assert
        assertNotNull(dataMap);
        assertEquals(6, dataMap.size());

        assertEquals(user.getID(), dataMap.get(0));
        assertEquals(user.getFirstName(), dataMap.get(1));
        assertEquals(user.getLastName(), dataMap.get(2));
        assertEquals(user.getUsername(), dataMap.get(3));
        assertEquals(user.getBirthday(), dataMap.get(4));
        assertEquals(user.getEmail(), dataMap.get(5));
    }
}
