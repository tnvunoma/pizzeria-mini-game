package indy;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This is the CashierTab class. It is responsible for setting up the screen under the cashier tab and instantiating
 * the Cashier class.
 */
public class CashierTab {
    private final Tab cashierTab;
    private final PizzaOrganizer pizzaOrganizer;

    public CashierTab(PizzaOrganizer pizzaOrganizer) {
        this.cashierTab = new Tab("Cashier");
        this.pizzaOrganizer = pizzaOrganizer;
        this.cashierTab.setContent(this.createCashierPane());
    }

    /**
     * This method returns the cashier tab;
     */
    public Tab getCashierTab() {
        return this.cashierTab;
    }

    /**
     * This method creates the border pane and returns it. It also instantiates the Cashier class.
     */
    public BorderPane createCashierPane() {
        BorderPane cashierPane = new BorderPane();
        cashierPane.setPrefSize(Constants.CASHIER_PANE_WIDTH,Constants.PANE_HEIGHT);
        cashierPane.setStyle("-fx-background-color: #B5D7C8");
        ImageView cashierBackground = new ImageView(new Image("./indy/cashier_bg.png"));
        cashierPane.getChildren().add(cashierBackground);

        Pane pane = new Pane();
        cashierPane.setCenter(pane);
        new Cashier(pane, this.pizzaOrganizer);

        VBox buttonVBox = new VBox();
        buttonVBox.setAlignment(Pos.BOTTOM_RIGHT);
        createQuitButton(buttonVBox);
        cashierPane.setRight(buttonVBox);

        return cashierPane;
    }

    /**
     * This method creates the quit button for the screen under the cashier tab.
     */
    static void createQuitButton(VBox vBox) {
        Button quitButton = new Button("Quit");
        quitButton.setFocusTraversable(false);
        vBox.getChildren().add(quitButton);
        quitButton.setOnAction((ActionEvent) -> System.exit(0));
        quitButton.setFocusTraversable(false);
    }
}
