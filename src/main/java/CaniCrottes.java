/**
 * Entry point of the program
 */

import java.sql.SQLException;

public class CaniCrottes {

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
}
