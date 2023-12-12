package indy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

/**
 * This is the NiceCustomer class which inherits from the Customer class to create a special personality customer
 * who orders like a regular customer but gives a big tip and is more patient.
 */
public class NiceCustomer extends Customer{
    public NiceCustomer(Pane pane) {
        super(pane);
    }

    /**
     * This overrides the normal customer images to display the sprite of the nice customer.
     */
    @Override
    public void generateCustomer() {
        this.customerImg = new Image("./indy/customer_nice.png");
    }

    /**
     * This method overrides the normal customer's wait time by making it longer for the nice customer.
     */
    @Override
    public int randomizeTimer() {
        return (int) (Math.random() * Constants.NICE_WAIT_RANGE + Constants.NICE_WAIT);
    }

    /**
     * This method overrides the amount that normal customers would tip and increases it to what the nice customer
     * would tip.
     */
    @Override
    public double giveTip(int score) {
        return score * Constants.NICE_TIP;
    }
}
