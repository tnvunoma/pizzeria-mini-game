package indy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This is the Cheese class. It allows users to either have cheese or not have cheese on the pizza determined by a
 * boolean.
 */
public class Cheese {
    private final Pane pane;
    private final ImageView cheese;
    private final ImageView selectedCheese;
    public Cheese(Pane pane) {
        this.pane = pane;
        this.cheese = new ImageView(new Image("./indy/topping_cheese.png"));
        this.cheese.setOpacity(0);
        this.selectedCheese = new ImageView(new Image("./indy/cheese_stroke.png"));
        this.selectedCheese.setOpacity(0);
        this.setPosition(Constants.CHEESE_X, Constants.CHEESE_Y);
        this.addToPane();
    }

    /**
     * This method allows for the visibility of cheese to be togglable.
     */
    public void showCheese(boolean hasCheese) {
        if (hasCheese) {
            this.cheese.setOpacity(1);
        } else {
            this.cheese.setOpacity(0);
            this.selectedCheese.setOpacity(0);
        }
    }

    /**
     * This method checks if the cheese's visibility is toggled on or off.
     */
    public boolean hasCheese() {
        return this.cheese.getOpacity() == 1;
    }

    /**
     * This method adds cheese to the pane.
     */
    public void addToPane() {
        this.pane.getChildren().addAll(this.selectedCheese, this.cheese);
    }

    /**
     * This method checks if the cheese is clicked on.
     */
    public boolean isClicked(double x, double y) {
        return this.selectedCheese.contains(x, y);
    }

    /**
     * This method changes the appearance of the cheese when it is selected.
     */
    public void select() {
        this.selectedCheese.setOpacity(1);
    }

    /**
     * This method checks if the cheese has been selected.
     */
    public boolean isSelected() {
        return this.selectedCheese.getOpacity() == 1;
    }

    /**
     * This method deselects the cheese graphically.
     */
    public void deselect() {
        this.selectedCheese.setOpacity(0);
    }

    /**
     * This method sets the position of the cheese.
     */
    public  void setPosition(double x, double y) {
        this.cheese.setX(Constants.CHEESE_X + x);
        this.cheese.setY(Constants.CHEESE_Y + y);
        this.selectedCheese.setX(this.cheese.getX());
        this.selectedCheese.setY(this.cheese.getY());
    }

    /**
     * This method removes the cheese from the pane.
     */
    public void removeFromPane() {
        this.pane.getChildren().removeAll(this.cheese, this.selectedCheese);
    }
}