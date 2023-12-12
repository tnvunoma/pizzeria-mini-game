package indy;

/**
 * This is the AddCheese class. Since cheese does not inherit the Topping interface, it has its own undo and redo class
 * for adding cheese. This class implements undoable which allows the action of adding cheese to be undoable and
 * redoable.
 */
public class AddCheese implements Undoable {
    private final Cheese cheese;
    public AddCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * This method is fulfilling the contract of the undoable interface. When adding cheese is undone, the toggle for
     * the visibility of cheese is switched off.
     */
    @Override
    public void undo() {
        this.cheese.showCheese(false);
    }

    /**
     * This method is fulfilling the contract of the undoable interface. When adding cheese is redone, the toggle
     * for visibility of cheese is switched on.
     */
    @Override
    public void redo() {
        this.cheese.showCheese(true);
    }
}
