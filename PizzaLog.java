package indy;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/**
 * This is the PizzaLog class. It is responsible for visualizing an inventory of all the pizzas made (after being
 * sliced).
 */
public class PizzaLog {
    private final Pane pane;
    private final PizzaOrganizer pizzaOrganizer;
    private final ArrayList<PizzaSauce> pizzaSauce;
    private final ArrayList<Toppings> topping;
    private final ArrayList<Slice> slice;
    private final Ellipse slicingBoard;
    private final Pizza pizza;
    private Cheese cheese;
    private final Rectangle border;
    private ArrayList<Point2D> sauceCoords;
    private ArrayList<Point2D> toppingCoords;
    private ArrayList<Point2D> sliceCoords;
    private final ArrayList<String> pizzaMade;

    public PizzaLog(Pane pane, PizzaOrganizer pizzaOrganizer) {
        this.pane = pane;
        this.pizzaOrganizer = pizzaOrganizer;
        this.pizzaSauce = new ArrayList<>();
        this.topping = new ArrayList<>();
        this.slice = new ArrayList<>();
        this.pizzaMade = new ArrayList<>();
        this.pizza = new Pizza();
        this.border = new Rectangle(Constants.BORDER_SIZE, Constants.BORDER_SIZE);
        this.border.setFill(Color.web("8CC4B3"));
        this.border.setStroke(Color.WHITE);
        this.slicingBoard = new Ellipse(Constants.BOARD_RADIUS, Constants.BOARD_RADIUS);
        this.slicingBoard.setFill(Color.web("A6997B"));

        this.displayPizza();
    }

    /**
     * This method gets recreates the pizza and displays it in the pizza inventory.
     */
    public void displayPizza() {
        if (! this.pizzaOrganizer.logQueueEmpty()) {
            this.sauceCoords = this.pizzaOrganizer.dequeueSauceCoordsLog();
            this.toppingCoords = this.pizzaOrganizer.dequeueToppingCoordsLog();
            this.sliceCoords = this.pizzaOrganizer.dequeueSlicesLog();

            this.pane.getChildren().addAll(this.border, this.slicingBoard);
            this.recreatePizza();
            this.recreateSauce();
            this.recreateCheese();
            this.recreateToppings();
            this.recreateSlices();
        }
    }

    /**
     * This method creates a uniform translate method that relies on offset x and y coordinate. It doesn't use
     * getX or getY methods because this method is being called on every 0.1 second in the update game method of the
     * Cashier class.

     */
    public void translatePizza(double xOffset, double yOffset) {
        this.border.setX(Constants.BORDER_X + xOffset);
        this.border.setY(Constants.BORDER_Y + yOffset);
        this.slicingBoard.setCenterX(Constants.PIZZA_X + xOffset);
        this.slicingBoard.setCenterY(Constants.PIZZA_Y + yOffset);
        this.pizza.setPosition(Constants.PIZZA_X + xOffset, Constants.PIZZA_Y + yOffset);
        this.cheese.setPosition(xOffset, yOffset);
        for (int i = 0; i < this.sauceCoords.size(); i++) {
            double x = this.sauceCoords.get(i).getX() + xOffset;
            double y = this.sauceCoords.get(i).getY() + yOffset;
            this.pizzaSauce.get(i).positionSauce(x, y);
        }
        for (int j = 0; j < this.toppingCoords.size(); j++) {
            double x = this.toppingCoords.get(j).getX() + xOffset;
            double y = this.toppingCoords.get(j).getY() + yOffset;
            this.topping.get(j).translate(x, y);
        }
        for (int k = 0; k < this.sliceCoords.size(); k++) {
            double x = this.sliceCoords.get(k).getX() + xOffset;
            double y = this.sliceCoords.get(k).getY() + yOffset;
            this.slice.get(k).setStart(x, y);
            this.slice.get(k).setEnd(Constants.PIZZA_X + xOffset, Constants.PIZZA_Y + yOffset);
        }
    }

