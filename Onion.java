package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.StrokeLineCap;

/**
 * This is the Onion class. It implements the Toppings interface.
 */
public class Onion implements Toppings {
    private final Pane pane;
    private QuadCurve onion;
    private QuadCurve onionStroke;
    public Onion(Pane pane) {
        this.pane = pane;
        this.createOnion();
    }

    /**
     * This creates the visual components of the onion.
     */
    public void createOnion() {
        this.onion = new QuadCurve(0, 0, Constants.ONION_CONTROL_X, Constants.ONION_CONTROL_Y,
                Constants.ONION_END_X, Constants.ONION_END_Y);
        this.onion.setStrokeLineCap(StrokeLineCap.ROUND);
        this.onion.setStrokeWidth(Constants.ONION_WIDTH);
        this.onion.setFill(Color.web("D8C2A1"));
        this.onion.setStroke(Color.web("D8C2A1"));
        this.onionStroke = new QuadCurve(0, 0, Constants.ONION_CONTROL_X, Constants.ONION_CONTROL_Y,
                Constants.ONION_END_X, Constants.ONION_END_Y);
        this.onionStroke.setStrokeLineCap(StrokeLineCap.ROUND);
        this.onionStroke.setStrokeWidth(Constants.ONION_STROKE_WIDTH);
        this.onionStroke.setStroke(Color.WHITE);
        this.onionStroke.setOpacity(0);

        this.translate(Constants.ONION_X, Constants.ONION_Y);
    }

    /**
     * This method adds the components of the onion to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.onionStroke, this.onion);
    }

    /**
     * This method checks if the onion has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.onionStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the onion when it is selected.
     */
    @Override
    public void select() {
        this.onionStroke.setOpacity(1);
    }

    /**
     * This method checks if the onion has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.onionStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the onion when it is deselected.
     */
    @Override
    public void deselect() {
        this.onionStroke.setOpacity(0);
    }

    /**
     * This method moves the onion to the position that is passed in to the parameters.
     */
    @Override
    public void translate(double x, double y) {
        this.onion.setControlX(x);
        this.onion.setControlY(y);
        this.onion.setStartX(x - Constants.ONION_START_X_OFFSET);
        this.onion.setStartY(y - Constants.ONION_START_Y_OFFSET);
        this.onion.setEndX(x + Constants.ONION_END_X_OFFSET);
        this.onion.setEndY(y - Constants.ONION_END_Y_OFFSET);

        this.onionStroke.setControlX(x);
        this.onionStroke.setControlY(y);
        this.onionStroke.setStartX(x - Constants.ONION_START_X_OFFSET);
        this.onionStroke.setStartY(y - Constants.ONION_START_Y_OFFSET);
        this.onionStroke.setEndX(x + Constants.ONION_END_X_OFFSET);
        this.onionStroke.setEndY(y - Constants.ONION_END_Y_OFFSET);
    }

    /**
     * This method returns the x coordinate of the onion.
     */
    @Override
    public double getX() {
        return this.onion.getControlX();
    }

    /**
     * This method returns the y coordinate of the onion.
     */
    @Override
    public double getY() {
        return this.onion.getControlY();
    }

    /**
     * This method removes the onion from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.onion, this.onionStroke);
    }

    /**
     * This method converts the onion to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "onion";
    }
}
