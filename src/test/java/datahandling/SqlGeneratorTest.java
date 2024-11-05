package datahandling;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.SqlGenerator;
import com.echonet.datahandling.Table;
import com.echonet.domainmodel.User;

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
        ResultSet emptySet = null;
        User u = new User(1, emptySet);
        u.setTable(testTable);
        // When
        String result = sqlGenerator.queryStatement(testTable, u);

        // Then
        assertEquals("SELECT * FROM TestTable1 WHERE user_id = 1", result);
    }

    @Test
    public void testInsertStatement() {
        // When
        String result = sqlGenerator.insertStatement(testTable);

        // Then
        assertEquals("INSERT INTO TestTable1 VALUES (?,?,?)", result);
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