    /**
     * This recreates the pizza with its correct color.
     */
    public void recreatePizza() {
        Color pizzaColor = this.pizzaOrganizer.dequeuePizzaLog();
        this.pizza.setColor(pizzaColor);
        this.pizza.addToPane(this.pane);
        this.pizzaMade.add(this.pizza.getCondition());
    }

    /**
     * This recreates the sauce of the pizza.
     */
    public void recreateSauce() {
        ArrayList<Color> sauce = this.pizzaOrganizer.dequeueSauceLog();
        this.pizzaOrganizer.recreatePizzaSauce(this.pane, sauce, this.sauceCoords, this.pizzaSauce, 0, 0);
        this.summarizeSauce();
    }

    /**
     * This determines what sauce(s) has been used to make the pizza so that it can be used by the customer to score the
     * pizza.
     */
    public void summarizeSauce() {
        if (!this.pizzaSauce.isEmpty()) {
            this.pizzaMade.add(this.pizzaSauce.get(0).getSauce());
            boolean hasTwoSauces = false;
            for (int i = 1; i < this.pizzaSauce.size(); i++) {
                if (!(this.pizzaSauce.get(i).getSauce().equals(this.pizzaMade.get(1)))) {
                    hasTwoSauces = true;
                }
            }
            if (hasTwoSauces) {
                this.pizzaMade.remove(this.pizzaMade.size() - 1);
                this.pizzaMade.add("green");
                this.pizzaMade.add("red");
            }
        }
    }

    /**
     * This recreates the condition of the cheese on the pizza and records whether cheese was used so that the customer
     * can score the pizza.
     */
    public void recreateCheese() {
        boolean cheeseShown = this.pizzaOrganizer.dequeueCheeseLog();
        this.cheese = new Cheese(this.pane);
        this.cheese.showCheese(cheeseShown);
        if (this.cheese.hasCheese()) {this.pizzaMade.add("cheese");}
    }

    /**
     * This recreates the toppings on the pizza and records what toppings were used so that it can be scored by
     * the customer.
     */
    public void recreateToppings() {
        ArrayList<String> toppingsList = this.pizzaOrganizer.dequeueToppingsLog();
        this.pizzaOrganizer.recreatePizzaToppings(toppingsList, this.topping, this.pane);
        this.pizzaOrganizer.positionToppings(this.toppingCoords, this.topping, 0, 0);
        if (!toppingsList.isEmpty()) {
            this.pizzaMade.addAll(toppingsList);
        }
    }

    /**
     * This recreates the slices on the pizza and records how many slices it had so that it can be scored by the
     * customer.
     */
    public void recreateSlices() {
        for (Point2D coords : this.sliceCoords) {
            this.slice.add(new Slice(this.pane));
            this.slice.get(this.slice.size() - 1).setStart(coords.getX(), coords.getY());
        }
        this.pizzaMade.add(String.valueOf(this.sliceCoords.size()));
    }

    /**
     * This returns the "summarized" list of ingredients on the pizza that matches the format of the customer's order
     * so that it can be used for scoring.
     */
    public ArrayList<String> getPizzaMade() {
        return this.pizzaMade;
    }

    /**
     * This changes the borders of the pizza slot when it is selected.
     */
    public void selectPizza(double x, double y) {
        if (this.border.contains(x, y)) {
            this.border.setStrokeWidth(Constants.BORDER_WIDTH);
        }
    }

    /**
     * This changes the borders of the pizza slot when it is deselected.
     */
    public void deselectPizza(double x, double y) {
        if (!this.border.contains(x, y)) {
            this.border.setStrokeWidth(0);
        }
    }

    /**
     * This checks if the pizza has been selected.
     */
    public boolean pizzaSelected() {
        return this.border.getStrokeWidth() == Constants.BORDER_WIDTH;
    }

    /**
     * This removes the pizza graphically from the inventory.
     */
    public void removePizza() {
        this.pane.getChildren().removeAll(this.border, this.slicingBoard);
        this.pizza.removeFromPane(this.pane);
        this.cheese.removeFromPane();
        this.pizzaOrganizer.clearToppings(this.topping);
        this.pizzaOrganizer.clearSauces(this.pizzaSauce);
        this.pizzaOrganizer.clearSlices(this.slice);
    }
}
