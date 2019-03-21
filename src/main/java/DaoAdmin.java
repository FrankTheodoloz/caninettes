import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoAdmin extends DataBaseConnexion {
    public DaoAdmin(String urlDb) throws SQLException {
        super(urlDb);
    }


    /**
     *
     * @param login
     * @param password
     * @return
     */
    public boolean loginAdmin(String login, String password){
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select Adm_id, Adm_Nom, Adm_Prenom, Adm_Login, Adm_Motdepasse from Admin ");

            while (rs.next()) {
                // read the result set
                Admin admin = new Admin(rs.getInt("Adm_id"), rs.getString("Adm_Nom"), rs.getString("Adm_Prenom"), rs.getString("Adm_Login"), rs.getString("Adm_Motdepasse"));
                // A faire champ.getText().equalsadmin.getLogin() etc.
                if (admin.getLogin().equals(login) && admin.getMdp().equals(password)){
                    System.out.println("Vous êtes connecté");
                    ToastMessage toastMessage = new ToastMessage("Vous etes connecté",3000);
                    toastMessage.setVisible(true);
                    return true;

                }else{
                    System.out.println("Login érroné");
                    ToastMessage toastMessage = new ToastMessage("Identifiant ou mot de passe erroné ",3000);
                    toastMessage.setVisible(true);
                    return false;
                }
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Erreur SQL : " + e.getMessage());
        }
        return false;
    }

}


class ToastMessage extends JDialog {
    int miliseconds;
    public ToastMessage(String toastString, int time) {
        this.miliseconds = time;
        setUndecorated(true);
        getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel toastLabel = new JLabel("");
        toastLabel.setText(toastString);
        toastLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        toastLabel.setForeground(Color.WHITE);

        setBounds(100, 100, toastLabel.getPreferredSize().width+20, 31);


        setAlwaysOnTop(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int y = dim.height/2-getSize().height/2;
        int half = y/2;
        setLocation(dim.width/2-getSize().width/2, y+half);
        panel.add(toastLabel);
        setVisible(false);

        new Thread(){
            public void run() {
                try {
                    Thread.sleep(miliseconds);
                    dispose();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }}



//Toast inspired by : https://stackoverflow.com/questions/10161149/android-like-toast-in-swing