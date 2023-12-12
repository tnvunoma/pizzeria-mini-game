package indy;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This is teh SlicingTab class. It is responsible for setting up the screen under the slicing tab.
 */
public class SlicingTab {
    private final Tab slicingTab;
    private final PizzaOrganizer pizzaOrganizer;
    public SlicingTab(PizzaOrganizer pizzaOrganizer) {
        this.slicingTab = new Tab("Slicing");
        this.pizzaOrganizer = pizzaOrganizer;
        this.slicingTab.setContent(this.createSlicingPane());
    }

    /**
     * This method returns the slicing tab.
     */
    public Tab getSLicingTab() {
        return this.slicingTab;
    }

    /**
     * This method creates the border pane of the slicing tab and instantiates the Slicing class.
     */
    public BorderPane createSlicingPane() {
        BorderPane slicingPane = new BorderPane();
        slicingPane.setPrefSize(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        AssemblyTab.createSideBar(slicingPane);
        ImageView slicingBackground = new ImageView(new Image("./indy/slicing_bg.png"));
        slicingPane.getChildren().add(slicingBackground);

        Pane pane = new Pane();
        slicingPane.setCenter(pane);
        new Slicing(pane, this.pizzaOrganizer);

        return slicingPane;
    }
}
