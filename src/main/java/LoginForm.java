import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm extends JFrame implements ActionListener {

    JPasswordField txtPassword;
    JTextField txtLogin;
    JButton btnSignIn;
    Connexion connexionStatus;

    /**
     * login form to the database
     *
     * @param connexionStatus : object responsible for maintaining the login status for the admin user
     */
    LoginForm(Connexion connexionStatus) {

        this.connexionStatus = connexionStatus;


        this.setResizable(false);
        setTitle("Connexion");
        JPanel up = new JPanel();
        add(up, "North");

        JPanel middle = new JPanel();
        add(middle, "Center");

        JPanel down = new JPanel();
        add(down, "South");


        JLabel lblLogin = new JLabel("Login :        ");
        lblLogin.setBounds(10, 10, 80, 25);
        up.add(lblLogin);

        txtLogin = new JTextField(20);
        txtLogin.setName("txtLogin");
        txtLogin.setBounds(100, 10, 160, 25);
        up.add(txtLogin);

        JLabel lblPassword = new JLabel("Password :");
        lblLogin.setBounds(10, 10, 80, 25);
        middle.add(lblPassword);

        txtPassword = new JPasswordField(20);
        txtPassword.setName("txtPassword");
        txtPassword.setBounds(100, 10, 160, 25);
        middle.add(txtPassword);

        btnSignIn = new JButton("Connexion");
        btnSignIn.setName("btnSignIn");
        btnSignIn.addActionListener(this);
        down.add(btnSignIn);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSignIn)) {
            try {
                System.out.println(txtLogin.getText());
                DaoAdmin log = new DaoAdmin("jdbc:sqlite:mydatabase.db");
                if (log.loginAdmin(txtLogin.getText(), String.valueOf(txtPassword.getPassword()))) {
                    dispose();
                    connexionStatus.setConnection();
                } else {
                    connexionStatus.setDeconnection();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}


