package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * This is the Customer class. It is responsible for generating customers with their orders and a timer.
 */
public class Customer {
    Pane pane;
    ImageView customer;
    Image customerImg;
    ArrayList<String> possibleToppings;
    ArrayList<String> order;
    ArrayList<String> displayedOrder;
    ArrayList<Text> customerDialogue;
    Rectangle textBox;
    Rectangle timer;
    Rectangle timerBar;
    public Customer(Pane pane) {
        this.pane = pane;
        this.generateCustomer();
        this.generatePossibleOrder();
        this.customer = new ImageView(this.customerImg);
        this.order = new ArrayList<>();
        this.displayedOrder = new ArrayList<>();
        this.customerDialogue = new ArrayList<>();
        this.timer = new Rectangle(Constants.TIMER_WIDTH, Constants.TIMER_HEIGHT);
        this.timerBar = new Rectangle(Constants.TIMER_BAR_WIDTH, Constants.TIMER_BAR_HEIGHT);
        this.timerBar.setFill(Color.SALMON);
        this.startTimer();

        pane.getChildren().addAll(this.customer, this.timerBar, this.timer);
    }

    /**
     * This method randomizes the normal customers that can spawn.
     */
    public void generateCustomer() {
        int randCustomer = (int) (Math.random() * Constants.NORMAL_CUSTOMER_AMT);
        switch (randCustomer) {
            case 1:
                this.customerImg = new Image("./indy/customer_1.png");
                break;
            case 2:
                this.customerImg = new Image("./indy/customer_2.png");
                break;
            case 3:
                this.customerImg = new Image("./indy/customer_3.png");
                break;
            default:
                this.customerImg = new Image("./indy/customer_4.png");
                break;
        }
    }

    /**
     * This method lists all the possible toppings that can be ordered.
     */
    public void generatePossibleOrder() {
        this.possibleToppings = new ArrayList<>();
        this.possibleToppings.add("pepperoni");
        this.possibleToppings.add("sausage");
        this.possibleToppings.add("mushroom");
        this.possibleToppings.add("pepper");
        this.possibleToppings.add("olive");
        this.possibleToppings.add("onion");
        this.possibleToppings.add("pineapple");
        this.possibleToppings.add("sardine");
    }

    /**
     * This method trims down the list of possible toppings ordered down to a shorter list depending on the difficulty
     * level. When the difficulty is lower, more toppings are randomly removed from the set list of eight possible
     * toppings.
     */
    public ArrayList<String> orderToppings(int difficulty) {
        int maxDifficulty = Math.min(difficulty, Constants.MAX_DIF);
        int minusTopping = Math.abs(maxDifficulty - Constants.MAX_DIF);
        for (int i = 0; i < minusTopping; i++) {
            int randToppingDel = (int) (Math.random() * this.possibleToppings.size());
            this.possibleToppings.remove(randToppingDel);
        }
        return this.possibleToppings;
    }

    /**
     * This method generates the type of sauce that the customer would order.
     */
    public void generatePizzaOrder() {
        this.order.add("baked");
        this.displayedOrder.add("I want a pizza with ");
        int sauceOrder = (int) (Math.random() * Constants.SAUCE_CHANCE);
        if (sauceOrder < Constants.RED_SAUCE_CHANCE) {
            this.order.add("red");
            this.displayedOrder.add("margherita sauce ");
        } else {
            this.order.add("green");
            this.displayedOrder.add("pesto sauce ");
        }
    }

    /**
     * This method randomizes whether the customer would want cheese or not.
     */
    public void generateCheeseOrder() {
        int cheeseOrder = (int) (Math.random() * Constants.CHEESE_CHANCE);
        if (cheeseOrder < Constants.YES_CHEESE_CHANCE) {
            this.order.add("cheese");
            this.displayedOrder.add("and cheese. ");
        } else {
            this.displayedOrder.add("and no cheese. ");
        }
        this.displayedOrder.add("For toppings, I want: ");
    }

    /**
     * This method generates the amount that the customer would want for each topping in their order. The amount of
     * toppings they want correlates with the difficulty level.
     */
    public void generateToppingsOrder(int difficulty) {
        ArrayList<String> toppingsList = this.orderToppings(difficulty);
        for (String toppings : toppingsList) {
            int randAmount = (int) (Math.random() * difficulty + Constants.MIN_DIFFICULTY);
            this.displayedOrder.add(randAmount + " " + toppings + "s, ");
            for (int j = 0; j < randAmount; j++) {
                this.order.add(toppings);
            }
        }
    }

