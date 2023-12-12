package indy;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import java.util.ArrayList;

public class Slicing {
    private  Pane slicingPane;
    private PizzaOrganizer pizzaOrganizer;
    private ArrayList<PizzaSauce> pizzaSauce;
    private ArrayList<Toppings> topping;
    private ArrayList<Slice> slice;
    private Ellipse slicingBoard;
    private Pizza pizza;
    private Cheese cheese;

    int pizzaOnBoard = 0;

    public Slicing(Pane pane, PizzaOrganizer pizzaOrganizer) {
        this.slicingPane = pane;
        this.pizzaOrganizer = pizzaOrganizer;
        this.pizzaSauce = new ArrayList<>();
        this.topping = new ArrayList<>();
        this.slice = new ArrayList<>();
        this.pizza = new Pizza();
        this.slicingBoard = new Ellipse(480, 350, 210, 210);
        this.slicingBoard.setFill(Color.web("A6997B"));

        pane.setOnMousePressed(this::handleMousePressed);
        pane.getChildren().add(this.slicingBoard);
    }

    public void handleMousePressed(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            if (e.getX() > 140 && e.getX() < 390 && e.getY() > 635 && e.getY() < 675 && this.pizzaOnBoard < 1) {
                this.setPizza();
            }   else if (e.getX() > 565 && e.getX() < 820 && e.getY() > 635 && e.getY() < 675 && this.pizzaOnBoard > 0) {
                this.takePizza();
            }

            if (this.pizzaOnBoard > 0 && this.slicingBoard.contains(e.getX(), e.getY()) &&
                    (!this.pizza.contain(e.getX(), e.getY()))) {
                this.slice.add(new Slice(this.slicingPane));
                this.slice.get(this.slice.size() - 1).setStart(e.getX(), e.getY());
            }
        }
    }

    public void setPizza() {
        if (!this.pizzaOrganizer.slicingQueueEmpty()) {
            this.pizzaOnBoard++;
            this.recreatePizza();
            this.recreateSauce();
            this.cheese = new Cheese(this.slicingPane);
            this.cheese.setPosition(-120, -100);
            this.recreateCheese();
            this.recreateToppings();
            this.positionToppings();
        }
    }

    public void takePizza() {
        this.storeSlices();
        this.pizza.removeFromPane(this.slicingPane);
        this.cheese.showCheese(false);
        this.pizzaOrganizer.clearToppings(this.topping);
        this.pizzaOrganizer.clearSauces(this.pizzaSauce);
        this.pizzaOrganizer.clearSlices(this.slice);
        this.pizzaOnBoard--;
    }

    public void recreatePizza() {
        Color pizzaColor = this.pizzaOrganizer.dequeuePizzaSlice();
        this.pizzaOrganizer.addPizzaLog(pizzaColor);
        this.pizza.setColor(pizzaColor);
        this.pizza.setPosition(480, 350);
        this.pizza.addToPane(this.slicingPane);
    }

    public void recreateSauce() {
        ArrayList<Color> sauce = this.pizzaOrganizer.dequeueSauceSlice();
        ArrayList<Point2D> coords = this.pizzaOrganizer.dequeueSauceCoordsSlice();
        this.pizzaOrganizer.recreatePizzaSauce(this.slicingPane, sauce, coords, this.pizzaSauce,
                -120, -100);
        this.pizzaOrganizer.addSauceLog(sauce);
        this.pizzaOrganizer.addSauceCoordsLog(coords);
    }

    public void recreateToppings() {
        ArrayList<String> toppingsList = this.pizzaOrganizer.dequeueToppingsSlice();
        this.pizzaOrganizer.recreatePizzaToppings(toppingsList, this.topping, this.slicingPane);
        this.pizzaOrganizer.addToppingsLog(toppingsList);
    }

    public void positionToppings() {
        ArrayList<Point2D> toppingCoords = this.pizzaOrganizer.dequeueToppingCoordsSlice();
        this.pizzaOrganizer.positionToppings(toppingCoords, this.topping, -120, -100);
        this.pizzaOrganizer.addToppingCoordsLog(toppingCoords);
    }

    public void recreateCheese() {
        boolean cheese = this.pizzaOrganizer.dequeueCheeseSlice();
        this.cheese.showCheese(cheese);
        this.pizzaOrganizer.addCheeseLog(cheese);
    }

    public void storeSlices() {
        ArrayList<Point2D> slicesList = new ArrayList<>();
        for (Slice slices: this.slice) {
            Point2D coords = new Point2D(slices.getStartX() + 120, slices.getStartY() + 100);
            slicesList.add(coords);
        }
        this.pizzaOrganizer.addSlicesLog(slicesList);
    }
}
