
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CaninetteUnitTest {


    // Test that the Caninette's to String is correct
    @Test
    public void checkDisplayCaninette() {
        Caninette caninette = new Caninette(602, "Av. Curé-Baud 14", "2_99", "Posée", "Très belle", 2599865.69, 1112555.36);
        String expectedValue = "Caninette n°: 602" + "\n" +
                "Adresse: Av. Curé-Baud 14" + "\n" +
                "Numero: 2_99" + "\n" +
                "Etat: Posée" + "\n" +
                "Remarque: Très belle" + "\n" +
                "======================" + "\n";
        assertEquals(expectedValue, caninette.toString());
    }

    // Test that the method displayCaninettes() create correctly an object Caninette
    @Test
    public void checkCaninetteObjectCreation() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette("jdbc:sqlite:mydatabaseTest.db");
        String expectedValue = "Caninette n°: 2" + "\n" +
                "Adresse: Rue de Saint-Jean 1" + "\n" +
                "Numero: 5_44" + "\n" +
                "Etat: Posée" + "\n" +
                "Remarque: " + "\n" +
                "======================" + "\n";
        assertEquals(expectedValue, daoCanninettes.displayCaninettes().get(1).toString());
    }

    // Test that the method checkInsertCaninette() insert a caninette thanks to the return from the method
    @Test
    public void checkInsertCaninette() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette("jdbc:sqlite:mydatabaseTest.db");
        int testValue = -1;

        assertThat(daoCanninettes.insertCaninette("Adresse", "344", "Posée", "", 26.4, 27.4), not(equalTo(testValue)));
    }

    // Test that the method checkUpdateCaninette() update a caninette thanks to the return(statement.executeUpdate()) from the method
    @Test
    public void checkUpdateCaninette() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette("jdbc:sqlite:mydatabaseTest.db");
        int expectedResult = 1;
        assertEquals(expectedResult, daoCanninettes.updateCaninette(1, "Posée"));
    }

    // Test that the method checkDeleteCaninette() delete a caninette thanks to the return(statement.executeUpdate()) from the method
    @Test
    public void checkDeleteCaninette() throws SQLException {
        DaoCaninette daoCanninettes = new DaoCaninette("jdbc:sqlite:mydatabaseTest.db");
        int expectedResult = 1;
        int idTested = daoCanninettes.insertCaninette("AdresseTest", "344", "Posée", "", 26.4, 27.4);
        assertEquals(expectedResult, daoCanninettes.deleteCaninette(idTested));
    }

}
