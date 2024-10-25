package datahandling;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.Table;
import com.echonet.exceptions.DataBaseNotFoundException;

public class TableTest {

    String testDB = "jdbc:sqlite:src/test/echodatatest.db";
    String testDBPath = "src/test/echodatatest.db";
    private Table table;
    // private DataHanlder mockHandler;
    private final String tableName = "\"TestTable1\"";
    private final List<String> mockColumnNames = Arrays.asList("id", "name", "email");

    @Before
    public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        // Set up the mock for DataHanlder
        // mockHandler = new DataHanlder(testDB, testDBPath);
        table = new Table(tableName);
    }

    @Test
    public void testGetTableName() {
        assertEquals(tableName, table.getTableName());
    }

    @Test
    public void testGetTableColumns() {
        List<String> columns = table.getTableColumns();
        assertNotNull(columns);
        assertEquals(mockColumnNames, columns);
    }

    // @Test
    // public void testUpdateColumnNames() throws SQLException {
    //     // Update the mock to return a different set of column names
    //     List<String> newColumnNames = Arrays.asList("id", "username", "created_at");
    //     when(mockHandler.getTableColumnNames(tableName)).thenReturn(newColumnNames);

    //     // Call the update method
    //     table.updateColumnNames();

    //     // Verify that the column names were updated
    //     assertEquals(newColumnNames, table.getTableColumns());
    // }
}
