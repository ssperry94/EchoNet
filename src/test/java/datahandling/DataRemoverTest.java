/**
 * DEPRICATED UNIT TEST: made when DataRemover was public - now package private
 * 
 * @author Sam Perry
 */




// package datahandling;

// import java.sql.SQLException;
// import java.util.Map;

// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.fail;
// import org.junit.Before;
// import org.junit.Test;

// import com.echonet.datahandling.DataPipe;
// import com.echonet.datahandling.DataRemover;
// import com.echonet.datahandling.Table;
// import com.echonet.domainmodel.User;
// import com.echonet.exceptions.DataBaseNotFoundException;
// import com.echonet.utilities.Config;

// public class DataRemoverTest {

//     private DataRemover dataRemover;
//     private User testUser;
//     private DataPipe pipe;

//     @Before
//     public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
//         dataRemover = new DataRemover(Config.TEST_DATABASE_INIT);

//         // Insert test data to delete
//         testUser = new User(77); // Use a unique ID to avoid conflicts
//         testUser.setFirstName("Mark");
//         testUser.setLastName("Brown");
//         testUser.setUsername("mbrown123");
//         testUser.setBirthday("3/3/1999");
//         testUser.setEmail("mbrown@email.com");
//         testUser.setTable(new Table("user_test", true));

//         // Insert this user into the database to be deleted later
//         //String insertSql = "INSERT INTO user_test (user_id, first_name, last_name, username, birthday, email) VALUES (?, ?, ?, ?, ?, ?)";
//         pipe = new DataPipe();
//         pipe.write(testUser, true);
//     }

//     @Test
//     public void testRemove() {
//         try {
//             // Act: Remove the user from the database
//             dataRemover.remove(testUser.getTable(), testUser);

//             // Verify that the user was removed by querying for the user ID
//             Map <String, Object> testMap = pipe.read(testUser, true);
//             assertNull(testMap);
//         } catch (SQLException e) {
//             fail("SQLException occurred: " + e.getMessage());
//         }
//     }
// }
