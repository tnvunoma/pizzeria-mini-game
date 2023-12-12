package indy;

import java.util.ArrayList;

/**
 * This DeleteTopping class implements the Undoable interface, allowing for the action of deleting toppings to be
 * undoable and redoable.
 */
public class DeleteTopping implements Undoable {
    private final Toppings topping;
    ArrayList<Toppings> arraylist;
    public DeleteTopping(Toppings topping, ArrayList<Toppings> arraylist) {
        this.topping = topping;
        this.arraylist = arraylist;
    }

    /**
     * This method fulfills the contract of the Undoable interface. Undoing deleting toppings would add the topping
     * back to the pane and topping arraylist.
     */
    @Override
    public void undo() {
        this.arraylist.add(this.topping);
        this.topping.addToPane();
    }

    /**
     * This method fulfills the contract of the Undoable interface. Redoing deleting toppings would delete the topping
     * from the pane and the topping arraylist.
     */
    @Override
    public void redo() {
        this.topping.delete();
        this.arraylist.remove(this.topping);
    }
}
