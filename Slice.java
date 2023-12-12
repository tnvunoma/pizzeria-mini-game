package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This is the Slice class. It is responsible for visualizing a slice on a pizza.
 */
public class Slice {
    private final Pane pane;
    private final Line slice;
    public Slice(Pane pane) {
        this.pane = pane;
        this.slice = new Line(Constants.SLICE_X, Constants.SLICE_Y, Constants.SLICE_X, Constants.SLICE_Y);
        this.slice.setStrokeWidth(Constants.SLICE_WIDTH);
        this.slice.setStroke(Color.web("A6997B"));
        pane.getChildren().add(this.slice);
    }

    /**
     * This sets one end of the slice to the coordinates passed in.
     */
    public void setStart(double x, double y) {
        this.slice.setStartX(x);
        this.slice.setStartY(y);
    }

    /**
     * This sets one end of the slice to the coordinates passed in.
     */
    public void setEnd(double x, double y) {
        this.slice.setEndX(x);
        this.slice.setEndY(y);
    }

    /**
     * This gets the x coordinates of one side of the slice.
     */
    public double getStartX() {
        return this.slice.getStartX();
    }

    /**
     * This gets the y coordinates of one side of the slice.
     */
    public double getStartY() {
        return this.slice.getStartY();
    }

    /**
     * This removes the slice from the pane.
     */
    public void removeFromPane() {
        this.pane.getChildren().remove(this.slice);
    }
}
