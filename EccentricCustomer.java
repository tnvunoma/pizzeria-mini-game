package indy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This is the EccentricCustomer class which inherits from the Customer class to create a special personality customer
 * who orders more than the regular customer but gives a big tip and is more patient.
 */
public class EccentricCustomer extends Customer{
    public EccentricCustomer(Pane pane) {
        super(pane);
    }

    /**
     * This overrides the normal customer images to display the sprite of the eccentric customer.
     */
    @Override
    public void generateCustomer() {
        this.customerImg = new Image("./indy/customer_eccentric.png");
    }

    /**
     * This method overrides the normal customer's wait time by making it longer for the eccentric customer.
     */
    @Override
    public int randomizeTimer() {
        return (int) (Math.random() * Constants.ECCENTRIC_WAIT_RANGE + Constants.ECCENTRIC_WAIT);
    }

    /**
     * This method overrides the normal customer's usual amount of toppings ordered and increases the amount to
     * what the eccentric customer would order.
     */
    @Override
    public void generateToppingsOrder(int difficulty) {
        ArrayList<String> toppingsList = this.orderToppings(difficulty);
        for (String toppings : toppingsList) {
            int randAmount = (int) (Math.random() * Constants.MAX_DIF + (difficulty + Constants.MIN_DIFFICULTY));
            this.displayedOrder.add(randAmount + " " + toppings + "s, ");
            for (int j = 0; j < randAmount; j++) {
                this.order.add(toppings);
            }
        }
    }

    /**
     * This method overrides the amount that normal customers would tip and increases it to what the eccentric customer
     * would tip.
     */
    @Override
    public double giveTip(int score) {
        return score * Constants.ECCENTRIC_TIP;
    }
}
