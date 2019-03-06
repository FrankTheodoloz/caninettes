import java.sql.SQLException;

public class CaniCrottes {

    public static void main(String[] args) throws SQLException {
        FenetreAccueil f = new FenetreAccueil("CaniCrottes");

            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);

    }
}
