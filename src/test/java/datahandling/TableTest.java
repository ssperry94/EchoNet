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
    private Table table;
    private final String tableName = "\"TestTable1\"";
    private final List<String> mockColumnNames = Arrays.asList("user_id", "test_col_1", "test_col_2");

    @Before
    public void setUp() throws SQLException, ClassNotFoundException, DataBaseNotFoundException {
        table = new Table(tableName, true);
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
}
