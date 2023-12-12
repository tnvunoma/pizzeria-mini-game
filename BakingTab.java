package indy;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This is the BakingTab class. It creates the graphics of the screen of the baking tab and instantiates the Baking
 * class.
 */
public class BakingTab {
    private final Tab bakingTab;
    private final PizzaOrganizer pizzaOrganizer;
    public BakingTab(PizzaOrganizer pizzaOrganizer){
        this.bakingTab = new Tab("Baking");
        this.pizzaOrganizer = pizzaOrganizer;
        this.bakingTab.setContent(this.createBakingPane());
    }

    /**
     * This method returns the baking tab.
     */
    public Tab getBakingTab() {
        return this.bakingTab;
    }

    /**
     * This method creates the border pane of the baking tab and return it. It instantiates Baking.
     */
    public BorderPane createBakingPane() {
        BorderPane bakingPane = new BorderPane();
        bakingPane.setPrefSize(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        AssemblyTab.createSideBar(bakingPane);
        ImageView bakingBackground = new ImageView(new Image("./indy/baking_bg.png"));
        bakingPane.getChildren().add(bakingBackground);

        Pane pane = new Pane();
        bakingPane.setCenter(pane);
        new Baking(pane, this.pizzaOrganizer);

        return bakingPane;
    }
}
