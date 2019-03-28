import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class CaninetteOooListUnitTest {

    // Test the database connection and the number of stored records on a test database
    @Test
    public void validateNumberOfRecords() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette(CaniCrottes.getSqliteConnection(true));
        int numberOfRecords = daoCanninettes.displayOooCaninettes().size();

        // Checks that there is 1 caninette out of order in the db
        assertEquals(1, numberOfRecords);
    }

    // Test the method displayOooCaninettes() create an entire Caninette Out of Order list without NULL records
    @Test
    public void checkCaninetteList() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette(CaniCrottes.getSqliteConnection(true));
        ArrayList expectedCaninetteList = daoCanninettes.displayOooCaninettes();
        for (int i = 0; i < expectedCaninetteList.size(); i++) {
            Assert.assertNotNull(expectedCaninetteList.get(i));
        }
    }
}

