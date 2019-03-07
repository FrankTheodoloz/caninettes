/** **/
import org.junit.Assert;
import org.junit.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static junit.framework.TestCase.assertEquals;

public class TestUnitaireListeCaninette {

    /** Tester la connexion à la base de données et le nombre d'enregistrements stockés, sur une base de données test**/
    @Test public void nbEnregistrementValide() throws SQLException {
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabaseTest.db");
        int nbEnregistrement = daoCanninettes.afficherCaninette().size();
        /** Le nombre d'enregistrements de la base de données est de 601**/
        assertEquals(601,nbEnregistrement);
    }

    /** Tester que la méthode afficherCaninette() créer une liste entière de caninette sans enregistrements NULL**/
    @Test public void vérificationListeCaninette() throws SQLException {
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabaseTest.db");
        ArrayList listeCaninettesAttendues = daoCanninettes.afficherCaninette();
        for (int i = 0; i <listeCaninettesAttendues.size() ; i++) {
            Assert.assertNotNull(listeCaninettesAttendues.get(i));
        }
    }

}
