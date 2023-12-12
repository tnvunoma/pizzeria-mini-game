package indy;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * This is the PizzaOrganizer class. It is responsible for keeping track of pizza queues so that pizzas can be
 * transferred from tab to tab in the correct order. It also has methods for recreating these pizzas and removing
 * them.
 */
public class PizzaOrganizer {
    Queue<ArrayList<String>> toppings;
    Queue<ArrayList<Point2D>> toppingCoords;
    Queue<ArrayList<Color>> sauceColor;
    Queue<ArrayList<Point2D>> sauceCoords;
    Queue<Boolean> hasCheese;

    Queue<Color> pizzaDoughSlice;
    Queue<ArrayList<String>> toppingsSlice;
    Queue<ArrayList<Point2D>> toppingCoordsSlice;
    Queue<ArrayList<Color>> sauceColorSlice;
    Queue<ArrayList<Point2D>> sauceCoordsSlice;
    Queue<Boolean> hasCheeseSlice;

    Queue<Color> pizzaDoughLog;
    Queue<ArrayList<String>> toppingsLog;
    Queue<ArrayList<Point2D>> toppingCoordsLog;
    Queue<ArrayList<Color>> sauceColorLog;
    Queue<ArrayList<Point2D>> sauceCoordsLog;
    Queue<Boolean> hasCheeseLog;
    Queue<ArrayList<Point2D>> slicesLog;

    public PizzaOrganizer() {
        this.toppings = new LinkedList<>();
        this.toppingCoords = new LinkedList<>();
        this.sauceColor = new LinkedList<>();
        this.sauceCoords = new LinkedList<>();
        this.hasCheese = new LinkedList<>();

        this.pizzaDoughSlice = new LinkedList<>();
        this.toppingsSlice = new LinkedList<>();
        this.toppingCoordsSlice = new LinkedList<>();
        this.sauceColorSlice = new LinkedList<>();
        this.sauceCoordsSlice = new LinkedList<>();
        this.hasCheeseSlice = new LinkedList<>();

        this.pizzaDoughLog = new LinkedList<>();
        this.toppingsLog = new LinkedList<>();
        this.toppingCoordsLog = new LinkedList<>();
        this.sauceColorLog = new LinkedList<>();
        this.sauceCoordsLog = new LinkedList<>();
        this.hasCheeseLog = new LinkedList<>();
        this.slicesLog = new LinkedList<>();
    }

    /**
     * This method checks if there is a pizza in the queue waiting to be baked.
     */
    public boolean bakingQueueEmpty() {return (this.toppings.isEmpty() || this.sauceColor.isEmpty());}

    /**
     * This method adds toppings to a queue to wait until they can be recreated in the baking tab.
     */
    public void addToppingsBake(ArrayList<String> toppings) {this.toppings.add(toppings);}

    /**
     * This method adds topping coordinates to a queue so that the toppings can be recreated in the baking tab.
     */
    public void addToppingCoordsBake(ArrayList<Point2D> coords) {this.toppingCoords.add(coords);}

    /**
     * This method adds sauces to a queue to wait until they can be recreated in the baking tab.
     */
    public void addSauceBake(ArrayList<Color> color) {this.sauceColor.add(color);}

    /**
     * This method adds sauce coordinates to a queue so that the sauces can be recreated in the baking tab.
     */
    public void addSauceCoordsBake(ArrayList<Point2D> coords) {this.sauceCoords.add(coords);}

    /**
     * This method adds the cheese to a queue to wait until it can be recreated in the baking tab.
     */
    public void addCheeseBake(Boolean cheese) {this.hasCheese.add(cheese);}

    /**
     * This method dequeues the list of toppings waiting to be baked.
     */
    public ArrayList<String> dequeueToppingsBake() {return this.toppings.remove();}

    /**
     * This method dequeues the list of topping coordinates.
     */
    public ArrayList<Point2D> dequeueToppingCoordsBake() {
        return this.toppingCoords.remove();
    }

    /**
     * This method dequeues the list of sauces waiting to be baked.
     */
    public ArrayList<Color> dequeueSauceBake() {return this.sauceColor.remove();}

    /**
     * This method dequeues the list of sauce coordinates.
     */
    public ArrayList<Point2D> dequeueSauceCoordsBake() {return this.sauceCoords.remove();}

