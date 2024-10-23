package datahandling;

import java.sql.SQLException;

import static org.junit.Assert.fail;
import org.junit.Test;

import com.echonet.datahandling.DataHanlder;

public class DataHanlderTest {
    DataHanlder handlerToTest; 
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
            handlerToTest = new DataHanlder(testDB);
            return;
        } catch (SQLException e) {
            fail("Database connection could not be found.");
        } catch (ClassNotFoundException e) {
            fail("Driver could not be located.");
        }
    }
}
