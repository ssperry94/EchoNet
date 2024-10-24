package datahandling;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.echonet.datahandling.DataHanlder;
import com.echonet.exceptions.DataBaseNotFoundException;

public class DataHanlderTest {
    String testDB = "jdbc:sqlite:src/test/echodatatest.db";
    String fakeDB = "jdbc:sqlite:src/test/echodatatestfake.db";
    String testDBPath = "src/test/echodatatest.db";
    String fakeDBPath = "src/test/echodatatestfake.db";

    @Test
    public void testMainDataBaseConnection() {
        try {
            DataHanlder handler = new DataHanlder();
            return;
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }

    //tests constructor that goes to database used in testing 
    @Test
    public void testFakeDataBaseConnection() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB, testDBPath);
            return;
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }

    @Test
    public void testExistsMethodWithNoParams() {
        try 
        {
            DataHanlder h = new DataHanlder();
            assertTrue(h.isExist());
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }

    @Test
    public void testCannotFindDataBase() {
        try 
        {
            DataHanlder h = new DataHanlder(fakeDB, fakeDBPath);
            fail("Did not catch fake database.");
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            return;
        }
    }
    @Test
    public void testExistsMethodWithParams() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB, testDBPath);
            assertTrue(handlerToTest.isExist(testDBPath));
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }

    @Test
    public void testExistsWithFail() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB, testDBPath);
            assertFalse(handlerToTest.isExist(fakeDBPath));
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }

    @Test
    public void testGetTableColumnNames() {
        String tableName = "TestTable1";
        List <String> expectedTableNames = new ArrayList<>();
        List <String> acutalTableNames = new ArrayList<>();
        expectedTableNames.add("user_id");
        expectedTableNames.add("test_col_1");
        expectedTableNames.add("test_col_2");

        try {
            DataHanlder handlerToTest = new DataHanlder(testDB, testDBPath);
            acutalTableNames = handlerToTest.getTableColumnNames(tableName);
            assertEquals(expectedTableNames, acutalTableNames);
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        } catch (DataBaseNotFoundException e) {
            fail("Cannot find database.");
        }
    }
}
