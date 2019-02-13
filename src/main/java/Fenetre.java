import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

class Fenetre extends JFrame implements ActionListener {

    //Bouton
    JButton btnConnexion, btnCaninettesHS, btnQuitter;
    MapView mapView;


    public Fenetre(String aTitle) {
        setTitle(aTitle);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        // create stack pane and application scene
        StackPane stackPane = new StackPane();
        JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel);
        Scene scene = new Scene(stackPane, 800, 700);
        jfxPanel.setScene(scene);

        // create a ArcGISMap
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 46.20692080361156, 6.142971280091718, 15);

        // set the map to be displayed in this view
        mapView = new MapView();
        mapView.setMap(map);

        // add the map view to stack pane
        Platform.runLater(() -> {
            stackPane.getChildren().addAll(mapView);
        });


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(btnQuitter)) {
            System.exit(0);
        }
    }

}