package indy;

import java.util.ArrayList;

/**
 * This is the AddTopping class that implements teh Undoable interface. This class allows the action of adding any
 * toppings to be undone and redone. It keeps a record of the added topping in the pane and in the topping arraylist.
 */
public class AddTopping implements Undoable {
    private final Toppings topping;
    ArrayList<Toppings> arraylist;
    public AddTopping(Toppings topping, ArrayList<Toppings> arraylist) {
        this.topping = topping;
        this.arraylist = arraylist;
    }

    /**
     * This method fulfills the contract of the Undoable interface. When adding toppings is undone, the topping is
     * removed from the pane and deleted from the topping arraylist.
     */
    @Override
    public void undo() {
        this.topping.delete();
        this.arraylist.remove(this.topping);
    }

    /**
     * This method fulfills the contract of the Undoable interface. When adding toppings is redone, the topping is
     * added back to the pane and into the topping arraylist.
     */
    @Override
    public void redo() {
        this.arraylist.add(this.topping);
        this.topping.addToPane();
    }
}
