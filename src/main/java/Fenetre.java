import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Fenetre extends JFrame implements ActionListener {

    // Buttons
    JButton btnConnexion, btnCaninettesHS, btnQuitter, btnListeCani;
    MapView mapView;

    // CONSTANTS
    // Location in Geneva where the caninettes are located
    private static final double LAT_GVA = 46.20692080361156;
    private static final double LON_GVA = 6.142971280091718;

    // Map zoom level in order to reduce blank zones where there is no caninettes
    private static final int ZOOM_LEVEL = 15;

    // Window size
    private static final int SCENE_SIZE_X = 800;
    private static final int SCENE_SIZE_Y = 700;

    private int hexRed = 0xFFFF0000;
    private int hexBlue = 0xFF00FF00;
    private int hexGreen = 0xFF0000FF;
    private GraphicsOverlay graphicsOverlay;
    private PictureMarkerSymbol pinSymbol;
    private ListenableFuture<IdentifyGraphicsOverlayResult> identifyGraphics;


    public Fenetre(String aTitle) throws SQLException {
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
        btnListeCani.setName("btnListeCani");
        jpWest.add(btnListeCani);
        btnListeCani.addActionListener(this);

        btnCaninettesHS = new JButton("Caninettes hors service");
        btnCaninettesHS.setName("btnCaninettesHS");
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

        // create a ArcGISMap with Location and Zoom settings
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, LAT_GVA, LON_GVA, ZOOM_LEVEL);

        // set the map to be displayed in this view
        mapView = new MapView();

        setupGraphicsOverlay();
        addPointGraphic();

        mapView.setOnMouseClicked(event -> {
            // check that the primary mouse button was clicked
            if (event.isStillSincePress() && event.getButton() == MouseButton.PRIMARY) {
                // create a point from where the user clicked
                Point2D point = new Point2D(event.getX(), event.getY());

                // create a map point from a point
                com.esri.arcgisruntime.geometry.Point mapPoint = mapView.screenToLocation(point);

                // for a wrapped around map, the point coordinates include the wrapped around value
                // for a service in projected coordinate system, this wrapped around value has to be normalized
                com.esri.arcgisruntime.geometry.Point normalizedMapPoint = (Point) GeometryEngine.normalizeCentralMeridian(mapPoint);

                // add a new feature to the service feature table
//                addFeature(normalizedMapPoint, featureTable);
                System.out.println("Click at " + mapPoint.getX() + " " + mapPoint.getY());
            }
        });

        mapView.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY && e.isStillSincePress()) {
                // create a point from location clicked
                Point2D mapViewPoint = new Point2D(e.getX(), e.getY());

                // identify graphics on the graphics overlay
                identifyGraphics = mapView.identifyGraphicsOverlayAsync(graphicsOverlay, mapViewPoint, 1, false);

                identifyGraphics.addDoneListener(() -> Platform.runLater(this::createGraphicDialog));
            }
        });

        // Add the ArcGIS map to the mapView
        mapView.setMap(map);

        // Add the map view to stack pane
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
    public ArrayList<Caninette> caninettesList() throws SQLException {


        ArrayList<Caninette> list = new ArrayList<Caninette>();
        try {
            DaoCanninettes daoCanninettes = new DaoCanninettes("jdbc:sqlite:mydatabase.db");
            list = daoCanninettes.afficherCaninette();

        } catch (SQLException e) {
            System.out.println("erreur dao" + e);
        }

        return list;
    }

    private void setupGraphicsOverlay() {
        if (mapView != null) {
            graphicsOverlay = new GraphicsOverlay();
            mapView.getGraphicsOverlays().add(graphicsOverlay);
        }
    }

    private void addPointGraphic() throws SQLException {
        if (graphicsOverlay != null) {

            // create a pin graphic
            javafx.scene.image.Image img = new Image(getClass().getResourceAsStream("pin.png"), 0, 80, true, true);
            pinSymbol = new PictureMarkerSymbol(img);
            pinSymbol.loadAsync();

            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 5.0f);
//            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 2.0f));
//            Point point = new Point(-118.29507, 34.13501, SpatialReferences.getWgs84());

            SpatialReference wgs84 = SpatialReference.create(2056);

            double lat, lon;
            String Adresse = "";
            String Description = "";
            Map<String, Object> attributes = null;


            for (Caninette c : caninettesList()) {
                lat = c.getPositionN();
                lon = c.getPositionE();

                attributes = new HashMap<String, Object>();
                attributes.put("Adresse", c.getAdresse());
                attributes.put("Etat", c.getEtat());
                attributes.put("Numero", c.getNumero());
                attributes.put("Remarque", c.getRemarque());
                attributes.put("Id", c.getId());


                com.esri.arcgisruntime.geometry.Point point = new Point(lon, lat, wgs84);
//                Graphic pointGraphic = new Graphic(point, attributes, pointSymbol);
                Graphic pointGraphic = new Graphic(point, attributes, pinSymbol);
                graphicsOverlay.getGraphics().add(pointGraphic);
            }

        }
    }

    /**
     * Indicates when a graphic is clicked by showing an Alert.
     */
    private void createGraphicDialog() {

        try {
            // get the list of graphics returned by identify
            IdentifyGraphicsOverlayResult result = identifyGraphics.get();
            List<Graphic> graphics = result.getGraphics();


            if (!graphics.isEmpty()) {
                // show a alert dialog box if a graphic was returned
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.initOwner(mapView.getScene().getWindow());
                dialog.setHeaderText(null);
                dialog.setTitle("Information Dialog Sample");
                dialog.setContentText("Id : " + graphics.get(0).getAttributes().get("Id")
                        + "\nAdresse : " + graphics.get(0).getAttributes().get("Adresse")
                        + "\nEtat : " + graphics.get(0).getAttributes().get("Etat")
                        + "\nRemarque : " + graphics.get(0).getAttributes().get("Remarque")
                        + "\nNumero : " + graphics.get(0).getAttributes().get("Numero")
                );

                dialog.showAndWait();
            }
        } catch (Exception e) {
            // on any error, display the stack trace
            e.printStackTrace();
        }
    }
}