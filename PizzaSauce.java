package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This is the PizzaSauce class. It is responsible for visualizing the sauce.
 */
public class PizzaSauce {
    private final Pane pane;
    private Ellipse sauce;

    public PizzaSauce(Pane pane, double x, double y, Color color) {
        this.pane = pane;
        this.createSauce(x, y);
        this.setSauce(color);
    }

    /**
     * This creates a droplet of sauce at the x and y coordinates passed in.
     */
    public void createSauce(double x, double y) {
        this.sauce = new Ellipse(x, y, Constants.SAUCE_RADIUS, Constants.SAUCE_RADIUS);
        this.pane.getChildren().add(this.sauce);
    }

    /**
     * This sets the color of the sauce to the color passed in.
     */
    public void setSauce(Color color) {
        this.sauce.setFill(color);
    }

    /**
     * This sets the position of the sauce to the x and y coordinates passed in.
     */
    public void positionSauce(double x, double y) {
        this.sauce.setCenterX(x);
        this.sauce.setCenterY(y);
    }

    /**
     * This returns the color of the sauce;
     */
    public Color getSauceColor() {return (Color) this.sauce.getFill();}

    /**
     * This returns the type of sauce used so that it can be scored by the customer.
     */
    public String getSauce() {
        String sauce;
        if (this.getSauceColor() == Constants.RED_SAUCE) {
            sauce = "red";
        } else {
            sauce = "green";
        }
        return sauce;
    }

    /**
     * This returns the x coordinate of the sauce.
     */
    public double getX() {
        return this.sauce.getCenterX();
    }

    /**
     * This returns the y coordinate of the sauce.
     */
    public double getY() {
        return this.sauce.getCenterY();
    }

    /**
     * This removes the sauce from the pane.
     */
    public void delete() {
        this.pane.getChildren().remove(this.sauce);
    }
}
