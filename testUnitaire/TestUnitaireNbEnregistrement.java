import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class TestUnitaireNbEnregistrement  {
    @Test public void nbEnregistrementValide() throws SQLException {
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabaseTest.db");
        int nbEnregistrement = daoCanninettes.calculerNbEnregistrement();
        assertEquals(true,daoCanninettes.validerEnregistrement(nbEnregistrement));
    }

    @Test public void nbEnregistrementNonValide() throws SQLException {
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabaseTest.db");
        int nbEnregistrement = 299;
        assertEquals(false,daoCanninettes.validerEnregistrement(nbEnregistrement));
    }

}
