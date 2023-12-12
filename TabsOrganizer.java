package indy;

import javafx.scene.control.TabPane;

/**
 * This is the TabsOrganizer class. It is responsible for setting up the tabs pane and creating all the tabs.
 */
public class TabsOrganizer {
    private final TabPane tabPane;

    public TabsOrganizer() {
        this.tabPane = new TabPane();
        PizzaOrganizer pizzaOrganizer = new PizzaOrganizer();
        this.tabPane.getTabs().add(new CashierTab(pizzaOrganizer).getCashierTab());
        this.tabPane.getTabs().add(new AssemblyTab(pizzaOrganizer).getAssemblyTab());
        this.tabPane.getTabs().add(new BakingTab(pizzaOrganizer).getBakingTab());
        this.tabPane.getTabs().add(new SlicingTab(pizzaOrganizer).getSLicingTab());
    }

    /**
     * This method returns the tab pane.
     */
    public TabPane getTabPane() {
        return this.tabPane;
    }
}
