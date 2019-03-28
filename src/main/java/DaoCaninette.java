import java.sql.PreparedStatement;
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
        //dao.displayCaninettes();
        //System.out.println(dao.insertCaninette("Adresse","555","Etat","remarques ",25,26 ));
        //System.out.println(dao.updateCaninette(1,"Posée"));
        System.out.println(dao.deleteCaninette(836));
    }

    public ArrayList<Caninette> displayCaninettes() {
        ArrayList<Caninette> caninetteList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes");

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

    public Caninette getCaninette(Integer id) {
        Caninette caninette = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes where Can_id = " + id);

            while (rs.next()) {
                // read the result set
                caninette = new Caninette(rs.getInt("Can_id"), rs.getString("Can_adresse"), rs.getString("Can_numero"), rs.getString("Can_etat"), rs.getString("Can_remarques"), rs.getDouble("Can_positionE"), rs.getDouble("Can_positionN"));
            }
        } catch (SQLException e) {
            // If the error message is "out of memory",
            // It probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return caninette;
    }

    public ArrayList<Caninette> displayOooCaninettes() {
        ArrayList caninettesOooList = new ArrayList<Caninette>();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes where Can_etat = 'En travaux' OR Can_etat = 'Hors service' ");
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

    // Mirko m' aidé à utiliser preparedStatement sur la méthode insertCaninette()
    public int insertCaninette(String adresse, String numero, String etat, String remarques, double positionE, double positionN) {
        int result = -1;
        try {

            String sql = "INSERT INTO Caninettes (Can_Adresse,Can_Numero,Can_Etat,Can_Remarques,Can_PositionE,Can_PositionN) VALUES (?,?,?,?,?,? );";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, adresse);
            statement.setString(2, numero);
            statement.setString(3, etat);
            statement.setString(4, remarques);
            statement.setDouble(5, positionE);
            statement.setDouble(6, positionN);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return result;
    }

    public int updateCaninette(int id, String etat) {
        int result = 0;
        try {
            String sql = "UPDATE Caninettes SET Can_Etat = ? WHERE Can_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, etat);
            statement.setInt(2, id);
            result = statement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return result;
    }

    public int deleteCaninette(int id) {
        int result = 0;
        try {
            String sql = "DELETE FROM  Caninettes WHERE Can_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