    /**
     * This method dequeues the cheese waiting to be baked.
     */
    public boolean dequeueCheeseBake() {return this.hasCheese.remove();}


    /**
     * This method checks if there is a pizza in the queue waiting to be sliced.
     */
    public boolean slicingQueueEmpty() {return (this.toppingsSlice.isEmpty() || this.sauceColorSlice.isEmpty());}

    /**
     * This method adds the pizza to a queue to wait until it can be recreated in the slicing tab.
     */
    public void addPizzaSlice(Color color) {this.pizzaDoughSlice.add(color);}

    /**
     * This method adds toppings to a queue to wait until they can be recreated in the slicing tab.
     */
    public void addToppingsSlice(ArrayList<String> toppings) {this.toppingsSlice.add(toppings);}

    /**
     * This method adds topping coordinates to a queue so that the toppings can be recreated in the slicing tab.
     */
    public void addToppingCoordsSlice(ArrayList<Point2D> coords) {this.toppingCoordsSlice.add(coords);}

    /**
     * This method adds sauces to a queue to wait until they can be recreated in the slicing tab.
     */
    public void addSauceSlice(ArrayList<Color> color) {this.sauceColorSlice.add(color);}

    /**
     * This method adds sauce coordinates to a queue so that the sauces can be recreated in the slicing tab.
     */
    public void addSauceCoordsSlice(ArrayList<Point2D> coords) {this.sauceCoordsSlice.add(coords);}

    /**
     * This method adds the cheese to a queue to wait until it can be recreated in the slicing tab.
     */
    public void addCheeseSlice(Boolean cheese) {this.hasCheeseSlice.add(cheese);}


    /**
     * This method dequeues the pizza waiting to be sliced.
     */
    public Color dequeuePizzaSlice() {return this.pizzaDoughSlice.remove();}

    /**
     * This method dequeues the list of toppings waiting to be sliced.
     */
    public ArrayList<String> dequeueToppingsSlice() {return this.toppingsSlice.remove();}

    /**
     * This method dequeues the list of toppings coords.
     */
    public ArrayList<Point2D> dequeueToppingCoordsSlice() {return this.toppingCoordsSlice.remove();}

    /**
     * This method dequeues the list of sauces waiting to be sliced.
     */
    public ArrayList<Color> dequeueSauceSlice() {return this.sauceColorSlice.remove();}

    /**
     * This method dequeues the list of sauce coords.
     */
    public ArrayList<Point2D> dequeueSauceCoordsSlice() {return this.sauceCoordsSlice.remove();}

    /**
     * This method dequeues the cheese waiting to be sliced.
     */
    public boolean dequeueCheeseSlice() {return this.hasCheeseSlice.remove();}


    /**
     * This method checks if there is a pizza in the queue waiting to be sliced.
     */
    public boolean logQueueEmpty() {return this.slicesLog.isEmpty();}

    /**
     * This method adds the pizza to a queue to wait until it can be recreated in the pizza inventory.
     */
    public void addPizzaLog(Color color) {this.pizzaDoughLog.add(color);}

    /**
     * This method adds toppings to a queue to wait until they can be recreated in the pizza inventory.
     */
    public void addToppingsLog(ArrayList<String> toppings) {this.toppingsLog.add(toppings);}

    /**
     * This method adds topping coordinates to a queue so that the toppings can be recreated in the pizza
     * inventory.
     */
    public void addToppingCoordsLog(ArrayList<Point2D> coords) {this.toppingCoordsLog.add(coords);}

    /**
     * This method adds sauces to a queue to wait until they can be recreated in the pizza inventory.
     */
    public void addSauceLog(ArrayList<Color> color) {this.sauceColorLog.add(color);}

    /**
     * This method adds sauce coordinates to a queue so that the sauces can be recreated in the pizza
     * inventory.
     */
    public void addSauceCoordsLog(ArrayList<Point2D> coords) {this.sauceCoordsLog.add(coords);}

    /**
     * This method adds slices to a queue to wait until they can be recreated in the pizza inventory.
     */
    public void addSlicesLog(ArrayList<Point2D> coords) {this.slicesLog.add(coords);}

    /**
     * This method adds the cheese to a queue to wait until it can be recreated in the pizza inventory.
     */
    public void addCheeseLog(Boolean cheese) {this.hasCheeseLog.add(cheese);}


