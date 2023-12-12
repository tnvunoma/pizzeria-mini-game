package indy;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This is the Assembling class. It contains the main logic of the Assembly Tab, allowing for the pizza decorating
 * functionality of the game.
 */
public class Assembling {
    private final Pane pane;
    private final PizzaOrganizer pizzaOrganizer;
    private final Pizza pizza;
    private Cheese cheese;
    private final ImageView bakePizzaButton;
    private ArrayList<Toppings> topping;
    private ArrayList<PizzaSauce> pizzaSauce;
    private final Stack<Undoable> undoList;
    private final Stack<Undoable> redoList;
    int pizzaOnBoard = 0;
    private Color sauceType;
    boolean enableSauce = false;

    public Assembling(Pane pane, PizzaOrganizer pizzaOrganizer) {
        this.pane = pane;
        this.pizzaOrganizer = pizzaOrganizer;
        this.pizza = new Pizza();
        this.undoList = new Stack<>();
        this.redoList = new Stack<>();
        this.bakePizzaButton = new ImageView(new Image("./indy/bake_pizza_button.png"));

        pane.setOnMousePressed(this::handleMousePressed);
        pane.setOnMouseDragged(this::handleMouseDragged);
    }

    /**
     * This method enables user interaction with the screen using mouse events. It responds to mouse presses, and uses
     * the location of the presses to trigger certain methods. It only responds to the primary button.
     */
    public void handleMousePressed(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            if (e.getX() > Constants.MAKE_PIZZA_LEFT && e.getY() > Constants.MAKE_PIZZA_TOP &&
                    e.getX() < Constants.MAKE_PIZZA_RIGHT && e.getY() < Constants.MAKE_PIZZA_BTM &&
                    this.pizzaOnBoard < 1) {
                this.makePizza();
            }

            if (this.pizzaOnBoard > 0) {
                if (e.getX() > Constants.FIRST_COL && e.getY() > Constants.FIRST_ROW &&
                        e.getX() < Constants.FIRST_COL + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.FIRST_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Pepperoni(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.FIRST_COL && e.getY() > Constants.SECOND_ROW &&
                        e.getX() < Constants.FIRST_COL + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.SECOND_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Sausage(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.THIRD_ROW && e.getY() > Constants.FIRST_ROW &&
                        e.getX() < Constants.THIRD_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.FIRST_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Mushroom(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.THIRD_ROW && e.getY() > Constants.SECOND_ROW
                        && e.getX() < Constants.THIRD_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.SECOND_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new GreenPepper(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.FOURTH_ROW && e.getY() > Constants.SECOND_ROW &&
                        e.getX() < Constants.FOURTH_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.SECOND_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Onion(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.FOURTH_ROW && e.getY() > Constants.FIRST_ROW &&
                        e.getX() < Constants.FOURTH_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.FIRST_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Olive(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.FIFTH_ROW && e.getY() > Constants.FIRST_ROW &&
                        e.getX() < Constants.FIFTH_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.FIRST_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Pineapple(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.FIFTH_ROW && e.getY() > Constants.SECOND_ROW &&
                        e.getX() < Constants.FIFTH_ROW + Constants.TOPPING_COL_WIDTH &&
                        e.getY() < Constants.SECOND_ROW + Constants.TOPPING_ROW_HEIGHT) {
                    this.topping.add(new Sardine(this.pane));
                    this.addToppingUndo();

                } else if (e.getX() > Constants.CHEESE_lEFT && e.getY() > Constants.CHEESE_TOP &&
                        e.getX() < Constants.CHEESE_RIGHT && e.getY() < Constants.CHEESE_BTM) {
                    this.addCheese();
                }
                else if (e.getX() > Constants.DELETE_LEFT && e.getY() > Constants.DELETE_TOP &&
                        e.getX() < Constants.DELETE_RIGHT && e.getY() < Constants.DELETE_BTM) {
                    this.deleteTopping();
                    this.deleteCheese();

                } else if (e.getX() > Constants.CLEAR_LEFT && e.getY() > Constants.CLEAR_TOP &&
                        e.getX() < Constants.CLEAR_RIGHT && e.getY() < Constants.CLEAR_BTM) {
                    this.clearToppings();

                } else if (e.getX() > Constants.UNDO_LEFT && e.getY() > Constants.UNDO_TOP &&
                        e.getX() < Constants.UNDO_RIGHT && e.getY() < Constants.UNDO_BTM) {
                    this.undo();

                } else if (e.getX() > Constants.REDO_LEFT && e.getY() > Constants.REDO_TOP &&
                        e.getX() < Constants.REDO_RIGHT && e.getY() < Constants.REDO_BTM) {
                    this.redo();
                }

                if (e.getX() > Constants.SAUCE_LEFT && e.getY() > Constants.RED_SAUCE_TOP &&
                        e.getX() < Constants.SAUCE_RIGHT && e.getY() < Constants.RED_SAUCE_BTM) {
                    this.enableSauce = true;
                    this.sauceType = Constants.RED_SAUCE;

                } else if (e.getX() > Constants.SAUCE_LEFT && e.getY() > Constants.GREEN_SAUCE_TOP &&
                        e.getX() < Constants.SAUCE_RIGHT && e.getY() < Constants.GREEN_SAUCE_BTM) {
                    this.enableSauce = true;
                    this.sauceType = Constants.GREEN_SAUCE;

                } else if (e.getX() > Constants.TOPPINGS_LEFT && e.getY() > Constants.TOPPINGS_TOP &&
                        e.getX() < Constants.TOPPINGS_RIGHT && e.getY() < Constants.TOPPINGS_BTM) {
                    this.enableSauce = false;

                } else if (e.getX() > Constants.COMPLETE_MAKE_LEFT && e.getY() > Constants.COMPLETE_MAKE_TOP &&
                        e.getX() < Constants.COMPLETE_MAKE_RIGHT && e.getY() < Constants.COMPLETE_MAKE_BTM) {
                    this.completePizza();
                }

                if (!this.topping.isEmpty()) {
                    this.updateToppings(e.getX(), e.getY());
                }
                if (this.cheese.hasCheese()) {
                    this.selectCheese(e.getX(), e.getY());
                    this.deselectCheese(e.getX(), e.getY());
                }
            }
        }
    }

    /**
     * This method enables user interaction with the screen through the dragging of the mouse.
     */
    public void handleMouseDragged(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            this.addSauce(e.getX(), e.getY());
            this.translateTopping(e.getX(), e.getY());
        }
    }

    /**
     * This method creates a new pizza dough on screen and empty arraylists of toppings and sauces. It also creates a
     * togglable cheese that starts with 0 opacity, and indicates that there is a pizza currently being made.
     */
    public void makePizza() {
        this.pane.getChildren().add(this.bakePizzaButton);
        this.topping = new ArrayList<>();
        this.pizzaSauce = new ArrayList<>();
        this.pizza.addToPane(this.pane);
        this.cheese = new Cheese(this.pane);

        this.pizzaOnBoard++;
    }

    /**
     * This method saves the pizza in whatever state it is in by storing the arraylist of toppings and sauces, cheese,
     * and toppings and sauce locations within queues. Then it removes all components of the pizza from the pane and
     * indicates that there is no longer a pizza being made.
     */
    public void completePizza() {
        this.storeToppings();
        this.storeToppingCoords();
        this.storeSauce();
        this.storeSauceCoords();
        this.storeCheese();
        this.clearToppings();
        this.pane.getChildren().remove(this.bakePizzaButton);
        this.pizza.removeFromPane(this.pane);
        this.pizzaOnBoard--;
    }

    /**
     * This method is a helper method responsible for keeping all toppings in the correct state of layering, selection,
     * and translation.
     */
    public void updateToppings(double x, double y) {
        this.layerTopping(x, y);
        this.updateLayering();
        this.selectTopping(x, y);
        this.deselectTopping(x, y);
        this.recordToppingTranslation(x, y);
    }

    /**
     * This method logically layers any topping on top of all other toppings after that topping is clicked on by
     * removing it from the arraylist and adding it back to the arraylist.
     */
    public void layerTopping(double x, double y) {
        for (int i = 0; i < this.topping.size(); i ++) {
            if (this.topping.get(i).toppingClicked(x, y)) {
                this.topping.add(this.topping.get(i));
                this.topping.remove(this.topping.get(i));
            }
        }
    }

    /**
     * This method graphically layers toppings in the order determined by the arraylist.
     */
    public void updateLayering() {
        for (Toppings toppings: this.topping) {
            toppings.delete();
            toppings.addToPane();
        }
    }


    /**
     * This method updates the graphical layering of cheese by deleting it from the pane and adding it back to the pane.
     */
    public void updateCheeseLayering() {
        if (this.cheese.hasCheese()) {
            this.cheese.removeFromPane();
            this.cheese.addToPane();
        }
    }

    /**
     * This method allows for any toppings to be translated within the pizza.
     */
    public void translateTopping(double x, double y) {
        if (this.pizzaOnBoard > 0 && !this.topping.isEmpty()) {
            for (Toppings toppings : this.topping) {
                if (this.pizza.contain(x, y) && !this.topping.isEmpty() && toppings.isSelected()) {
                    toppings.translate(x, y);
                }
            }
        }
    }

    /**
     * This method records all the original locations of the toppings prior to its translation.
     */
    public void recordToppingTranslation(double x, double y) {
        if (this.pizzaOnBoard > 0 && !this.topping.isEmpty()) {
            for (Toppings toppings : this.topping) {
                if (this.pizza.contain(x, y) && !this.topping.isEmpty() && toppings.isSelected()) {
                    this.undoList.push(new TranslateTopping(toppings, x, y));
                    this.clearRedoList();
                }
            }
        }
    }

    /**
     * This method selects the last and most graphically forward topping when it is clicked.
     */
    public void selectTopping(double x, double y) {
        if (this.topping.get(this.topping.size() - 1).toppingClicked(x, y)) {
            this.enableSauce = false;
            this.topping.get(this.topping.size() - 1).select();
        }
    }

    /**
     * This method selects cheese when it is clicked. It ensures that the cheese is not selected when toppings
     * that may be on top of the cheese are selected.
     */
    public void selectCheese(double x, double y) {
        if (this.cheese.isClicked(x, y)) {
            if (this.topping.isEmpty()) {
                this.cheese.select();
            } else {
                for (Toppings toppings : this.topping) {
                    if (!toppings.isSelected()) {
                        this.cheese.select();
                    }
                }
            }
        }
    }

    /**
     * This method deselects toppings when the mouse press doesn't intersect the topping.
     */
    public void deselectTopping(double x, double y) {
        for (Toppings toppings: this.topping) {
            if (! toppings.toppingClicked(x, y)) {
                toppings.deselect();
            }
        }
    }

    /**
     * This method deselects the cheese when the mouse press doesn't intersect it.
     */
    public void deselectCheese(double x, double y) {
        for (Toppings toppings: this.topping) {
            if (!this.cheese.isClicked(x, y) || toppings.isSelected()) {
                this.cheese.deselect();
            }
        }
    }

    /**
     * This method "adds" the cheese by toggling the visibility of the cheese on;
     */
    public void addCheese() {
        this.enableSauce = false;
        this.cheese.showCheese(true);
        this.updateCheeseLayering();
        this.cheese.setPosition(0,0);
        this.undoList.push(new AddCheese(this.cheese));
        this.clearRedoList();
    }

    /**
     * This method allows user to draw sauce onto the pizza by dragging the mouse. It creates a new droplet of
     * sauce wherever the mouse is dragged.
     */
    public void addSauce(double x, double y) {
        if (this.pizza.contain(x, y) && this.enableSauce) {
            this.pizzaSauce.add(new PizzaSauce(this.pane, x, y, this.sauceType));
            this.updateCheeseLayering();
            this.updateLayering();
            this.clearRedoList();
        }
    }

    /**
     * This method deletes a selected topping.
     */
    public void deleteTopping() {
        for (int i = 0; i < this.topping.size(); i ++) {
            if (this.topping.get(i).isSelected()) {
                this.undoList.add(new DeleteTopping(this.topping.get(i), this.topping));
                this.clearRedoList();
                this.topping.get(i).delete();
                this.topping.remove(this.topping.get(i));
            }
        }
    }

    /**
     * This method toggles off teh visibility of cheese when it is selected.
     */
    public void deleteCheese() {
        if (this.cheese.hasCheese() && this.cheese.isSelected()) {
            this.cheese.showCheese(false);
            this.undoList.add(new DeleteCheese(this.cheese));
            this.clearRedoList();
        }
    }

    /**
     * This method clears all toppings, sauces, and cheese off the pizza.
     */
    public void clearToppings() {
        this.pizzaOrganizer.clearToppings(this.topping);
        this.pizzaOrganizer.clearSauces(this.pizzaSauce);
        this.cheese.showCheese(false);
        this.clearRedoList();
        this.clearUndoList();
    }

    /**
     * This methods allows for any undoable action to be undone and pushes that action to the redo list.
     */
    public void undo() {
        if (! this.undoList.empty()) {
            Undoable undoable = this.undoList.pop();
            undoable.undo();
            this.redoList.push(undoable);
        }
    }

    /**
     * This method allows for any undone action to be redone.
     */
    public void redo() {
        if (! this.redoList.empty()) {
            Undoable redoable = this.redoList.pop();
            redoable.redo();
            this.undoList.push(redoable);
        }
    }

    /**
     * This method adds the action of adding toppings to the undoable list and clears the redo list.
     */
    public void addToppingUndo() {
        this.undoList.push(new AddTopping(this.topping.get(this.topping.size() - 1), this.topping));
        this.clearRedoList();
    }

    /**
     * This method clears the redo list. It is called whenever a new action is done.
     */
    public void clearRedoList() {
        for (int i = 0; i < this.redoList.size(); i++) {
            this.redoList.remove(i);
            i--;
        }
    }

    /**
     * This method clears the undo list. It is called only when you clear all toppings.
     */
    public void clearUndoList() {
        for (int i = 0; i < this.undoList.size(); i++) {
            this.undoList.remove(i);
            i--;
        }
    }

    /**
     * This method stores all the toppings added in string format so that they can be recreated in the next tab.
     */
    public void storeToppings() {
        ArrayList<String> toppingsList = new ArrayList<>();
        for (Toppings toppings : this.topping) {
            String topping = toppings.parseToppings();
            toppingsList.add(topping);
        }
        this.pizzaOrganizer.addToppingsBake(toppingsList);
    }

    /**
     * This method stores all the relative locations of the toppings so that they can be recreated in the next tab.
     */
    public void storeToppingCoords() {
        ArrayList<Point2D> coordsList = new ArrayList<>();
        for (Toppings toppings : this.topping) {
            Point2D coords = new Point2D(toppings.getX(), toppings.getY());
            coordsList.add(coords);
        }
        this.pizzaOrganizer.addToppingCoordsBake(coordsList);
    }

    /**
     * This method stores all the sauce that was drawn so that it can be recreated in the next tab.
     */
    public void storeSauce() {
        ArrayList<Color> sauceList = new ArrayList<>();
        for (PizzaSauce sauces: this.pizzaSauce) {
            Color sauce = sauces.getSauceColor();
            sauceList.add(sauce);
        }
        this.pizzaOrganizer.addSauceBake(sauceList);
    }

    /**
     * This method stores all the coordinates of the drawn sauce so that it can be recreated in the next tab.
     */
    public void storeSauceCoords() {
        ArrayList<Point2D> sauceCoordsList = new ArrayList<>();
        for (PizzaSauce sauces : this.pizzaSauce) {
            Point2D coords = new Point2D(sauces.getX(), sauces.getY());
            sauceCoordsList.add(coords);
        }
        this.pizzaOrganizer.addSauceCoordsBake(sauceCoordsList);
    }

    /**
     * This method stores the visibility of the cheese so that it can be recreated in the next tab.
     */
    public void storeCheese() {
        this.pizzaOrganizer.addCheeseBake(this.cheese.hasCheese());
    }
}