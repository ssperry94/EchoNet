import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.SqlGenerator;

public class SqlGeneratorTest {

    private SqlGenerator sqlGenerator;

    @Before
    public void setUp() throws Exception {
        sqlGenerator = new SqlGenerator();
    }

    @Test
    public void testQueryStatement() {
        // Given
        String table = "test";
        
        // When
        String result = sqlGenerator.queryStatement(table);

        // Then
        assertEquals("SELECT * FROM test WHERE user_id = ", result);
    }

    @Test
    public void testInsertStatement() {
        // Given
        String table = "test";
        
        // When
        String result = sqlGenerator.insertStatement(table);

        // Then
        assertEquals("INSERT INTO test VALUES ", result);
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
