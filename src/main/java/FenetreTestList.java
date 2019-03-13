import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FenetreTestList extends JFrame implements ActionListener{


    //Bouton
    JButton  btnFermer;
    JTextArea listCani;


    public FenetreTestList(String aTitle) throws SQLException {
        setTitle(aTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabase.db") ;

        JPanel jpHaut = new JPanel(new BorderLayout());
        add(jpHaut, "North");
        JPanel jpWest = new JPanel();
        jpHaut.add(jpWest, "West");
        JPanel jpBas = new JPanel(new BorderLayout());
        add(jpBas, "South");

         btnFermer = new JButton("Fermer");
        jpWest.add(btnFermer);
        btnFermer.addActionListener(this);

        listCani = new JTextArea();
        JScrollPane jsp = new JScrollPane(listCani, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp = new JScrollPane(listCani);
        listCani.append(daoCanninettes.afficherCaninette().toString());
        add(jsp, "Center");

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(btnFermer)) {
            this.dispose();
        }
    }
}
