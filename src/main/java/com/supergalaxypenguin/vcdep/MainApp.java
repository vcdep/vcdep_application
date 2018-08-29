package com.supergalaxypenguin.vcdep;

import com.supergalaxypenguin.vcdep.controller.implementations.MainController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *  Main class for the VCDEP Application
 * @author Howtoon
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainController controller = MainController.getInstance();
        controller.setJavaFXStage(stage);
        controller.displayConfigurationScene();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
