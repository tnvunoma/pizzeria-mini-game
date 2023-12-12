package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This is the Pepperoni class. It implements the Toppings interface.
 */
public class Pepperoni implements Toppings {
    private final Pane pane;
    private Ellipse pepperoni1;
    private Ellipse pepperoni2;
    private Ellipse pepperoni3;
    private Ellipse pepperoni4;

    public Pepperoni(Pane pane) {
        this.pane = pane;
        this.createPepperoni();
    }

    /**
     * This creates the visual components of the pepperoni.
     */
    public void createPepperoni() {
        this.pepperoni1 = new Ellipse(Constants.PEPPERONI1_RAD, Constants.PEPPERONI1_RAD);
        this.pepperoni1.setFill(Color.web("883636"));
        this.pepperoni2 = new Ellipse(Constants.PEPPERONI2_RAD, Constants.PEPPERONI2_RAD);
        this.pepperoni2.setFill(Color.web("B7484A"));
        this.pepperoni3 = new Ellipse(Constants.PEPPERONI3_RAD, Constants.PEPPERONI3_RAD);
        this.pepperoni3.setFill(Color.web("883636"));
        this.pepperoni4 = new Ellipse(Constants.PEPPERONI4_RAD, Constants.PEPPERONI4_RAD);
        this.pepperoni4.setFill(Color.web("B7484A"));

        this.setPepperoniPos(Constants.PEPPERONI_X, Constants.PEPPERONI_Y);
    }

    /**
     * This positions the visual components of the pepperoni.
     */
    public void setPepperoniPos(double x, double y) {
        this.pepperoni1.setCenterX(x);
        this.pepperoni1.setCenterY(y);
        this.pepperoni2.setCenterX(x + 1);
        this.pepperoni2.setCenterY(y + 1);
        this.pepperoni3.setCenterX(x + Constants.PEPPERONI3_OFFSET);
        this.pepperoni3.setCenterY(y + Constants.PEPPERONI3_OFFSET);
        this.pepperoni4.setCenterX(x + Constants.PEPPERONI4_OFFSET);
        this.pepperoni4.setCenterY(y + Constants.PEPPERONI4_OFFSET);
    }

    /**
     * This method adds the components of the pepperoni to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.pepperoni1, this.pepperoni2, this.pepperoni3, this.pepperoni4);
    }

    /**
     * This method checks if the pepperoni has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.pepperoni1.contains(x, y);
    }

    /**
     * This method changes the stroke of the pepperoni when it is selected.
     */
    @Override
    public void select() {
        this.pepperoni1.setStrokeWidth(Constants.PEPPERONI_STROKE);
        this.pepperoni1.setStroke(Color.WHITE);
    }

    /**
     * This method checks if the pepperoni has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.pepperoni1.getStrokeWidth() == Constants.PEPPERONI_STROKE;
    }

    /**
     * This method changes the stroke of the pepperoni when it is deselected.
     */
    @Override
    public void deselect() {
        this.pepperoni1.setStrokeWidth(0);
    }

    /**
     * This method moves the pepperoni to the position that is passed in to the parameters.
     */
    @Override
    public void translate(double x, double y) {
        this.setPepperoniPos(x, y);
    }

    /**
     * This method returns the x coordinate of the pepperoni.
     */
    @Override
    public double getX() {
        return this.pepperoni1.getCenterX();
    }

    /**
     * This method returns the y coordinate of the pepperoni.
     */
    @Override
    public double getY() {
        return this.pepperoni1.getCenterY();
    }

    /**
     * This method removes the pepperoni from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.pepperoni1, this.pepperoni2, this.pepperoni3, this.pepperoni4);
    }

    /**
     * This method converts the pepperoni to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "pepperoni";
    }
}
