
import java.awt.event.ActionListener;
        import java.awt.event.ActionEvent;
        import javax.swing.*;
        import java.awt.*;
        import java.sql.SQLException;
        import java.util.ArrayList;

        import static com.sun.javafx.fxml.expression.Expression.add;

public class ListeCaninetteHS extends JFrame implements ActionListener {

    private DaoCanninettes daoCanninettes;
    //Bouton
    JButton  btnQuitter;
    JTextArea listCaniHs;


    public ListeCaninetteHS(String aTitle) throws SQLException {
        setTitle(aTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabase.db") ;

        JPanel jpHaut = new JPanel(new BorderLayout());
        add(jpHaut, "North");
        JPanel jpWest = new JPanel();
        jpHaut.add(jpWest, "West");
        JPanel jpBas = new JPanel(new BorderLayout());
        add(jpBas, "South");

        btnQuitter = new JButton("Fermer");
        jpWest.add(btnQuitter);
        btnQuitter.addActionListener(this);

        listCaniHs = new JTextArea();
        JScrollPane jsp = new JScrollPane(listCaniHs, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jsp = new JScrollPane(listCaniHs);
        listCaniHs.append(daoCanninettes.afficherCaninetteHS().toString());
        add(jsp, "Center");

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(btnQuitter)) {
            this.dispose();
        }
    }
}