package indy;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

/**
 * This is the GreenPepper class. It implements the Toppings interface.
 */
public class GreenPepper implements Toppings {
    private final Pane pane;
    private QuadCurve greenPepper;
    private QuadCurve greenPepperStroke;
    public GreenPepper(Pane pane) {
        this.pane = pane;
        this.createGreenPepper();
    }

    /**
     * This creates the visual components of the green pepper.
     */
    public void createGreenPepper() {
        this.greenPepper = new QuadCurve(0, 0, Constants.PEPPER_CONTROL_X,
                Constants.PEPPER_CONTROL_Y, 1, Constants.PEPPER_END_Y);
        this.greenPepper.setFill(Constants.PEPPER_COLOR);
        this.greenPepper.setStroke(Constants.PEPPER_COLOR);
        this.greenPepper.setStrokeWidth(Constants.PEPPER_WIDTH);
        this.greenPepperStroke = new QuadCurve(0, 0, Constants.PEPPER_CONTROL_X,
                Constants.PEPPER_CONTROL_Y, 1, Constants.PEPPER_END_Y);
        this.greenPepperStroke.setStroke(Color.WHITE);
        this.greenPepperStroke.setStrokeWidth(Constants.PEPPER_STROKE_WIDTH);
        this.greenPepperStroke.setOpacity(0);

        this.translate(Constants.PEPPER_X, Constants.PEPPER_Y);
    }

    /**
     * This method adds the components of the green pepper to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.greenPepperStroke, this.greenPepper);
    }

    /**
     * This method checks if the green pepper has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.greenPepperStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the green pepper when it is selected.
     */
    @Override
    public void select() {
        this.greenPepperStroke.setOpacity(1);
    }

    /**
     * This method checks if the green pepper has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.greenPepperStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the green pepper when it is deselected.
     */
    @Override
    public void deselect() {
        this.greenPepperStroke.setOpacity(0);
    }

    /**
     * This method moves the green pepper to the position that is passed in to the parameters.
     */
    @Override
    public void translate(double x, double y) {
        this.greenPepper.setControlX(x + Constants.PEPPER_CONTROL_OFFSET);
        this.greenPepper.setControlY(y);
        this.greenPepper.setStartX(x - Constants.PEPPER_CONTROL_OFFSET);
        this.greenPepper.setStartY(y - Constants.PEPPER_START_Y_OFFSET);
        this.greenPepper.setEndX(x);
        this.greenPepper.setEndY(y + Constants.PEPPER_END_Y_OFFSET);

        this.greenPepperStroke.setControlX(x + Constants.PEPPER_CONTROL_OFFSET);
        this.greenPepperStroke.setControlY(y);
        this.greenPepperStroke.setStartX(x - Constants.PEPPER_CONTROL_OFFSET);
        this.greenPepperStroke.setStartY(y - Constants.PEPPER_START_Y_OFFSET);
        this.greenPepperStroke.setEndX(x);
        this.greenPepperStroke.setEndY(y + Constants.PEPPER_END_Y_OFFSET);
    }

    /**
     * This method returns the x coordinate of the green pepper.
     */
    @Override
    public double getX() {
        return this.greenPepper.getEndX();
    }

    /**
     * This method returns the y coordinate of the green pepper.
     */
    @Override
    public double getY() {
        return this.greenPepper.getControlY();
    }

    /**
     * This method removes the green pepper from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.greenPepperStroke, this.greenPepper);
    }

    /**
     * This method converts the green pepper to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "pepper";
    }
}