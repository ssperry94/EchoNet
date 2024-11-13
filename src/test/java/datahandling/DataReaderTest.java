/**
 * DEPRICAITED UNIT TEST: made when DataReader was public - now package private
 * 
 * @author Sam Perry
 */



// package datahandling;

// import java.sql.ResultSet;
// import java.sql.SQLException;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.fail;
// import org.junit.Before;
// import org.junit.Test;

// import com.echonet.datahandling.DataReader;
// import com.echonet.datahandling.Table;
// import com.echonet.domainmodel.User;
// import com.echonet.exceptions.DataBaseNotFoundException;
// import com.echonet.utilities.Config;

// public class DataReaderTest {

//     private DataReader dataReader;

//     @Before
//     public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
//         dataReader = new DataReader(Config.TEST_DATABASE_INIT);
//     }

//     @Test
//     public void testRead_ValidQuery() throws SQLException {
//         // Arrange
//         try {
//             Table testTable = new Table("TestTable1", true); // Replace with your actual table name in the test DB
//             User testUser = new User(1, null); // Assuming ID 1 exists in the database
            
//             int expectedID = 1;
//             String expectedFirstName = "John";
//             String expectedLastName = "Smith";
//             // Act
//             ResultSet resultSet = dataReader.read(testTable, testUser);
    
//             // Assert
//             assertNotNull(resultSet);
//             assertTrue(resultSet.next()); // Check that there is at least one result
    
//             // Example assertions on columns; adjust as needed for your table structure
//             int userId = resultSet.getInt("user_id");
//             assertEquals(expectedID, userId); // Assuming the test user has ID 1
    
//             String actualFirstName = resultSet.getString("test_col_1");
//             assertNotNull(actualFirstName); // Ensure the username column is not null for this user
//             assertEquals(expectedFirstName, actualFirstName);

//             String actualLastName = resultSet.getString("test_col_2");
//             assertNotNull(actualLastName);
//             assertEquals(expectedLastName, actualLastName);

//             // Close the ResultSet after assertions
//             resultSet.close();  
//         } catch (SQLException | ClassNotFoundException | DataBaseNotFoundException e) {
//             fail("Excception occured.");
//         }

//     }

//     @Test
//     public void testRead_InvalidTable() throws SQLException {
//         // Arrange
//         try {
//             Table nonExistentTable = new Table("non_existent_table", true);
//             User testUser = new User(1, null);
    
//             // Act
//             dataReader.read(nonExistentTable, testUser); // Should throw SQLException
//             fail("Did not throw SQLException.");
//         } catch (SQLException e) {
//             return;
//         } catch (Exception e) {
//             fail("Exception other than SQLException was thrown.");
//         }

//     }
// }
