/***
 * Fichier pour tester l'affichage des points OpenData sur la carte
 */

import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class TestMapApp extends Application {

    private MapView mapView;

    /* ** ADD ** */
    private FeatureLayer featureLayer;
    private final static String FEATURE_SERVICE_URL = "https://ge.ch/sitgags1/rest/services/VECTOR/SITG_OPENDATA_04/MapServer/3681";
    private int hexRed = 0xFFFF0000;
    private int hexBlue = 0xFF00FF00;
    private int hexGreen = 0xFF0000FF;
    private GraphicsOverlay graphicsOverlay;

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

        // ** create a service feature table using the url
        final ServiceFeatureTable featureTable = new ServiceFeatureTable(FEATURE_SERVICE_URL);
        // ** create a feature layer from the service feature table
        featureLayer = new FeatureLayer(featureTable);
        // ** add feature layer to ArcGISMap
        map.getOperationalLayers().add(featureLayer);

        // set the map to be displayed in this view
        mapView = new MapView();
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