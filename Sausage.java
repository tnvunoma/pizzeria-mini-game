package indy;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This is the Sausage class. It implements the Toppings interface.
 */
public class Sausage implements Toppings {
    private final Pane pane;
    private ImageView sausage;
    private ImageView sausageStroke;
    public Sausage(Pane pane) {
        this.pane = pane;
        this.createSausage();
    }

    /**
     * This creates the visual components of the sausage.
     */
    public void createSausage() {
        this.sausage = new ImageView(new Image("./indy/sausage.png"));
        this.sausageStroke = new ImageView(new Image("./indy/sausage_stroke.png"));
        this.sausageStroke.setOpacity(0);
        this.translate(Constants.SAUSAGE_X, Constants.SAUSAGE_Y);
    }

    /**
     * This method adds the components of the sausage to the pane.
     */
    @Override
    public void addToPane() {
        this.pane.getChildren().addAll(this.sausageStroke, this.sausage);
    }

    /**
     * This method checks if the sausage has been clicked on
     */
    @Override
    public boolean toppingClicked(double x, double y) {
        return this.sausageStroke.contains(x, y);
    }

    /**
     * This method changes the stroke of the sausage when it is selected.
     */
    @Override
    public void select() {
        this.sausageStroke.setOpacity(1);
    }

    /**
     * This method checks if the sausage has been selected.
     */
    @Override
    public boolean isSelected() {
        return this.sausageStroke.getOpacity() == 1;
    }

    /**
     * This method changes the stroke of the sausage when it is deselected.
     */
    @Override
    public void deselect() {
        this.sausageStroke.setOpacity(0);
    }

    /**
     * This method moves the sausage to the position that is passed in to the parameters.
     */
    @Override
    public  void translate(double x, double y) {
        this.sausage.setX(x - Constants.SAUSAGE_OFFSET);
        this.sausage.setY(y - Constants.SAUSAGE_OFFSET);
        this.sausageStroke.setX(x - Constants.SAUSAGE_OFFSET);
        this.sausageStroke.setY(y - Constants.SAUSAGE_OFFSET);
    }

    /**
     * This method returns the x coordinate of the sausage.
     */
    @Override
    public double getX() {
        return this.sausage.getX() + Constants.SAUSAGE_OFFSET;
    }

    /**
     * This method returns the y coordinate of the sausage.
     */
    @Override
    public double getY() {
        return this.sausage.getY() + Constants.SAUSAGE_OFFSET;
    }

    /**
     * This method removes the sausage from the pane.
     */
    @Override
    public void delete() {
        this.pane.getChildren().removeAll(this.sausageStroke, this.sausage);
    }

    /**
     * This method converts the sausage to a String so that it can be recreated in other tabs.
     */
    @Override
    public String parseToppings() {
        return "sausage";
    }
}
