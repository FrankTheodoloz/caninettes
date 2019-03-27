import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginForm extends JFrame implements ActionListener {

        JPasswordField txtPassword;
        JTextField txtLogin;
        JButton btnSignIn;
        Connexion connexionStatus;

    /**
     *  Formulaire de connexion à la base de données
     * @param connexionStatus : objet chargé de maintenir le status de connexion pour l'utilisateur admin
     */
    LoginForm(Connexion connexionStatus){

            this.connexionStatus= connexionStatus;

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
            txtLogin.setName("log");
            txtLogin.setBounds(100,10,160,25);
            up.add(txtLogin);

            JLabel lblPassword = new JLabel("Password :");
            lblLogin.setBounds(10, 10, 80, 25);
            middle.add(lblPassword);

            txtPassword = new JPasswordField(20);
            txtPassword.setName("Password");
            txtPassword.setBounds(100,10,160,25);
            middle.add(txtPassword);

            btnSignIn = new JButton("Connexion");
            btnSignIn.setName("btnSignIn");
            btnSignIn.addActionListener(this);
            down.add(btnSignIn);

        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnSignIn)){
            try {
//                if(txtLogin.getText().equals("") || txtPassword.equals("")){
//
//                }
                System.out.println(txtLogin.getText());
                DaoAdmin log = new DaoAdmin("jdbc:sqlite:mydatabase.db");
//                log.loginAdmin(txtLogin.getText(), String.valueOf(txtPassword.getPassword()));
                if(log.loginAdmin(txtLogin.getText(), String.valueOf(txtPassword.getPassword()))){
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


