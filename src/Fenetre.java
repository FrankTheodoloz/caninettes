import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

class Fenetre extends JFrame implements ActionListener {

    //Bouton
    JButton btnConnexion, btnCaninettesHS, btnQuitter;

    public Fenetre(String aTitle) {
        setTitle(aTitle);

        JPanel jpHaut = new JPanel(new BorderLayout());
        add(jpHaut, "North");
        JPanel jpWest = new JPanel();
        jpHaut.add(jpWest, "West");

        btnConnexion = new JButton("Connexion");
        jpWest.add(btnConnexion);
        btnConnexion.addActionListener(this);

        btnCaninettesHS = new JButton("Caninettes hors service");
        jpWest.add(btnCaninettesHS);
        btnCaninettesHS.addActionListener(this);

        btnQuitter = new JButton("Quitter");
        jpWest.add(btnQuitter);
        btnQuitter.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(btnQuitter)) {
            System.exit(0);
        }
    }

}