import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DaoAdmin extends DataBaseConnexion {

<<<<<<< HEAD
    DaoAdmin(String urlDb) throws SQLException {
        super(urlDb);
    }

    /**
     * @param login    String
     * @param password String
     * @return boolean
     */
    boolean loginAdmin(String login, String password) {
=======
    public static void main(String[] args) throws SQLException {
        DaoAdmin dao = new DaoAdmin("jdbc:sqlite:mydatabase.db");
        //System.out.println(dao.loginAdmin("AdminCani", "adminMDP"));
    }

    //Method to check if the user is an admin or not

    /**
     * @param log
     * @param psd
     * @return
     */
    public String loginAdmin(String log, String psd) {
        String result = null;
>>>>>>> 17e033ba3480eaae5c522fa9d693989fb2dba8ae
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select Adm_id, Adm_Nom, Adm_Prenom, Adm_Login, Adm_Motdepasse from Admin ");

            while (rs.next()) {
                // read the result set
                Admin admin = new Admin(rs.getInt("Adm_id"), rs.getString("Adm_Nom"), rs.getString("Adm_Prenom"), rs.getString("Adm_Login"), rs.getString("Adm_Motdepasse"));
                // A faire champ.getText().equalsadmin.getLogin() etc.
<<<<<<< HEAD
                if (admin.getLogin().equals(login) && admin.getMdp().equals(password)) {
                    System.out.println("Vous êtes connecté");
                    ToastMessage toastMessage = new ToastMessage("Vous etes connecté", 3000);
                    toastMessage.setVisible(true);
                    return true;

                } else {
                    System.out.println("Login érroné");
                    ToastMessage toastMessage = new ToastMessage("Identifiant ou mot de passe erroné ", 3000);
                    toastMessage.setVisible(true);
                    return false;
=======
                if (admin.getLogin().equals(log) && admin.getMdp().equals(psd)) {
                    result = ("Vous êtes connecté");
                } else {
                    result = ("Vous n'êtes pas Admin");
>>>>>>> 17e033ba3480eaae5c522fa9d693989fb2dba8ae
                }
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }
<<<<<<< HEAD
        return false;
=======
        return result;
>>>>>>> 17e033ba3480eaae5c522fa9d693989fb2dba8ae
    }
}