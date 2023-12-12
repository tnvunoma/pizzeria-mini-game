package indy;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * This is the MeanCustomer class that inherits from the Customer class. It overrides methods from its super class
 * to create a mean customer that orders a lot, tips little, and has a short wait time.
 */
public class MeanCustomer extends Customer{
    public MeanCustomer(Pane pane) {
        super(pane);
    }

    /**
     * This method changes the appearance of the normal customers to the mean customer.
     */
    @Override
    public void generateCustomer() {
        this.customerImg = new Image("./indy/customer_mean.png");
    }

    /**
     * This method decreases the usual wait times of normal customer to how long the mean customer would wait.
     */
    @Override
    public int randomizeTimer() {
        return (int) (Math.random() * Constants.MEAN_WAIT_RANGE + Constants.MEAN_WAIT);
    }

    /**
     * This method increases the amount of toppings that normal customers would order to the amount that the mean
     * customer would order.
     */
    @Override
    public void generateToppingsOrder(int difficulty) {
        ArrayList<String> toppingsList = this.orderToppings(difficulty);
        for (String toppings : toppingsList) {
            int randAmount = (int) (Math.random() * Constants.MEAN_RANGE + (difficulty + Constants.MIN_DIFFICULTY));
            this.displayedOrder.add(randAmount + " " + toppings + "s, ");
            for (int j = 0; j < randAmount; j++) {
                this.order.add(toppings);
            }
        }
    }

    /**
     * This method decreases the amount of tip that normal customers would give to the amount that the mean customer
     * would tip.
     */
    @Override
    public double giveTip(int score) {
        return score * Constants.MEAN_TIP;
    }
}
