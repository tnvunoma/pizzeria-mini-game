package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This is the Mushroom class. It implements the Toppings interface.
 */
public class Mushroom implements Toppings {
    private final Pane pane;
    private ImageView mushroom;
    private ImageView mushroomStroke;
    public Mushroom(Pane pane) {
        this.pane = pane;
        this.createMushroom();
    }

    /**
     * This creates the visual components of the mushroom.
     */
    public void createMushroom() {
        this.mushroom = new ImageView(new Image("./indy/mushroom.png"));
        this.mushroomStroke = new ImageView(new Image("./indy/mushroom_stroke.png"));
        this.mushroomStroke.setOpacity(0);
        this.translate(Constants.MUSHROOM_X, Constants.MUSHROOM_Y);
    }

    /**
     * This method adds the components of the mushroom to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.mushroomStroke, this.mushroom);
    }

    /**
     * This method checks if the mushroom has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.mushroomStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the mushroom when it is selected.
     */
    @Override
    public void select() {
        this.mushroomStroke.setOpacity(1);
    }

    /**
     * This method checks if the mushroom has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.mushroomStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the mushroom when it is deselected.
     */
    @Override
    public void deselect() {
        this.mushroomStroke.setOpacity(0);
    }

    /**
     * This method moves the mushroom to the position that is passed in to the parameters.
     */
    @Override
    public  void translate(double x, double y) {
        this.mushroom.setX(x - Constants.MUSHROOM_MOUSE_OFFSET);
        this.mushroom.setY(y - Constants.MUSHROOM_MOUSE_OFFSET);
        this.mushroomStroke.setX(x - Constants.MUSHROOM_MOUSE_OFFSET);
        this.mushroomStroke.setY(y - Constants.MUSHROOM_MOUSE_OFFSET);
    }

    /**
     * This method returns the x coordinate of the mushroom.
     */
    @Override
    public double getX() {
        return this.mushroom.getX() + Constants.MUSHROOM_MOUSE_OFFSET;
    }

    /**
     * This method returns the y coordinate of the mushroom.
     */
    @Override
    public double getY() {
        return this.mushroom.getY() + Constants.MUSHROOM_MOUSE_OFFSET;
    }

    /**
     * This method removes the mushroom from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.mushroomStroke, this.mushroom);
    }

    /**
     * This method converts the mushroom to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "mushroom";
    }
}
