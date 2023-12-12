package indy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the App class. It sets the stage and starts the game.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Papa's Pizzeria");
        stage.setScene(new Scene(new TabsOrganizer().getTabPane()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // launch is a method inherited from Application
    }
}
