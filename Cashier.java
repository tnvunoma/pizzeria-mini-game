package indy;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;


/**
 * This is the Cashier class. It is responsible for creating all the customers of the game, keeping track of difficulty,
 * scores, tips, and customer orders. It also allows for users to view all the pizzas they've made and serve and delete
 * pizzas from the log.
 */
public class Cashier {
    private final Pane pane;
    private final PizzaOrganizer pizzaOrganizer;
    private final Customer[] customers;
    private final ArrayList<PizzaLog> pizzas;
    private final ArrayList<Integer> pizzaScores;
    private final ArrayList<Double> tip;
    private Text scoreText;
    private Text averageScore;
    private Text tipText;
    private Text totalTip;
    int customerDifficulty = 1;

    public Cashier(Pane pane, PizzaOrganizer pizzaOrganizer) {
        this.pane = pane;
        this.pizzaScores = new ArrayList<>();
        this.tip = new ArrayList<>();
        this.pizzaOrganizer = pizzaOrganizer;
        this.pizzas = new ArrayList<>();
        this.customers = new Customer[Constants.CUSTOMER_AMT];

        this.setUpText();
        this.setUpAllCustomers();
        this.checkForUpdates();
        pane.setOnMousePressed(this::handleMousePressed);
    }

    /**
     * This sets up all the text to display the most recent score, average score, most recent tip, and total tip.
     */
    public void setUpText() {
        this.scoreText = new Text("last score: ");
        this.scoreText.setX(Constants.SCORE_TEXT_X);
        this.scoreText.setY(Constants.LAST_SCORE_Y);
        this.averageScore = new Text("average score: ");
        this.averageScore.setX(Constants.SCORE_TEXT_X);
        this.averageScore.setY(Constants.AVG_SCORE_Y);
        this.tipText = new Text("last tip: $");
        this.tipText.setX(Constants.TIP_TEXT_X);
        this.tipText.setY(Constants.LAST_SCORE_Y);
        this.totalTip = new Text("tip jar: $");
        this.totalTip.setY(Constants.AVG_SCORE_Y);
        this.totalTip.setX(Constants.TIP_TEXT_X);

        this.pane.getChildren().addAll(this.scoreText, this.averageScore, this.tipText, this.totalTip);
    }

    /**
     * This sets up all three customers at teh start of the game.
     */
    public void setUpAllCustomers() {
        for (int i = 0; i < Constants.CUSTOMER_AMT; i++) {
            this.setUpCustomer(i);
        }
    }

    /**
     * This method sets up a customer by graphically adding them to the pane, logically assigning them to the array
     * and generating their order.
     */
    public void setUpCustomer(int i) {
        this.customers[i] = this.generateCustomers();
        this.customers[i].generateOrder(this.customerDifficulty);
        this.customers[i].setPosition(i * Constants.CUSTOMER_X + Constants.CUSTOMER_X_OFFSET, Constants.CUSTOMER_Y);
    }

    /**
     * This method determines the chance of getting a normal customer or special personality customer depending on the
     * current difficulty of the game. As user performance improves, the difficulty will increase, and the chance of
     * getting special customers increases.
     */
    public Customer generateCustomers() {
        int randCustomer = (int) (Math.random() * this.customerDifficulty + Constants.RAND_CUSTOMER_CALC);
        Customer generatedCustomer;
        if (randCustomer < Constants.NORMAL_CUSTOMER_THRESHOLD) {
            generatedCustomer = new Customer(this.pane);
        } else {
            int randSpecialCustomer = (int) (Math.random() * Constants.SPECIAL_CUSTOMER_AMT);
            switch (randSpecialCustomer) {
                case 1:
                    generatedCustomer = new MeanCustomer(this.pane);
                    break;
                case 2:
                    generatedCustomer = new EccentricCustomer(this.pane);
                    break;
                default:
                    generatedCustomer = new NiceCustomer(this.pane);
                    break;
            }
        }
        return generatedCustomer;
    }

