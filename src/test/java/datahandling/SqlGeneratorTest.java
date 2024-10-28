package datahandling;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.SqlGenerator;
import com.echonet.datahandling.Table;

public class SqlGeneratorTest {

    private SqlGenerator sqlGenerator;
    private Table testTable;
    
    @Before
    public void setUp() throws Exception {
        sqlGenerator = new SqlGenerator();
        testTable = new Table("TestTable1", true);
    }

    @Test
    public void testQueryStatement() {
        // When
        String result = sqlGenerator.queryStatement(testTable);

        // Then
        assertEquals("SELECT * FROM TestTable1 WHERE user_id = ", result);
    }

    @Test
    public void testInsertStatement() {
        // When
        String result = sqlGenerator.insertStatement(testTable);

        // Then
        assertEquals("INSERT INTO TestTable1 VALUES ", result);
    }

    @Test
    public void testGetTableInfoQuery() {
        // Given
        String tableName = "test";
        
        // When
        String result = sqlGenerator.getTableInfoQuery(tableName);

        // Then
        assertEquals("SELECT * FROM test", result);
    }
}
