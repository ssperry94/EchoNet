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
import com.echonet.utilities.Config;

public class DataHanlderTest {
    String fakeDB = "jdbc:sqlite:src/test/echodatatestfake.db";

    @Test
    public void testMainDataBaseConnection() {
        try {
            DataHanlder handler = new DataHanlder(Config.DATABASE_INIT);
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
    public void testUnitTestDataBaseConnection() {
        try {
            DataHanlder handlerToTest = new DataHanlder(Config.TEST_DATABASE_INIT);
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
    public void testCannotFindDataBase() {
        try 
        {
            DataHanlder h = new DataHanlder(fakeDB);
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
            DataHanlder handlerToTest = new DataHanlder(Config.TEST_DATABASE_INIT);
            assertTrue(handlerToTest.isExist(Config.TEST_DATABASE_INIT));
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
            DataHanlder handlerToTest = new DataHanlder(Config.TEST_DATABASE_INIT);
            assertFalse(handlerToTest.isExist(fakeDB));
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
            DataHanlder handlerToTest = new DataHanlder(Config.TEST_DATABASE_INIT);
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
