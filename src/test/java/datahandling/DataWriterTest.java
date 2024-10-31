package datahandling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataWriter;
import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;
import com.echonet.utilities.Config;

public class DataWriterTest {

    private DataWriter dataWriter;
    private Connection connection;
    private String driver = Config.DATABASE_DRIVER;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        dataWriter = new DataWriter(Config.TEST_DATABASE_INIT);
        Class.forName(driver);
        connection = DriverManager.getConnection(Config.TEST_DATABASE_INIT);
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testPopulatePreparedStatement_UserIdAndStringValues() throws SQLException {
        // Arrange
        String sql = "INSERT INTO TestTable1 (user_id, test_col_1, test_col_2) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("user_id", 1);           // Integer for user_id
        dataMap.put("test_col_1", "value1"); // String for test_col_1
        dataMap.put("test_col_2", "value2"); // String for test_col_2

        // Act
        dataWriter.populatePreparedStatement(pstmt, dataMap);

        // Assert
        assertEquals(1, pstmt.getParameterMetaData().getParameterType(1)); // user_id as int
        assertEquals("VARCHAR", pstmt.getParameterMetaData().getParameterTypeName(2)); // test_col_1 as String
        assertEquals("VARCHAR", pstmt.getParameterMetaData().getParameterTypeName(3)); // test_col_2 as String
        pstmt.close();
    }

    @Test
    public void testWrite_InsertDataIntoTestTable1() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Arrange
        Table testTable = new Table("TestTable1", true);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("user_id", 1);           // Integer for user_id
        dataMap.put("test_col_1", "value1"); // String for test_col_1
        dataMap.put("test_col_2", "value2"); // String for test_col_2

        // Act
        dataWriter.write(testTable, dataMap);

        // Verify insertion by querying data back
        String verifySql = "SELECT * FROM TestTable1 WHERE user_id = ? AND test_col_1 = ? AND test_col_2 = ?";
        PreparedStatement verifyStmt = connection.prepareStatement(verifySql);
        verifyStmt.setInt(1, 1);
        verifyStmt.setString(2, "value1");
        verifyStmt.setString(3, "value2");
        ResultSet rs = verifyStmt.executeQuery();

        // Assert
        assertTrue(rs.next());
        assertEquals(1, rs.getInt("user_id"));
        assertEquals("value1", rs.getString("test_col_1"));
        assertEquals("value2", rs.getString("test_col_2"));

        // Clean up
        rs.close();
        verifyStmt.close();
    }

    @Test(expected = SQLException.class)
    public void testWrite_ThrowsSQLExceptionOnInvalidTable() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Arrange
        Table invalidTable = new Table("nonexistent_table"); // Assuming this table does not exist in the DB
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("user_id", 1);
        dataMap.put("test_col_1", "value1");
        dataMap.put("test_col_2", "value2");

        // Act - Should throw SQLException since the table doesn't exist
        dataWriter.write(invalidTable, dataMap);
    }
}

