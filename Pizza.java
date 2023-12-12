package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This is the Pizza class. It visualizes the dough of the pizza.
 */
public class Pizza {
    private final Ellipse pizzaDough;
    public Pizza() {
        this.pizzaDough = new Ellipse(Constants.PIZZA_X, Constants.PIZZA_Y, Constants.PIZZA_RADIUS,
                Constants.PIZZA_RADIUS);
        this.pizzaDough.setFill(Constants.RAW_COLOR);
    }

    /**
     * This method adds the pizza dough to the pane.
     */
    public void addToPane(Pane pane) {
        pane.getChildren().add(this.pizzaDough);
    }

    /**
     * This method removes the pizza dough from the pane.
     */
    public void removeFromPane(Pane pane) {
        pane.getChildren().remove(this.pizzaDough);
    }

    /**
     * This method checks if the passed in coordinates in contained within the pizza.
     */
    public boolean contain(double x, double y) {
        return this.pizzaDough.contains(x, y);
    }

    /**
     * This method sets the position of the pizza to the passed in coords.
     */
    public void setPosition(double x, double y) {
        this.pizzaDough.setCenterX(x);
        this.pizzaDough.setCenterY(y);
    }

    /**
     * This method sets the dough color to a "raw" color.
     */
    public void underBake() {
        this.pizzaDough.setFill(Constants.RAW_COLOR);
    }

    /**
     * This method sets the dough color to a "baked" color.
     */
    public void bake() {
        this.pizzaDough.setFill(Constants.BAKED_COLOR);
    }

    /**
     * This method sets the dough color to a "burnt" color.
     */
    public void burn() {
        this.pizzaDough.setFill(Constants.BURNT_COLOR);
    }

    /**
     * This method sets the dough color to the passed in color.
     */
    public void setColor(Color color) {
        this.pizzaDough.setFill(color);
    }

    /**
     * This method gets the dough's color.
     */
    public Color getColor() {
        return (Color) this.pizzaDough.getFill();
    }

    /**
     * This method checks the color of the dough to see if it is baked so that it can be scored by the customer.
     */
    public String getCondition() {
        String baked;
            if (this.getColor() == Constants.BAKED_COLOR) {
                baked = "baked";
            } else {
                baked = "fail";
            }
            return baked;
    }
}
