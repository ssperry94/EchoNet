package datahandling;

import java.sql.SQLException;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.echonet.datahandling.DataHanlder;

public class DataHanlderTest {
    String testDB = "jdbc:sqlite:src/test/echodatatest.db";

    @Test
    public void testMainDataBaseConnection() {
        try {
            DataHanlder handler = new DataHanlder();
            return;
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        }
    }

    //tests constructor that goes to database used in testing 
    @Test
    public void testFakeDataBaseConnection() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB);
            return;
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
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
        }
    }

    @Test
    public void testExistsMethodWithParams() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB);
            assertTrue(handlerToTest.isExist("src/test/echodatatest.db"));
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        }
    }

    @Test
    public void testExistsWithFail() {
        try {
            DataHanlder handlerToTest = new DataHanlder(testDB);
            assertFalse(handlerToTest.isExist("src/test/echodatatestfake.db"));
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        }
    }
}
