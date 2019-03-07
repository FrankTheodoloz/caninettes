import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class Fenetre extends JFrame implements ActionListener {

    //Bouton
    JButton btnConnexion, btnCaninettesHS, btnQuitter, btnListeCani;
    MapView mapView;

    //Constantes
    private static final double LAT_GVA = 46.20692080361156; // Centre de Genève
    private static final double LON_GVA = 6.142971280091718; // Centre de Genève
    private static final int SCENE_SIZE_X = 800; // Taille par défaut de la fenêtre
    private static final int SCENE_SIZE_Y = 700; // Taille par défaut de la fenêtre
    private static final int ZOOM_LEVEL = 15;

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

        btnListeCani = new JButton("Liste des caninettes");
        jpWest.add(btnListeCani);
        btnListeCani.addActionListener(this);

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
        Scene scene = new Scene(stackPane, SCENE_SIZE_X, SCENE_SIZE_Y );
        jfxPanel.setScene(scene);

        // create a ArcGISMap
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, LAT_GVA, LON_GVA, ZOOM_LEVEL);

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

        if (event.getSource().equals(btnListeCani)) {
            FenetreTestList fList = null;
            try {
                fList = new FenetreTestList("La liste de toutes les canninettes");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fList.pack();
            fList.setLocationRelativeTo(null);
            fList.setVisible(true);
        }

        if (event.getSource().equals(btnCaninettesHS)) {
            ListeCaninetteHS fList = null;
            try {
                fList = new ListeCaninetteHS("La liste des canninettes hors service");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fList.pack();
            fList.setLocationRelativeTo(null);
            fList.setVisible(true);
        }
    }

}