import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame{

        JPasswordField txtPassword;
        JTextField txtLogin;
        JButton btnConnexion;

        LoginForm(){
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
            txtLogin.setBounds(100,10,160,25);
            up.add(txtLogin);

            JLabel lblPassword = new JLabel("Password :");
            lblLogin.setBounds(10, 10, 80, 25);
            middle.add(lblPassword);

            txtPassword = new JPasswordField(20);
            txtLogin.setBounds(100,10,160,25);
            middle.add(txtPassword);

            btnConnexion = new JButton("Connexion");
            down.add(btnConnexion);

        }

    }