    /**
     * This checks for any game updates and updates the game by the second.
     */
    public void checkForUpdates() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.GAME_UPDATES),
                (ActionEvent e) -> this.updateGame());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * This updates the game by update the game texts and the pizza log.
     */
    public void updateGame() {
        this.updateText();
        this.displayPizzas();
        this.giveBadScore();
        this.updateLogPosition();
    }

    /**
     * This updates the texts of the game to display the scores and tips.
     */
    public void updateText() {
        if (!this.pizzaScores.isEmpty()) {
            this.scoreText.setText("last score: " + this.pizzaScores.get(this.pizzaScores.size() - 1));
            this.averageScore.setText("average score: " + this.calcAvgScore());
            this.tipText.setText("last tip : $" + this.tip.get(this.tip.size() - 1));
            this.totalTip.setText("tip jar: $" + this.calcTotalTip());
        }
    }

    /**
     * This method calculates the average of the scores that the customers have given.
     */
    public int calcAvgScore() {
        double avgScore = 0;
        for (int score : this.pizzaScores) {
            avgScore = avgScore + score;
        }
        avgScore = (avgScore/ this.pizzaScores.size());
        return (int) avgScore;
    }

    /**
     * This method calculates the total tips that the player has accumulated.
     */
    public double calcTotalTip() {
        double totalTip = 0;
        for (double tip : this.tip) {
            totalTip = totalTip + tip;
        }
        return totalTip;
    }

    /**
     * This method adds pizzas to the pizza inventory whenever a pizza has been completed.
     */
    public void displayPizzas() {
        if (!this.pizzaOrganizer.logQueueEmpty()) {
            this.pizzas.add(new PizzaLog(this.pane, this.pizzaOrganizer));
        }
    }

    /**
     * This method updates the pizzas positions in the pizza log whenever pizzas are deleted or served.
     */
    public void updateLogPosition() {
        if (!this.pizzas.isEmpty()) {
            for (int i = 0; i < this.pizzas.size(); i++) {
                this.pizzas.get(i).translatePizza(Constants.PIZZA_LOG_X,
                        (i * Constants.PIZZA_LOG_Y) - Constants.PIZZA_LOG_Y_OFFSET);
            }
        }
    }

    /**
     * This method updates the difficulty of the game based on the most recent pizza score.
     */
    public void updateDifficulty() {
        int score = this.pizzaScores.get(this.pizzaScores.size() - 1);
        if (score > Constants.SCORE_THRESHOLD) {
            this.customerDifficulty = this.customerDifficulty + Constants.MIN_DIFFICULTY;
        } else {
            this.customerDifficulty = Math.max(this.customerDifficulty -
                    ((Constants.SCORE_THRESHOLD - score)/Constants.SCORE_DIVIDE), Constants.MIN_DIFFICULTY);
        }
    }

    /**
     * This method allows for user interaction with teg screen through mouse inputs.
     */
    public void handleMousePressed(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            this.handlePizzaSelection(e.getX(), e.getY());

            if ((! this.pizzas.isEmpty()) && this.pizzaIsSelected().pizzaSelected()) {
                if (e.getX() > Constants.CUSTOMER1_LEFT && e.getY() > Constants.CUSTOMER1_TOP &&
                        e.getX() < Constants.CUSTOMER1_RIGHT && e.getY() < Constants.CUSTOMER1_BTM) {
                    this.pizzaGetsScored(0);
                    this.handlePizzaDeselection(e.getX(), e.getY());
                } else if (e.getX() > Constants.CUSTOMER2_LEFT && e.getY() > Constants.CUSTOMER2_TOP &&
                        e.getX() < Constants.CUSTOMER2_RIGHT && e.getY() < Constants.CUSTOMER2_BTM) {
                    this.pizzaGetsScored(1);
                    this.handlePizzaDeselection(e.getX(), e.getY());
                } else if (e.getX() > Constants.CUSTOMER3_LEFT && e.getY() > Constants.CUSTOMER3_TOP &&
                        e.getX() < Constants.CUSTOMER3_RIGHT && e.getY() < Constants.CUSTOMER3_BTM) {
                    this.pizzaGetsScored(Constants.CUSTOMER_AMT - 1);
                    this.handlePizzaDeselection(e.getX(), e.getY());
                } else if (e.getX() > Constants.PIZZA_DELETE_LEFT && e.getY() > Constants.PIZZA_DELETE_TOP &&
                        e.getX() < Constants.PIZZA_DELETE_RIGHT && e.getY() < Constants.PIZZA_DELETE_BTM) {
                    this.deletePizza();
                } else {
                    this.handlePizzaDeselection(e.getX(), e.getY());
                }
            }
        }
    }

    /**
     * This method selects a pizza from the inventory when it is clicked on.
     */
    public void handlePizzaSelection(double x, double y) {
        if (!this.pizzas.isEmpty()) {
            for (PizzaLog pizza : this.pizzas) {
                pizza.selectPizza(x, y);
            }
        }
    }

    /**
     * This method deselects the pizza when it is not being clicked on;
     */
    public void handlePizzaDeselection(double x, double y) {
        for (PizzaLog pizza : this.pizzas) {
            pizza.deselectPizza(x, y);
        }
    }

    /**
     * This method returns which pizza within the inventory is being selected.
     */
    public PizzaLog pizzaIsSelected() {
        PizzaLog selected = null;
        for (PizzaLog pizza : this.pizzas) {
            if (pizza.pizzaSelected()) {
                selected = pizza;
            }
        }
        return selected;
    }

    /**
     * This method deletes the pizza that is selected.
     */
    public void deletePizza() {
        PizzaLog pizza = this.pizzaIsSelected();
        pizza.removePizza();
        this.pizzas.remove(pizza);
    }

    /**
     * This method allows players to serve customers the pizza and receive a score and tip. It removes the pizza and
     * the customer graphically and logically, and generates a new customer to replace the old one.
     */
    public void pizzaGetsScored(int i) {
        if (!this.pizzas.isEmpty()) {
            PizzaLog pizza = this.pizzaIsSelected();
            int score = this.customers[i].scorePizza(pizza.getPizzaMade());
            this.pizzaScores.add(score);
            this.tip.add(this.customers[i].giveTip(score));
            this.updateDifficulty();
            this.deletePizza();
            this.customers[i].deleteFromPane();
            this.setUpCustomer(i);
        }
    }

    /**
     * This method removes a customer when their timer runs out, gives a score of 0, and generates a new customer
     * to replace the old one.
     */
    public void giveBadScore() {
        for (int i = 0; i < Constants.CUSTOMER_AMT; i++) {
            if (this.customers[i].timerIsUp()) {
                this.pizzaScores.add(0);
                this.tip.add((double) 0);
                this.updateDifficulty();
                this.customers[i].deleteFromPane();
                this.setUpCustomer(i);
            }
        }
    }
}