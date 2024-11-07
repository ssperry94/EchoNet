/**
 * DEPRICATED UNIT TEST: made when DataWriter was public - now package private
 * 
 * @author Sam Perry
 */

// package datahandling;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.HashMap;
// import java.util.Map;

// import org.junit.After;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertTrue;
// import org.junit.Before;
// import org.junit.Test;

// import com.echonet.datahandling.DataWriter;
// import com.echonet.datahandling.Table;
// import com.echonet.exceptions.DataBaseNotFoundException;
// import com.echonet.utilities.Config;

// public class DataWriterTest {

//     private DataWriter dataWriter;
//     private Connection connection;
//     private String driver = Config.DATABASE_DRIVER;

//     @Before
//     public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
//         dataWriter = new DataWriter(Config.TEST_DATABASE_INIT);
//         Class.forName(driver);
//         connection = DriverManager.getConnection(Config.TEST_DATABASE_INIT);
//     }

//     @After
//     public void tearDown() throws SQLException {
//         if (connection != null && !connection.isClosed()) {
//             connection.close();
//         }
//     }

//     private void deleteRecord() throws SQLException {
//         String sql = "DELETE FROM TestTable1 WHERE user_id = 3";
//         Statement stmt = connection.createStatement();
//         stmt.executeUpdate(sql);

//     }
//     @Test
//     public void testWrite_InsertDataIntoTestTable1() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
//         // Arrange
//         Table testTable = new Table("TestTable1", true);
//         Map<Integer, Object> dataMap = new HashMap<>();
//         dataMap.put(0, 3);           // Integer for user_id
//         dataMap.put(1, "value1"); // String for test_col_1
//         dataMap.put(2, "value2"); // String for test_col_2

//         // Act
//         dataWriter.write(testTable, dataMap);

//         // Verify insertion by querying data back
//         String verifySql = "SELECT * FROM TestTable1 WHERE user_id = ? AND test_col_1 = ? AND test_col_2 = ?";
//         PreparedStatement verifyStmt = connection.prepareStatement(verifySql);
//         verifyStmt.setInt(1, 3);
//         verifyStmt.setString(2, "value1");
//         verifyStmt.setString(3, "value2");
//         ResultSet rs = verifyStmt.executeQuery();

//         // Assert
//         assertTrue(rs.next());
//         assertEquals(3, rs.getInt("user_id"));
//         assertEquals("value1", rs.getString("test_col_1"));
//         assertEquals("value2", rs.getString("test_col_2"));

//         // Clean up
//         rs.close();
//         verifyStmt.close();
//         deleteRecord();
//     }

//     @Test(expected = SQLException.class)
//     public void testWrite_ThrowsSQLExceptionOnInvalidTable() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
//         // Arrange
//         Table invalidTable = new Table("nonexistent_table"); // Assuming this table does not exist in the DB
//         Map<Integer, Object> dataMap = new HashMap<>();
//         dataMap.put(0, 1);
//         dataMap.put(1, "value1");
//         dataMap.put(2, "value2");

//         // Act - Should throw SQLException since the table doesn't exist
//         dataWriter.write(invalidTable, dataMap);
//     }
// }

