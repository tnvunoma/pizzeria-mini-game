package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This is the Olive class. It implements the Toppings interface.
 */
public class Olive implements Toppings {
    private final Pane pane;
    private ImageView olive;
    private ImageView oliveStroke;
    public Olive(Pane pane) {
        this.pane = pane;
        this.createOlive();
    }

    /**
     * This creates the visual components of the Olive.
     */
    public void createOlive() {
        this.olive = new ImageView(new Image("./indy/olive.png"));
        this.oliveStroke = new ImageView(new Image("./indy/olive_stroke.png"));
        this.oliveStroke.setOpacity(0);
        this.translate(Constants.OLIVE_X, Constants.OLIVE_Y);
    }

    /**
     * This method adds the components of the olive to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.oliveStroke, this.olive);
    }

    /**
     * This method checks if the olive has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.oliveStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the olive when it is selected.
     */
    @Override
    public void select() {
        this.oliveStroke.setOpacity(1);
    }

    /**
     * This method checks if the olive has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.oliveStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the olive when it is deselected.
     */
    @Override
    public void deselect() {
        this.oliveStroke.setOpacity(0);
    }

    /**
     * This method moves the olive to the position that is passed in to the parameters.
     */
    @Override
    public  void translate(double x, double y) {
        this.olive.setX(x - Constants.OLIVE_MOUSE_OFFSET_X);
        this.olive.setY(y - Constants.OLIVE_MOUSE_OFFSET_Y);
        this.oliveStroke.setX(x - Constants.OLIVE_MOUSE_OFFSET_X);
        this.oliveStroke.setY(y - Constants.OLIVE_MOUSE_OFFSET_Y);
    }

    /**
     * This method returns the x coordinate of the olive.
     */
    @Override
    public double getX() {
        return this.olive.getX() + Constants.OLIVE_MOUSE_OFFSET_X;
    }

    /**
     * This method returns the y coordinate of the olive.
     */
    @Override
    public double getY() {
        return this.olive.getY() + Constants.OLIVE_MOUSE_OFFSET_Y;
    }

    /**
     * This method removes the olive from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.oliveStroke, this.olive);
    }

    /**
     * This method converts the olive to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "olive";
    }
}
