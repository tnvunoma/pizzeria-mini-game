package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This is the Pineapple class. It implements the Toppings interface.
 */
public class Pineapple implements Toppings {
    private final Pane pane;
    private ImageView pineapple;
    private ImageView pineappleStroke;
    public Pineapple(Pane pane) {
        this.pane = pane;
        this.createPineapple();
    }

    /**
     * This creates the visual components of the pineapple.
     */
    public void createPineapple() {
        this.pineapple = new ImageView(new Image("./indy/pineapple.png"));
        this.pineappleStroke = new ImageView(new Image("./indy/pineapple_stroke.png"));
        this.pineappleStroke.setOpacity(0);
        this.translate(Constants.PINEAPPLE_X, Constants.PINEAPPLE_Y);
    }

    /**
     * This method adds the components of the pineapple to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.pineappleStroke, this.pineapple);
    }

    /**
     * This method checks if the pineapple has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.pineappleStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the pineapple when it is selected.
     */
    @Override
    public void select() {
        this.pineappleStroke.setOpacity(1);
    }

    /**
     * This method checks if the pineapple has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.pineappleStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the pineapple when it is deselected.
     */
    @Override
    public void deselect() {
        this.pineappleStroke.setOpacity(0);
    }

    /**
     * This method moves the pineapple to the position that is passed in to the parameters.
     */
    @Override
    public  void translate(double x, double y) {
        this.pineapple.setX(x - Constants.PINEAPPLE_X_OFFSET);
        this.pineapple.setY(y - Constants.PINEAPPLE_Y_OFFSET);
        this.pineappleStroke.setX(x - Constants.PINEAPPLE_X_OFFSET);
        this.pineappleStroke.setY(y - Constants.PINEAPPLE_Y_OFFSET);
    }

    /**
     * This method returns the x coordinate of the pineapple.
     */
    @Override
    public double getX() {
        return this.pineapple.getX() + Constants.PINEAPPLE_X_OFFSET;
    }

    /**
     * This method returns the y coordinate of the pineapple.
     */
    @Override
    public double getY() {
        return this.pineapple.getY() + Constants.PINEAPPLE_Y_OFFSET;
    }

    /**
     * This method removes the pineapple from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.pineappleStroke, this.pineapple);
    }

    /**
     * This method converts the pineapple to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "pineapple";
    }
}