    /**
     * This method randomizes how many slices a customer would want on their pizza.
     */
    public void generateSlicesOrder() {
        int randSlices = (int) (Math.random() * Constants.SLICE_CHANCE + Constants.SLICE_CHANCE);
        this.order.add(String.valueOf(randSlices));
        this.displayedOrder.add("and my pizza should have " + randSlices + " slices.");
    }

    /**
     * This method generates the whole order including all specifications on the bake of the pizza, the cheese,
     * toppings, sauces, and slices.
     */
    public void generateOrder(int difficulty) {
        this.generatePizzaOrder();
        this.generateCheeseOrder();
        this.generateToppingsOrder(difficulty);
        this.generateSlicesOrder();
    }

    /**
     * This method sets up yhe dialogue box of the customer with their verbalized order.
     */
    public void displayOrder(double x, double y) {
        this.textBox = new Rectangle(x - Constants.TEXT_BOX_X_OFFSET, y + Constants.TEXT_BOX_Y_OFFSET,
                Constants.TEXT_BOX_WIDTH, Constants.TEXT_BOX_HEIGHT);
        this.textBox.setStrokeWidth(Constants.TEXT_BOX_STROKE);
        this.textBox.setStrokeLineJoin(StrokeLineJoin.ROUND);
        this.textBox.setFill(Color.web("E1F0EA"));
        this.textBox.setStroke(Color.web("F9B9BF"));
        this.pane.getChildren().add(this.textBox);
        for (int i = 0; i < this.displayedOrder.size(); i++) {
            String text = this.displayedOrder.get(i);
            this.customerDialogue.add(new Text(text));
            this.customerDialogue.get(i).setX(x + Constants.DIALOGUE_X);
            this.customerDialogue.get(i).setY(i * Constants.DIALOGUE_Y_OFFSET + (y + Constants.DIALOGUE_Y));
            this.pane.getChildren().add(this.customerDialogue.get(i));
        }
    }

    /**
     * This method randomizes how long the customers are willing to wait for their pizzas.
     */
    public int randomizeTimer() {
        return (int) (Math.random() * Constants.WAIT_RANGE + Constants.WAIT_TIME);
    }

    /**
     * This method sets up the timer that counts down how long the customer will wait for their pizza.
     */
    public void setUpTimer() {
        if (this.timer.getX() > this.timerBar.getX()) {
            this.timer.setX(this.timer.getX() - 1);
        }
    }

    /**
     * This method starts the customer's timer.
     */
    public void startTimer() {
        KeyFrame kf = new KeyFrame(Duration.millis(this.randomizeTimer()),
                (ActionEvent e) -> this.setUpTimer());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * This method sets up the position of the customer, their timer, and their dialogue box.
     */
    public void setPosition(double x, double y) {
        this.customer.setX(x);
        this.customer.setY(y);
        this.timer.setX(x + Constants.TIMER_X_OFFSET);
        this.timer.setY(y + Constants.TIMER_Y_OFFSET);
        this.timerBar.setX(x + Constants.TIMER_BAR_X_OFFSET);
        this.timerBar.setY(y + Constants.TIMER_BAR_Y_OFFSET);
        this.displayOrder(x, y);
    }

    /**
     * This method compares the customer's order list with a list of the pizza served and calculates a score based on
     * how many parts of the order the user got correct. It takes into account any unnecessary or wrong ingredients
     * of the pizza and deducts points from the score.
     */
    public int scorePizza(ArrayList<String> pizzaMade) {
        int correctIngredients = 0;
        for (int i = 0; i < this.order.size(); i++) {
            boolean hasIngredient = false;
            for (int j = 0; j < pizzaMade.size(); j++) {
                if (this.order.get(i).equals(pizzaMade.get(j))) {
                    hasIngredient = true;
                    pizzaMade.remove(j);
                    break;
                }
            }
            if (hasIngredient) {
                correctIngredients++;
            }
        }

        double correctPercentage = ((double) correctIngredients /this.order.size()) * Constants.PERCENT;
        int incorrectIngredients = (pizzaMade.size() * Constants.PENALTY);
        return (int) Math.max(correctPercentage - incorrectIngredients, 0);
    }

    /**
     * This method calculates and returns the tip that the customer would give based on their score.
     */
    public double giveTip(int score) {
        return Math.round(score * Constants.TIP_AMT * 100.0) / 100.0;
    }

    /**
     * This method checks if the customer's wait time has ended.
     */
    public boolean timerIsUp() {
        return this.timer.getX() <= this.timerBar.getX();
    }

    /**
     * This method removes the customer and their timer from the pane.
     */
    public void deleteFromPane() {
        this.pane.getChildren().removeAll(this.customer, this.timer, this.timerBar);
    }
}
