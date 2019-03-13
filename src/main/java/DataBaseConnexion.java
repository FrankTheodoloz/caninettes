import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection to the SQLlite db
 */
public class DataBaseConnexion {
    public Connection connection;

    public DataBaseConnexion(String urlDb) throws SQLException {
        connection = DriverManager.getConnection(urlDb);
    }
}



