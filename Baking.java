package indy;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * This is the Baking class. It allows for users to interact with the screen of the Baking tab through mouse inputs.
 * It contains several functioning ovens.
 */
public class Baking {
    private final Oven topLeftOven;
    private final Oven topRightOven;
    private final Oven bttmLeftOven;
    private final Oven bttmRightOven;
    private boolean topLeftTaken;
    private boolean topRightTaken;
    private boolean bttmLeftTaken;
    private boolean bttmRightTaken;
    public Baking(Pane pane, PizzaOrganizer pizzaOrganizer) {
        this.topLeftOven = new Oven(pizzaOrganizer, pane, Constants.LEFT_OVEN_OFFSET, Constants.TOP_OVEN_OFFSET);
        this.topRightOven = new Oven(pizzaOrganizer, pane, Constants.RIGHT_OVEN_OFFSET, Constants.TOP_OVEN_OFFSET);
        this.bttmLeftOven = new Oven(pizzaOrganizer, pane, Constants.LEFT_OVEN_OFFSET, Constants.BTM_OVEN_OFFSET);
        this.bttmRightOven = new Oven(pizzaOrganizer, pane, Constants.RIGHT_OVEN_OFFSET, Constants.BTM_OVEN_OFFSET);
        this.topLeftTaken = false;
        this.topRightTaken = false;
        this.bttmLeftTaken = false;
        this.bttmRightTaken = false;

        pane.setOnMousePressed(this::handleMousePressed);
    }

    /**
     * This method allows for users to interact with the ovens through mouse input.
     */
    public void handleMousePressed(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            if (e.getX() > Constants.OVEN1_BUTTON_LEFT && e.getX() < Constants.OVEN1_BUTTON_RIGHT &&
                    e.getY() > Constants.OVEN1_BUTTON_TOP && e.getY() < Constants.OVEN1_BUTTON_BTM &&
                    (!this.topLeftTaken)) {
                this.topLeftOven.loadPizza(Constants.LEFT_OVEN_X_OFFSET, Constants.TOP_OVEN_Y_OFFSET);
                this.topLeftTaken = true;

            } else if (e.getX() > Constants.OVEN2_BUTTON_LEFT && e.getX() < Constants.OVEN2_BUTTON_RIGHT &&
                    e.getY() > Constants.OVEN2_BUTTON_TOP && e.getY() < Constants.OVEN2_BUTTON_BTM &&
                    (!this.topRightTaken)) {
                this.topRightOven.loadPizza(Constants.RIGHT_OVEN_X_OFFSET, Constants.TOP_OVEN_Y_OFFSET);
                this.topRightTaken = true;

            } else if (e.getX() > Constants.OVEN3_BUTTON_LEFT && e.getX() < Constants.OVEN3_BUTTON_RIGHT &&
                    e.getY() > Constants.OVEN3_BUTTON_TOP && e.getY() < Constants.OVEN3_BUTTON_BTM &&
                    (!this.bttmLeftTaken)) {
                this.bttmLeftOven.loadPizza(Constants.LEFT_OVEN_X_OFFSET, Constants.BTM_OVEN_Y_OFFSET);
                this.bttmLeftTaken = true;

            } else if (e.getX() > Constants.OVEN4_BUTTON_LEFT && e.getX() < Constants.OVEN4_BUTTON_RIGHT &&
                    e.getY() > Constants.OVEN4_BUTTON_TOP && e.getY() < Constants.OVEN4_BUTTON_BTM &&
                    (!this.bttmRightTaken)) {
                this.bttmRightOven.loadPizza(Constants.RIGHT_OVEN_X_OFFSET, Constants.BTM_OVEN_Y_OFFSET);
                this.bttmRightTaken = true;
            }

            if (e.getX() > Constants.OVEN1_LEFT && e.getX() < Constants.OVEN1_RIGHT &&
                    e.getY() > Constants.OVEN1_TOP && e.getY() < Constants.OVEN1_BTM &&
                    this.topLeftTaken) {
                this.topLeftOven.unloadPizza();
                this.topLeftTaken = false;
            } else if (e.getX() > Constants.OVEN2_LEFT && e.getX() < Constants.OVEN2_RIGHT &&
                    e.getY() > Constants.OVEN2_TOP && e.getY() < Constants.OVEN2_BTM &&
                    this.topRightTaken) {
                this.topRightOven.unloadPizza();
                this.topRightTaken = false;
            } else if (e.getX() > Constants.OVEN3_LEFT && e.getX() < Constants.OVEN3_RIGHT &&
                    e.getY() > Constants.OVEN3_TOP && e.getY() < Constants.OVEN3_BTM &&
                    this.bttmLeftTaken) {
                this.bttmLeftOven.unloadPizza();
                this.bttmLeftTaken = false;
            } else if (e.getX() > Constants.OVEN4_LEFT && e.getX() < Constants.OVEN4_RIGHT &&
                    e.getY() > Constants.OVEN4_TOP && e.getY() < Constants.OVEN4_BTM &&
                    this.bttmRightTaken) {
                this.bttmRightOven.unloadPizza();
                this.bttmRightTaken = false;
            }
        }
    }
}