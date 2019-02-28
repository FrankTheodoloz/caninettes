/***
 * Fichier pour tester l'affichage d'un pin sur la carte
 */

import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class TestMapPin extends Application {

    private MapView mapView;

    /* ** ADD ** */
    private int hexRed = 0xFFFF0000;
    private int hexBlue = 0xFF00FF00;
    private int hexGreen = 0xFF0000FF;
    private GraphicsOverlay graphicsOverlay;

    private void setupGraphicsOverlay() {
        if (mapView != null) {
            graphicsOverlay = new GraphicsOverlay();
            mapView.getGraphicsOverlays().add(graphicsOverlay);
        }
    }
    private void addPointGraphic() {
        if (graphicsOverlay != null) {
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, hexRed, 10.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 2.0f));
//            Point point = new Point(-118.29507, 34.13501, SpatialReferences.getWgs84());
            Point point = new Point(6.142971280091718, 46.20692080361156, SpatialReferences.getWgs84());
            Graphic pointGraphic = new Graphic(point, pointSymbol);
            graphicsOverlay.getGraphics().add(pointGraphic);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        // create stack pane and application scene
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);

        // set title, size, and add scene to stage
        stage.setTitle("Display Map Sample");
        stage.setWidth(800);
        stage.setHeight(700);
        stage.setScene(scene);
        stage.show();

        // create a ArcGISMap with the a Basemap instance with an Imagery base layer
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 46.20692080361156, 6.142971280091718, 15);
        // set the map to be displayed in this view
        mapView = new MapView();

        /* ** ADD ** */
        setupGraphicsOverlay();
        addPointGraphic();


        mapView.setOnMouseClicked(event -> {
            // check that the primary mouse button was clicked
            if (event.isStillSincePress() && event.getButton() == MouseButton.PRIMARY) {
                // create a point from where the user clicked
                Point2D point = new Point2D(event.getX(), event.getY());

                // create a map point from a point
                Point mapPoint = mapView.screenToLocation(point);

                // for a wrapped around map, the point coordinates include the wrapped around value
                // for a service in projected coordinate system, this wrapped around value has to be normalized
                Point normalizedMapPoint = (Point) GeometryEngine.normalizeCentralMeridian(mapPoint);

                // add a new feature to the service feature table
//                addFeature(normalizedMapPoint, featureTable);
                System.out.println("Click at " + mapPoint.getX() + " " + mapPoint.getY());
            }
        });



        mapView.setMap(map);

        // add the map view to stack pane
        stackPane.getChildren().addAll(mapView);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /***
     * Stops and releases all resources used in application.
     */
    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose();
        }
    }

}