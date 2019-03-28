/**
 * Entry point of the program
 */

import java.sql.SQLException;

public class CaniCrottes {
    private static String SqliteConnection = "jdbc:sqlite:mydatabase.db";
    private static String SqliteConnectionTest = "jdbc:sqlite:mydatabaseTest.db";

    public static void main(String[] args) throws SQLException {
        createWindow();

    }

    public static MainWindow createWindow() throws SQLException {
        MainWindow f = new MainWindow("CaniCrottes");

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        return f;
    }

    public static String getSqliteConnection(boolean isTest) {
        if (isTest) {
            return SqliteConnectionTest;
        } else {
            return SqliteConnection;
        }
    }
}
