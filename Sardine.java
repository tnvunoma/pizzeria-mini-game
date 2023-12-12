package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This is the Sardine class. It implements the Toppings interface.
 */
public class Sardine implements Toppings {
    private final Pane pane;
    private ImageView sardine;
    private ImageView sardineStroke;
    public Sardine(Pane pane) {
        this.pane = pane;
        this.createSardine();
    }

    /**
     * This creates the visual components of the sardine.
     */
    public void createSardine() {
        this.sardine = new ImageView(new Image("./indy/sardine.png"));
        this.sardineStroke = new ImageView(new Image("./indy/sardine_stroke.png"));
        this.sardineStroke.setOpacity(0);
        this.translate(Constants.SARDINE_X, Constants.SARDINE_Y);
    }

    /**
     * This method adds the components of the sardine to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.sardineStroke, this.sardine);
    }

    /**
     * This method checks if the sardine has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.sardineStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the sardine when it is selected.
     */
    @Override
    public void select() {
        this.sardineStroke.setOpacity(1);
    }

    /**
     * This method checks if the sardine has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.sardineStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the sardine when it is deselected.
     */
    @Override
    public void deselect() {
        this.sardineStroke.setOpacity(0);
    }

    /**
     * This method moves the sardine to the position that is passed in to the parameters.
     */
    @Override
    public  void translate(double x, double y) {
        this.sardine.setX(x - Constants.SARDINE_X_OFFSET);
        this.sardine.setY(y - Constants.SARDINE_Y_OFFSET);
        this.sardineStroke.setX(x - Constants.SARDINE_X_OFFSET);
        this.sardineStroke.setY(y - Constants.SARDINE_Y_OFFSET);
    }

    /**
     * This method returns the x coordinate of the sardine.
     */
    @Override
    public double getX() {
        return this.sardine.getX() + Constants.SARDINE_X_OFFSET;
    }

    /**
     * This method returns the y coordinate of the sardine.
     */
    @Override
    public double getY() {
        return this.sardine.getY() + Constants.SARDINE_Y_OFFSET;
    }

    /**
     * This method removes the sardine from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.sardineStroke, this.sardine);
    }

    /**
     * This method converts the sardine to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "sardine";
    }
}
