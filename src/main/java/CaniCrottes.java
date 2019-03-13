import java.sql.SQLException;

public class CaniCrottes {

    public static void main(String[] args) throws SQLException {
        Fenetre f = new Fenetre("CaniCrottes");

            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

    }
}
