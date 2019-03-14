
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

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

}