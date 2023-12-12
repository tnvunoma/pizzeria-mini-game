package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * This is the Oven class. It is responsible for setting up ovens that can bake and burn pizza.
 */
public class Oven {
    private final Pane pane;
    private final PizzaOrganizer pizzaOrganizer;
    private final ArrayList<PizzaSauce> pizzaSauce;
    private final ArrayList<Toppings> topping;
    private final Pizza pizza;
    private Cheese cheese;
    private Rectangle timer;
    private Rectangle bakedTime;
    private Rectangle burntTime;
    private Rectangle timerBar;
    double xOffset;
    double yOffset;
    public Oven(PizzaOrganizer pizzaOrganizer, Pane pane, double xOffset, double yOffset) {
        this.pane = pane;
        this.pizzaOrganizer = pizzaOrganizer;
        this.pizzaSauce = new ArrayList<>();
        this.topping = new ArrayList<>();
        this.pizza = new Pizza();
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * This method recreates the all components of the pizza on top of the oven and starts the timer to bake/burn
     * the pizza.

     */
    public void loadPizza(double x, double y) {
        if (!this.pizzaOrganizer.bakingQueueEmpty()) {
            this.setUpTimer(x, y);
            this.startTimer();
            this.recreateSauce();
            this.recreateCheese();
            this.recreateToppings();
            this.positionToppings();
        }
    }

    /**
     * This method recreates the state of the cheese of the pizza made in the Assembly tab.
     */
    public void recreateCheese() {
        this.cheese = new Cheese(this.pane);
        this.cheese.setPosition(this.xOffset, this.yOffset);
        this.cheese.showCheese(this.pizzaOrganizer.dequeueCheeseBake());
    }

    /**
     * This method recreates the sauce of the pizza.
     */
    public void recreateSauce() {
        ArrayList<Color> sauce = this.pizzaOrganizer.dequeueSauceBake();
        ArrayList<Point2D> coords = this.pizzaOrganizer.dequeueSauceCoordsBake();
        this.pizzaOrganizer.recreatePizzaSauce(this.pane, sauce, coords, this.pizzaSauce, this.xOffset, this.yOffset);
    }

    /**
     * This method recreates the toppings of the pizza.
     */
    public void recreateToppings() {
        ArrayList<String> toppingsList = this.pizzaOrganizer.dequeueToppingsBake();
        this.pizzaOrganizer.recreatePizzaToppings(toppingsList, this.topping, this.pane);
    }

    /**
     * This method recreates the positions of the toppings.
     */
    public void positionToppings() {
        ArrayList<Point2D> toppingCoords = this.pizzaOrganizer.dequeueToppingCoordsBake();
        this.pizzaOrganizer.positionToppings(toppingCoords, this.topping, this.xOffset, this.yOffset);
    }

    /**
     * This method sets up the timer that shows how long the pizza has been in the oven.
     * The timer randomizes the point of when the pizza is baked and then being burnt.
     */
    public void setUpTimer(double x, double y) {
        this.pizza.setPosition(x + Constants.PIZZA_OVEN_OFFSET_X, y + Constants.PIZZA_OVEN_OFFSET_Y);
        this.timerBar = new Rectangle(x, y, Constants.OVEN_TIMER_BAR_WIDTH, Constants.OVEN_TIMER_BAR_HEIGHT);
        this.timer = new Rectangle(x, y - 1, Constants.OVEN_TIMER_WIDTH, Constants.OVEN_TIMER_HEIGHT);

        double randBurntRange = Constants.BAKED_DEFAULT + (Math.random() * Constants.BAKED_RANGE);
        double burntX = this.timerBar.getX() + (this.timerBar.getWidth() - randBurntRange);
        this.burntTime = new Rectangle(burntX, y, randBurntRange, Constants.OVEN_TIMER_BAR_HEIGHT);

        double randBakedRange = Constants.BAKED_DEFAULT + (Math.random() * Constants.BAKED_RANGE);
        double bakedX = this.burntTime.getX() - randBakedRange;
        this.bakedTime = new Rectangle(bakedX, y, randBakedRange, Constants.OVEN_TIMER_BAR_HEIGHT);

        this.timer.setFill(Color.web("7C7774"));
        this.timerBar.setFill(Color.web("FCE37C"));
        this.bakedTime.setFill(Color.web("FAA681"));
        this.burntTime.setFill(Color.web("FD6C58"));

        this.pizza.addToPane(this.pane);
        this.pane.getChildren().addAll(this.timerBar, this.bakedTime, this.burntTime, this.timer);
    }

    /**
     * This method creates the movement of the timer.
     */
    public void startTimer() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.OVEN_SPEED),
                (ActionEvent e) -> this.bakePizza());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * This method bakes or burns the pizza based on how long the pizza has been in the oven.
     */
    public void bakePizza() {
        if (this.timer.getX() < (this.timerBar.getX() + this.timerBar.getWidth())) {
            this.timer.setX(this.timer.getX() + 1);

            double timerPos = this.timer.getX();
            if (timerPos >= this.bakedTime.getX() &&
                    timerPos <= this.bakedTime.getX() + this.bakedTime.getWidth()) {
                this.pizza.bake();
            } else if (timerPos >= this.burntTime.getX() &&
                    timerPos <= this.burntTime.getX() + this.burntTime.getWidth() + 1) {
                this.pizza.burn();
            } else {
                this.pizza.underBake();
            }
        }
    }

    /**
     * This takes the pizza out of the oven and saves the condition of the pizza to be recreated in the cutting tab.
     */
    public void unloadPizza() {
        this.storePizza();
        this.storeSauce();
        this.storeSauceCoords();
        this.storeToppings();
        this.storeToppingCoords();
        this.storeCheese();

        this.pizza.removeFromPane(this.pane);
        this.pizzaOrganizer.clearToppings(this.topping);
        this.pizzaOrganizer.clearSauces(this.pizzaSauce);
        this.cheese.showCheese(false);
        this.pane.getChildren().removeAll(this.timer, this.timerBar, this.bakedTime, this.burntTime);
    }

    /**
     * This saves the baked condition of the pizza so that it can be recreated in the slicing tab.
     */
    public void storePizza() {
        this.pizzaOrganizer.addPizzaSlice(this.pizza.getColor());
    }

    /**
     * This saves the toppings on the pizza so that it can be recreated in the slicing tab.
     */
    public void storeToppings() {
        ArrayList<String> toppingsList = new ArrayList<>();
        for (Toppings toppings : this.topping) {
            String topping = toppings.parseToppings();
            toppingsList.add(topping);
        }
        this.pizzaOrganizer.addToppingsSlice(toppingsList);
    }

    /**
     * This saves the topping coords so that they can be recreated in the slicing tab.
     */
    public void storeToppingCoords() {
        ArrayList<Point2D> coordsList = new ArrayList<>();
        for (Toppings toppings : this.topping) {
            Point2D coords = new Point2D(toppings.getX() - this.xOffset, toppings.getY() - this.yOffset);
            coordsList.add(coords);
        }
        this.pizzaOrganizer.addToppingCoordsSlice(coordsList);
    }

    /**
     * This saves the sauce on the pizza so that it can be recreated in the slicing tab.
     */
    public void storeSauce() {
        ArrayList<Color> sauceList = new ArrayList<>();
        for (PizzaSauce sauces: this.pizzaSauce) {
            Color sauce = sauces.getSauceColor();
            sauceList.add(sauce);
        }
        this.pizzaOrganizer.addSauceSlice(sauceList);
    }

    /**
     * This saves the position of the sauce on the pizza so that it can be recreated in the slicing tab.
     */
    public void storeSauceCoords() {
        ArrayList<Point2D> sauceCoordsList = new ArrayList<>();
        for (PizzaSauce sauces : this.pizzaSauce) {
            Point2D coords = new Point2D(sauces.getX() - this.xOffset, sauces.getY() - this.yOffset);
            sauceCoordsList.add(coords);
        }
        this.pizzaOrganizer.addSauceCoordsSlice(sauceCoordsList);
    }

    /**
     * This saves the condition of the cheese on the pizza so that it can be recreated in the slicing tab.
     */
    public void storeCheese() {
        this.pizzaOrganizer.addCheeseSlice(this.cheese.hasCheese());
    }
}
