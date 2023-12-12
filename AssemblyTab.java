package indy;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class creates the graphics of the assembly tab. It instantiates the Assembling class which allows users to
 * decorate pizzas while they are on this tab.
 */
public class AssemblyTab {
    private final Tab assemblyTab;
    private final PizzaOrganizer pizzaOrganizer;
    public AssemblyTab(PizzaOrganizer pizzaOrganizer) {
        this.assemblyTab = new Tab("Assembling");
        this.pizzaOrganizer = pizzaOrganizer;
        this.assemblyTab.setContent(this.createAssemblyPane());
    }

    /**
     * This method gets the assembly tab.
     */
    public Tab getAssemblyTab() {
        return this.assemblyTab;
    }

    /**
     * This method creates the border pane and returns it;
     */
    public BorderPane createAssemblyPane() {
        BorderPane assemblyPane = new BorderPane();
        assemblyPane.setPrefSize(Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        createSideBar(assemblyPane);
        ImageView assemblyBackground = new ImageView(new Image("./indy/assembly_bg.png"));
        ImageView makePizzaButton = new ImageView(new Image("./indy/make_pizza_button.png"));
        assemblyPane.getChildren().addAll(assemblyBackground, makePizzaButton);

        Pane pane = new Pane();
        assemblyPane.setCenter(pane);
        new Assembling(pane, this.pizzaOrganizer);


        return assemblyPane;
    }

    /**
     * This method creates the side section of the screen that contains the quit button for all tabs.
     */
    static void createSideBar(BorderPane pane) {
        VBox ordersList = new VBox();
        ordersList.setPrefSize(Constants.SIDE_WIDTH, Constants.SIDE_HEIGHT);
        ordersList.setStyle("-fx-background-color: #B5D7C8");
        ordersList.setAlignment(Pos.BOTTOM_RIGHT);
        pane.setRight(ordersList);
        CashierTab.createQuitButton(ordersList);
    }
}
