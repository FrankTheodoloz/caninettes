import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoCanninettes extends DataBaseConnexion {

    public DaoCanninettes(String urlDb)throws SQLException {
        super(urlDb);
    }

    public ArrayList <Caninette> afficherCaninette() {
        ArrayList listeCaninette = new ArrayList<Caninette>();
        try
        {
           Statement statement = connection.createStatement();
           statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from Caninettes");
            ;
            while (rs.next())
            {
                // read the result set
                Caninette caninette = new Caninette(rs.getInt("Can_id"),rs.getString("Can_adresse"),rs.getString("Can_numero"),rs.getString("Can_etat"), rs.getString("Can_remarques"),rs.getDouble("Can_positionE"),rs.getDouble("Can_positionN"));
                listeCaninette.add(caninette);
            }

        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : "+e.getMessage());
        }
        return listeCaninette;
    }
}