    /**
     * This method dequeues the pizza waiting to be logged.
     */
    public Color dequeuePizzaLog() {return this.pizzaDoughLog.remove();}

    /**
     * This method dequeues the list of toppings waiting to be logged.
     */
    public ArrayList<String> dequeueToppingsLog() {return this.toppingsLog.remove();}

    /**
     * This method dequeues the list of toppings coords.
     */
    public ArrayList<Point2D> dequeueToppingCoordsLog() {return this.toppingCoordsLog.remove();}

    /**
     * This method dequeues the list of sauces waiting to be logged.
     */
    public ArrayList<Color> dequeueSauceLog() {return this.sauceColorLog.remove();}

    /**
     * This method dequeues the list of sauce coords.
     */
    public ArrayList<Point2D> dequeueSauceCoordsLog() {return this.sauceCoordsLog.remove();}

    /**
     * This method dequeues the list of slices waiting to be logged.
     */
    public ArrayList<Point2D> dequeueSlicesLog() {return this.slicesLog.remove();}

    /**
     * This method dequeues the cheese waiting to be logged.
     */
    public boolean dequeueCheeseLog() {return this.hasCheeseLog.remove();}

    /**
     * This method uses a list of topping Strings to instantiate new toppings so that the toppings can be
     * recreated in all the tabs.
     */
    public void recreatePizzaToppings(ArrayList<String> toppingsList, ArrayList<Toppings> topping, Pane pane) {
        for (String toppingType : toppingsList) {
            switch (toppingType) {
                case ("pepperoni"):
                    topping.add(new Pepperoni(pane));
                    break;
                case ("sausage"):
                    topping.add(new Sausage(pane));
                    break;
                case ("mushroom"):
                    topping.add(new Mushroom(pane));
                    break;
                case ("pepper"):
                    topping.add(new GreenPepper((pane)));
                    break;
                case ("olive"):
                    topping.add(new Olive(pane));
                    break;
                case ("onion"):
                    topping.add(new Onion(pane));
                    break;
                case ("pineapple"):
                    topping.add(new Pineapple(pane));
                    break;
                case ("sardine"):
                    topping.add(new Sardine(pane));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method relies on saved lists of sauce colors and coordinates to recreate the sauce in all tabs.
     */
    public void recreatePizzaSauce(Pane pane, ArrayList<Color> sauceType, ArrayList<Point2D> sauceCoords,
                                   ArrayList<PizzaSauce> pizzaSauce, double xOffset, double yOffset) {
        for (int i = 0; i < sauceType.size(); i++) {
            double x = sauceCoords.get(i).getX() + xOffset;
            double y = sauceCoords.get(i).getY() + yOffset;
            Color sauceColor = sauceType.get(i);
            pizzaSauce.add(new PizzaSauce(pane,x, y, sauceColor));
        }
    }

    /**
     * This method relies on a saved list of topping coordinates to recreate the toppings in all tabs.
     */
    public void positionToppings(ArrayList<Point2D> toppingCoords, ArrayList<Toppings> topping,
                                 double xOffset, double yOffset) {
        for (int i = 0; i < toppingCoords.size(); i++) {
            double x = toppingCoords.get(i).getX() + xOffset;
            double y = toppingCoords.get(i).getY() + yOffset;
            topping.get(i).translate(x, y);
            topping.get(i).addToPane();
        }
    }

    /**
     * This method graphically removes any list of toppings.
     */
    public void clearToppings(ArrayList<Toppings> topping) {
        for (int i = 0; i < topping.size(); i++) {
            topping.get(i).delete();
            topping.remove(i);
            i--;
        }
    }

    /**
     * This method graphically removes any list of sauces.
     */
    public void clearSauces(ArrayList<PizzaSauce> pizzaSauce) {
        for (int i = 0; i < pizzaSauce.size(); i++) {
            pizzaSauce.get(i).delete();
            pizzaSauce.remove(i);
            i--;
        }
    }

    /**
     * This method graphically removes any list of slices.
     */
    public void clearSlices(ArrayList<Slice> slice) {
        for (int i = 0; i < slice.size(); i++) {
            slice.get(i).removeFromPane();
            slice.remove(i);
            i--;
        }
    }
}