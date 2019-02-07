import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class TestMapApp extends Application {

    private MapView mapView;

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
        ArcGISMap map = new ArcGISMap(Basemap.Type.IMAGERY, 46.20692080361156, 6.142971280091718, 15);
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