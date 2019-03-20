import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DAO Caninettes to access to the db records
 */
public class DaoCaninette extends DataBaseConnexion {

    public DaoCaninette(String urlDb) throws SQLException {
        super(urlDb);
    }

    public static void main(String[] args) throws SQLException {
        DaoCaninette dao = new DaoCaninette("jdbc:sqlite:mydatabaseTest.db");
        dao.displayCaninettes();
        //dao.insertCaninette();
        //dao.updateCaninette();
        //dao.deleteCaninette();
    }

    public ArrayList<Caninette> displayCaninettes() {
        ArrayList<Caninette> caninetteList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes");
            ;
            while (rs.next()) {
                // read the result set
                Caninette caninette = new Caninette(rs.getInt("Can_id"), rs.getString("Can_adresse"), rs.getString("Can_numero"), rs.getString("Can_etat"), rs.getString("Can_remarques"), rs.getDouble("Can_positionE"), rs.getDouble("Can_positionN"));
                caninetteList.add(caninette);
            }

        } catch (SQLException e) {

            // If the error message is "out of memory",
            // It probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return caninetteList;
    }

    public ArrayList<Caninette> displayOooCaninettes() {
        ArrayList caninettesOooList = new ArrayList<Caninette>();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes where Can_etat = 'En travaux' ");
            ;
            while (rs.next()) {
                // read the result set
                Caninette caninette = new Caninette(rs.getInt("Can_id"), rs.getString("Can_adresse"), rs.getString("Can_numero"), rs.getString("Can_etat"), rs.getString("Can_remarques"), rs.getDouble("Can_positionE"), rs.getDouble("Can_positionN"));
                caninettesOooList.add(caninette);
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return caninettesOooList;
    }

    public void insertCaninette(){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Caninettes (Can_Adresse,Can_Numero,Can_Etat,Can_Remarques,Can_PositionE,Can_PositionN) VALUES ('Adresse', 'Numero', 'Etat', 'Remarque', 20.3, 34.2 );";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }

    }

    public void updateCaninette(){
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE Caninettes SET Can_Etat = 'Travaux' WHERE Can_id = 802;";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }

    }

    public void deleteCaninette(){
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM  Caninettes WHERE Can_id = 800;";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }

    }
}
